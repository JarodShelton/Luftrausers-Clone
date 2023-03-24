import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 * A class to represent a dead ship
 * @author Blake
 *
 */
public class DeadShip implements Detail {

	private static final double SINK_VELOCITY = 1;
	
	private double xPos;
	private double yPos;
	private boolean active;
	private int waitTimer;
	
	/**
	 * Creates a new dead ship
	 * @param xPos the x position
	 * @param yPos the y position
	 * @param waitTime the length of time to wait before sinking
	 */
	public DeadShip(double xPos, double yPos, int waitTime) {
		this.xPos = xPos;
		this.yPos = yPos;
		active = true;
		waitTimer = waitTime;
	}
	
	/**
	 * Draws the dead ship to a given Graphics object
	 * @param g the given Graphics object
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(active) {
			AffineTransform trans = AffineTransform.getTranslateInstance(xPos, yPos);
			trans.scale(2, 2);
			((Graphics2D)g).drawImage(ImageAtlas.getImage("Ship"), trans, null);
		}
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle((int)xPos, (int)yPos, 1350, 200);
		if(rect.getMaxX()>Camera.getDisplacement()[0]-3000 && rect.getMinX()<Camera.getDisplacement()[0]+Game.FRAME_WIDTH+3000 && rect.getMinY()>Camera.getDisplacement()[1]-2000 && rect.getMaxY()<Camera.getDisplacement()[1]+Game.FRAME_HEIGHT+2000){
			if(waitTimer<1) {
				yPos += SINK_VELOCITY;
			}else {
				waitTimer--;
			}
			
		}else {
			active = false;
		}
	}

	/**
	 * Returns whether the dead ship is active
	 * @return whether the dead ship is active
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

}
