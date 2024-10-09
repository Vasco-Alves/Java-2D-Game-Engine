package com.engine.graphics;

import javax.swing.JFrame;

/* Game Frame - Creates the window for the Game to be rendered */

public class Frame {

	private JFrame frame;

	public Frame(Display gameDisplay) {
		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.add(gameDisplay);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}
}
