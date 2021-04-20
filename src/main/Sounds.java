/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	private AudioInputStream audioIn;
	private AudioFormat format;
	private Clip clip;

	private URL url;

	public Sounds() {
		try {
			url = World.class.getResource("/res/swords.mid");
			this.audioIn = AudioSystem.getAudioInputStream(url);
			this.format = this.audioIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			this.clip = (Clip) AudioSystem.getLine(info);
			this.clip.open(audioIn);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
			Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void startMusic() {
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		this.clip.stop();
	}
}
