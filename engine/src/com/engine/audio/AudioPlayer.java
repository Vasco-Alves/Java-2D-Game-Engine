package com.engine.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private Clip sound;
	private FloatControl volumeControl;

	public AudioPlayer(String path) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
			sound = AudioSystem.getClip();
			sound.open(audioStream);

			// Obtain the FloatControl for controlling the volume
			volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (!sound.isActive()) {
			sound.setMicrosecondPosition(0);
			sound.start();
		}
	}

	public void stop() {
		if (sound.isActive()) {
			sound.stop();
			sound.setMicrosecondPosition(0);
		}
	}

	public void close() {
		if (sound.isOpen()) {
			sound.close();
		}
	}

	public Clip getClip(String name) {
		return sound;
	}

	public boolean isPlaying() {
		return sound.isActive();
	}

	public void setVolume(int percent) {
		// Calculate the gain based on the percentage
		float gain = (float) (Math.log(percent / 100.0) / Math.log(10.0) * 20.0);

		// Set the gain
		volumeControl.setValue(gain);
	}

	public void setVolume50() {
		setVolume(50);
	}

	public void setVolume25() {
		setVolume(25);
	}
}
