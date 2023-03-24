import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * A class which represent the game as a whole
 * @author Blake
 *
 */
public class Game extends JComponent implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3272259728878594695L;
	private Player player;
	private Menu menu;
	
	static final int FRAME_WIDTH = 1800;
	static final int FRAME_HEIGHT = 1000;
	
	/**
	 * Creates a new game
	 */
	public Game() {
		  JFrame frame = new JFrame();
	
	      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	      frame.setTitle("LuftRausers");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      Camera.initialize();
	      //SoundAtlas.init();
	      player = new Player(2786, Sky.SKY_HEIGHT - 223);
	      Ocean.initialize(player);
	      AIManager.initialize(player);
	      menu = new Menu(Menu.TITLE_SCREEN, this);
	      player.step();
	      //Enemies.addEnemy(new BasicPlane(2700, Sky.SKY_HEIGHT - 243, player));
	      frame.addKeyListener(menu);
	      frame.addKeyListener(player);
	      frame.add(this);
		  frame.setVisible(true);
	}
	
	/**
	 * Restarts the game by resetting all of its instance variables
	 */
	private void reset() {
		Camera.initialize();
		Hazards.reset();
		Enemies.reset();
		player.reset(2786, Sky.SKY_HEIGHT - 223);
		Ocean.initialize(player);
		menu.setMode(Menu.END_SCREEN);
		AIManager.initialize(player);
		player.step();
		
	}
	
	/**
	 * Draws all of the game objects to a given Graphics object
	 * @param g the given Graphics objects
	 */
	public void paintComponent(Graphics g) {
		Camera.translate(g);
		Sky.draw(g);
		player.draw(g);
		Hazards.draw(g);
		Enemies.draw(g);
		Ocean.draw(g);
		ScoreManager.draw(g);
		if(menu.getMode()!=Menu.INACTIVE)
			menu.draw(g);
		g.dispose();
	}
	
	/**
	 * Runs the simulation of the game
	 */
	public void run() {
		while(true){
			if(menu.getMode() == Menu.INACTIVE) {
				player.step();
				if(!player.isAlive())
					reset();
				Hazards.step();
				Enemies.step();
				AIManager.step();
				ScoreManager.step();
				repaint();
			}
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * A method called when the game's menu mode changes, repaints the screen
	 */
	public void menuSwitch() {
		repaint();
	}

}
