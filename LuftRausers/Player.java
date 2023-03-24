import java.awt.geom.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 * A class to represent the player
 * @author Blake
 *
 */
public class Player implements Collideable, KeyListener {
	
	private boolean[] keys;
	
	private final static int UP = 0;
	private final static int LEFT = 1;
	private final static int RIGHT = 2;
	private final static int SHOOT = 3;
	
	private final static int SHOOT_DELAY = 6;
	private final static double PLAYER_ACCELERATION = 1.9;
	private final static double GRAVITY = 0.37;
	private final static double RECOIL = 0.01;
	private final static double MAX_VELOCITY=16.5;
	private static final double DRAG = .9;
	private static final double FAST_TURN = 5;
	private static final double SLOW_TURN = 1.5;
	private static final double SHOOT_TURN = 2.25;
	private static final double MAX_HEALTH = 750;
	private static final double REGEN_RATE = 7;
	private static final double BLOOM = 0;
	
	private double xPos;
	private double yPos;
	private double acceleration;
	private double velocity;
	private double playerDirection;
	private double movementDirection;
	private double health;
	
	private int shootTimer;
	private int boostTimer;
	private int CameraBoostConst;
	private boolean alive;
	
	/**
	 * Creates a new player
	 * @param x the x position
	 * @param y the y position
	 */
	public Player(double x, double y){
		keys = new boolean[4];
		xPos = x;
		yPos = y;
		acceleration = 0;
		velocity = 0;
		playerDirection = 91;
		movementDirection = 91;
		shootTimer = 0;
		boostTimer = 0;
		CameraBoostConst = 0;
		health = MAX_HEALTH;
		alive = true;
	}
	
	/**
	 * Resets the player when it dies
	 * @param x the x position
	 * @param y the y position
	 */
	public void reset(double x, double y) {
		keys = new boolean[4];
		xPos = x;
		yPos = y;
		acceleration = 0;
		velocity = 0;
		playerDirection = 91;
		movementDirection = 91;
		shootTimer = 0;
		boostTimer = 0;
		CameraBoostConst = 0;
		health = MAX_HEALTH;
		alive = true;
	}
	
	/**
	 * Returns the position of the player
	 * @return the position of the player
	 */
	public double[] getPosition(){
		double[] position = {xPos, yPos};
		return position;
	}
	
	/**
	 * Returns the direction of movement of the player in degrees
	 * @return the direction of movement of the player in degrees
	 */
	public double getMovementDirection(){
		return movementDirection;
	}
	
	/**
	 * Returns the direction the player is facing in degrees
	 * @return the direction the player is facing in degrees
	 */
	public double getPlayerDirection(){
		return playerDirection;
	}
	
	/**
	 * Returns whether the player is alive
	 * @return whether the player is alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Returns whether the player is currently boosting
	 * @return whether the player is currently boosting
	 */
	public boolean isBoosting() {
		return keys[UP];
	}
	
	/**
	 * Draws the player to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		double diameter = (Game.FRAME_WIDTH+1000)/MAX_HEALTH*health; //Math.sqrt(Game.FRAME_HEIGHT*Game.FRAME_HEIGHT + Game.FRAME_WIDTH*Game.FRAME_WIDTH);
		Area a = new Area(new Rectangle2D.Double(Camera.getDisplacement()[0], Camera.getDisplacement()[1], Game.FRAME_WIDTH, Game.FRAME_HEIGHT));
		a.subtract(new Area(new Ellipse2D.Double(xPos-25*Math.cos(Math.toRadians(playerDirection))+10*Math.sin((Math.toRadians(playerDirection)))-diameter/2, yPos-25*Math.sin(Math.toRadians(playerDirection))-10*Math.cos(Math.toRadians(playerDirection))-diameter/2, diameter, diameter)));
		g2.setColor(new Color(64, 64, 64));
		g2.fill(a);
		
		AffineTransform trans = null;
		double spriteDirection = playerDirection>0? playerDirection : (360+playerDirection);
		if(keys[UP]){
			double xOffset = 0;
			if((spriteDirection>=75 && spriteDirection<=105) || (spriteDirection>=255 && spriteDirection<=285))
				xOffset = 23;
			else if((spriteDirection>=45 && spriteDirection<=135) || (spriteDirection>=225 && spriteDirection<=315))
				xOffset = 19;
			else
				xOffset = 13;
			trans = AffineTransform.getRotateInstance(Math.toRadians(playerDirection+90),xPos+23*Math.cos(Math.toRadians(playerDirection))+xOffset*Math.sin(Math.toRadians(playerDirection)),yPos+23*Math.sin(Math.toRadians(playerDirection))-xOffset*Math.cos(Math.toRadians(playerDirection)));
			trans.translate(xPos+23*Math.cos(Math.toRadians(playerDirection))+xOffset*Math.sin(Math.toRadians(playerDirection)),yPos+23*Math.sin(Math.toRadians(playerDirection))-xOffset*Math.cos(Math.toRadians(playerDirection)));
			//trans.scale(0.8, 0.8);
			g2.drawImage(ImageAtlas.getImage("Boost"+boostTimer/2), trans, null);
			boostTimer++;
			boostTimer%=20;
		}
		
		trans = AffineTransform.getRotateInstance(Math.toRadians(playerDirection+180),xPos,yPos);
		trans.translate(xPos,yPos);
		trans.scale(1.2,  1.2);
		
		//g2.drawImage(ImageAtlas.getImage("PlayerSprite1"), trans, null);
		if(spriteDirection>=75 && spriteDirection<=105) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite1"), trans, null);
		}else if(spriteDirection>=105 && spriteDirection<=135) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite2"), trans, null);
		}else if(spriteDirection>=135 && spriteDirection<=165) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite3"), trans, null);
		}else if(spriteDirection>=165 && spriteDirection<=195) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite4"), trans, null);
		}else if(spriteDirection>=195 &&spriteDirection<=225) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite3"), trans, null);
		}else if(spriteDirection>=225 && spriteDirection<=255) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite2"), trans, null);
		}else if(spriteDirection>=255 && spriteDirection<=285) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite5"), trans, null);
		}else if(spriteDirection>=285 && spriteDirection<=315) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite6"), trans, null);
		}else if(spriteDirection>=315 && spriteDirection<=345) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite7"), trans, null);
		}else if((spriteDirection>=345 && spriteDirection<=360) || (spriteDirection>=0 && spriteDirection<=15)) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite8"), trans, null);
		}else if(spriteDirection>=15 && spriteDirection<=45) {
			g2.drawImage(ImageAtlas.getImage("PlayerSprite7"), trans, null);
		}else{
			g2.drawImage(ImageAtlas.getImage("PlayerSprite6"), trans, null);
		}
		//g.setColor(Color.yellow);
		//g.fillOval((int)xPos-5,(int)yPos-5, 10, 10);
		
		//g2.draw(getCollision());
		
		
		/*g2.setColor(Color.yellow);
		g2.fill(getCollision());*/
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	public void step() {
		for(Bullet h : Hazards.getHazards()) {
			if(Math.abs(xPos-h.getPosition()[0])<50 || Math.abs(yPos-h.getPosition()[1])<50) {
				Area a = h.getCollision();
				a.intersect(getCollision());
				if(!a.isEmpty()) {
					h.notifyCollision(null); //doesn't use the bullet 
					notifyCollision(h);
				}
			}
		}
		
		for(Enemy e : Enemies.getEnemies()) {
			if(getCollision().getBounds().intersects(e.getCollision().getBounds())) {
				Area a = e.getCollision();
				a.intersect(getCollision());
				if(!a.isEmpty()) {
					try {
						Thread.sleep(2);
						Camera.shakeScreen(5);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					e.notifyCollision(new ContactBullet()); //doesn't use the bullet 
					notifyCollision(new ContactBullet());
				}
			}
		}
		
		double direction = playerDirection;
		boolean shot = false;
		if(shootTimer == SHOOT_DELAY+1)
			shootTimer = 0;
		else
			shootTimer++;
		
		if(keys[SHOOT]){
			if(shootTimer == 0) {
				shot = true;
				Hazards.addHazard(new PlayerBullet(xPos-120*Math.cos(Math.toRadians(playerDirection))+32*Math.sin((Math.toRadians(playerDirection))), yPos-120*Math.sin(Math.toRadians(playerDirection))-32*Math.cos(Math.toRadians(playerDirection)), playerDirection+Math.random()*BLOOM));
				//  SoundAtlas.play("Shot");
				shootTimer = 1;
				Camera.shakeScreen(3, playerDirection-180);
			}
		}
		
		if(keys[UP]) {
			if(CameraBoostConst<20)
				CameraBoostConst++;
			if(yPos<100){
				acceleration = 0;
			}else if(yPos<725) {
				acceleration = PLAYER_ACCELERATION-PLAYER_ACCELERATION/25*Math.sqrt(725-yPos);
			}else {
				acceleration = PLAYER_ACCELERATION;
			}
			
		}else{
			if(CameraBoostConst>1)
				CameraBoostConst--;
			acceleration = 0;
		}
		if(keys[LEFT]) {
			playerDirection -= keys[UP]?SLOW_TURN: keys[SHOOT]? SHOOT_TURN : FAST_TURN;
			playerDirection%=360;
		}else if(keys[RIGHT]) {
			playerDirection += keys[UP]?SLOW_TURN: keys[SHOOT]? SHOOT_TURN : FAST_TURN;
			playerDirection%=360;
		}
		
		
		double playerYVelocity = Math.sin(Math.toRadians(direction))*acceleration;
		double playerXVelocity = Math.cos(Math.toRadians(direction))*acceleration;
		double movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
		double movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
		double newDirection = 0;
		
		newDirection = Math.toDegrees(Math.atan2((movementYVelocity+playerYVelocity),(movementXVelocity+playerXVelocity)));
		velocity = Math.sqrt(acceleration*acceleration + velocity*velocity);
		movementDirection = newDirection;
		
		double gravityYVelocity = Math.sin(Math.toRadians(-90))*GRAVITY;
		double gravityXVelocity = 0;
		movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
		movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
		
		newDirection = Math.toDegrees(Math.atan2((movementYVelocity+gravityYVelocity),(movementXVelocity+gravityXVelocity)));
		velocity = Math.sqrt(GRAVITY*GRAVITY + velocity*velocity);
		movementDirection = newDirection;
		
		double dragYVelocity = Math.sin(Math.toRadians(movementDirection-180))*DRAG*velocity;
		double dragXVelocity = Math.cos(Math.toRadians(movementDirection-180))*DRAG*velocity;
		movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
		movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
		
		newDirection = Math.toDegrees(Math.atan2((movementYVelocity+dragYVelocity),(movementXVelocity+dragXVelocity)));
		velocity = Math.sqrt(DRAG*velocity*DRAG*velocity + velocity*velocity);
		movementDirection = newDirection;
		
		if(yPos>Sky.SKY_HEIGHT) {
			health -= 2;
			double waterYVelocity = Math.sin(Math.toRadians(90))*0.0075*(yPos-Sky.SKY_HEIGHT)+Math.sin(Math.toRadians(movementDirection-180))*0.060*velocity;
			double waterXVelocity = Math.cos(Math.toRadians(movementDirection-180))*0.040*velocity;
			movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
			movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
			
			newDirection = Math.toDegrees(Math.atan2((movementYVelocity+waterYVelocity),(movementXVelocity+waterXVelocity)));
			velocity = Math.sqrt(RECOIL*RECOIL + velocity*velocity);
			movementDirection = newDirection;
		}else {
			if(!shot && shootTimer == 0 && health <MAX_HEALTH+1)
				health += REGEN_RATE;
		}
		
		if(yPos<100) {
			double skyYVelocity = Math.sin(Math.toRadians(-90))*0.6;
			double skyXVelocity = 0;
			movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
			movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
			
			newDirection = Math.toDegrees(Math.atan2((movementYVelocity+skyYVelocity),(movementXVelocity+skyXVelocity)));
			velocity = Math.sqrt(RECOIL*RECOIL + velocity*velocity);
			movementDirection = newDirection;
		}
		
		if(shot) {
			double recoilYVelocity = Math.sin(Math.toRadians(playerDirection-180))*RECOIL;
			double recoilXVelocity = Math.cos(Math.toRadians(playerDirection-180))*RECOIL;
			movementYVelocity = Math.sin(Math.toRadians(movementDirection))*velocity;
			movementXVelocity = Math.cos(Math.toRadians(movementDirection))*velocity;
			
			newDirection = Math.toDegrees(Math.atan2((movementYVelocity+recoilYVelocity),(movementXVelocity+recoilXVelocity)));
			velocity = Math.sqrt(RECOIL*RECOIL + velocity*velocity);
			movementDirection = newDirection;
		}
		
		if(velocity>MAX_VELOCITY) {
			velocity = MAX_VELOCITY;
		}
		
		xPos -= Math.cos(Math.toRadians(movementDirection))*velocity;
		yPos -= Math.sin(Math.toRadians(movementDirection))*velocity;
		Camera.setDisplacement(	xPos - Math.cos(Math.toRadians(playerDirection)) * (200 + 5*CameraBoostConst) - Math.cos(Math.toRadians(movementDirection))*40-900,
								yPos - Math.sin(Math.toRadians(playerDirection)) * (200 + 5*CameraBoostConst) - Math.sin(Math.toRadians(movementDirection))*40-400);
		if(health < 0) 
			alive = false;
	}
	
	/**
	 * Reacts to a given key press
	 * @param key the given key press
	 */
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode()==KeyEvent.VK_UP || key.getKeyCode()==KeyEvent.VK_KP_UP || key.getKeyCode()==KeyEvent.VK_NUMPAD8) {
			keys[UP] = true;
		}else if(key.getKeyCode()==KeyEvent.VK_LEFT || key.getKeyCode()==KeyEvent.VK_KP_LEFT || key.getKeyCode()==KeyEvent.VK_NUMPAD4) {
			keys[LEFT] = true;
		}else if(key.getKeyCode()==KeyEvent.VK_RIGHT || key.getKeyCode()==KeyEvent.VK_KP_RIGHT || key.getKeyCode()==KeyEvent.VK_NUMPAD6) {
			keys[RIGHT] = true;
		}else if(key.getKeyCode()==KeyEvent.VK_SPACE || key.getKeyCode()==KeyEvent.VK_W || key.getKeyCode()==KeyEvent.VK_X) {
			keys[SHOOT] = true;
		}
	}

	/**
	 * Reacts to a given key release
	 * @param key the given key release
	 */
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode()==KeyEvent.VK_UP || key.getKeyCode()==KeyEvent.VK_KP_UP || key.getKeyCode()==KeyEvent.VK_NUMPAD8) {
			keys[UP] = false;
		}else if(key.getKeyCode()==KeyEvent.VK_LEFT || key.getKeyCode()==KeyEvent.VK_KP_LEFT || key.getKeyCode()==KeyEvent.VK_NUMPAD4) {
			keys[LEFT] = false;
		}else if(key.getKeyCode()==KeyEvent.VK_RIGHT || key.getKeyCode()==KeyEvent.VK_KP_RIGHT || key.getKeyCode()==KeyEvent.VK_NUMPAD6) {
			keys[RIGHT] = false;
		}else if(key.getKeyCode()==KeyEvent.VK_SPACE || key.getKeyCode()==KeyEvent.VK_W || key.getKeyCode()==KeyEvent.VK_X) {
			keys[SHOOT] = false;
		}
	}

	/**
	 * Automatically generated method, no current use
	 */
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
	}

	/**
	 * Returns the hitbox of the player
	 * @return the hitbox of the player
	 */
	public Area getCollision() {
		Area a = new Area(new Ellipse2D.Double(0,0,50,20));
		AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(playerDirection+180),xPos,yPos);
		trans.translate(xPos,yPos);
		trans.scale(1.2,  1.2);
		a.transform(trans);
		return a;
	}

	/**
	 * Reacts to a collision with a given entity
	 * @param bullet the given entity
	 */
	public void notifyCollision(Bullet bullet) {
		// TODO Auto-generated method stub
		if(bullet.getBulletGroup() == Bullet.BulletGroup.CONTACT)
			health -= 5;
		else if(bullet.getBulletGroup()!=Bullet.BulletGroup.PLAYER)
			health -= 50;
		
	}
	
	/**
	 * Converts a given Image to a new BufferedImage
	 * @param img the given Image
	 * @return the new BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
	    BufferedImage newImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	
	    Graphics2D gg = newImage.createGraphics();
	    gg.drawImage(img, 0, 0, null);
	    gg.dispose();
	
	    return newImage;
	}
	
}
