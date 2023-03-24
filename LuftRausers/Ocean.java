import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A class to represent the ocean
 * @author Blake
 *
 */
public class Ocean {
	
	private static final int PANELSIZE = 5000;
	
	private static Image ocean;
	private static Player player;

	private static int boostTimer;
	
	/**
	 * Creates a new Ocean
	 * @param player the player
	 */
	public static void initialize(Player player) {
		new Ocean(player);
	}
	
	/**
	 * Creates a new Ocean
	 * @param p the player
	 */
	private Ocean(Player p) {
		player = p;
		boostTimer = 0;
		ocean = ImageAtlas.getImage("Ocean");
	}
	
	/**
	 * Draws the ocean to a given Graphics object
	 * @param g the given Graphics object
	 */
	public static void draw(Graphics g) {
		int constant = Camera.getDisplacement()[0]<0? PANELSIZE: 0;
		int otherConstant = Camera.getDisplacement()[0]<-PANELSIZE ? PANELSIZE: 0;
		if(Camera.getDisplacement()[1]>Sky.SKY_HEIGHT-Game.FRAME_HEIGHT) {
			if(Camera.getDisplacement()[0]%PANELSIZE + constant > PANELSIZE-1800) {
				// left Side
				g.drawImage(ocean, (int)Camera.getDisplacement()[0],Sky.SKY_HEIGHT,(int)Camera.getDisplacement()[0]+PANELSIZE-(int)Camera.getDisplacement()[0]%PANELSIZE-constant,(int)Camera.getDisplacement()[1]+1000,
						(int)Camera.getDisplacement()[0]%PANELSIZE+constant, 0,PANELSIZE,(int)Camera.getDisplacement()[1]+1000-Sky.SKY_HEIGHT, null);	
				// right Side
				g.drawImage(ocean, (int)Camera.getDisplacement()[0]+PANELSIZE-(int)Camera.getDisplacement()[0]%PANELSIZE-constant,Sky.SKY_HEIGHT,(int)Camera.getDisplacement()[0]+1800,(int)Camera.getDisplacement()[1]+1000,
						0, 0,((int)Camera.getDisplacement()[0]+1800)%PANELSIZE+otherConstant,(int)Camera.getDisplacement()[1]+1000-Sky.SKY_HEIGHT, null);
			}else {
				g.drawImage(ocean, (int)Camera.getDisplacement()[0],Sky.SKY_HEIGHT,(int)Camera.getDisplacement()[0]+1800,(int)Camera.getDisplacement()[1]+1000,
						(int)Camera.getDisplacement()[0]%PANELSIZE+constant, 0,((int)Camera.getDisplacement()[0]+1800)%PANELSIZE+constant,(int)Camera.getDisplacement()[1]+1000-Sky.SKY_HEIGHT, null);	
			}
		}
		
		double xPos = player.getPosition()[0];
		double yPos = player.getPosition()[1];
		double playerDirection = -player.getPlayerDirection();
		Graphics2D g2 = (Graphics2D)g;
		
		if(yPos>Sky.SKY_HEIGHT-Game.FRAME_HEIGHT && yPos<Sky.SKY_HEIGHT) {
			
			double refY = Sky.SKY_HEIGHT+Sky.SKY_HEIGHT-yPos;
			double spriteDirection = playerDirection>0? playerDirection : (360+playerDirection);
			AffineTransform trans = null;
			if(player.isBoosting()){
				double xOffset = 0;
				if((spriteDirection>=45 && spriteDirection<=135) || (spriteDirection>=225 && spriteDirection<=315))
					xOffset = 19;
				else
					xOffset = 13;
				trans = AffineTransform.getRotateInstance(Math.toRadians(playerDirection+90),xPos+23*Math.cos(Math.toRadians(playerDirection))+xOffset*Math.sin(Math.toRadians(playerDirection)),refY+23*Math.sin(Math.toRadians(playerDirection))-xOffset*Math.cos(Math.toRadians(playerDirection)));
				trans.translate(xPos+23*Math.cos(Math.toRadians(playerDirection))+xOffset*Math.sin(Math.toRadians(playerDirection)),refY+23*Math.sin(Math.toRadians(playerDirection))-xOffset*Math.cos(Math.toRadians(playerDirection)));
				//trans.scale(0.8, 0.8);
				g2.drawImage(ImageAtlas.getImage("BoostReflection"+boostTimer/2), trans, null);
				boostTimer++;
				boostTimer%=20;
			}
			
			trans = AffineTransform.getRotateInstance(Math.toRadians(playerDirection+180),xPos,refY);
			trans.translate(xPos,refY);
			trans.scale(1.2,  1.2);
			
		
			if(spriteDirection>=75 && spriteDirection<=105) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite1"), trans, null);
			}else if(spriteDirection>=105 && spriteDirection<=135) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite2"), trans, null);
			}else if(spriteDirection>=135 && spriteDirection<=165) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite3"), trans, null);
			}else if(spriteDirection>=165 && spriteDirection<=195) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite4"), trans, null);
			}else if(spriteDirection>=195 &&spriteDirection<=225) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite3"), trans, null);
			}else if(spriteDirection>=225 && spriteDirection<=255) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite2"), trans, null);
			}else if(spriteDirection>=255 && spriteDirection<=285) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite5"), trans, null);
			}else if(spriteDirection>=285 && spriteDirection<=315) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite6"), trans, null);
			}else if(spriteDirection>=315 && spriteDirection<=345) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite7"), trans, null);
			}else if((spriteDirection>=345 && spriteDirection<=360) || (spriteDirection>=0 && spriteDirection<=15)) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite8"), trans, null);
			}else if(spriteDirection>=15 && spriteDirection<=45) {
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite7"), trans, null);
			}else{
				g2.drawImage(ImageAtlas.getImage("PlayerReflectionSprite6"), trans, null);
			}
			/*g.setColor(Color.yellow);
			g.fillOval((int)xPos,(int)yPos, 5, 5);
			g.fillOval((int)xPos,(int)refY, 5, 5);*/
		}
		
	}
	
}
