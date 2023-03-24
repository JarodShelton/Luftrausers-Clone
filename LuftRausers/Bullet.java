/**
 * An interface to represent a bullet
 * @author Blake
 *
 */
public interface Bullet extends Collideable{

	/**
	 * Returns whether the bullet is active
	 * @return whether the bullet is active
	 */
	public boolean isActive();
	
	/**
	 * Returns the type of bullet
	 * @return the type of bullet
	 */
	public BulletGroup getBulletGroup();
	
	/**
	 * An enum of bullet types
	 * @author Blake
	 *
	 */
	enum BulletGroup {
		PLAYER, SHIP, PLANE, CONTACT;
	}
}