package com.game1.entities;

import java.awt.Color;

import com.engine.objects.GameObject;
import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.ImageSprite;
import com.game1.entities.enemies.Skeleton;
import com.game1.sheets.GameSprites;
import com.game1.ui.MobUI;

public class SkeletonBuilder implements ObjectBuilder {

	Skeleton skeleton;

	@Override
	public void reset() {
		skeleton = new Skeleton();
	}

	@Override
	public void initSprites() {
		skeleton.idleSpriteSouth = new ImageSprite(GameSprites.skeletonSouth);
		skeleton.setDefaultSprite(skeleton.idleSpriteSouth);
		skeleton.currentSprite = skeleton.getDefaultSprite();
	}

	@Override
	public void initHitBox() {
		ImageSprite sprite = (ImageSprite) skeleton.getDefaultSprite();
		skeleton.setHitBox(new HitBox(Color.RED, sprite.getWidth(), sprite.getHeight(), -45, -18, -7, -7));
	}

	@Override
	public void initAnimations() {
	}

	@Override
	public void initUi() {
		skeleton.setInfoUI(new MobUI(skeleton));
	}

	@Override
	public void finish() {
		skeleton.randomizeTargetPosition();
	}

	@Override
	public GameObject getObject() {
		Skeleton s = skeleton;
		reset();
		return s;
	}
}
