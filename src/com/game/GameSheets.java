package com.game;

import com.engine.objects.sprite.SpriteSheet;

public class GameSheets {

	public static SpriteSheet player;
	public static SpriteSheet skeleton;
	public static SpriteSheet world;
	public static SpriteSheet objects;
	public static SpriteSheet coins;

	public GameSheets() {
		System.out.println("[*] Loading spritesheets.");

		initEntitiesSheets();
		initWorldSheets();
		initObjectsSheets();

		System.out.println("[*] Loaded.");
	}

	private void initEntitiesSheets() {
		player = new SpriteSheet("./assets/sprites/characters/player.png");
		skeleton = new SpriteSheet("./assets/sprites/characters/npc_empty.png");
	}

	private void initObjectsSheets() {
		objects = new SpriteSheet("./assets/sprites/objects/objects.png");
		coins = new SpriteSheet("./assets/sprites/objects/coins.png");
	}

	private void initWorldSheets() {
		world = new SpriteSheet("./assets/sprites/maps/overworld.png");
	}
}
