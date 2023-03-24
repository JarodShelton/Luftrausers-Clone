import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * A class to represent the sky and other details
 * @author Blake
 *
 */
public class Sky {
	
	static final int SKY_LENGTH = 5000;
	static final int SKY_HEIGHT = 2500;
	
	private static Image sky;
	private static LinkedList<Detail> details;
	
	/**
	 * Creates a new Sky
	 */
	private Sky() {
		details = new LinkedList<Detail>();
		sky = ImageAtlas.getImage("Sky");
	}
	
	/**
	 * Adds a new detail to the list of details
	 * @param d the new detail to add
	 */
	public static void addDetail(Detail d){
		if(sky == null)
			new Sky();
		details.add(d);
	}
	
	/**
	 * Draws the sky and other details to a given Graphics object
	 * @param g the given Graphics object
	 */
	public static void draw(Graphics g) {
		if(sky == null)
			new Sky();
		int constant = Camera.getDisplacement()[0]<0? SKY_LENGTH: 0;
		int otherConstant = Camera.getDisplacement()[0]<-SKY_LENGTH ? SKY_LENGTH: 0;
		if(Camera.getDisplacement()[0]%SKY_LENGTH + constant > SKY_LENGTH-1800) {
			// left Side
			g.drawImage(sky, (int)Camera.getDisplacement()[0],(int)Camera.getDisplacement()[1],(int)Camera.getDisplacement()[0]+SKY_LENGTH-(int)Camera.getDisplacement()[0]%SKY_LENGTH-constant,(int)Camera.getDisplacement()[1]+1000,
					(int)Camera.getDisplacement()[0]%SKY_LENGTH+constant, (int)Camera.getDisplacement()[1],SKY_LENGTH,(int)Camera.getDisplacement()[1]+1000, null);	
			// right Side
			g.drawImage(sky, (int)Camera.getDisplacement()[0]+SKY_LENGTH-(int)Camera.getDisplacement()[0]%SKY_LENGTH-constant,(int)Camera.getDisplacement()[1],(int)Camera.getDisplacement()[0]+1800,(int)Camera.getDisplacement()[1]+1000,
					0, (int)Camera.getDisplacement()[1],((int)Camera.getDisplacement()[0]+1800)%SKY_LENGTH+otherConstant,(int)Camera.getDisplacement()[1]+1000, null);
		}else {
			g.drawImage(sky, (int)Camera.getDisplacement()[0],(int)Camera.getDisplacement()[1],(int)Camera.getDisplacement()[0]+1800,(int)Camera.getDisplacement()[1]+1000,
					(int)Camera.getDisplacement()[0]%SKY_LENGTH+constant, (int)Camera.getDisplacement()[1],((int)Camera.getDisplacement()[0]+1800)%SKY_LENGTH+constant,(int)Camera.getDisplacement()[1]+1000, null);	
		}
		
		ListIterator<Detail> iter = details.listIterator();
		while(iter.hasNext()) {
			Detail d = iter.next();
			if(d.isActive()) {
				d.step();
				d.draw(g);
			}else
				iter.remove();
			
		}
	}
	
}
