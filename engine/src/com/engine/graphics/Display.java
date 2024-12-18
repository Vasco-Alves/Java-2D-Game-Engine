package com.engine.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.game1.managers.GameProcessor;

/* Game Display - Renders everything on the Display */

@SuppressWarnings("serial")
public class Display extends JPanel {

	public static int WIDTH = 2100;
	public static int HEIGHT = WIDTH / 16 * 9;

	private GameProcessor gameProcessor;

	public static Color backgroundColor;

	public Display(GameProcessor gameProcessor) {
		this.gameProcessor = gameProcessor;
		backgroundColor = Color.BLACK;

		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(backgroundColor);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gameProcessor.render(g);
	}
}
