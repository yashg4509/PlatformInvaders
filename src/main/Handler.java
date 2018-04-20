package main;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import objects.Block;

public class Handler {
	public static int HEALTH;

	public LinkedList<GameObject> oList = new LinkedList<>();
	
	private GameObject temp;
	
	public void tick() {
		for (int i = 0; i < oList.size(); i++) {
			temp = oList.get(i);
			
			temp.tick(oList);
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < oList.size(); i++) {
			temp = oList.get(i);
			
			temp.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.oList.add(object);
		
	}
	
	public void removeObject(GameObject o) {
		this.oList.remove(o);         
	}
	
	public void createLevel() {
		for (int yy = 0; yy < InvisiblocksPlatformer.HEIGHT + 32; yy += 32) {
			addObject(new Block(0, yy, ObjectId.Block));
		}
		
		for (int xx = 0; xx < InvisiblocksPlatformer.WIDTH * 2; xx += 32) {
			addObject(new Block(xx, InvisiblocksPlatformer.HEIGHT - 32, ObjectId.Block));
		}
		
		for (int xx = 200; xx < 600; xx += 32) {
			addObject(new Block(xx, 400, ObjectId.Block));
		}
	}
}

