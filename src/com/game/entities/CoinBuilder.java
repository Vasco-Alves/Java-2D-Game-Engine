package com.game.entities;

import java.awt.Color;

import com.engine.objects.GameObject;
import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.ImageSprite;
import com.game.GameSprites;
import com.game.entities.objects.Coin;

public class CoinBuilder implements ObjectBuilder {

	private int value;
	private String color;

	Coin coin;

	@Override
	public void reset() {
		coin = new Coin();
		value = 10;
		color = "gold";
	}

	@Override
	public void initSprites() {
		switch (color) {
		case "grey":
			coin.setDefaultSprite(new ImageSprite(GameSprites.greyCoin));
			break;

		default:
			coin.setDefaultSprite(new ImageSprite(GameSprites.goldCoin));
			break;
		}
	}

	@Override
	public void initHitBox() {
		ImageSprite sprite = (ImageSprite) coin.getDefaultSprite();
		coin.setHitBox(new HitBox(Color.RED, sprite.getWidth(), sprite.getHeight(), 0, 0, 0, 0));
	}

	@Override
	public void initAnimations() {
	}

	@Override
	public void initUi() {
	}

	@Override
	public void finish() {
		coin.setValue(value);
	}

	/* GETTERS AND SETTERS */

	@Override
	public GameObject getObject() {
		Coin c = coin;
		reset();
		return c;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
