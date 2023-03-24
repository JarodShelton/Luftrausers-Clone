import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Manages both the players score and the list of ScoreFlairs
 * @author Blake
 *
 */
public class ScoreManager {

	private static final int COMBO_LENGTH = 350;
	
	private static int combo;
	private static int score;
	private static int timer;
	private static LinkedList<ScoreFlair> scoreFlairs = new LinkedList<ScoreFlair>();
	
	/**
	 * Draws the players score to the given Graphics object, and passes the given Graphics object to each ScoreFlair
	 * @param g the given Graphics object
	 */
	public static void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.setFont(new Font("Score", Font.ROMAN_BASELINE, 60));
		g.drawString(String.format("x %02d", combo), (int)(Camera.getDisplacement()[0]+Game.FRAME_WIDTH)-150, (int)Camera.getDisplacement()[1]+50);
		g.setFont(new Font("Score", Font.ROMAN_BASELINE, 30));
		g.drawString(String.format("%08d", score), (int)(Camera.getDisplacement()[0]+Game.FRAME_WIDTH)-160, (int)Camera.getDisplacement()[1]+80);
		for(ScoreFlair sf : scoreFlairs)
			sf.draw(g);
	}
	
	/**
	 * Returns the player's current score
	 * @return the player's current score
	 */
	public static int getScore() {
		return score;
	}
	
	/**
	 * Reacts to the death of an enemy killed by the player by creating a score flair and incrementing the players score
	 * @param enemy the fallen enemy
	 */
	public static void addKill(Enemy enemy) {
		score += enemy.getPointValue()*combo;
		scoreFlairs.add(new ScoreFlair(enemy.getPointValue()*combo, (int)(enemy.getPosition()[0]), (int)(enemy.getPosition()[1])));
		if(combo<40)
			combo++;
		timer = COMBO_LENGTH;
	}
	
	/**
	 * Restarts the game by resetting the score to zero
	 */
	public static void reset() {
		score = 0;
		combo = 1;
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	public static void step() {
		if(timer<1) 
			combo = 1;
		else
			timer--;
		ListIterator<ScoreFlair> iter = scoreFlairs.listIterator();
		while(iter.hasNext()) {
			ScoreFlair temp = iter.next();
			if(temp.isActive()) 
				temp.step();
			else
				iter.remove();
		}
	}
}
