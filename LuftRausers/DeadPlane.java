import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 * A class to represent a dead plane
 * @author Blake
 *
 */
public class DeadPlane implements Detail{

	private static final double GRAVITY = 0.37;
	private static final double DRAG = 0.3;
	
	private int type;
	private double xPos;
	private double yPos;
	private double velocity;
	private double direction;
	private double movementDirection;
	private boolean active;
	
	/**
	 * Creates a new dead plane
	 * @param xPos the x position
	 * @param yPos the y position
	 * @param direction the direction of movement
	 * @param velocity the velocity
	 */
	public DeadPlane(double xPos, double yPos, double direction, double velocity) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		this.velocity = velocity;
		movementDirection = direction;
		type = (int)(Math.random()*4);
		active = true;
	}
	
	/**
	 * Draws the dead plane to a given Graphics object
	 * @param g the given Graphics object
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Image image = ImageAtlas.getImage("DamagedPlane"+type);
		AffineTransform trans = AffineTransform.getRotateInstance(Math.toDegrees(direction), xPos+image.getWidth(null)/2*1.2, yPos+image.getHeight(null)/2*1.2);
		trans.translate(xPos+image.getWidth(null)/2*1.2, yPos+image.getHeight(null)/2*1.2);
		trans.scale(1.2, 1.2);
		((Graphics2D)g).drawImage(image, trans, null);
	}

	/**
	 * Simulates the next frame of the simulation
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle((int)xPos, (int)yPos, 50, 50);
		if(rect.getMaxX()>Camera.getDisplacement()[0]-1000 && rect.getMinX()<Camera.getDisplacement()[0]+Game.FRAME_WIDTH+1000 && rect.getMinY()>Camera.getDisplacement()[1]-1000 && rect.getMaxY()<Camera.getDisplacement()[1]+Game.FRAME_HEIGHT+1000){
			double gravityYVelocity = -GRAVITY;
			double movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
			double movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
			
			double newDirection = Math.toDegrees(Math.atan2((movementYVelocity+gravityYVelocity),(movementXVelocity)));
			velocity = Math.sqrt(GRAVITY*GRAVITY + velocity*velocity);
			movementDirection = newDirection;
			
			double dragYVelocity = Math.sin(Math.toRadians(movementDirection-180))*DRAG*velocity;
			double dragXVelocity = Math.cos(Math.toRadians(movementDirection-180))*DRAG*velocity;
			movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
			movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
			
			newDirection = Math.toDegrees(Math.atan2((movementYVelocity+dragYVelocity),(movementXVelocity+dragXVelocity)));
			velocity = Math.sqrt(DRAG*velocity*DRAG*velocity + velocity*velocity);
			movementDirection = newDirection;
			
			if(velocity>16) {
				velocity = 16;
			}
			
			xPos -= Math.cos(Math.toRadians(movementDirection))*velocity;
			yPos -= Math.sin(Math.toRadians(movementDirection))*velocity;
			
		}else {
			active = false;
		}
		
		
	}

	/**
	 * Returns whether the dead plane is active
	 * @return whether the dead plane is active
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

}
