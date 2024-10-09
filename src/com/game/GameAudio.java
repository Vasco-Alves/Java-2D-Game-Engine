package com.game;

import com.engine.audio.AudioPlayer;

public class GameAudio {

	// Player
	public static AudioPlayer playerWalk;
	public static AudioPlayer playerSprint;
	public static AudioPlayer playerHurt;
	public static AudioPlayer playerDie;
	public static AudioPlayer playerSwordAttack;

	// Coin
	public static AudioPlayer coinCollected;

	// Chest
	public static AudioPlayer chestOpen;

	public GameAudio() {
		System.out.println("[*] Loading audio files.");

		// Player
		playerWalk = new AudioPlayer("./assets/audio/player/footsteps-barefoot.wav");
		playerHurt = new AudioPlayer("./assets/audio/player/hurt.wav");
		playerSwordAttack = new AudioPlayer("./assets/audio/player/sword-attack.wav");

		playerWalk.setVolume50();
		playerHurt.setVolume(10);

		// Coin
		coinCollected = new AudioPlayer("./assets/audio/coin/coin-flip.wav");

		// Chest
		chestOpen = new AudioPlayer("./assets/audio/chest/chest-lock.wav");
		chestOpen.setVolume25();

		System.out.println("[*] Loaded.");
	}

	public void close() {
		playerWalk.stop();
		playerHurt.stop();
		playerSwordAttack.stop();
		coinCollected.stop();
	}
}
