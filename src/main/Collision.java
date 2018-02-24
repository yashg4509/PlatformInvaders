package main;

public class Collision {
	public static boolean checkCollision(Player fe, Enemy ee) {
		return fe.getBounds().intersects(ee.getBounds());
		
	}
}
