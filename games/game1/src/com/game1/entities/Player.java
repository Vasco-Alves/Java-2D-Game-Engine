package com.game1.entities;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.engine.graphics.ui.UIComponent;
import com.engine.input.Input;
import com.engine.objects.GameObject;
import com.engine.objects.components.Collidable;
import com.game1.audio.GameAudio;
import com.game1.config.GameConfig;
import com.game1.entities.enemies.Skeleton;
import com.game1.entities.objects.Chest;
import com.game1.entities.objects.Coin;
import com.game1.entities.objects.Tree;
import com.game1.inventory.Inventory;
import com.game1.inventory.Wallet;
import com.game1.ui.PlayerInventoryUI;
import com.game1.ui.PlayerUI;

public class Player extends Mob implements Collidable {

	public static final float DEFAULT_HEALTH = 100.0f;
	public static final float DEFAULT_STAMINA = 100.0f;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final float DEFAULT_STAMINA_DAMAGE = 0.5f;
	public static final int INVENTORY_CAPACITY = 25;

	// Stat parameters
	protected float stamina;
	protected float maxStamina;
	private float staminaDamage;

	// State parameters
	public boolean canMove;
	public boolean insideCamera;
	private boolean isAttacking;
	private boolean attackButtonPressed;
	private boolean onInventory;

	private ScheduledExecutorService scheduler;

	private Inventory inventory;
	private Wallet wallet;

	// UI components
	protected PlayerUI playerUI;
	protected PlayerInventoryUI inventoryUI;

	public Player() {
		super();
		maxHealth = health = DEFAULT_HEALTH;
		maxStamina = stamina = DEFAULT_STAMINA;
		staminaDamage = DEFAULT_STAMINA_DAMAGE;
		movementSpeed = DEFAULT_SPEED;
		canMove = true;
		isAttacking = false;
		attackButtonPressed = false;
		scheduler = Executors.newScheduledThreadPool(1);
		inventory = new Inventory(INVENTORY_CAPACITY);
		wallet = new Wallet();
	}

	private void checkRunning() {
		if (Input.shift && stamina > 0 && actionState == ActionState.WALKING) {
			movementSpeed = Player.DEFAULT_SPEED * 1.5;
			actionState = ActionState.RUNNING;
			stamina -= staminaDamage;
		}

		if (!Input.shift || actionState == ActionState.IDLE)
			stamina += staminaDamage;

		stamina = Math.clamp(stamina, 0, maxStamina);
	}

	@Override
	public void resetStates() {
		super.resetStates();
		movementSpeed = DEFAULT_SPEED;
	}

	@Override
	public void move() {
		if (!insideCamera || !canMove)
			return;

		double combinedSpeed = Math.sqrt(Input.xAxis * Input.xAxis + Input.yAxis * Input.yAxis);
		if (combinedSpeed > 1.0) {
			Input.xAxis /= combinedSpeed;
			Input.yAxis /= combinedSpeed;
		}

		if ((Input.left && !isCollidingLeft) || (Input.right && !isCollidingRight))
			x += Input.xAxis * movementSpeed;

		if ((Input.up && !isCollidingTop) || (Input.down && !isCollidingBottom))
			y += Input.yAxis * movementSpeed;

		setX(x);
		setY(y);
	}

	private void updateStates() {
		if (isAttacking)
			return;

		// Walk
		if (Input.up || Input.down || Input.left || Input.right)
			actionState = ActionState.WALKING;

		// Run
		checkRunning();

		// Attack
		if (Input.space && !attackButtonPressed) {
			actionState = ActionState.ATTACKING;
		}
	}

	private void updateUI() {
		playerUI.update();
	}

	private void updateLookDirection() {
		if (Input.left)
			lookDirection = Direction.WEST;

		if (Input.right)
			lookDirection = Direction.EAST;

		if (Input.up)
			lookDirection = Direction.NORTH;

		if (Input.down)
			lookDirection = Direction.SOUTH;
	}

	private void updateGraphics() {
		currentSprite.setX(x);
		currentSprite.setY(y);
		currentAnimation.update(x, y);
	}

	private void updateCurrentAnimation(AnimationGroup group) {
		switch (lookDirection) {
		case NORTH:
			currentSprite = idleSpriteNorth;
			currentAnimation = group.north;
			break;
		case SOUTH:
			currentSprite = idleSpriteSouth;
			currentAnimation = group.south;
			break;
		case WEST:
			currentSprite = idleSpriteWest;
			currentAnimation = group.west;
			break;
		case EAST:
			currentSprite = idleSpriteEast;
			currentAnimation = group.east;
			break;
		}
	}

	@Override
	public void update() {
		updateLookDirection();
		updateStates();
		applyState();
		updateUI();
		updateGraphics();
		move();

		if (!Input.space)
			attackButtonPressed = false;
	}

	@Override
	public void renderDebug(Graphics g) {
		hitBox.render(g);
	}

	@Override
	public void renderUI(Graphics g) {
		playerUI.render(g);
		inventoryUI.render(g);
	}

	@Override
	public void render(Graphics g) {
		if (actionState == ActionState.IDLE)
			currentSprite.render(g);
		else
			currentAnimation.render(g);

//		renderDebug(g);
	}

	private void applyState() {
		switch (actionState) {
		case RUNNING:
		case WALKING:
			GameAudio.playerWalk.play();
			updateCurrentAnimation(walkAnimations);
			break;

		case ATTACKING:
			GameAudio.playerWalk.stop();
			GameAudio.playerSwordAttack.play();
			updateCurrentAnimation(attackAnimations);
			attack();
			break;

		default:
			GameAudio.playerWalk.stop();
			currentAnimation.reset();
			break;
		}

		if (!isAttacking && !Input.space)
			attackButtonPressed = false;
	}

	@Override
	public void onCollisionWith(GameObject gameObject) {
		updateCollisionSide(gameObject);

		if (gameObject instanceof Skeleton) {
		} else if (gameObject instanceof Tree) {
		} else if (gameObject instanceof Chest) {
		} else if (gameObject instanceof Coin) {
			wallet.addCoins(((Coin) gameObject).getValue());
		}
	}

	private void attack() {
		if (isAttacking || attackButtonPressed)
			return;

		isAttacking = true;
		attackButtonPressed = true;

		// Convert frames to milliseconds
		long attackDuration = (long) (currentAnimation.getTotalDuration() * 1000L / GameConfig.N_TICKS);

		scheduler.schedule(() -> {
			isAttacking = false;
			setActionState(ActionState.IDLE);
		}, attackDuration, TimeUnit.MILLISECONDS);
	}

	@Override
	public void takeDamage(double damage) {
		GameAudio.playerHurt.play();
		super.takeDamage(damage);
	}

	@Override
	public void close() {
		GameAudio.playerWalk.close();
		GameAudio.playerHurt.close();

		if (scheduler != null && !scheduler.isShutdown())
			scheduler.shutdown();
	}

	/* GETTERS AND SETTERS */

	public UIComponent getHealthUI() {
		return playerUI;
	}

	public UIComponent getInventoryUI() {
		return inventoryUI;
	}

	public float getStamina() {
		return stamina;
	}

	public void setStamina(float stamina) {
		this.stamina = stamina;
	}

	public double getMaxStamina() {
		return maxStamina;
	}

	public double getStaminaDamage() {
		return staminaDamage;
	}

	public void setStaminaDamage(float staminaDamage) {
		this.staminaDamage = staminaDamage;
	}
}
