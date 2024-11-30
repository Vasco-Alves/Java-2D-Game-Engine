package com.engine.objects.sprite;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A simple sprite with a solid color representation.
 */
public class ColorSprite extends Sprite {

	private Color color;

	public ColorSprite(Color color) {
		super();
		this.color = color;
	}

	public ColorSprite(Color color, int width, int height) {
		super(width, height);
		this.color = color;
	}

	public ColorSprite(ColorSprite other) {
		super(other.width, other.height);
		this.color = other.color;

	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
	}

	/* GETTERS AND SETTERS */

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
