package com.engine.objects;

import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.Sprite;

/**
 * Represents a game object, which serves as a template for basic entities and
 * tiles in the game.
 */
public abstract class GameObject {

	public boolean isEnabled = true;
	protected double x, y;
	protected Sprite defaultSprite;
	protected HitBox hitBox;

	protected boolean renderHitbox = false;

	public void addPosOffsetXY(double x, double y) {
		setX(this.x - x);
		setY(this.y - y);
	}

	/* GETTERS AND SETTERS */

	public double getX() {
		return hitBox.getX();
	}

	public void setX(double x) {
		this.x = x;
		defaultSprite.setX(x);
		hitBox.setX(x);
	}

	public double getRightX() {
		return hitBox.getX() + hitBox.getWidth();
	}

	public double getY() {
		return hitBox.getY();
	}

	public void setY(double y) {
		this.y = y;
		defaultSprite.setY(y);
		hitBox.setY(y);
	}

	public double getBottomY() {
		return hitBox.getY() + hitBox.getHeight();
	}

	public int getHeight() {
		return hitBox.getHeight();
	}

	public int getWidth() {
		return hitBox.getWidth();
	}

	public Sprite getDefaultSprite() {
		return defaultSprite;
	}

	public void setDefaultSprite(Sprite defaultSprite) {
		this.defaultSprite = defaultSprite;
	}

	public HitBox getHitBox() {
		return hitBox;
	}

	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}

	public double getMiddleX() {
		return hitBox.getX() + hitBox.getWidth() / 2;
	}

	public double getMiddleY() {
		return hitBox.getY() + hitBox.getHeight() / 2;
	}
}
