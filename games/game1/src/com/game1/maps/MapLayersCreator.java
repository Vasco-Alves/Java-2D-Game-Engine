package com.game1.maps;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.engine.objects.Tile;
import com.engine.objects.sprite.ColorSprite;
import com.game1.sheets.GameSprites;

public class MapLayersCreator {

	int nTilesWidth;
	int nTilesHeight;

	public MapLayersCreator(int nTilesWidth, int nTilesHeight) {
		this.nTilesWidth = nTilesWidth;
		this.nTilesHeight = nTilesHeight;
	}

	public MapLayersCreator(String fileName) {
		BufferedImage mapImg = null;
		try {
			mapImg = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		nTilesWidth = mapImg.getWidth() / GameSprites.DEFAULT_WIDTH;
		nTilesHeight = mapImg.getHeight() / GameSprites.DEFAULT_HEIGHT;
	}

	public Tile[][] solidColorLayer(int xStart, int yStart, Color color) {
		Tile[][] layer = new Tile[nTilesHeight][nTilesWidth];

		for (int y = 0; y < layer.length; y++) {
			for (int x = 0; x < layer[y].length; x++) {
				int scale = 3;
				int w = GameSprites.DEFAULT_WIDTH * scale;
				int h = GameSprites.DEFAULT_HEIGHT * scale;

				layer[y][x] = new Tile(new ColorSprite(color, w, h));
				layer[y][x].setX(x * layer[y][x].getWidth() - xStart);
				layer[y][x].setY(y * layer[y][x].getHeight() - yStart);
			}
		}

		return layer;
	}

	public Tile[][] whiteLayer(int xStart, int yStart) {
		Tile[][] layer = new Tile[nTilesHeight][nTilesWidth];

		for (int y = 0; y < layer.length; y++) {
			for (int x = 0; x < layer[y].length; x++) {
				int scale = 3;
				int w = GameSprites.DEFAULT_WIDTH * scale;
				int h = GameSprites.DEFAULT_HEIGHT * scale;

				layer[y][x] = new Tile(new ColorSprite(Color.WHITE, w, h));
				layer[y][x].setX(x * layer[y][x].getWidth() - xStart);
				layer[y][x].setY(y * layer[y][x].getHeight() - yStart);
			}
		}

		return layer;
	}

	public Tile[][] grassLayer(int xStart, int yStart) {
		Random rand = new Random();
		Tile[][] layer = new Tile[nTilesHeight][nTilesWidth];

		for (int y = 0; y < layer.length; y++) {
			for (int x = 0; x < layer[y].length; x++) {
				layer[y][x] = new Tile(GameSprites.worldSprites[0]);
				layer[y][x].setX(x * layer[y][x].getWidth() - xStart);
				layer[y][x].setY(y * layer[y][x].getHeight() - yStart);
			}
		}

		return layer;
	}

	public Tile[][] layerFromCSV(String filename, int xStart, int yStart) {
		Tile[][] layer = new Tile[nTilesHeight][nTilesWidth];

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			int y = 0;

			while ((line = br.readLine()) != null) {
				String[] rowData = line.split(",");

				for (int x = 0; x < layer[y].length; x++) {
					int tileNumber = Integer.parseInt(rowData[x]);
					if (tileNumber == -1) {
						layer[y][x] = null;
						continue;
					}

					if (y < layer.length && x < layer[y].length) {
						layer[y][x] = new Tile(GameSprites.worldSprites[tileNumber]);
						layer[y][x].setX(x * layer[y][x].getWidth() - xStart);
						layer[y][x].setY(y * layer[y][x].getHeight() - yStart);
					}
				}
				y++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return layer;
	}

	public Tile[][] randomLayer(int xStart, int yStart) {
		Random rand = new Random();

		Tile[][] layer = new Tile[nTilesHeight][nTilesWidth];

		for (int y = 0; y < layer.length; y++) {
			for (int x = 0; x < layer[y].length; x++) {
				float number = rand.nextFloat() % 1;
				int index;

				if (number > 0.4) {
					index = GameSprites.WorldSprites.GRASS_TILE_0.ordinal();
					layer[y][x] = new Tile(GameSprites.worldSprites[index]);
				}

				else if (number < 0.4 && number > 0.27) {
					index = 161;
					layer[y][x] = new Tile(GameSprites.worldSprites[index]);
				}

				else if (number < 0.27 && number > 0.14) {
					index = 1201;
					layer[y][x] = new Tile(GameSprites.worldSprites[index]);
				}

				else {
					index = 283;
					layer[y][x] = new Tile(GameSprites.worldSprites[index]);
				}

				layer[y][x].setX(x * layer[y][x].getWidth() - xStart);
				layer[y][x].setY(y * layer[y][x].getHeight() - yStart);
			}
		}

		return layer;
	}

	/* GETTERS AND SETTERS */

	public int getMapHeight() {
		return nTilesHeight;
	}

	public void setnTilesHeight(int nTilesHeight) {
		this.nTilesHeight = nTilesHeight;
	}

	public int getMapWidth() {
		return nTilesWidth;
	}

	public void setnTilesWidth(int nTilesWidth) {
		this.nTilesWidth = nTilesWidth;
	}
}
