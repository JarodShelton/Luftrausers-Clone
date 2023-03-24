import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A class to represent an individual turret on a motorboat
 * @author Blake
 *
 */
public class MotorBoatTurret {
	private static final double TURN_SPEED = 5;
	private static final double SHOOT_ERROR_MARGIN = 3;
	private static final double TURRET_LENGTH = 33;
	private static final int SHOOT_DELAY = 10;
	private static final int BURST_DELAY = 90;
	private static final int MAX_BURST_SHOTS = 5;
	
	private double baseX;
	private double baseY;
	private double direction;
	
	private boolean attacking; //0 is passive, 1 is attacking
	
	private Player player;
	private int shootTimer;
	private int burstTimer;
	private int shotCounter;
	
	/**
	 * Creates a new MotorBoatTurret
	 * @param baseX the x position
	 * @param baseY the y position
	 * @param player the player
	 */
	public MotorBoatTurret(double baseX, double baseY, Player player) {
		this.baseX = baseX;
		this.baseY = baseY;
		this.player = player;
		
		direction = 270;
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
		AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(direction),baseX-5*Math.cos(Math.toRadians(direction))+10*Math.sin((Math.toRadians(direction))), baseY-5*Math.sin(Math.toRadians(direction))-10*Math.cos(Math.toRadians(direction)));
		trans.translate(baseX-5*Math.cos(Math.toRadians(direction))+10*Math.sin((Math.toRadians(direction))), baseY-5*Math.sin(Math.toRadians(direction))-10*Math.cos(Math.toRadians(direction)));
		trans.scale(1.3,1.3);
		g2.drawImage(ImageAtlas.getImage("MotorBoatTurret"), trans, null);
		
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
				if(cross>0 && direction<345){
					direction += TURN_SPEED;
				}else if(direction > 190){
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
