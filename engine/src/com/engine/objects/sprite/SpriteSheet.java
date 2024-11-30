package com.engine.objects.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Contains a sprite image to take sub sprites from.
 */
public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(String path) {
		try {
			sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("[X] Spritesheet " + path + " not found.");
		}
	}

	public BufferedImage takeSubImage(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public int getWidth() {
		return sheet.getWidth();
	}

	public int getHeight() {
		return sheet.getHeight();
	}
}
