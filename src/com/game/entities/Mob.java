package com.game.entities;

import java.awt.Graphics;

import com.engine.objects.GameObject;
import com.engine.objects.animation.Animation;
import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;
import com.engine.objects.sprite.Sprite;

class AnimationGroup {
	Animation north;
	Animation south;
	Animation west;
	Animation east;
}

/**
 * An abstract class representing mobile game objects. Extends the base
 * GameObject class.
 */
public abstract class Mob extends GameObject implements Updatable, Renderable {

	public static enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	public static enum ActionState {
		IDLE, WALKING, RUNNING, ATTACKING
	}

	protected Direction lookDirection;
	protected ActionState actionState;

	// Collision states
	protected boolean isCollidingTop;
	protected boolean isCollidingBottom;
	protected boolean isCollidingLeft;
	protected boolean isCollidingRight;

	// Movement parameters
	protected double movementSpeed;
	protected double topSpeed;
	protected double jumpForce;

	protected Sprite idleSpriteNorth;
	protected Sprite idleSpriteSouth;
	protected Sprite idleSpriteWest;
	protected Sprite idleSpriteEast;

	// Animations
	protected AnimationGroup idleAnimations;
	protected AnimationGroup walkAnimations;
	protected AnimationGroup attackAnimations;

	protected Sprite currentSprite;
	protected Animation currentAnimation;

	// Game parameters
	protected float maxHealth;
	protected float health;

	public Mob() {
		actionState = ActionState.IDLE;
		lookDirection = Direction.SOUTH;

		idleAnimations = new AnimationGroup();
		walkAnimations = new AnimationGroup();
		attackAnimations = new AnimationGroup();

		resetStates();
	}

	public void resetStates() {
		isCollidingTop = false;
		isCollidingBottom = false;
		isCollidingLeft = false;
		isCollidingRight = false;

		if (actionState != ActionState.ATTACKING)
			actionState = ActionState.IDLE;
	}

	protected abstract void move();

	public abstract void renderDebug(Graphics g);

	public abstract void renderUI(Graphics g);

	protected void updateCollisionSide(GameObject gameObject) {
		double mobLeft = getX();
		double mobRight = getRightX();
		double mobTop = getY();
		double mobBottom = getBottomY();

		double entityLeft = gameObject.getX();
		double entityRight = gameObject.getRightX();
		double entityTop = gameObject.getY();
		double entityBottom = gameObject.getBottomY();

		double overlapLeft = mobRight - entityLeft;
		double overlapRight = entityRight - mobLeft;
		double overlapTop = mobBottom - entityTop;
		double overlapBottom = entityBottom - mobTop;

		double minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));

		if (minOverlap == overlapLeft)
			isCollidingRight = true;
		else if (minOverlap == overlapRight)
			isCollidingLeft = true;
		else if (minOverlap == overlapTop)
			isCollidingBottom = true;
		else if (minOverlap == overlapBottom)
			isCollidingTop = true;
	}

	public void takeDamage(double damage) {
		health -= damage;
		if (health <= 0) {
//			isEnabled = false;
			health = 0;
		}
	}

	public abstract void close();

	/* GETTERS AND SETTERS */

	@Override
	public void setX(double x) {
		super.setX(x);
		defaultSprite.setX(x);
	}

	@Override
	public void setY(double y) {
		super.setY(y);
		defaultSprite.setY(y);
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public boolean isCollidingUp() {
		return isCollidingTop;
	}

	public void setCollidingTop(boolean isCollidingTop) {
		this.isCollidingTop = isCollidingTop;
	}

	public boolean isCollidingBottom() {
		return isCollidingBottom;
	}

	public void setCollidingDown(boolean isCollidingBottom) {
		this.isCollidingBottom = isCollidingBottom;
	}

	public boolean isCollidingLeft() {
		return isCollidingLeft;
	}

	public void setCollidingLeft(boolean isCollidingLeft) {
		this.isCollidingLeft = isCollidingLeft;
	}

	public boolean isCollidingRight() {
		return isCollidingRight;
	}

	public void setCollidingRight(boolean isCollidingRight) {
		this.isCollidingRight = isCollidingRight;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public ActionState getActionState() {
		return actionState;
	}

	public void setActionState(ActionState actionState) {
		this.actionState = actionState;
	}
}
