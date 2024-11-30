package com.game1.entities.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.objects.GameObject;
import com.engine.objects.animation.Animation;
import com.engine.objects.components.Collidable;
import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;
import com.engine.objects.sprite.ImageSprite;
import com.game1.audio.GameAudio;
import com.game1.entities.Player;

public class Coin extends GameObject implements Updatable, Renderable, Collidable {

	private Animation animation;

	// Animation parameters
	private double initialY;
	private int offsetY = 0;
	private final double frequency = 0.005;

	private int value;

	@Override
	public void update() {
		offsetY = (int) (2 * Math.sin(System.currentTimeMillis() * frequency));
	}

	@Override
	public void render(Graphics g) {
		int w = defaultSprite.getWidth();
		int h = defaultSprite.getHeight();
		BufferedImage img = ((ImageSprite) defaultSprite).getImage();
		g.drawImage(img, (int) x, (int) (initialY + offsetY), w, h, null);
	}

	@Override
	public void onCollisionWith(GameObject gameObject) {
		if (gameObject instanceof Player) {
			isEnabled = false;
			GameAudio.coinCollected.play();
		}
	}

	/* GETTERS AND SETTERS */

	@Override
	public void setY(double y) {
		super.setY(y);
		initialY = y;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}