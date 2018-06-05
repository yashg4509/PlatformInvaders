package main;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Window {


	Window(int w, int h, String title, RunBounceJump game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		JFrame window = new JFrame(title);
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		JOptionPane.showMessageDialog(null, "Be wise! Some enemies you cannot see and enemies don't follow the walls! Good Luck!");
		game.start();
		
	}
}
