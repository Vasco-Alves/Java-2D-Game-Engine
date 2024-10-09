package com.game.levels;

import com.engine.graphics.Display;
import com.game.GameLevel;
import com.game.GameSprites;
import com.game.entities.BuilderDirector;
import com.game.entities.ChestBuilder;
import com.game.entities.CoinBuilder;
import com.game.entities.ObjectBuilder;
import com.game.entities.Player;
import com.game.entities.PlayerBuilder;
import com.game.entities.SkeletonBuilder;
import com.game.entities.enemies.Skeleton;
import com.game.entities.objects.Chest;
import com.game.entities.objects.Coin;
import com.game.entities.objects.Tree;
import com.game.maps.MapLayersCreator;

public class DebugLevel extends GameLevel {

	private MapLayersCreator mapCreator;
	private BuilderDirector director; // Is this over engineering???? - Builder Pattern

	public DebugLevel() {
		super();

		// Camera borders
		cameraTopBorder = 50;
		cameraDownBorder = 50;
		cameraLeftBorder = 50;
		cameraRighrBorder = 50;

		director = new BuilderDirector();

		initMap();
		initEntities();
	}

	private void initMap() {
		mapCreator = new MapLayersCreator(70, 70);

		// Map borders
		topMapBorder = 5;
		bottomMapBorder = mapCreator.getMapHeight() - 5;
		leftMapBorder = 5;
		rightMapBorder = mapCreator.getMapWidth() - 5;

		// Set start position of map
		int xStart = (mapCreator.getMapWidth() * GameSprites.DEFAULT_WIDTH) / 2;
		int yStart = (mapCreator.getMapHeight() * GameSprites.DEFAULT_HEIGHT) / 2;

		// Add map layers
//		map.add(mapCreator.solidColorLayer(xStart, yStart, Color.PINK));
		map.add(mapCreator.grassLayer(xStart, yStart));
	}

	private void initEntities() {
		// Player
		ObjectBuilder builder = new PlayerBuilder();
		director.makeObject(builder);

		player = (Player) builder.getObject();
		player.setX(Display.WIDTH / 2 - player.getMiddleX());
		player.setY(Display.HEIGHT / 2 - player.getMiddleY());

		// Skeleton
		builder = new SkeletonBuilder();
		director.makeObject(builder);

		Skeleton skeleton = (Skeleton) builder.getObject();
		skeleton.setX(Display.WIDTH / 2 + 250 - skeleton.getMiddleX());
		skeleton.setY(Display.HEIGHT / 2 - 200 - skeleton.getMiddleY());
		allObjects.addLast(skeleton);

		director.makeObject(builder);
		skeleton = (Skeleton) builder.getObject();
		skeleton.setX(Display.WIDTH / 2 - 250 - skeleton.getMiddleX());
		skeleton.setY(Display.HEIGHT / 2 - 200 - skeleton.getMiddleY());
//		allObjects.addLast(skeleton);

		// Coin
		builder = new CoinBuilder();

		director.makeObject(builder);
		Coin coin = (Coin) builder.getObject();
		coin.setX(Display.WIDTH / 2 + 100);
		coin.setY(Display.HEIGHT / 2 - 100);
		allObjects.addLast(coin);

		Tree tree = new Tree();
		tree.setX(Display.WIDTH / 2 + 100);
		tree.setY(Display.HEIGHT / 2 + 100);
		allObjects.addLast(tree);

		builder = new ChestBuilder();
		director.makeObject(builder);
		Chest chest = (Chest) builder.getObject();
		chest.setX(Display.WIDTH / 2 - 120);
		chest.setY(Display.HEIGHT / 2 + 120);
		allObjects.addLast(chest);

		allObjects.addLast(player);

		populateObjectsArrays();
	}

	@Override
	public void update() {
		resetEntitiesStates();
		checkPlayerInsideCameraBorders();
		checkCollisions();
		updateEntities();
		move();
	}
}
