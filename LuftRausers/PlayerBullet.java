import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * A class which represents a bullet fired by the player
 * @author Blake
 *
 */
public class PlayerBullet implements Bullet {
	
	private double x;
	private double y;
	private double direction;
	private int frameCount = 1;
	private boolean active;
	
	/**
	 * Creates a new bullet
	 * @param x the x position
	 * @param y the y position
	 * @param direction the direction of travel in degrees
	 */
	public PlayerBullet(double x, double y, double direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		active = true;
	}
	
	/**
	 * Draws the player's bullet to the given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(active) {
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(direction), x, y);
			//trans.rotate(Math.toRadians(direction));
			trans.translate(x, y);
			trans.scale(4, 4);
			g2.drawImage(ImageAtlas.getImage("PlayerBullet" + frameCount/3), trans, null);
			/*g2.setColor(Color.red);
			g2.fill(getCollision());*/
			if (frameCount < 9)
				frameCount++;
		}
		
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	public void step() {
		// TODO Auto-generated method stub
		Rectangle rect = getCollision().getBounds();
		if(rect.getMaxX()>Camera.getDisplacement()[0]-1000 && rect.getMinX()<Camera.getDisplacement()[0]+Game.FRAME_WIDTH+1000 && rect.getMinY()>Camera.getDisplacement()[1]-1000 && rect.getMaxY()<Camera.getDisplacement()[1]+Game.FRAME_HEIGHT+1000){
			x -= Math.cos(Math.toRadians(direction))*32;
			y -= Math.sin(Math.toRadians(direction))*32;
		}else {
			active = false;
		}
		
	}
	
	/**
	 * Returns the hitbox of the bullet
	 * @return the hitbox of the bullet
	 */
	public Area getCollision() {
		Area a = new Area(new Ellipse2D.Double(x,y,35,35));
		a.transform(AffineTransform.getRotateInstance(Math.toRadians(direction), x, y));
		//a.transform(AffineTransform.getScaleInstance(2, 2));
		return a;
	}
	
	/**
	 * Responds to a collision with another object by deactivating the bullet
	 */
	public void notifyCollision(Bullet bullet) {
		// TODO Auto-generated method stub
		active = false;
	}
	
	/**
	 * Returns whether the bullet is active
	 * @return whether the bullet is active
	 */
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}
	
	/**
	 * Returns the position of the bullet
	 * @return the position of the bullet
	 */
	public double[] getPosition(){
		double[] position = {x, y};
		return position;
	}
	
	/**
	 * Returns the type of bullet
	 * @return the type of bullet
	 */
	public BulletGroup getBulletGroup() {
		// TODO Auto-generated method stub
		return Bullet.BulletGroup.PLAYER;
	}
}
