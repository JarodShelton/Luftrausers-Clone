/**
 * An interface which represents an individual enemy
 * @author Blake
 *
 */
public interface Enemy extends Collideable{
	
	/**
	 * Returns whether the enemy is alive
	 * @return whether the enemy is alive
	 */
	public boolean isAlive();
	
	/**
	 * Returns the point value of the enemy
	 * @return the point value of the enemy
	 */
	public int getPointValue();
}
