/**
 * A class to manage all of the separate Enemies that spawn
 * 
 * @author Blake
 *
 */
public class AIManager {

	private static double timer;
	private static final double PLANE_GROWTH_BASE = 12;
	private static final double BATTLESHIP_GROWTH_BASE = 40;
	private static final double MOTORBOAT_GROWTH_BASE = 20;
	private static Player player;
	
	/**
	 * sets the AIManager to its default Settings. Necessary for the first time the AIManager is used.
	 * @param p the player
	 */
	public static void initialize(Player p) {
		new AIManager(p);
	}
	
	/** 
	 * Creates the AI manager
	 * @param p the player
	 */
	private AIManager(Player p) {
		timer = 0;
		player = p;
	}
	
	public static void step() {
		timer++;
		if(timer%160 == 0) {
			int numPlanes = (int)(Math.log(timer/3)/Math.log(PLANE_GROWTH_BASE));
			for(int i = 0; i<numPlanes; i++) {
				double xOffset = Math.random()*1000-500;
				double yOffset = Math.random()*1000- (Camera.getDisplacement()[1]+Game.FRAME_HEIGHT>Sky.SKY_HEIGHT? 1000 : 500);
				Enemies.addEnemy(new BasicPlane(Camera.getDisplacement()[0]+xOffset + (xOffset<0? 0 : Game.FRAME_WIDTH), Camera.getDisplacement()[1]+yOffset + (yOffset<0? 0 : +Game.FRAME_HEIGHT), player));
			}
		}
		
		if(timer%1200 == 0) {
			int numBoats = (int)(Math.log(timer/20)/Math.log(MOTORBOAT_GROWTH_BASE));
			for(int i = 0; i<numBoats; i++) {
				double xOffset = Math.random()*1000-500;
				Enemies.addEnemy(new MotorBoat(Camera.getDisplacement()[0]+xOffset + (xOffset<0? -200 : Game.FRAME_WIDTH), (double)Sky.SKY_HEIGHT-110, player));
			}
		}
		
		if(timer%3300==0) {
			int numBattleShips = (int)(Math.log(timer/20)/Math.log(BATTLESHIP_GROWTH_BASE));
			for(int i = 0; i<numBattleShips; i++) {
				double xOffset = Math.random()*6000-3000;
				Enemies.addEnemy(new Ship(Camera.getDisplacement()[0]+xOffset + (xOffset<0? -1500 : Game.FRAME_WIDTH), (double)Sky.SKY_HEIGHT-350, player));
			}
		}
		
	}
	
}
