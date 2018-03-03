
package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import objects.Block;
public class Enemy extends GameObject{
        private Handler handler;
        public Enemy(int x, int y, ObjectId id, Handler handler){
                super(x,y,id);
                this.handler = handler;
                velX = (float) 1.5;
                velY = (float) 1.5;
        }
        public Rectangle getBounds(){
                return new Rectangle((int) x,(int) y, 70, 70);
        }
        public void tick(LinkedList<GameObject> object){
                x += velX;
                y += velY;     
                for(GameObject e: object) {
                	if(e.getId() == ObjectId.Block) {
                	if(e.getBounds().intersects(getBounds())) {
                		velY *= -1;
                	}
                	}
                	else {
                		break;
                	}
                }
        }
        
        public void render(Graphics g){
                g.setColor(Color.red);
                g.fillRect((int)x,(int)y,70,70);
        }
}