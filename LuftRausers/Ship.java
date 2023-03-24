import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * Represents the battleship
 * @author Blake
 *
 */
public class Ship implements Enemy {

	private static final double VELOCITY = 2;
	private static final int MAX_HEALTH = 2000;
	private static final int POINT_VALUE = 400;
	
	private boolean alive;
	
	private double xPos;
	private double yPos;
	private Player player;
	private Turret[] turrets; // all 4, from left to right
	
	private int health;
	
	/**
	 * Creates a new battleship
	 * @param xPos the x position
	 * @param yPos the y position
	 * @param player the player
	 */
	public Ship(double xPos, double yPos, Player player) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.player = player;
		alive = true;
		health = MAX_HEALTH;
		turrets = new Turret[4];
		turrets[0] = new Turret(118 * 2 + xPos, 141 * 2 + yPos, player, Turret.LEFT);
		turrets[1] = new Turret(169 * 2 + xPos, 132 * 2 + yPos, player, Turret.LEFT);
		turrets[2] = new Turret(480 * 2 + xPos, 122 * 2 + yPos, player, Turret.RIGHT);
		turrets[3] = new Turret(533 * 2 + xPos, 131 * 2 + yPos, player, Turret.RIGHT);
		
	}
	
	/**
	 * Returns the hitbox of the battleship
	 */
	public Area getCollision() {
		Area a = new Area(new Rectangle2D.Double(xPos,yPos+300,1350,75));
		a.add(new Area(new Rectangle2D.Double(xPos+415,yPos+250,455,50)));
		return a;
	}
	
	/**
	 * Reacts to a collision with a given bullet
	 * @param bullet the given bullet
	 */
	public void notifyCollision(Bullet bullet) {
		// TODO Auto-generated method stub
		if(bullet.getBulletGroup()==Bullet.BulletGroup.PLAYER) {
			health -= 50;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(bullet.getBulletGroup()==Bullet.BulletGroup.CONTACT) {
			health -= 20;
		}else {
			health -= 10;
		}
		
		if(health<1) {
			alive = false;
			if(bullet.getBulletGroup() == Bullet.BulletGroup.PLAYER || bullet.getBulletGroup() == Bullet.BulletGroup.CONTACT) {
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Camera.shakeScreen(25);
				ScoreManager.addKill(this);
			}
			//SoundAtlas.play("Explosion");
			Sky.addDetail(new DeadShip(xPos, yPos, 70));
			
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,0));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,2));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,5));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,0));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,2));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,5));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,0));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,2));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,5));
			
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,25));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,28));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,30));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,25));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,28));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,30));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,25));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,28));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,30));
			
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,50));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,51));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,54));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,50));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,51));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,54));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,50));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,51));
			Sky.addDetail(new DelayedExplosion(xPos+Math.random()*1350, yPos+Math.random()*150+25, 2.5 ,54));
		}
	}
	
	/**
	 * Returns the position of the battleship
	 * @return the position of the battleship
	 */
	public double[] getPosition() {
		double[] position = {xPos, yPos};
		return position;
	}
	
	/**
	 * Draws the battleship to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(alive && (Math.abs(xPos-player.getPosition()[0]+675)<Game.FRAME_WIDTH+675 || Math.abs(yPos-player.getPosition()[1])<Game.FRAME_HEIGHT)) {
			AffineTransform trans = AffineTransform.getTranslateInstance(xPos, yPos);
			trans.scale(2, 2);
			((Graphics2D)g).drawImage(ImageAtlas.getImage("Ship"), trans, null);
		}
		
		for (int i = 0; i < turrets.length; i++) {
			turrets[i].draw(g);
		}
		//g.setColor(Color.yellow);
		//((Graphics2D)g).fill(getCollision());
	}

	/**
	 * Simulates the next frame of the simulation
	 */
	public void step() {
		// TODO Auto-generated method stub
		
		if(xPos<player.getPosition()[0]-600) {
			xPos += VELOCITY;
			for (int i = 0; i < turrets.length; i++) {
				turrets[i].shift(VELOCITY, 0);
			}
		}else if(xPos>player.getPosition()[0]-600) {
			xPos -= VELOCITY;
			for (int i = 0; i < turrets.length; i++) {
				turrets[i].shift(-VELOCITY, 0);
			}
		}
		if(Math.abs(xPos+675-player.getPosition()[0])<Game.FRAME_WIDTH*1.5) {
			for (int i = 0; i < turrets.length; i++) {
				turrets[i].step();
			}
		}
		
	}
	
	/**
	 * Returns whether the ship is alive
	 * @return whether the ship is alive
	 */
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return alive;
	}
	
	/**
	 * Returns the score value of the battleship
	 * @return the score value of the battleship
	 */
	@Override
	public int getPointValue() {
		// TODO Auto-generated method stub
		return POINT_VALUE;
	}
}
