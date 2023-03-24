

import java.awt.*;

/**
 * Represent the part of the screen that is being drawn
 * @author Blake
 *
 */
public class Camera
{  
   	
   	private static double x;
   	private static  double y;
	private static  int timer;
	private static  double amplitude;
    private static  double direction;
	private static  boolean directedScreenShake;
	
	/**
	 * sets the camera to its default position. Necessary for the first time the camera is used.
	 */
	public static void initialize() {
		new Camera();
	}
    
	/**
	 * Creates a new camera
	 */
   private Camera()
   {
   	x = 0;
   	y = 0;
   	timer = 0;
   	amplitude = 0;
   	direction = 0;
   	directedScreenShake = false;
   }
   
   /**
    * Returns the displacement of the camera
    * @return the displacement of the camera
    */
   public static double[] getDisplacement() {
	   double[] ret = {x, y<0? 0 : y>Sky.SKY_HEIGHT-600? Sky.SKY_HEIGHT-600 : y};
	   return ret;
   }
   
   /**
    * Sets the displacement of the camera
    * @param xPos the displacement in the x direction
    * @param yPos the displacement in the y direction
    */
   public static void setDisplacement(double xPos, double yPos) {
	   x = xPos;
	   y = yPos;
   }
   
   /**
    * Translates what is being drawn to a given Graphics object
    * @param g the given Graphics object
    */
   public static void translate(Graphics g){
	   if(timer>7) 
		   timer = 0;
	   double shakeDistance = directedScreenShake? Math.sin(Math.PI*timer/7)*amplitude : Math.sin(Math.PI*timer/3.5)*amplitude ;
	   if(y<0) 
		    ((Graphics2D)g).translate((int)-x-Math.cos(Math.toRadians(direction))*shakeDistance,0);
	   else if(y>Sky.SKY_HEIGHT-600)
	   		((Graphics2D)g).translate((int)-x-Math.cos(Math.toRadians(direction))*shakeDistance,-Sky.SKY_HEIGHT+600);
	   else
		  ((Graphics2D)g).translate((int)-x-Math.cos(Math.toRadians(direction))*shakeDistance,(int)-y-Math.sin(Math.toRadians(direction))*shakeDistance);
	   //System.out.println(((Graphics2D)g).getTransform());
   	   if(timer>0)
   		   timer++;
   }
   
   /**
    * Shakes the camera in a given direction
    * @param amp the amplitude of the shaking
    * @param dir the given direction of the shaking in degrees
    */
   public static void shakeScreen(double amp, double dir) {
	   if(amp >= amplitude) {
		   amplitude = amp;
		   direction = dir;
		   timer = 1;
		   directedScreenShake = true;
	   }
	   
   }
   
   /**
    * Shakes the camera in a random direction
    * @param amp the amplitude of the shaking
    */
   public static void shakeScreen(double amp) {
	   if(amp >= amplitude) {
		   amplitude = amp;
		   direction = Math.random()*360;
		   timer = 1;
		   directedScreenShake = false;
	   }
   }
}