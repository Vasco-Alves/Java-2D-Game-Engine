package com.engine.objects.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The ImageSprite class represents a sprite loaded from an image file.
 */
public class ImageSprite extends Sprite {

	private BufferedImage image;

	private double offsetX, offsetY;

	public ImageSprite(BufferedImage image, int scale) {
		super(image.getWidth() * scale, image.getHeight() * scale);
		this.image = image;
	}

	public ImageSprite(ImageSprite other) {
		super(other.width, other.height);
		this.image = other.image;
		this.offsetX = other.offsetX;
		this.offsetY = other.offsetY;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int) (x - offsetX), (int) (y - offsetY), width, height, null);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetX() {
		return offsetX;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

	public double getOffsetY() {
		return offsetY;
	}
}
