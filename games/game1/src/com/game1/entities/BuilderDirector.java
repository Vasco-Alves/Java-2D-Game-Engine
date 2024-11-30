package com.game1.entities;

public class BuilderDirector {

	public void makeObject(ObjectBuilder builder) {
		builder.reset();
		builder.initSprites();
		builder.initHitBox();
		builder.initAnimations();
		builder.initUi();
		builder.finish();
	}
}
