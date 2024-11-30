package com.game1.entities;

import java.awt.Color;

import com.engine.graphics.Display;
import com.engine.objects.animation.Animation;
import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.ImageSprite;
import com.game1.sheets.GameSprites;
import com.game1.ui.PlayerInventoryUI;
import com.game1.ui.PlayerUI;

public class PlayerBuilder implements ObjectBuilder {

	Player player;

	@Override
	public void reset() {
		player = new Player();
	}

	@Override
	public void initSprites() {
		player.idleSpriteNorth = new ImageSprite(GameSprites.playerNorth);
		player.idleSpriteSouth = new ImageSprite(GameSprites.playerSouth);
		player.idleSpriteWest = new ImageSprite(GameSprites.playerWest);
		player.idleSpriteEast = new ImageSprite(GameSprites.playerEast);

		player.setDefaultSprite(player.idleSpriteSouth);
		player.currentSprite = player.getDefaultSprite();
	}

	@Override
	public void initHitBox() {
		player.setHitBox(new HitBox(Color.RED, player.getDefaultSprite().getWidth(),
				player.getDefaultSprite().getHeight(), -50, -18, -7, -7));
	}

	@Override
	public void initAnimations() {
		int delay = 10;

		player.idleAnimations.north = null;
		player.idleAnimations.south = null;
		player.idleAnimations.west = null;
		player.idleAnimations.east = null;

		player.walkAnimations.north = new Animation(delay, GameSprites.playerWalkNorth);
		player.walkAnimations.south = new Animation(delay, GameSprites.playerWalkSouth);
		player.walkAnimations.west = new Animation(delay, GameSprites.playerWalkWest);
		player.walkAnimations.east = new Animation(delay, GameSprites.playerWalkEast);

		delay = 4;
		player.attackAnimations.north = new Animation(delay, GameSprites.playerAttackNorth);
		player.attackAnimations.south = new Animation(delay, GameSprites.playerAttackSouth);
		player.attackAnimations.west = new Animation(delay, GameSprites.playerAttackWest);
		player.attackAnimations.east = new Animation(delay, GameSprites.playerAttackEast);

		player.currentAnimation = player.walkAnimations.south;
	}

	@Override
	public void initUi() {
		player.playerUI = new PlayerUI(player, 10, 10, 275, 70, Color.DARK_GRAY);
		player.inventoryUI = new PlayerInventoryUI(player, Display.WIDTH / 2 - 250, Display.HEIGHT - 60, 500, 50,
				Color.DARK_GRAY);
	}

	@Override
	public void finish() {
	}

	@Override
	public Player getObject() {
		Player p = player;
		reset();
		return p;
	}
}
