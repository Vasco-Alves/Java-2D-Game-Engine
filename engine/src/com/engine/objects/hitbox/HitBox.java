package com.engine.objects.hitbox;

import java.awt.Color;
import java.awt.Graphics;

import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;

public class HitBox implements Updatable, Renderable {

	private double x, y;
	private int width, height;
	private int topOffset, bottomOffset, leftOffset, rightOffset;

	private Color color;

	public HitBox(Color color, int width, int height) {
		this.color = color;

		setX(0);
		setY(0);

		setWidth(width);
		setHeight(height);
	}

	public HitBox(Color color, int width, int height, int topOffset, int bottomOffset, int leftOffset,
			int rightOffset) {
		this.color = color;
		this.topOffset = topOffset;
		this.bottomOffset = bottomOffset;
		this.leftOffset = leftOffset;
		this.rightOffset = rightOffset;

		setX(0);
		setY(0);

		setWidth(width);
		setHeight(height);
	}

	@Override
	public void update() {
	}

	public void update(double x, double y) {
		setX(x);
		setY(y);
		update();
	}

	public void update(double x, double y, int width, int height) {
		update(x, y);
		setWidth(width);
		setHeight(height);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.drawRect((int) x, (int) y, width - 1, height - 1);
	}

	/* GETTERS AND SETTERS */

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x - leftOffset;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y - topOffset;
	}

	public int getTopOffset() {
		return topOffset;
	}

	public void setTopOffset(int topOffset) {
		this.topOffset = topOffset;
	}

	public int getBottomOffset() {
		return bottomOffset;
	}

	public void setBottomOffset(int bottomOffset) {
		this.bottomOffset = bottomOffset;
	}

	public int getLeftOffset() {
		return leftOffset;
	}

	public void setLeftOffset(int leftOffset) {
		this.leftOffset = leftOffset;
	}

	public int getRightOffset() {
		return rightOffset;
	}

	public void setRightOffset(int rightOffset) {
		this.rightOffset = rightOffset;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height + topOffset + bottomOffset;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width + leftOffset + rightOffset;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}

//package com.engine.objects.hitbox;
//
//import java.awt.Color;
//import java.awt.Graphics;
//
//import com.engine.objects.components.Renderable;
//import com.engine.objects.components.Updatable;
//
//public class HitBox implements Updatable, Renderable {
//
//	private double x, y;
//
//	private int width, height;
//	private int xOffset, yOffset;
//
//	private int topOffset, bottomOffset, leftOffset, rightOffset;
//
//	private Color color;
//	private boolean isSolid, isCollisioning;
//
//	public HitBox(Color color, int w, int h, int xOffset, int yOffset) {
//		this.color = color;
//		this.xOffset = xOffset;
//		this.yOffset = yOffset;
//
//		setX(0);
//		setY(0);
//
//		setWidth(w);
//		setHeight(h);
//
//	}
//
//	@Override
//	public void update() {
//	}
//
//	public void update(double x, double y) {
//		setX(x);
//		setY(y);
//		update();
//	}
//
//	public void update(double x, double y, int w, int h) {
//		update(x, y);
//		setWidth(w);
//		setHeight(h);
//	}
//
//	@Override
//	public void render(Graphics g) {
//		g.setColor(color);
//		g.drawRect((int) x, (int) y, width - 1, height - 1);
//	}
//
//	/* GETTERS AND SETTERS */
//
//	public double getX() {
//		return x;
//	}
//
//	public void setX(double x) {
//		this.x = x - xOffset;
//	}
//
//	public double getY() {
//		return y;
//	}
//
//	public void setY(double y) {
//		this.y = y - yOffset;
//	}
//
//	public boolean isSolid() {
//		return isSolid;
//	}
//
//	public void setSolid(boolean isSolid) {
//		this.isSolid = isSolid;
//	}
//
//	public boolean isCollisioning() {
//		return isCollisioning;
//	}
//
//	public void setCollisioning(boolean isCollisioning) {
//		this.isCollisioning = isCollisioning;
//	}
//
//	public int getXOffset() {
//		return xOffset;
//	}
//
//	public void setxOffset(int xOffset) {
//		this.xOffset = xOffset;
//	}
//
//	public void setyOffset(int yOffset) {
//		this.yOffset = yOffset;
//	}
//
//	public int getYOffset() {
//		return yOffset;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
//	public void setHeight(int height) {
//		this.height = height + yOffset * 2;
//	}
//
//	public int getWidth() {
//		return width;
//	}
//
//	public void setWidth(int width) {
//		this.width = width + xOffset * 2;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}
//}
