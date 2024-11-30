package com.game1.Utils;

import com.engine.objects.GameObject;

public class Utils {

	public static double distancebetweenPoints(double x, double y, double x2, double y2) {
		double dx = x - x2;
		double dy = y - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static double distanceBetweenObjets(GameObject g1, GameObject g2) {
		double dx = g1.getMiddleX() - g2.getMiddleX();
		double dy = g1.getMiddleY() - g2.getMiddleY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static void separateObjects(GameObject g1, GameObject g2) {
		double dx = g1.getMiddleX() - g2.getMiddleX();
		double dy = g1.getMiddleY() - g2.getMiddleY();
		double absDx = Math.abs(dx);
		double absDy = Math.abs(dy);

		// Separate along the axis of greatest overlap
		if (absDx > absDy) {
			if (dx < 0) {
				g1.setX(g1.getX() - 1);
				g2.setX(g2.getX() + 1);
			} else {
				g1.setX(g1.getX() + 1);
				g2.setX(g2.getX() - 1);
			}
		} else {
			if (dy < 0) {
				g1.setY(g1.getY() - 1);
				g2.setY(g2.getY() + 1);
			} else {
				g1.setY(g1.getY() + 1);
				g2.setY(g2.getY() - 1);
			}
		}
	}

	public static boolean areObjectsColliding(GameObject g1, GameObject g2) {
		boolean xOverlap = (g1.getRightX() >= g2.getX()) && (g2.getRightX() >= g1.getX());
		boolean yOverlap = (g1.getBottomY() >= g2.getY()) && (g2.getBottomY() >= g1.getY());
		return xOverlap && yOverlap;
	}
}
