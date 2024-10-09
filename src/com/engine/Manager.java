package com.engine;

public class Manager {

	private static Processor gameProcessor;

	public void stop() {
		gameProcessor.stopGame();
	}

	public void update() {
		gameProcessor.getGameInput().update();
		gameProcessor.update();
	}

	public void render() {
		gameProcessor.getGameDisplay().repaint();
	}

	public static void main(String[] args) {
		gameProcessor = new Processor(new Manager());
		gameProcessor.startGame();
	}
}
