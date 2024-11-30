package com.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/* Controls all input by user */

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static double xAxis, yAxis; // Movement Axis

	private final int NUM_KEYS = 120; // Number of keys available
	private boolean[] keys = new boolean[NUM_KEYS]; // List of keys

	// Keyboard inputs
	public static boolean up, down, left, right;
	public static boolean use;
	public static boolean escape;
	public static boolean space;
	public static boolean shift;

	public static boolean play;
	public static boolean stop;

	public static boolean inventory;

	// Mouse inputs
	public static int mouseX, mouseY;

	private int scroll;
	
	public Input() {
	}

	public void update() {
		listenKeyboardInput();
		updateAxis();

//		showInputs();
	}

	public void showInputs() {
		for (int i = 0; i < keys.length; i++)
			if (keys[i])
				System.out.println("Key : " + (char) i);
	}

	public void listenKeyboardInput() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

		use = keys[KeyEvent.VK_E];
		inventory = keys[KeyEvent.VK_I];

		escape = keys[KeyEvent.VK_ESCAPE];
		space = keys[KeyEvent.VK_SPACE];
		shift = keys[KeyEvent.VK_SHIFT];

		play = keys[KeyEvent.VK_P];
		stop = keys[KeyEvent.VK_Q];
	}

	public void updateAxis() {
		yAxis = 0;
		xAxis = 0;

		if (up && !down)
			yAxis = -1;

		if (down && !up)
			yAxis = 1;

		if (right && !left)
			xAxis = 1;

		if (left && !right)
			xAxis = -1;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
//		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
