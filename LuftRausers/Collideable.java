
import java.awt.geom.Area;

/**
 * An interface to represent an object that can be collided with
 * @author Blake
 *
 */
public interface Collideable extends GameObject {

	/**
	 * Returns the hitbox of the object
	 * @return the hitbox of the object
	 */
	public Area getCollision();
	
	/**
	 * Reacts to a collision with a given bullet
	 * @param bullet the given bullet
	 */
	public void notifyCollision(Bullet bullet);
	
	/**
	 * Returns the position of the object
	 * @return the position of the object
	 */
	public double[] getPosition();
}
