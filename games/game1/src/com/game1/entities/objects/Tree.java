package com.game1.entities.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.engine.objects.GameObject;
import com.engine.objects.components.Collidable;
import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;
import com.engine.objects.hitbox.HitBox;
import com.engine.objects.sprite.ImageSprite;
import com.game1.sheets.GameSprites;

public class Tree extends GameObject implements Updatable, Renderable, Collidable {

	public Tree() {
		defaultSprite = new ImageSprite(GameSprites.tree);
		hitBox = new HitBox(Color.RED, defaultSprite.getWidth(), defaultSprite.getHeight(), -30, 0, 0, 0);
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		defaultSprite.render(g);
	}

	@Override
	public void onCollisionWith(GameObject gameObject) {
	}
}
