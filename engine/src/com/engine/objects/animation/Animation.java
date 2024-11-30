package com.engine.objects.animation;

import java.awt.Graphics;

import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;
import com.engine.objects.sprite.ImageSprite;

public class Animation implements Updatable, Renderable {

	private int frameDelay; // Delay between frames
	private int currentFrame; // Index of the current frame
	private long frameTick;

	private ImageSprite[] frames; // Array of sprites for the animation

	public Animation(int frameDelay, ImageSprite[] sprites) {
		this.frameDelay = frameDelay;
		this.currentFrame = 0;

		frames = new ImageSprite[sprites.length];
		for (int i = 0; i < sprites.length; i++)
			frames[i] = new ImageSprite(sprites[i]);
	}

	@Override
	public void update() {
		if (frameTick++ >= frameDelay) {
			frameTick = 0;
			currentFrame = (currentFrame + 1) % frames.length;
		}
	}

	public void update(double x, double y) {
		for (ImageSprite sprite : frames) {
			sprite.setX(x);
			sprite.setY(y);
		}
		update();
	}

	@Override
	public void render(Graphics g) {
		frames[currentFrame].render(g);
	}

	public void reset() {
		currentFrame = 0;
	}

	public long getTotalDuration() {
		return frameDelay * frames.length;
	}
}
