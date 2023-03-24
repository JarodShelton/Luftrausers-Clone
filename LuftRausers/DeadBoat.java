import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 * A class to represent a dead boat
 * @author Blake
 *
 */
public class DeadBoat implements Detail {

	private static final double SINK_VELOCITY = 1;
	
	private double xPos;
	private double yPos;
	private boolean active;
	private int waitTimer;
	private boolean reflected;
	
	/**
	 * Creates a new dead boat
	 * @param xPos the x position
	 * @param yPos the y position
	 * @param waitTime the time t wait before sinking
	 * @param reflected whether or not the ship sprite should be reflected
	 */
	public DeadBoat(double xPos, double yPos, int waitTime, boolean reflected) {
		this.xPos = xPos;
		this.yPos = yPos;
		active = true;
		waitTimer = waitTime;
		this.reflected = reflected;
	}
	
	/**
	 * Draws the dead boat to a given Graphics object
	 * @param g the given Graphics object
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(active) {
			AffineTransform trans = AffineTransform.getTranslateInstance(xPos, yPos);
			trans.scale(1.3, 1.3);
			if(reflected) {
				trans.scale(-1,  1);
				trans.translate(-185, 0);
			}
			((Graphics2D)g).drawImage(ImageAtlas.getImage("MotorBoat"), trans, null);
		}
	}

	/**
	 * Simulates the next frame of the simulation
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle((int)xPos, (int)yPos, 1350, 200);
		if(rect.getMaxX()>Camera.getDisplacement()[0]-2000 && rect.getMinX()<Camera.getDisplacement()[0]+Game.FRAME_WIDTH+2000 && rect.getMinY()>Camera.getDisplacement()[1]-1000 && rect.getMaxY()<Camera.getDisplacement()[1]+Game.FRAME_HEIGHT+1000){
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
	 * Returns whether the dead boat is active
	 * @return whether the dead boat is active
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

}
