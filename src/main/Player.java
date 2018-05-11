package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import framework.GameObject;
import framework.ObjectId;

public class Player extends GameObject {
	private float width = 48, height = 96;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	public static float x;

	
	private Handler handler;
	private Camera c;
	
	public Image playerimg;
	
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.x = x;
		
		try {
			playerimg = ImageIO.read(this.getClass().getResourceAsStream("player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if (falling || jumping) {
			velY += gravity + 0.1;
			
			if(velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		
		if(y > InvisiblocksPlatformer.HEIGHT) {
			y = InvisiblocksPlatformer.HEIGHT - 128;
		}
		
	
		
		collision(object);
		
	}
	
	private void collision(LinkedList<GameObject> object) {
		for(int i = 0; i < handler.oList.size(); i++) {
			GameObject temp = handler.oList.get(i);
			
			if(temp.getId() == ObjectId.Block) {
				if(getBoundsTop().intersects(temp.getBounds())) {
					y = temp.getY() - 32;
					velY = 0;
			}
				
				
				if(getBounds().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
			} else {
					falling = true;			
				}
				
				if(getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - width;
			}
				
				if(getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + 35;
				}
				
				
			}
			
		}
	}

	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		g.drawImage(playerimg, (int) x, (int) y, (int) width, (int) height, null);
		
		for(int i = 0; i < handler.oList.size(); i++) {
			GameObject temp = handler.oList.get(i);
		
			if(temp.getId() == ObjectId.Enemy) {
				if(temp.getBounds().intersects(getBounds())) {
					JOptionPane.showMessageDialog(null, "Sorry! You lost! Double tap ESC! You score is " + x + ".");
				}
				
				if(temp.getBounds().intersects(getBoundsTop())) {
					JOptionPane.showMessageDialog(null, "Sorry! You lost! Double tap ESC! You score is " + x + ".");
				}
				
				if(temp.getBounds().intersects(getBoundsRight())) { 
					JOptionPane.showMessageDialog(null, "Sorry! You lost! Double tap ESC! You score is " + x + ".");
				}
				
				if(temp.getBounds().intersects(getBoundsLeft())) {
					JOptionPane.showMessageDialog(null, "Sorry! You lost! Double tap ESC! You score is " + x + ".");
			}
			
			if(x <= Math.abs((double) Camera.x)) {
				x = Math.abs(Camera.x) + 2;
			}
			
			if(x >= Math.abs((double) Camera.x - 800)) {
				x = Math.abs(Camera.x);
			}
			}
		}
	}
			

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - (width/2)/2), (int) ((int)y + (height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - (width/2)/2), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width-5), (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int)y + 5, (int)5, (int)height- 10);
	}
}
