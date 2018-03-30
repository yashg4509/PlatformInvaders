package main;

import framework.GameObject;

public class Camera {
	static float x;
	private float y;
	private float speed = 1.5f;
	
	public Camera (float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void tick(GameObject enemy) {
		x-=speed;
	}
 }
