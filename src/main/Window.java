package main;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window {
	JLabel timeAlive;
	
	public JLabel getTimeAlive() {
		return timeAlive;
	}

	public void setTimeAlive(JLabel timeAlive) {
		this.timeAlive = timeAlive;
	}
	
	public void changeTimeAlive(String time) {
		timeAlive.setText(time);
	}

	Window(int w, int h, String title, Invisivaders game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		JFrame window = new JFrame(title);
		timeAlive = new JLabel("");
		window.add(timeAlive);
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		game.start();
		
	}
}
