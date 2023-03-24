import java.awt.Font;
import java.awt.Graphics;

/**
 * A class to represent a score gained from eliminating an enemy
 * @author Blake
 *
 */
public class ScoreFlair implements Detail {

	private static final int TIME_ON_SCREEN = 90;
	
	private boolean active;
	private int timer;
	private int score;
	private int x;
	private int y;
	
	/**
	 * Creates a new ScoreFlair
	 * @param score the score to represent
	 * @param x the x position to display the flair at
	 * @param y the y position to display the flair at
	 */
	public ScoreFlair(int score, int x, int y) {
		timer = TIME_ON_SCREEN;
		this.score = score;
		this.x = x;
		this.y = y;
		active = true;
	}
	
	/**
	 * Draws the ScoreFlair to the given graphics object
	 * @param g the given graphics object
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setFont(new Font("Score", Font.ROMAN_BASELINE, 15+(score/50)));
		g.drawString(score+"", x, y);
	}
	
	/**
	 * Simulates the next frame of the simulation
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		if(timer < 1)
			active = false;
		else
			timer--;
	}

	/**
	 * Returns whether the flair is active
	 * @return whether the flair is active
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

}
