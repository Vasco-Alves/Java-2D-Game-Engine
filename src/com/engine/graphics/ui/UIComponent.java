package com.engine.graphics.ui;

import java.awt.Color;

import com.engine.objects.components.Renderable;

public abstract class UIComponent implements Renderable {

	protected double x, y;
	protected int width, height;
	protected Color bgColor;

	public UIComponent() {
		bgColor = Color.BLACK;
	}

	public UIComponent(int x, int y) {
		this.x = x;
		this.y = y;
		bgColor = Color.BLACK;
	}

	public UIComponent(int x, int y, int w, int h, Color bgColor) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		this.bgColor = bgColor;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public double getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Color getBgColor() {
		return bgColor;
	}
}
