import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class ContactBullet implements Bullet {

	private double x;
	private double y;
	private double direction;
	private boolean active;
	public ContactBullet() {
	}
	
	public void draw(Graphics g) {
	}

	public void step() {
	}

	public Area getCollision() {
		Area a = new Area(new Ellipse2D.Double(x,y,20,20));
		a.transform(AffineTransform.getRotateInstance(Math.toRadians(direction), x, y));
		//a.transform(AffineTransform.getScaleInstance(1.2, 1.2));
		return a;
	}

	public void notifyCollision(Bullet bullet) {
		// TODO Auto-generated method stub
		active = false;
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}
	
	public double[] getPosition(){
		double[] position = {x, y};
		return position;
	}

	public BulletGroup getBulletGroup() {
		// TODO Auto-generated method stub
		return Bullet.BulletGroup.CONTACT;
	}

}
