

/**
 * A class which starts the game thread
 * @author Blake
 *
 */
public class Luftrausers
{
   /**
    * Starts the game thread
    */
   public static void main(String[] args)
   {
      Game game = new Game();
	  Thread gameRunner = new Thread(game);
	  gameRunner.start();
   }
}

 