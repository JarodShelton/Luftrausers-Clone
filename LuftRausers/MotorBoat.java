import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * A class which represents a motorboat
 */
public class MotorBoat implements Enemy {

	private static final double VELOCITY = 5;
	private static final double SLOW_VELOCITY = 2;
	private static final int MAX_HEALTH = 250;
	private static final int POINT_VALUE = 75;
	
	private boolean alive;
	
	private double xPos;
	private double yPos;
	private Player player;
	private MotorBoatTurret turret; // all 4, from left to right
	
	private int health;
	private boolean reflected; //base sprite faces left
	
	/**
	 * Creates a motor boat at a given location
	 * @param xPos the given x coordinate
	 * @param yPos the given y coordinate
	 * @param player the player
	 */
	public MotorBoat(double xPos, double yPos, Player player) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.player = player;
		alive = true;
		health = MAX_HEALTH;
		reflected = xPos>player.getPosition()[0]-60;
		if(reflected) {
			turret = new MotorBoatTurret(xPos+190,yPos+60, player);
		}else {
			turret = new MotorBoatTurret(xPos+40,yPos+60, player);
		}
		
		
	}
	
	/**
	 * Returns the hitbox of the motorboat
	 * @return the hitbox of the motorboat
	 */
	public Area getCollision() {
		Area a = new Area(new Rectangle2D.Double(xPos+5,yPos+70,175,75));
		if(reflected) {
			a.transform(AffineTransform.getTranslateInstance(55, 0));
		}
		return a;
	}
	
	/**
	 *  Reacts to a collision with either a bullet or another enemy
	 *  @param bullet the bullet
	 */
	public void notifyCollision(Bullet bullet) {
		// TODO Auto-generated method stub
		if(bullet.getBulletGroup()==Bullet.BulletGroup.PLAYER)
			health -= 50;
		else if(bullet.getBulletGroup()==Bullet.BulletGroup.CONTACT) {
			health -= 20;
		}else {
			health -= 10;
		}
		
		if(health<1) {
			alive = false;
			if(bullet.getBulletGroup() == Bullet.BulletGroup.PLAYER || bullet.getBulletGroup() == Bullet.BulletGroup.CONTACT) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			//SoundAtlas.play("Explosion");
			Camera.shakeScreen(20);
			ScoreManager.addKill(this);
			Sky.addDetail(new DeadBoat(xPos, yPos, 50, reflected));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*125-40, yPos+Math.random()*15-20, 1.5, 0));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*125-40, yPos+Math.random()*15-20, 1.5, 2));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*125-40, yPos+Math.random()*15-20, 1.5, 5));
			
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*125-40, yPos+Math.random()*15-20, 1.5, 25));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*125-40, yPos+Math.random()*15-20, 1.5, 30));
		}
	}
	
	/**
	 * Returns the position of the motorboat
	 * @return the position of the motorboat
	 */
	public double[] getPosition() {
		double[] position = {xPos, yPos};
		return position;
	}
	
	/**
	 * Draw the motorboat to the given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(alive && (Math.abs(xPos-player.getPosition()[0]+675)<Game.FRAME_WIDTH+675 || Math.abs(yPos-player.getPosition()[1])<Game.FRAME_HEIGHT)) {
			AffineTransform trans = AffineTransform.getTranslateInstance(xPos, yPos);
			trans.scale(1.3, 1.3);
			if(reflected) {
				trans.scale(-1,  1);
				trans.translate(-185, 0);
			}
			((Graphics2D)g).drawImage(ImageAtlas.getImage("MotorBoat"), trans, null);
		}
		turret.draw(g);
		/*g.setColor(Color.yellow);
		((Graphics2D)g).fill(getCollision());*/
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	public void step() {
		// TODO Auto-generated method stub
		
		if(xPos<player.getPosition()[0]-60) {
			if(!reflected) {
				xPos -= SLOW_VELOCITY;
				turret.shift(-SLOW_VELOCITY, 0);
			}else {
				xPos += VELOCITY;
				turret.shift(VELOCITY, 0);
			}
				
			
		}else if(xPos>player.getPosition()[0]-60) {
			if(reflected) {
				xPos += SLOW_VELOCITY;
				turret.shift(SLOW_VELOCITY, 0);
			}else {
				xPos -= VELOCITY;
				turret.shift(-VELOCITY, 0);
			}
			
		}
		
		if((Math.abs(xPos-player.getPosition()[0]-92)>Game.FRAME_WIDTH-92 || Math.abs(yPos-player.getPosition()[1])>Game.FRAME_HEIGHT) && reflected != xPos<player.getPosition()[0]-60) { // if off screen face the player
			reflected = !reflected; //always correct since checked in if statement
			if(reflected)
				turret.shift(150, 0);
			else
				turret.shift(-150, 0);
		}
		
		if(Math.abs(xPos+675-player.getPosition()[0])<Game.FRAME_WIDTH*1.5) 
			turret.step();
		
	}
	
	/**
	 * Returns whether the motorboat is still alive
	 * @return whether the motorboat is still alive
	 */
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	
	/**
	 * Returns the point value of the motorboat
	 * @return the point value of the motorboat
	 */
	@Override
	public int getPointValue() {
		// TODO Auto-generated method stub
		return POINT_VALUE;
	}

}
