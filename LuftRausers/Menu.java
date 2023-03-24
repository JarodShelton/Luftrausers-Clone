import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class to represent the various menus in the game
 * @author Blake
 *
 */
public class Menu implements KeyListener{
	
	public static final int INACTIVE = -1;
	public static final int START_SCREEN = 0;
	public static final int END_SCREEN = 1;
	public static final int TITLE_SCREEN = 2;
	private static final int SCORE_X = 2750;
	private static final int SCORE_Y = 1975;
	private static final int SCORE_WIDTH = 16;
	private static final int SCORE_HEIGHT = 16;
	
	private int mode;
	private Game game;
	
	/**
	 * Creates a new menu
	 * @param mode the menu mode
	 * @param game the game
	 */
	public Menu(int mode, Game game) {
		this.mode = mode;
		this.game = game;
		game.menuSwitch();
	}
	
	/**
	 * Draws the current menu mode to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void draw(Graphics g) { 
		if (mode == TITLE_SCREEN) {
			g.drawImage(ImageAtlas.getImage("TitleMenu"), 1886, 1647, Game.FRAME_WIDTH, Game.FRAME_HEIGHT, null);
		} else if (mode == START_SCREEN) {
			g.drawImage(ImageAtlas.getImage("StartMenu"), 1886, 1647, Game.FRAME_WIDTH, Game.FRAME_HEIGHT, null);
		} else if (mode == END_SCREEN) {
			g.drawImage(ImageAtlas.getImage("EndMenu"), 1886, 1697, Game.FRAME_WIDTH, Game.FRAME_HEIGHT, null);
			drawScore(g);
			ScoreManager.reset();
		}
		
	}
	
	/**
	 * Draws the player's final score on the death screen to a given Graphics object
	 * @param g the given Graphics object
	 */
	public void drawScore(Graphics g) {
		String score = "" + ScoreManager.getScore();
		int length = score.length();
		for (int i = 0; i < 10 - length; i++) {
			score = "0" + score;
		}
		for (int i = 0; i < score.length(); i++) {
			g.drawImage(ImageAtlas.getImage("score" + score.substring(i, i + 1)), SCORE_X + (i * SCORE_WIDTH), SCORE_Y, SCORE_WIDTH, SCORE_HEIGHT, null);
		}
	}
	
	/**
	 * Sets the current menu mode
	 * @param mode the current menu mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	/**
	 * Returns the current menu mode
	 * @return the current menu mode
	 */
	public int getMode() {
		return mode;
	}
	
	/**
	 * Reacts to a given key press
	 * @param key the given key press
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		if(mode != INACTIVE) {
			if (mode == TITLE_SCREEN) {
				if(key.getKeyCode()==KeyEvent.VK_SPACE || key.getKeyCode()==KeyEvent.VK_W || key.getKeyCode()==KeyEvent.VK_X) {
					mode = START_SCREEN;
					game.menuSwitch();
				}
			} else if (mode == START_SCREEN) {
				if(key.getKeyCode()==KeyEvent.VK_NUMPAD8 || key.getKeyCode()==KeyEvent.VK_KP_UP || key.getKeyCode()==KeyEvent.VK_UP) {
					mode = INACTIVE;
				}
			} else if (mode == END_SCREEN) {
				if(key.getKeyCode()==KeyEvent.VK_SPACE || key.getKeyCode()==KeyEvent.VK_W || key.getKeyCode()==KeyEvent.VK_X) {
					mode = START_SCREEN;
					game.menuSwitch();
				}
			}
		}
		
	}

	/**
	 * Automatically generated method, no current use
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Automatically generated method, no current use
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
