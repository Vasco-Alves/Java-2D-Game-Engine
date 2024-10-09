package com.game.entities.objects;

import java.awt.Graphics;

import com.engine.input.Input;
import com.engine.objects.GameObject;
import com.engine.objects.components.Collidable;
import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;
import com.engine.objects.sprite.Sprite;
import com.game.GameAudio;
import com.game.entities.Player;

public class Chest extends GameObject implements Updatable, Renderable, Collidable {

	private Sprite currentSprite;
	private Sprite openSprite;
	private boolean open;

	public Chest() {
		open = false;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		currentSprite.render(g);
//		hitBox.render(g);
	}

	@Override
	public void onCollisionWith(GameObject gameObject) {
		if (gameObject instanceof Player && Input.use) {
			open();
		}
	}

	private void open() {
		if (open)
			return;

		open = true;
		currentSprite = openSprite;
		GameAudio.chestOpen.play();
	}

	/* GETTERS AND SETTERS */

	@Override
	public void setX(double x) {
		super.setX(x);
		openSprite.setX(x);
	}

	@Override
	public void setY(double y) {
		super.setY(y);
		openSprite.setY(y);
	}

	public void setOpenSprite(Sprite openSprite) {
		this.openSprite = openSprite;
	}

	public void setCurrentSprite(Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}
}
