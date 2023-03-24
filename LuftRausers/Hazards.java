import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A class to represent a list of bullets
 * @author Blake
 *
 */
public class Hazards{
	
	private static LinkedList<Bullet> hazardList = new LinkedList<Bullet>();
	
	/**
	 * Adds a new bullet
	 * @param o the new bullet
	 */
	public static void addHazard(Bullet o) {
		hazardList.add(o);
	}
	
	/**
	 * resets the Hazards object by clearing its list of Bullets
	 */
	public static void reset() {
		if(hazardList!=null)
			hazardList.clear();
	}
	
	/**
	 * Draws the bullets to the given Graphics object
	 * @param the given Graphics object
	 */
	public static void draw(Graphics g) {
		for(Bullet b : hazardList) {
			b.draw(g);;
		}
	}

	/**
	 * Returns the list of bullets
	 * @return the list of bullets
	 */
	public static LinkedList<Bullet> getHazards(){
		return hazardList;
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	public static void step() {
		// TODO Auto-generated method stub
		ListIterator<Bullet> iter = hazardList.listIterator();
		while(iter.hasNext()) {
			Bullet b = iter.next();
			if(b.isActive()) 
				b.step();
			else
				iter.remove();
		}
		while(hazardList.size() > 96) {
			hazardList.remove(0);
		}
	}
	
}
