import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A class to represent an individual turret on a battleship
 * @author Blake
 *
 */
public class Turret {
	private static final double TURN_SPEED = 1;
	private static final double SHOOT_ERROR_MARGIN = 20;
	private static final double TURRET_LENGTH = 74;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	private static final int SHOOT_DELAY = 3;
	private static final int BURST_DELAY = 150;
	private static final int MAX_BURST_SHOTS = 16;
	
	private double baseX;
	private double baseY;
	private int type; //left facing: 0, right facing: 1
	private double direction; //if left, west is 0 degrees, goes clockwise up, if right, east is 0 degrees, goes counterclockwise up
	
	private boolean attacking; //0 is passive, 1 is attacking
	
	private Player player;
	private int shootTimer;
	private int burstTimer;
	private int shotCounter;
	
	/**
	 * Creates a new turret
	 * @param baseX the x position
	 * @param baseY the y position
	 * @param player the player
	 * @param type the type of turret, either left or right facing
	 */
	public Turret(double baseX, double baseY, Player player, int type) {
		this.baseX = baseX;
		this.baseY = baseY;
		this.player = player;
		this.type = type;
		if (type == LEFT) {
			direction = 180;
		} else {
			direction = 360;
		}
		
		shotCounter = 0;
		burstTimer = 0;
		shootTimer = 0;
	}
	
	/**
	 * Draws the turret to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(direction),baseX,baseY);
		trans.translate(baseX, baseY);
		trans.scale(2,2);
		g2.drawImage(ImageAtlas.getImage("Turret"), trans, null);
		
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	public void step() {
		if(!attacking) {
			double planeXVelocity = Math.cos(Math.toRadians(direction));
			double planeYVelocity = Math.sin(Math.toRadians(direction));
			double relativeX = player.getPosition()[0]-baseX;
			double relativeY = player.getPosition()[1]-baseY;
			
			double cross = planeXVelocity*relativeY - planeYVelocity*relativeX;
			if(Math.abs(cross)>200) {
				if(cross>0 && direction < (type == LEFT? 270 : 360)){
					direction += TURN_SPEED;
				}else if(direction > (type == LEFT? 180 : 270)) {
					direction -= TURN_SPEED;
				}
			}else{
				if(burstTimer == 0) {
					attacking = true;
					burstTimer = 1;
				}
			}
			if(burstTimer == BURST_DELAY+1) {
					burstTimer = 0;
			}else {
				burstTimer++;
			}
		}else {
			if(shotCounter >= MAX_BURST_SHOTS) {
				attacking = false;
				shootTimer = 0;
				shotCounter = 0;
			}
			if(shootTimer == 0) {
				Hazards.addHazard(new ShipBullet(baseX+TURRET_LENGTH*2*Math.cos(Math.toRadians(direction))-8*Math.sin((Math.toRadians(direction))), baseY+TURRET_LENGTH*2*Math.sin(Math.toRadians(direction))+8*Math.cos(Math.toRadians(direction)), direction+180+Math.random()*SHOOT_ERROR_MARGIN));
				shootTimer = 1;
				shotCounter++;
			}
			if(shootTimer == SHOOT_DELAY+1) {
				shootTimer = 0;
			}else{
				shootTimer++;
			}
				
		}
		
		
	}
	
	/**
	 * Moves the turret to maintain locking with its parent ship
	 * @param adjX the movement in the x direction
	 * @param adjY the movement in the y direction
	 */
	public void shift(double adjX, double adjY) {
		baseX += adjX;
		baseY += adjY;
	}
}
