import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A class that represents a list of enemies
 * @author Blake
 *
 */
public class Enemies{

	private static LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	
	/**
	 * resets the Enemies object by clearing its list of Enemies
	 */
	public static void reset() {
		if(enemyList!=null)
			enemyList.clear();
	}
	
	/**
	 * Draws the enemies to the given graphics object
	 * @param g the given graphics object
	 */
	public static void draw(Graphics g) {
		for(Enemy e : enemyList) {
			e.draw(g);
		}
	}

	/**
	 * Adds a new enemy to the list
	 * @param enemy the new enemy
	 */
	public static void addEnemy(Enemy enemy) {
		if(enemyList.size()<64) {
			enemyList.add(enemy);
		}
		
	}
	
	/**
	 * Initiates the next frame of simulation for each of its enemies
	 */
	public static void step() {
		Area a = null;
		ListIterator<Enemy> iter = enemyList.listIterator();
		while(iter.hasNext()) {
			Enemy e = iter.next();
			Rectangle rect = e.getCollision().getBounds();
			
			if(e.isAlive()) {
				if(rect.getMaxX()>Camera.getDisplacement()[0] && rect.getMinX()<Camera.getDisplacement()[0]+Game.FRAME_WIDTH && rect.getMinY()>Camera.getDisplacement()[1] && rect.getMaxY()<Camera.getDisplacement()[1]+Game.FRAME_HEIGHT){
					for(Bullet h : Hazards.getHazards()) {
						try {
							if(rect.intersects(h.getCollision().getBounds())) {
								a = h.getCollision();
								a.intersect(e.getCollision());
								if(!a.isEmpty()) {
									h.notifyCollision(null); //does nothing with collision
									e.notifyCollision(h);
								}
							}
						}catch(NullPointerException ex) {
							
						}
					}	
				}
				
				e.step();
			}else {
				iter.remove();
			}
			
		}
	}
	
	/**
	 * Returns the list of enemies
	 * @return the list of enemies
	 */
	public static LinkedList<Enemy> getEnemies() {
		return enemyList;
	}

}
