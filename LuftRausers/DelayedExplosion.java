import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 * A class to represent a delayed explosion
 * @author Blake
 *
 */
public class DelayedExplosion implements Detail {

	private static final int EXPLOSION_LENGTH = 27;
	
	private double xPos;
	private double yPos;
	private double size;
	private int timer;
	private boolean active;
	private int waitTimer;
	
	/**
	 * Creates a new DelayedExplosion
	 * @param x the x position
	 * @param y the y position
	 * @param size the size of the explosion
	 * @param waitTime the length of the delay
	 */
	public DelayedExplosion(double x, double y, double size, int waitTime) {
		xPos = x;
		yPos = y;
		this.size = size;
		active = true;
		timer = 0;
		waitTimer = waitTime;
	}
	
	/**
	 * Draws the DelayedExplosion to a given Graphics object
	 * @param g the given Graphics object
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(active && waitTimer<1) {
			Image image = ImageAtlas.getImage("Explosion"+timer/(EXPLOSION_LENGTH/9));
			AffineTransform trans = AffineTransform.getTranslateInstance(xPos+image.getWidth(null)/2*size, yPos+image.getHeight(null)/2*size);
			trans.scale(size, size);
			((Graphics2D)g).drawImage(image, trans, null);
		}
		
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		if(waitTimer<1) {
			if(timer<EXPLOSION_LENGTH-1)
				timer++;
			else
				active = false;
		}else {
			waitTimer--;
		}
		
	}

	/**
	 * Returns whether the explosion is active
	 * @return whether the explosion is active
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

}
