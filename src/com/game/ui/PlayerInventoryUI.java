package com.game.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.engine.graphics.ui.UIComponent;
import com.game.entities.Player;

public class PlayerInventoryUI extends UIComponent {

	public PlayerInventoryUI(Player player) {
		super();
	}

	public PlayerInventoryUI(Player player, int x, int y) {
		super(x, y);
	}

	public PlayerInventoryUI(Player player, int x, int y, int w, int h, Color bgColor) {
		super(x, y, w, h, bgColor);
	}

	public void renderHotbar(Graphics g) {
		g.setColor(bgColor);
		g.fillRect((int) x, (int) y, width, height);

		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, width, height);
	}

	@Override
	public void render(Graphics g) {
		renderHotbar(g);
	}
}
