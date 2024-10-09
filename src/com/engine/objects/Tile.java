package com.engine.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.engine.objects.components.Renderable;
import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.ColorSprite;
import com.engine.objects.sprite.ImageSprite;

public class Tile extends GameObject implements Renderable {

	public Tile(ImageSprite sprite) {
		defaultSprite = new ImageSprite(sprite);
		hitBox = new HitBox(Color.RED, defaultSprite.getWidth(), defaultSprite.getHeight());
	}

	public Tile(ColorSprite sprite) {
		defaultSprite = new ColorSprite(sprite);
		hitBox = new HitBox(Color.RED, defaultSprite.getWidth(), defaultSprite.getHeight());
	}

	@Override
	public void render(Graphics g) {
		defaultSprite.render(g);

		if (renderHitbox)
			hitBox.render(g);
	}

	public void setRenderHitbox(boolean renderHitbox) {
		this.renderHitbox = renderHitbox;
	}
}
