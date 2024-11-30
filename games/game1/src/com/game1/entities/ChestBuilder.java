package com.game1.entities;

import java.awt.Color;

import com.engine.objects.GameObject;
import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.ImageSprite;
import com.game1.entities.objects.Chest;
import com.game1.sheets.GameSprites;

public class ChestBuilder implements ObjectBuilder {

	Chest chest;

	@Override
	public void reset() {
		chest = new Chest();
	}

	@Override
	public void initSprites() {
		chest.setDefaultSprite(new ImageSprite(GameSprites.chest));
		chest.setOpenSprite(new ImageSprite(GameSprites.openChest));
		chest.setCurrentSprite(chest.getDefaultSprite());
	}

	@Override
	public void initHitBox() {
		ImageSprite sprite = (ImageSprite) chest.getDefaultSprite();
		chest.setHitBox(new HitBox(Color.RED, sprite.getWidth(), sprite.getHeight(), -10, 0, 0, 0));
	}

	@Override
	public void initAnimations() {
	}

	@Override
	public void initUi() {
	}

	@Override
	public void finish() {
	}

	@Override
	public GameObject getObject() {
		Chest c = chest;
		reset();
		return c;
	}
}
