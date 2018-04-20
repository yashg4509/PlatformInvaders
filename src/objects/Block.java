package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import main.BufferedImageLoader;

public class Block extends GameObject{
	BufferedImageLoader loader = new BufferedImageLoader();
	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
//		collisionBox.setBounds((int) x, (int) y, 32, 32);
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> object) {
		
	}
	

	public void render(Graphics g) {
		g.drawRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}