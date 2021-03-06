package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {
	
	private BufferedImage block = null;
	private BufferedImage coin = null;
	private BufferedImage lava = null;
	private BufferedImage playerSheet = null;
	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
		block = loader.loadImage("/brick-block-platforms.png");
		coin = loader.loadImage("/coin.PNG");
		lava = loader.loadImage("/lava.PNG");
		playerSheet = loader.loadImage("/player.jpg");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}

	private void getTextures() {
		
	}
	
}
