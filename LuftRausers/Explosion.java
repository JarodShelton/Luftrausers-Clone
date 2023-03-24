import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 * A class to represent an explosion
 * @author Blake
 *
 */
public class Explosion implements Detail {

	private static final int EXPLOSION_LENGTH = 27;
	
	private double xPos;
	private double yPos;
	private double size;
	private int timer;
	private boolean active;
	
	/**
	 * Creates a new explosion
	 * @param x the x position
	 * @param y the y position
	 * @param size the size of the explosion
	 */
	public Explosion(double x, double y, double size) {
		xPos = x;
		yPos = y;
		this.size = size;
		active = true;
		timer = 0;
	}
	
	/**
	 * Draws the explosion to a given Graphics object
	 * @param g the given Graphics object
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(active) {
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
		if(timer<EXPLOSION_LENGTH-1)
			timer++;
		else
			active = false;
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
