//import java.io.BufferedInputStream;
import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundAtlas {

	private static HashMap<String, Clip> clips = new HashMap<String, Clip>();

	public SoundAtlas() { 
	}

	public static void init() {
		loadSound("Explosion", "Sounds/Explosion.wav");
		loadSound("Shot", "Sounds/Shot.wav");
		//loadSound("save", "/Sound/save.wav");
	}
	public static void loadSound(String name, String file) {
		try {
			System.out.print("Loading sound file: \"" + file + "\" into clip: \"" + name + "\", ");
			//BufferedInputStream in = new BufferedInputStream(new File("Sounds/"+file));
			AudioInputStream ain = AudioSystem.getAudioInputStream(new File(file));

			Clip c = AudioSystem.getClip();
			c.open(ain);
			c.setLoopPoints(0, -1);
			clips.put(name, c);
			ain.close();
			//in.close();
			System.out.println("Done.");
		} catch (Exception e) {
			System.out.println("Failed. (" + e.getMessage() + ")");
		}
	}

	public static boolean play(String name) {
		if (clips.containsKey(name)) {
			clips.get(name).setFramePosition(0);
			clips.get(name).start();
			return true;
		}
		return false;
	}

	public static boolean stop(String name) {
		if (clips.containsKey(name) && clips.get(name).isActive()) {
			clips.get(name).stop();
			clips.get(name).setFramePosition(0);
			return true;
		}
		return false;
	}

	public static boolean isPlaying(String name) {
		if (clips.containsKey(name) && clips.get(name).isRunning()) {
			return true;
		}
		return false;
	}

	public long getPosition(String name) {
		if (clips.containsKey(name) && clips.get(name).isRunning()) {
			return clips.get(name).getLongFramePosition();
		}
		return -1;
	}
}