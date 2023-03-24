
import java.awt.Graphics;

/**
 * An interface to represent an object in the game
 * @author Blake
 *
 */
public interface GameObject {

	/**
	 * Draws the object to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g);
	
	/**
	 * Simulates the next step of the simulation
	 */
	public void step();
	
}
