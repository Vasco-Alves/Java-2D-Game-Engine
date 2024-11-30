package com.game1.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.engine.audio.AudioPlayer;
import com.engine.graphics.Display;
import com.engine.input.Input;
import com.engine.objects.GameObject;
import com.engine.objects.Tile;
import com.engine.objects.components.Collidable;
import com.engine.objects.components.Renderable;
import com.engine.objects.components.Updatable;
import com.game1.Utils.Utils;
import com.game1.entities.Mob;
import com.game1.entities.Player;
import com.game1.entities.enemies.Skeleton;

public abstract class GameLevel implements Updatable, Renderable {

	protected AudioPlayer ambienceAudio;

	// Map
	protected double mapX, mapY;
	protected List<Tile[][]> map;

	// Map borders
	protected int topMapBorder;
	protected int bottomMapBorder;
	protected int leftMapBorder;
	protected int rightMapBorder;

	// Entities
	protected List<GameObject> allObjects;
	protected List<Updatable> updatableEntities;
	protected List<Renderable> renderableEntities;

	protected Player player;

	// Camera States
	protected boolean canMoveUp;
	protected boolean canMoveDown;
	protected boolean canMoveLeft;
	protected boolean canMoveRight;

	// Camera Borders
	protected int cameraTopBorder;
	protected int cameraDownBorder;
	protected int cameraLeftBorder;
	protected int cameraRighrBorder;

	public GameLevel() {
		map = new ArrayList<>();
		allObjects = new ArrayList<GameObject>();
		updatableEntities = new ArrayList<Updatable>();
		renderableEntities = new ArrayList<Renderable>();
	}

	private void moveMap() {
		// O(n^3)
		for (Tile[][] map : map) {
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[y].length; x++) {
					if (map[y][x] != null) {
						map[y][x].setX(map[y][x].getX() - this.mapX);
						map[y][x].setY(map[y][x].getY() - this.mapY);
					}
				}
			}
		}
	}

	private void moveEntities() {
		for (Updatable entity : updatableEntities)
			if (entity != player)
				((GameObject) entity).addPosOffsetXY(mapX, mapY);
	}

	protected void move() {
		if (player.insideCamera || !player.canMove)
			return;

		double combinedSpeed = Math.sqrt(Input.xAxis * Input.xAxis + Input.yAxis * Input.yAxis);
		if (combinedSpeed > 1.0) {
			Input.xAxis /= combinedSpeed;
			Input.yAxis /= combinedSpeed;
		}

		boolean canMoveH = (Input.left && !player.isCollidingLeft()) || (Input.right && !player.isCollidingRight());
		boolean canMoveV = (Input.up && !player.isCollidingUp()) || (Input.down && !player.isCollidingBottom());

		mapX = (!canMoveH) ? 0 : Input.xAxis * player.getMovementSpeed();
		mapY = (!canMoveV) ? 0 : Input.yAxis * player.getMovementSpeed();

		moveMap();
		moveEntities();

	}

	protected void resetEntitiesStates() {
		for (GameObject entity : allObjects)
			if (entity instanceof Mob)
				((Mob) entity).resetStates();
	}

	private void remove(GameObject gameObject) {
		allObjects.remove(gameObject);
//		updatableEntities.remove(gameObject);
//      renderableEntities.remove(gameObject);
	}

	protected void updateEntities() {
		for (Updatable entity : updatableEntities) {
			if (!((GameObject) entity).isEnabled) {
				remove((GameObject) entity);
				continue;
			}

			if (entity instanceof Mob)
				checkMobMapBorders((Mob) entity);

			if (entity instanceof Skeleton)
				((Skeleton) entity).followMob(player);

			entity.update();
		}
	}

	@Override
	public abstract void update();

	private void renderMap(Graphics g) {
		for (Tile[][] layer : map)
			for (Tile[] tileRow : layer)
				for (Tile tile : tileRow)
					if (tile != null && isObjectVisible(tile))
						tile.render(g);
	}

	private void renderEntities(Graphics g) {
		// Sort entities array
		Collections.sort(renderableEntities, (entity1, entity2) -> Double.compare(((GameObject) entity1).getBottomY(),
				((GameObject) entity2).getBottomY()));

		for (Renderable entity : renderableEntities)
			if (entity != null && isObjectVisible((GameObject) entity) && ((GameObject) entity).isEnabled)
				entity.render(g);
	}

	@Override
	public void render(Graphics g) {
		renderMap(g);
		renderEntities(g);
		player.renderUI(g);
	}

	public Tile getTileAtPosition(double x, double y) {
		// TODO improve

		// O(n^3)
		for (Tile[][] layer : map)
			for (Tile[] tileRow : layer)
				for (Tile tile : tileRow)
					if (tile.getX() <= x && tile.getRightX() >= x && tile.getY() <= y && tile.getBottomY() >= y)
						return tile;
		return null;
	}

	private boolean isObjectVisible(GameObject object) {
		return object.getRightX() > 0 && object.getX() <= Display.WIDTH && object.getBottomY() > 0
				&& object.getY() <= Display.HEIGHT;
	}

	private void checkMobDisplayBorders(Mob mob) {
		double topBorder = 0;
		double bottomBorder = Display.HEIGHT;
		double leftBorder = 0;
		double rightBorder = Display.WIDTH;

		double topCheck = mob.getY();
		double bottomCheck = mob.getBottomY();
		double leftCheck = mob.getX();
		double rightCheck = mob.getRightX();

		double[] borders = { topBorder, bottomBorder, leftBorder, rightBorder };
		double[] mobChecks = { topCheck, bottomCheck, leftCheck, rightCheck };

		checkBorders(mob, borders, mobChecks);
	}

	private void checkMobMapBorders(Mob mob) {
		double topBorder = map.get(0)[topMapBorder - 1][0].getBottomY();
		double bottomBorder = map.get(0)[bottomMapBorder][0].getY();
		double leftBorder = map.get(0)[0][leftMapBorder - 1].getRightX();
		double rightBorder = map.get(0)[0][rightMapBorder].getX();

		double topCheck = mob.getMiddleY();
		double bottomCheck = mob.getBottomY();
		double leftCheck = mob.getX();
		double rightCheck = mob.getRightX();

		double[] borders = { topBorder, bottomBorder, leftBorder, rightBorder };
		double[] mobChecks = { topCheck, bottomCheck, leftCheck, rightCheck };

		checkBorders(mob, borders, mobChecks);
	}

	protected void checkPlayerInsideCameraBorders() {
		boolean top = (player.getY() < Display.HEIGHT / 2 - cameraTopBorder) && Input.up;
		boolean bottom = (player.getBottomY() + 1 > Display.HEIGHT / 2 + cameraDownBorder) && Input.down;
		boolean left = (player.getX() < Display.WIDTH / 2 - cameraLeftBorder) && Input.left;
		boolean right = (player.getRightX() > Display.WIDTH / 2 + cameraRighrBorder) && Input.right;

		player.insideCamera = !top && !bottom && !left && !right;
	}

	private void checkBorders(Mob mob, double[] borders, double[] mobChecks) {
		if (mobChecks[0] < borders[0])
			mob.setCollidingTop(true);

		if (mobChecks[1] > borders[1])
			mob.setCollidingDown(true);

		if (mobChecks[2] < borders[2])
			mob.setCollidingLeft(true);

		if (mobChecks[3] > borders[3])
			mob.setCollidingRight(true);
	}

	private void drawCameraBorders(Graphics g) {
		g.setColor(Color.RED);
		g.drawLine(Display.WIDTH / 2 - cameraTopBorder, 0, Display.WIDTH / 2 - cameraTopBorder, Display.HEIGHT);
		g.drawLine(Display.WIDTH / 2 + cameraDownBorder, 0, Display.WIDTH / 2 + cameraDownBorder, Display.HEIGHT);

		g.drawLine(0, Display.HEIGHT / 2 - cameraLeftBorder, Display.WIDTH, Display.HEIGHT / 2 - cameraLeftBorder);
		g.drawLine(0, Display.HEIGHT / 2 + cameraRighrBorder, Display.WIDTH, Display.HEIGHT / 2 + cameraRighrBorder);
	}

	protected void populateObjectsArrays() {
		for (GameObject object : allObjects) {
			if (object instanceof Updatable)
				updatableEntities.add((Updatable) object);

			if (object instanceof Renderable)
				renderableEntities.add((Renderable) object);
		}
	}

	/* Game Logic */

	protected void checkCollisions() {
		// TODO implement spatial partitioning techniques (like quad trees) instead

		// TODO improve so only checks once per pair of objects
		for (GameObject entity1 : allObjects) {
			for (GameObject entity2 : allObjects) {
				if (entity1 != entity2 && entity2 instanceof Collidable) {
					if (Utils.areObjectsColliding(entity1, entity2))
						((Collidable) entity1).onCollisionWith(entity2);
				}
			}
		}
	}

	public void close() {
		for (GameObject object : allObjects)
			if (object instanceof Mob)
				((Mob) object).close();
	}

	/* GETTERS AND SETTERS */

	public Player getPlayer() {
		return player;
	}
}
