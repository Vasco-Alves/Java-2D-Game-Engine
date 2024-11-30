package com.game1.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.engine.graphics.ui.UIComponent;
import com.engine.objects.components.Updatable;
import com.game1.entities.Mob;

public class MobUI extends UIComponent implements Updatable {

	private Mob mob;

	private int barWidth, barHeight;
	private int healthWidth;

	public MobUI(Mob mob) {
		super();
		this.mob = mob;
		init();
	}

	public MobUI(Mob mob, int x, int y) {
		super(x, y);
		this.mob = mob;
		init();
	}

	public MobUI(Mob mob, int x, int y, int w, int h, Color bgColor) {
		super(x, y, w, h, bgColor);
		this.mob = mob;
		init();
	}

	private void init() {
		barHeight = 6;
		barWidth = mob.getWidth();
		healthWidth = barWidth;
	}

	@Override
	public void update() {
		double healthPercentage = mob.getHealth() / mob.getMaxHealth();
		if (healthPercentage <= 0)
			healthPercentage = 0;

		healthWidth = (int) (barWidth * healthPercentage);
	}

	@Override
	public void render(Graphics g) {
		int x = (int) mob.getX() - 1;
		int y = (int) mob.getY() - 10;

		g.setColor(Color.RED);
		g.fillRect(x, y, healthWidth, barHeight);
	}
}
