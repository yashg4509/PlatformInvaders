package framework;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import main.Handler;
import main.RunBounceJump;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.oList.size(); i++) {
			GameObject temp = handler.oList.get(i);
			
			if(temp.getId() == ObjectId.Player ) {
				
				if(key == KeyEvent.VK_D) {
					temp.setVelX(5);
				}
				
				else if(key == KeyEvent.VK_A) {
					temp.setVelX(-5);
				}
			}
		
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //EXIT
			System.exit(1);
			
		}
	}
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.oList.size(); i++) {
			GameObject temp = handler.oList.get(i);
			
			if(temp.getId() == ObjectId.Player) {
				
				if(key == KeyEvent.VK_D) {
					temp.setVelX(0);
				}
				
				else if(key == KeyEvent.VK_A) {
					temp.setVelX(0);
				}
				
				else if(key == KeyEvent.VK_SPACE && !temp.isJumping()) {
					temp.setJumping(true);
					temp.setVelY(-10);
				}
			}
		}
	}
}
