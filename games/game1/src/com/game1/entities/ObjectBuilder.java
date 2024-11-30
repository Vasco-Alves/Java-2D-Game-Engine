package com.game1.entities;

import com.engine.objects.GameObject;

public interface ObjectBuilder {

	void reset();

	void initSprites();

	void initHitBox();

	void initAnimations();

	void initUi();

	void finish();

	GameObject getObject();
}
