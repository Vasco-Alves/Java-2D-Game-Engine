package com.game1.managers;

public class GameManager {

	private static GameProcessor processor;

	public GameManager() {
		processor = new GameProcessor(this);
	}

	public void start() {
		processor.startGame();
	}

	public void stop() {
		processor.stopGame();
	}

	public void update() {
		processor.getGameInput().update();
		processor.update();
	}

	public void render() {
		processor.getGameDisplay().repaint();
	}
}
