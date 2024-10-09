package com.engine.objects.components;

import com.engine.objects.GameObject;

public interface Collidable {

	void onCollisionWith(GameObject gameObject);
}
