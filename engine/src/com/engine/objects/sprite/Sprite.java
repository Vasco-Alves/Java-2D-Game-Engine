package com.engine.objects.sprite;

import com.engine.objects.components.Renderable;

/**
 * Represents a sprite for rendering.
 */
public abstract class Sprite implements Renderable {

	public int id;

	protected double x, y;
	protected int width, height;

	public Sprite() {
	}

	public Sprite(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/* GETTERS AND SETTERS */

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
