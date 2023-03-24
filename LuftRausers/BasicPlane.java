import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * A class to represent an enemy plane
 * @author Blake
 *
 */
public class BasicPlane implements Enemy {
	private final static double ACCELERATION = 1;
	private static final double MAX_VELOCITY = 12;
	private static final double MIN_VELOCITY = 8;
	private final static double GRAVITY = 0.37;
	private static final double DRAG = .9;
	private final static int SHOOT_DELAY = 30;
	private static final double MIN_TURN_SPEED = 1;
	private static final double MAX_TURN_SPEED = 2.5;
	private static final int MAX_HEALTH = 50;
	private static final int POINT_VALUE = 25;
	
	private boolean alive;
	
	private double xPos;
	private double yPos;
	private double velocity;
	private double planeDirection;
	private double movementDirection;
	private Player player;
	
	private int shootTimer;
	private int health;
	
	/**
	 * Creates a new basic plane
	 * @param xPos the x position
	 * @param yPos the y position
	 * @param player the player
	 */
	public BasicPlane(double xPos, double yPos, Player player) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.player = player;
		alive = true;
		velocity = 0;
		planeDirection = 0;
		movementDirection = 0;
		health = MAX_HEALTH;
	}
	
	/**
	 * Draws the BasicPlane to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g){
		if(alive && (Math.abs(xPos-player.getPosition()[0])<Game.FRAME_WIDTH || Math.abs(xPos-player.getPosition()[1])<Game.FRAME_HEIGHT)) {
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(planeDirection+180),xPos,yPos);
			trans.translate(xPos,yPos);
			trans.scale(1.2,  1.2);
			double spriteDirection = planeDirection>0? planeDirection : (360+planeDirection);
			if(spriteDirection>=75 && spriteDirection<=105) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite1"), trans, null);
			}else if(spriteDirection>=105 && spriteDirection<=135) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite2"), trans, null);
			}else if(spriteDirection>=135 && spriteDirection<=165) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite3"), trans, null);
			}else if(spriteDirection>=165 && spriteDirection<=195) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite4"), trans, null);
			}else if(spriteDirection>=195 &&spriteDirection<=225) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite3"), trans, null);
			}else if(spriteDirection>=225 && spriteDirection<=255) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite2"), trans, null);
			}else if(spriteDirection>=255 && spriteDirection<=285) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite5"), trans, null);
			}else if(spriteDirection>=285 && spriteDirection<=315) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite6"), trans, null);
			}else if(spriteDirection>=315 && spriteDirection<=345) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite7"), trans, null);
			}else if((spriteDirection>=345 && spriteDirection<=360) || (spriteDirection>=0 && spriteDirection<=15)) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite8"), trans, null);
			}else if(spriteDirection>=15 && spriteDirection<=45) {
				g2.drawImage(ImageAtlas.getImage("EnemySprite7"), trans, null);
			}else{
				g2.drawImage(ImageAtlas.getImage("EnemySprite6"), trans, null);
			}
			//g.setColor(Color.yellow);
			//g.fillOval((int)(xPos-25*Math.cos(Math.toRadians(direction))+10*Math.sin((Math.toRadians(direction)))-5), (int)(yPos-25*Math.sin(Math.toRadians(direction))-10*Math.cos(Math.toRadians(direction))-5), 10, 10);
			if(yPos>Sky.SKY_HEIGHT)
				alive = false;
		}
		
	}
	
	/**
	 * Simulates the next step of the simulation
	 */
	public void step() {
		double planeXVelocity = Math.cos(Math.toRadians(planeDirection))*velocity;
		double planeYVelocity = Math.sin(Math.toRadians(planeDirection))*velocity;
		double relativeX = player.getPosition()[0]-xPos;
		double relativeY = player.getPosition()[1]-yPos;
		
		double angleDiff = Math.toDegrees(Math.acos((planeXVelocity*relativeX+planeYVelocity*relativeY)/(Math.sqrt(relativeX*relativeX+relativeY*relativeY)*velocity)));
		double cross = planeXVelocity*relativeY - planeYVelocity*relativeX;
		if(Math.abs(cross)>600) {
			if(cross>0){
				planeDirection -= MIN_TURN_SPEED+(MAX_TURN_SPEED-MIN_TURN_SPEED)/180*angleDiff;
			}else {
				planeDirection += MIN_TURN_SPEED+(MAX_TURN_SPEED-MIN_TURN_SPEED)/180*angleDiff;
			}
		}else {
			if(shootTimer == 0) {
				Hazards.addHazard(new PlaneBullet(xPos-100*Math.cos(Math.toRadians(planeDirection))+25*Math.sin((Math.toRadians(planeDirection))), yPos-100*Math.sin(Math.toRadians(planeDirection))-25*Math.cos(Math.toRadians(planeDirection)), planeDirection));
				shootTimer = 1;
			}
		}
		
		if(shootTimer == SHOOT_DELAY+1) {
				shootTimer = 0;
		}else {
			shootTimer++;
		}
		double acceleration = ACCELERATION;
		
		
		double playerYVelocity = Math.sin(Math.toRadians(planeDirection))*acceleration;
		double playerXVelocity = Math.cos(Math.toRadians(planeDirection))*acceleration;
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
		
		double velocityLimit = MIN_VELOCITY+(MAX_VELOCITY-MIN_VELOCITY)/180*(180-angleDiff);
		
		if(velocity>velocityLimit) {
			velocity = velocityLimit;
		}
		
		xPos -= Math.cos(Math.toRadians(movementDirection))*velocity;
		yPos -= Math.sin(Math.toRadians(movementDirection))*velocity;
		
		if(yPos>Sky.SKY_HEIGHT) {
			alive = false;
			ScoreManager.addKill(this);
		}
	}
	
	/**
	 * Returns the hitbox of the BasicPlane
	 * @return the hitbox of the BasicPlane
	 */
	public Area getCollision() {
		Area a = new Area(new Rectangle2D.Double(0,0,50,20));
		AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(planeDirection+180),xPos,yPos);
		trans.translate(xPos,yPos);
		trans.scale(1.2,  1.2);
		a.transform(trans);
		return a;
	}
	
	/**
	 * Reacts to a collision with a given bullet
	 * @param bullet the given bullet
	 */
	public void notifyCollision(Bullet bullet) {
		if(bullet.getBulletGroup()==Bullet.BulletGroup.CONTACT) {
			health -= 15;
		}else {
			health -= 50;
		}
		if(health < 1) {
			if(bullet.getBulletGroup() == Bullet.BulletGroup.CONTACT) {
				Camera.shakeScreen(15);
				ScoreManager.addKill(this);
			}else if(bullet.getBulletGroup() == Bullet.BulletGroup.PLAYER){
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ScoreManager.addKill(this);
				Camera.shakeScreen(12);
			}
			//SoundAtlas.play("Explosion");
			Sky.addDetail(new DeadPlane(xPos-25*Math.cos(Math.toRadians(planeDirection))+10*Math.sin((Math.toRadians(planeDirection))), yPos-25*Math.sin(Math.toRadians(planeDirection))-10*Math.cos(Math.toRadians(planeDirection)), planeDirection, velocity*0.75));
			Sky.addDetail(new Explosion(xPos-25*Math.cos(Math.toRadians(planeDirection))+10*Math.sin((Math.toRadians(planeDirection))), yPos-25*Math.sin(Math.toRadians(planeDirection))-10*Math.cos(Math.toRadians(planeDirection)),2));
			alive = false;
		}
		
	}
		
	/**
	 * Returns whether the BasicPlane is alive
	 * @return whether the BasicPlane is alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Returns the position of the BasicPlane
	 * @return the position of the BasicPlane
	 */
	public double[] getPosition(){
		double[] position = {xPos, yPos};
		return position;
	}

	/**
	 * Returns the score value of the BasicPlane
	 * @return the score value of the BasicPlane
	 */
	@Override
	public int getPointValue() {
		// TODO Auto-generated method stub
		return POINT_VALUE;
	}

}
