import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * A class to store the images the game requires
 * @author Blake
 *
 */
public class ImageAtlas {
	
	private static HashMap<String,Image> atlas;
	
	/**
	 * Creats the ImageAtlas, and reads in the images
	 */
	private ImageAtlas() {
		atlas = new HashMap<String,Image>();
		atlas.put("PlayerBullet0", new ImageIcon(getClass().getResource("Bullet/Bullet1.png")).getImage());
		atlas.put("PlayerBullet1", new ImageIcon(getClass().getResource("Bullet/Bullet2.png")).getImage());
		atlas.put("PlayerBullet2", new ImageIcon(getClass().getResource("Bullet/Bullet3.png")).getImage());
		atlas.put("PlayerBullet3", new ImageIcon(getClass().getResource("Bullet/Bullet4.png")).getImage());
		
		atlas.put("TitleMenu", new ImageIcon(getClass().getResource("MenuScreens/Title.png")).getImage());
		atlas.put("StartMenu", new ImageIcon(getClass().getResource("MenuScreens/Start.png")).getImage());
		atlas.put("EndMenu", new ImageIcon(getClass().getResource("MenuScreens/Death.png")).getImage());
		
		atlas.put("PlayerSprite4", new ImageIcon(getClass().getResource("Planes/plane4.png")).getImage());
		atlas.put("PlayerSprite3", new ImageIcon(getClass().getResource("Planes/plane3.png")).getImage());
		atlas.put("PlayerSprite2", new ImageIcon(getClass().getResource("Planes/plane2.png")).getImage());
		atlas.put("PlayerSprite1", new ImageIcon(getClass().getResource("Planes/plane1.png")).getImage());
		atlas.put("PlayerSprite5", new ImageIcon(getClass().getResource("Planes/plane5.png")).getImage());
		atlas.put("PlayerSprite6", new ImageIcon(getClass().getResource("Planes/plane6.png")).getImage());
		atlas.put("PlayerSprite7", new ImageIcon(getClass().getResource("Planes/plane7.png")).getImage());
		atlas.put("PlayerSprite8", new ImageIcon(getClass().getResource("Planes/plane8.png")).getImage());
		
		atlas.put("PlayerReflectionSprite8", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane4.png")).getImage());
		atlas.put("PlayerReflectionSprite7", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane3.png")).getImage());
		atlas.put("PlayerReflectionSprite6", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane2.png")).getImage());
		atlas.put("PlayerReflectionSprite5", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane1.png")).getImage());
		atlas.put("PlayerReflectionSprite1", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane5.png")).getImage());
		atlas.put("PlayerReflectionSprite2", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane6.png")).getImage());
		atlas.put("PlayerReflectionSprite3", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane7.png")).getImage());
		atlas.put("PlayerReflectionSprite4", new ImageIcon(getClass().getResource("PlaneReflections/reflectPlane8.png")).getImage());
		
		atlas.put("EnemySprite4", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane4.png")).getImage());
		atlas.put("EnemySprite3", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane3.png")).getImage());
		atlas.put("EnemySprite2", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane2.png")).getImage());
		atlas.put("EnemySprite1", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane1.png")).getImage());
		atlas.put("EnemySprite5", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane5.png")).getImage());
		atlas.put("EnemySprite6", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane6.png")).getImage());
		atlas.put("EnemySprite7", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane7.png")).getImage());
		atlas.put("EnemySprite8", new ImageIcon(getClass().getResource("Enemy Planes/enemyPlane8.png")).getImage());
		
		atlas.put("Boost0", new ImageIcon(getClass().getResource("Boosts/boost1.png")).getImage());
		atlas.put("Boost1", new ImageIcon(getClass().getResource("Boosts/boost2.png")).getImage());
		atlas.put("Boost2", new ImageIcon(getClass().getResource("Boosts/boost3.png")).getImage());
		atlas.put("Boost3", new ImageIcon(getClass().getResource("Boosts/boost4.png")).getImage());
		atlas.put("Boost4", new ImageIcon(getClass().getResource("Boosts/boost5.png")).getImage());
		atlas.put("Boost5", new ImageIcon(getClass().getResource("Boosts/boost6.png")).getImage());
		atlas.put("Boost6", new ImageIcon(getClass().getResource("Boosts/boost7.png")).getImage());
		atlas.put("Boost7", new ImageIcon(getClass().getResource("Boosts/boost8.png")).getImage());
		atlas.put("Boost8", new ImageIcon(getClass().getResource("Boosts/boost9.png")).getImage());
		atlas.put("Boost9", new ImageIcon(getClass().getResource("Boosts/boost10.png")).getImage());
		
		atlas.put("Sky", new ImageIcon(getClass().getResource("sky.png")).getImage());
		atlas.put("Ocean", new ImageIcon(getClass().getResource("ocean.png")).getImage());
		
		atlas.put("Ship", new ImageIcon(getClass().getResource("ship.png")).getImage());
		atlas.put("Turret", new ImageIcon(getClass().getResource("turret.png")).getImage());
		
		atlas.put("MotorBoat", new ImageIcon(getClass().getResource("motorboat.png")).getImage());
		atlas.put("MotorBoatTurret", new ImageIcon(getClass().getResource("motorboatTurret.png")).getImage());
		
		atlas.put("Explosion0", new ImageIcon(getClass().getResource("Explosions/explosion1.png")).getImage());
		atlas.put("Explosion1", new ImageIcon(getClass().getResource("Explosions/explosion2.png")).getImage());
		atlas.put("Explosion2", new ImageIcon(getClass().getResource("Explosions/explosion3.png")).getImage());
		atlas.put("Explosion3", new ImageIcon(getClass().getResource("Explosions/explosion4.png")).getImage());
		atlas.put("Explosion4", new ImageIcon(getClass().getResource("Explosions/explosion5.png")).getImage());
		atlas.put("Explosion5", new ImageIcon(getClass().getResource("Explosions/explosion6.png")).getImage());
		atlas.put("Explosion6", new ImageIcon(getClass().getResource("Explosions/explosion7.png")).getImage());
		atlas.put("Explosion7", new ImageIcon(getClass().getResource("Explosions/explosion8.png")).getImage());
		atlas.put("Explosion8", new ImageIcon(getClass().getResource("Explosions/explosion9.png")).getImage());
		
		atlas.put("BoostReflection0", new ImageIcon(getClass().getResource("BoostReflections/boostReflection1.png")).getImage());
		atlas.put("BoostReflection1", new ImageIcon(getClass().getResource("BoostReflections/boostReflection2.png")).getImage());
		atlas.put("BoostReflection2", new ImageIcon(getClass().getResource("BoostReflections/boostReflection3.png")).getImage());
		atlas.put("BoostReflection3", new ImageIcon(getClass().getResource("BoostReflections/boostReflection4.png")).getImage());
		atlas.put("BoostReflection4", new ImageIcon(getClass().getResource("BoostReflections/boostReflection5.png")).getImage());
		atlas.put("BoostReflection5", new ImageIcon(getClass().getResource("BoostReflections/boostReflection6.png")).getImage());
		atlas.put("BoostReflection6", new ImageIcon(getClass().getResource("BoostReflections/boostReflection7.png")).getImage());
		atlas.put("BoostReflection7", new ImageIcon(getClass().getResource("BoostReflections/boostReflection8.png")).getImage());
		atlas.put("BoostReflection8", new ImageIcon(getClass().getResource("BoostReflections/boostReflection9.png")).getImage());
		atlas.put("BoostReflection9", new ImageIcon(getClass().getResource("BoostReflections/boostReflection10.png")).getImage());
		
		atlas.put("DamagedPlane0", new ImageIcon(getClass().getResource("DamagedPlanes/damagedPlane1.png")).getImage());
		atlas.put("DamagedPlane1", new ImageIcon(getClass().getResource("DamagedPlanes/damagedPlane2.png")).getImage());
		atlas.put("DamagedPlane2", new ImageIcon(getClass().getResource("DamagedPlanes/damagedPlane3.png")).getImage());
		atlas.put("DamagedPlane3", new ImageIcon(getClass().getResource("DamagedPlanes/damagedPlane4.png")).getImage());
		
		atlas.put("score0", new ImageIcon(getClass().getResource("ScoreNumbers/0.png")).getImage());
		atlas.put("score1", new ImageIcon(getClass().getResource("ScoreNumbers/1.png")).getImage());
		atlas.put("score2", new ImageIcon(getClass().getResource("ScoreNumbers/2.png")).getImage());
		atlas.put("score3", new ImageIcon(getClass().getResource("ScoreNumbers/3.png")).getImage());
		atlas.put("score4", new ImageIcon(getClass().getResource("ScoreNumbers/4.png")).getImage());
		atlas.put("score5", new ImageIcon(getClass().getResource("ScoreNumbers/5.png")).getImage());
		atlas.put("score6", new ImageIcon(getClass().getResource("ScoreNumbers/6.png")).getImage());
		atlas.put("score7", new ImageIcon(getClass().getResource("ScoreNumbers/7.png")).getImage());
		atlas.put("score8", new ImageIcon(getClass().getResource("ScoreNumbers/8.png")).getImage());
		atlas.put("score9", new ImageIcon(getClass().getResource("ScoreNumbers/9.png")).getImage());
	}
	
	/**
	 * Returns the image that matches the given name
	 * @param s the given name
	 * @return the image
	 */
	public static Image getImage(String s) {
		if(atlas == null)
			new ImageAtlas();
		if(atlas.containsKey(s))
			return atlas.get(s);
		else
			throw new IllegalArgumentException("Image "+s+" is not in the atlas");
	}
}
