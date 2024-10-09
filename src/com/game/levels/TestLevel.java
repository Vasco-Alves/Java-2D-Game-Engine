package com.game.levels;

import java.util.ArrayList;

import com.engine.audio.AudioPlayer;
import com.engine.graphics.Display;
import com.game.GameLevel;
import com.game.GameSprites;
import com.game.entities.Player;
import com.game.maps.MapLayersCreator;

public class TestLevel extends GameLevel {

	private MapLayersCreator mapCreator;

	public TestLevel() {
		super();

		// Camera borders
		cameraTopBorder = 50;
		cameraDownBorder = 50;
		cameraLeftBorder = 50;
		cameraRighrBorder = 50;

		initMap();
		initEntities();

		ambienceAudio = new AudioPlayer("./assets/audio/wav/Light Ambience 5.wav");
		ambienceAudio.play();
	}

	private void initMap() {
		mapCreator = new MapLayersCreator("./assets/maps/spawn/spawn.png");

		// Map borders
		topMapBorder = 5;
		bottomMapBorder = mapCreator.getMapHeight() - 5;
		leftMapBorder = 5;
		rightMapBorder = mapCreator.getMapWidth() - 5;

		// Set start position of map
		int xStart = (mapCreator.getMapWidth() * GameSprites.DEFAULT_WIDTH) / 2;
		int yStart = (mapCreator.getMapHeight() * GameSprites.DEFAULT_HEIGHT) / 2;

		// Add map layers
		map.add(mapCreator.layerFromCSV("./assets/maps/spawn/spawn_Ground_ground.csv", xStart, yStart));
		map.add(mapCreator.layerFromCSV("./assets/maps/spawn/spawn_Ground_tallgrass.csv", xStart, yStart));
		map.add(mapCreator.layerFromCSV("./assets/maps/spawn/spawn_Ground_flowers.csv", xStart, yStart));
	}

	private void initEntities() {
		allObjects = new ArrayList<>();
		updatableEntities = new ArrayList<>();
		renderableEntities = new ArrayList<>();

		player = new Player();

		// Center player on map
		player.setX(Display.WIDTH / 2 - player.getMiddleX());
		player.setY(Display.HEIGHT / 2 - player.getMiddleY());

		allObjects.addLast(player);

		populateObjectsArrays();
	}

	@Override
	public void update() {
		player.resetStates();

		// TODO check collisions

		updateEntities();

		checkPlayerInsideCameraBorders();
//		if (!player.outsideCamera)
		move();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
}
