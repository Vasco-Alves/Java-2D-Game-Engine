package com.game1.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.engine.objects.GameObject;
import com.engine.objects.components.Collidable;
import com.game1.Utils.Utils;
import com.game1.entities.Mob;
import com.game1.entities.Mob.ActionState;
import com.game1.entities.Player;
import com.game1.ui.MobUI;

public class Skeleton extends Mob implements Collidable {

	public static final double DEFAULT_SPEED = 2;
	private static final int MOVE_INTERVAL = 5000; // 5 s
	private static final int ATTACK_COOLDOWN = 1000; // 1 s

	private static final double DAMAGE = 20;

	private double targetX, targetY;
	private int viewRadius;

	private long lastMoveTime;
	private long lastAttackTime;

	private boolean isFollowing;
	private boolean canAttack;

	private MobUI infoUI;

	public Skeleton() {
		super();
		movementSpeed = DEFAULT_SPEED;
		viewRadius = 600;
		lastAttackTime = System.currentTimeMillis();
		lastMoveTime = System.currentTimeMillis();
		canAttack = true;
	}

	private void moveTowardsTarget() {
		double dx = targetX - getMiddleX();
		double dy = targetY - getBottomY();
		double distance = Utils.distancebetweenPoints(targetX, targetY, getMiddleX(), getBottomY());

		if (distance <= 10)
			return;

		double speedX = 0;
		double speedY = 0;

		speedY = (dy / distance) * movementSpeed;
		speedX = (dx / distance) * movementSpeed;

		setX(x + speedX);
		setY(y + speedY);

		actionState = ActionState.WALKING;
	}

	@Override
	protected void move() {
		moveTowardsTarget();
	}

	@Override
	public void resetStates() {
		super.resetStates();
		isFollowing = false;
	}

	private void updateLookDirection() {
	}

	private void updateLastMoveTime(long currentTime) {
		if (isFollowing)
			return;

		if (currentTime - lastMoveTime >= MOVE_INTERVAL) {
			randomizeTargetPosition();
			lastMoveTime = currentTime;
		}
	}

	private void updateLastAttackTime(long currentTime) {
		if (currentTime - lastAttackTime >= ATTACK_COOLDOWN)
			canAttack = true;
	}

	@Override
	public void update() {
		long currentTime = System.currentTimeMillis();

		updateLastMoveTime(currentTime);
		updateLastAttackTime(currentTime);
		move();
		updateLookDirection();
	}

	public void randomizeTargetPosition() {
		Random random = new Random();
		double angle = random.nextDouble() * 2 * Math.PI; // Random angle in radians
		int radius = random.nextInt() % (viewRadius / 2); // Random radius within the maximum radius

		// Calculate the target position using polar to Cartesian conversion
		targetX = getMiddleX() + radius * Math.cos(angle);
		targetY = getBottomY() + radius * Math.sin(angle);
	}

	@Override
	public void renderDebug(Graphics g) {
		if (renderHitbox)
			hitBox.render(g);

		// View Radius
		g.setColor(Color.RED);
		int x = ((int) getMiddleX() - viewRadius / 2);
		int y = ((int) getBottomY() - viewRadius / 2);
		g.drawOval(x, y, viewRadius, viewRadius);

		// Target
		g.fillOval((int) targetX - 2, (int) targetY - 2, 5, 5);
		g.drawLine((int) getMiddleX(), (int) getBottomY(), (int) targetX, (int) targetY);
		hitBox.render(g);
	}

	@Override
	public void renderUI(Graphics g) {
		if (health < maxHealth)
			infoUI.render(g);
	}

	@Override
	public void render(Graphics g) {
		currentSprite.render(g);
//		renderDebug(g);
		renderUI(g);
	}

	// TODO maybe change parameter to mob and check its instance to follow or not
	public boolean isWithinViewRange(double x, double y) {
		double distance = Utils.distancebetweenPoints(x, y, getMiddleX(), getBottomY());
		return distance <= viewRadius / 2;
	}

	public void followMob(Mob mob) {
		double x = mob.getMiddleX();
		double y = mob.getBottomY();

		if (!isWithinViewRange(x, y))
			return;

		isFollowing = true;
		setTargetX(x);
		setTargetY(y);
	}

	@Override
	public void addPosOffsetXY(double x, double y) {
		super.addPosOffsetXY(x, y);
		targetX -= x;
		targetY -= y;
	}

	@Override
	public void onCollisionWith(GameObject gameObject) {
//		double distance = Utils.distanceBetweenObjets(this, gameObject);
//		if (distance > 30)
//			return;

		updateCollisionSide(gameObject);

		if (gameObject instanceof Player)
			attack((Mob) gameObject);

		else if (gameObject instanceof Skeleton)
			Utils.separateObjects(this, gameObject);
	}

	private void attack(Mob mob) {
		if (!canAttack)
			return;

		actionState = ActionState.ATTACKING;
		mob.takeDamage(DAMAGE);
		canAttack = false;
		lastAttackTime = System.currentTimeMillis();
	}

	@Override
	public void takeDamage(double damage) {
		super.takeDamage(damage);
	}

	@Override
	public void close() {
	}

	/* GETTERS AND SETTERS */

	@Override
	public void setY(double y) {
		super.setY(y);
		hitBox.setY(y - 18);
	}

	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}

	public void setTargetY(double targetY) {
		this.targetY = targetY;
	}

	public MobUI getInfoUI() {
		return infoUI;
	}

	public void setInfoUI(MobUI infoUI) {
		this.infoUI = infoUI;
	}
}
