package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class States extends GameObject{
	Font titleFont;
	
	States(float x, float y, ObjectId id) {
		super(x, y, id);
		titleFont = new Font("Arial", Font.PLAIN, 48);
	}
	
	public void drawMenuState(Graphics g) {
		g.setFont(titleFont);
		g.drawString("text",	(int)x, (int)y);
	}
	
	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, TriggeredPlatvaders.WIDTH, TriggeredPlatvaders.HEIGHT);   
		g.setFont(titleFont);
		g.drawString("text", (int)x, (int)y);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
