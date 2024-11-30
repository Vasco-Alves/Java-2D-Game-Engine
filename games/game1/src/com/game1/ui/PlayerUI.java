package com.game1.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.engine.graphics.ui.UIComponent;
import com.engine.objects.components.Updatable;
import com.game1.entities.Player;

public class PlayerUI extends UIComponent implements Updatable {

	private Player player;

	private double xHOffset, yHOffset; // Health bar
	private double xSOffset, ySOffset; // Stamina bar

	private int barWidth, barHeight;
	private int healthWidth, staminaWidth;

	public PlayerUI(Player player) {
		super();
		this.player = player;
		init();
	}

	public PlayerUI(Player player, int x, int y) {
		super(x, y);
		this.player = player;
		init();
	}

	public PlayerUI(Player player, int x, int y, int w, int h, Color bgColor) {
		super(x, y, w, h, bgColor);
		this.player = player;
		init();
	}

	private void init() {
		barWidth = width - 20;
		barHeight = 20;

		// Health bar
		xHOffset = x + 10;
		yHOffset = y + 10;

		// Stamina bar
		xSOffset = x + 10;
		ySOffset = yHOffset + barHeight + 10;

		healthWidth = barWidth;
		staminaWidth = barWidth;
	}

	@Override
	public void update() {
		double healthPercentage = player.getHealth() / player.getMaxHealth();
		double staminaPercentage = player.getStamina() / player.getMaxStamina();

		if (healthPercentage <= 0)
			healthPercentage = 0;

		if (staminaPercentage <= 0)
			staminaPercentage = 0;

		healthWidth = (int) (barWidth * healthPercentage);
		staminaWidth = (int) (barWidth * staminaPercentage);
	}

	@Override
	public void render(Graphics g) {
		// Background
		g.setColor(bgColor);
		g.fillRect((int) x, (int) y, width, height);

		// Border
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, width, height);

		// Health bar
		g.setColor(Color.RED);
		g.fillRect((int) xHOffset, (int) yHOffset, healthWidth, barHeight);

		// Stamina bar
		g.setColor(Color.GREEN);
		g.fillRect((int) xSOffset, (int) ySOffset, staminaWidth, barHeight);
	}
}
