
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import framework.GameObject;
import framework.ObjectId;
import objects.Block;

public class Enemy extends GameObject {
	private Handler handler;
	Random r = new Random();
	double angle = r.nextDouble();
	public Image alienImg;

	public Enemy(int x, int y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.x = x;
		
		try {
			alienImg = ImageIO.read(this.getClass().getResourceAsStream("enemySprite.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		velX = (float) 1.5;
		velY = (float) 1.5;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 70, 70);
	}

	public void tick(LinkedList<GameObject> objectList) {
		x += velX;
		y += velY;
		for (GameObject e : objectList) {
			if (e.getId() == ObjectId.Block) {
				if (e.getBounds().intersects(getBounds())) {
					velY *= -1;
					velX+=.01;				}

			}

		}

		if (y > Invisivaders.HEIGHT - 116) {
			velY *= -1;
			velX+=.01;		}
		
		if(y < 36) {
			velY *= -1;
			velX+=.01;
		}
		
		if(x >= (Math.abs((double) Camera.x) + 800)) {
			velX *= -1;
			velY *= -1;
		}
		
		if(x <= Math.abs((double) Camera.x)) {
				x = (Math.abs(Camera.x)) + r.nextInt(800);
			
		}
		
		
		

	}

	public void render(Graphics g) {
		g.drawImage(alienImg, (int) x, (int) y, 70, 70, null);
//		g.setColor(Color.red);
//		g.fillRect((int) x, (int) y, 70, 70);
	}
}