package com.engine;

import java.awt.Graphics;

import com.engine.graphics.Display;
import com.engine.graphics.Frame;
import com.engine.input.Input;
import com.game.GameAudio;
import com.game.GameLevel;
import com.game.GameSheets;
import com.game.GameSprites;
import com.game.config.GameConfig;
import com.game.levels.DebugLevel;

/* Game Processor - Contains all Engine Logic - Updates and renders Game*/

public class Processor implements Runnable {

	// Engine Components
	private Manager gameManager;
	private Frame gameFrame;
	private Display gameDisplay;
	private Input gameInput;
	private Thread gameThread;

	private volatile boolean running;

	// Game Components
	GameAudio gameAudio;
	GameSheets gameSheets;
	GameSprites gameSprites;
	GameLevel gameLevel;

	public Processor(Manager gameManager) {
		initEngine(gameManager);
		initGame();
	}

	private void initEngine(Manager gameManager) {
		System.out.println("[*] Initializing Engine.");

		this.gameManager = gameManager;
		gameDisplay = new Display(this);
		gameFrame = new Frame(gameDisplay);
		gameInput = new Input();

		gameFrame.getFrame().addKeyListener(gameInput);
	}

	private void initGame() {
		System.out.println("[*] Initializing Game.");

		gameAudio = new GameAudio();
		gameSheets = new GameSheets();
		gameSprites = new GameSprites();

//		gameMap = new TestLevel();
		gameLevel = new DebugLevel();
	}

	public void startGame() {
		running = true;
		gameThread = new Thread(this, "Main Game");
		gameThread.start();
	}

	public void stopGame() {
		gameLevel.close();
		gameAudio.close();
		gameFrame.getFrame().dispose();
	}

	public void update() {
		if (gameLevel != null)
			gameLevel.update();
	}

	public void render(Graphics g) {
		if (gameLevel != null)
			gameLevel.render(g);
	}

	// MAIN GAME LOOP
	@Override
	public void run() {
		System.out.println("[*] Starting Game.");

		long timer = System.currentTimeMillis();
		long currentTime;

		double timePerFrame = 1_000_000_000 / GameConfig.N_FPS;
		double timePerTick = 1_000_000_000 / GameConfig.N_TICKS;

		long lastFrame = System.nanoTime();
		long lastTick = System.nanoTime();

		double framesDelta = 0;
		double ticksDelta = 0;

		int frames = 0;
		int ticks = 0;

		gameFrame.getFrame().setTitle(ticks + " ticks, " + frames + " fps");
		gameFrame.getFrame().requestFocus();

		while (running) {
			currentTime = System.nanoTime();
			framesDelta += (currentTime - lastFrame) / timePerTick;
			lastFrame = currentTime;

			if (framesDelta >= 1.0) {
				gameManager.update();
				ticks++;
				framesDelta--;
			}

			currentTime = System.nanoTime();
			ticksDelta += (currentTime - lastTick) / timePerFrame;
			lastTick = currentTime;

			if (ticksDelta >= 1.0) {
				gameManager.render();
				frames++;
				ticksDelta--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				gameFrame.getFrame().setTitle("My 2D Game - " + ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}

			if (Input.escape) {
				System.out.println("[*] Stopping Game.");
				running = false;
			}
		}

		gameManager.stop();
	}

	/* GETTERS AND SETTERS */

	public void setGameManager(Manager gameManager) {
		this.gameManager = gameManager;
	}

	public Frame getGameFrame() {
		return gameFrame;
	}

	public Display getGameDisplay() {
		return gameDisplay;
	}

	public Input getGameInput() {
		return gameInput;
	}
}
