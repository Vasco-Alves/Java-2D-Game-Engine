package com.game1.sheets;

import java.awt.image.BufferedImage;

import com.engine.objects.sprite.ImageSprite;
import com.engine.objects.sprite.SpriteSheet;

/**
 * The GameSprites class initializes all game sprites.
 */

public class GameSprites {

	public static final int DEFAULT_WIDTH = 16;
	public static final int DEFAULT_HEIGHT = 16;
	public static int scale;

	/* Mobs */

	// Player
	public static ImageSprite playerNorth;
	public static ImageSprite playerSouth;
	public static ImageSprite playerWest;
	public static ImageSprite playerEast;

	public static ImageSprite[] playerWalkNorth;
	public static ImageSprite[] playerWalkSouth;
	public static ImageSprite[] playerWalkWest;
	public static ImageSprite[] playerWalkEast;

	public static ImageSprite[] playerAttackNorth;
	public static ImageSprite[] playerAttackSouth;
	public static ImageSprite[] playerAttackWest;
	public static ImageSprite[] playerAttackEast;

	// Skeleton
	public static ImageSprite skeletonNorth;
	public static ImageSprite skeletonSouth;
	public static ImageSprite skeletonWest;
	public static ImageSprite skeletonEast;

	public static ImageSprite[] skeletonWalkNorth;
	public static ImageSprite[] skeletonWalkSouth;
	public static ImageSprite[] skeletonWalkWest;
	public static ImageSprite[] skeletonWalkEast;

	public static ImageSprite[] skeletonAttackNorth;
	public static ImageSprite[] skeletonAttackSouth;
	public static ImageSprite[] skeletonAttackWest;
	public static ImageSprite[] skeletonAttackkEast;

	// Slime
	public static ImageSprite slimeNorth;
	public static ImageSprite slimeSouth;
	public static ImageSprite slimeWest;
	public static ImageSprite slimeEast;

	// Zombie
	public static ImageSprite zombieNorth;
	public static ImageSprite zombieSouth;
	public static ImageSprite zombieWest;
	public static ImageSprite zombieEast;

	public static ImageSprite[] zombieWalkNorth;
	public static ImageSprite[] zombieWalkSouth;
	public static ImageSprite[] zombieWalkWest;
	public static ImageSprite[] zombieWalkEast;

	public static ImageSprite[] zombieAttackNorth;
	public static ImageSprite[] zombieAttackSouth;
	public static ImageSprite[] zombieAttackWest;
	public static ImageSprite[] zombieAttackkEast;

	// Nature
	public static ImageSprite tree;
	public static ImageSprite rock;
	public static ImageSprite bush;
	public static ImageSprite tallTree;

	public static ImageSprite blossomTree;

	public static ImageSprite goldCoin;
	public static ImageSprite greyCoin;

	// Objects
	public static ImageSprite chest;
	public static ImageSprite openChest;

	// World
	public static ImageSprite[] worldSprites;

	public static enum WorldSprites {
		GRASS_TILE_0, GRASS_TILE_1
	}

	public GameSprites() {
		scale = 3;
		initPlayerSprites();
		initEntitiesSprites();
		initNatureSprites();
		initObjectsSprites();
		initWorldSprites();
	}

	private void initPlayerSprites() {
		System.out.println("[*] Loading Player sprites.");

		int w = DEFAULT_WIDTH;
		int h = DEFAULT_HEIGHT * 2;

		// Idle - Image
		playerNorth = new ImageSprite(GameSheets.player.takeSubImage(0 * w, 2 * h, w, h), scale);
		playerSouth = new ImageSprite(GameSheets.player.takeSubImage(0 * w, 0 * h, w, h), scale);
		playerWest = new ImageSprite(GameSheets.player.takeSubImage(0 * w, 3 * h, w, h), scale);
		playerEast = new ImageSprite(GameSheets.player.takeSubImage(0 * w, 1 * h, w, h), scale);

		// Walk && Attack - Animation
		int frameCount = 4;

		playerWalkNorth = new ImageSprite[frameCount];
		playerWalkSouth = new ImageSprite[frameCount];
		playerWalkWest = new ImageSprite[frameCount];
		playerWalkEast = new ImageSprite[frameCount];

		playerAttackNorth = new ImageSprite[frameCount];
		playerAttackSouth = new ImageSprite[frameCount];
		playerAttackWest = new ImageSprite[frameCount];
		playerAttackEast = new ImageSprite[frameCount];

		for (int i = 0; i < frameCount; i++) {
			playerWalkSouth[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w, 0 * h, w, h), scale);
			playerWalkEast[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w, 1 * h, w, h), scale);
			playerWalkNorth[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w, 2 * h, w, h), scale);
			playerWalkWest[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w, 3 * h, w, h), scale);

			playerAttackNorth[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w * 2, 5 * h, w * 2, h), scale);
			playerAttackSouth[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w * 2, 4 * h, w * 2, h), scale);
			playerAttackWest[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w * 2, 7 * h, w * 2, h), scale);
			playerAttackEast[i] = new ImageSprite(GameSheets.player.takeSubImage(i * w * 2, 6 * h, w * 2, h), scale);

			playerAttackNorth[i].setOffsetX(20);
			playerAttackSouth[i].setOffsetX(20);
			playerAttackWest[i].setOffsetX(20);
			playerAttackEast[i].setOffsetX(20);
		}

		System.out.println("[*] Loaded.");
	}

	private void initEntitiesSprites() {
		System.out.println("[*] Loading Entities sprites.");

		int w = DEFAULT_WIDTH;
		int h = DEFAULT_HEIGHT * 2;

		skeletonSouth = new ImageSprite(GameSheets.skeleton.takeSubImage(0 * w, 0 * h, w, h), scale);

		System.out.println("[*] Loaded.");
	}

	private void initNatureSprites() {
		System.out.println("[*] Loading Nature sprites.");

		int w = DEFAULT_WIDTH;
		int h = DEFAULT_HEIGHT;

		// TODO
		tree = new ImageSprite(GameSheets.objects.takeSubImage(16 * w, 12 * h, w * 2, h * 2), scale);
		rock = null;
		bush = null;
		tallTree = null;

		System.out.println("[*] Loaded.");
	}

	private void initObjectsSprites() {
		System.out.println("[*] Loading Objects Sprites.");

		// Chest
		chest = new ImageSprite(GameSheets.objects.takeSubImage(0, 0, 14, 14), scale);
		openChest = new ImageSprite(GameSheets.objects.takeSubImage(16, 0, 14, 14), scale);

		// Coin
		SpriteSheet c = GameSheets.coins;
		goldCoin = new ImageSprite(c.takeSubImage(0, 0, c.getWidth(), 16), 1);
		greyCoin = new ImageSprite(c.takeSubImage(0, 16, c.getWidth(), 16), 1);

		System.out.println("[*] Loaded.");
	}

	private void initWorldSprites() {
		System.out.println("[*] Loading World sprites.");

		int rows = GameSheets.world.getHeight() / DEFAULT_HEIGHT;
		int cols = GameSheets.world.getWidth() / DEFAULT_WIDTH;

		worldSprites = new ImageSprite[rows * cols];

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				int xx = x * DEFAULT_WIDTH;
				int yy = y * DEFAULT_HEIGHT;

				BufferedImage img = GameSheets.world.takeSubImage(xx, yy, DEFAULT_WIDTH, DEFAULT_HEIGHT);
				worldSprites[y * cols + x] = new ImageSprite(img, 3);
			}
		}

		System.out.println("[*] Loaded.");
	}
}
