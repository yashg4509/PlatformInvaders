	package main;
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.util.LinkedList;

	import framework.GameObject;
	import framework.ObjectId;

public class Enemy extends GameObject{
		private float width = 48, height = 96;
		private float gravity = 0.5f;
		private final float MAX_SPEED = 10;
		
		private Handler handler;

		
		public Enemy(float x, float y, Handler handler, ObjectId id) {
			super(x, y, id);
			this.handler = handler;
		}

		public void tick(LinkedList<GameObject> object) {
			x += velX;
			y += velY;
			
			if (falling || jumping) {
				velY += gravity;
				
				if(velY > MAX_SPEED) {
					velY = MAX_SPEED;
				}
			}
			collision(object);
			
		}
		
		private void collision(LinkedList<GameObject> object) {
			for(int i = 0; i < handler.oList.size(); i++) {
				GameObject temp = handler.oList.get(i);
				
				if(temp.getId() == ObjectId.Block) {
					if(getBoundsTop().intersects(temp.getBounds())) {
						y = temp.getY() + 32;
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
			g.setColor(Color.blue);
			g.fillRect((int)x, (int)y, (int)width, (int)height);
			

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


