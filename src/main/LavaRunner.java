package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import framework.KeyInput;
import framework.ObjectId;
import objects.Block;

public class LavaRunner extends Canvas implements Runnable {

	private static final long serialVersionUID = -599111449181499216L;
	private boolean running = false;
	private Thread thread;

	public static int WIDTH, HEIGHT;

	private BufferedImage level = null;

	Handler handler;
	Camera c;

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		requestFocus();
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void init() {
		WIDTH = getWidth();

		HEIGHT = getHeight();

		BufferedImageLoader loader = new BufferedImageLoader();

		level = loader.loadImage("/lvl1.png");

		handler = new Handler();

		c = new Camera(0, 0);

		loadLevel(level);

		handler.addObject(new Player(100, 100, handler, ObjectId.Player));

		// handler.createLevel();

		this.addKeyListener(new KeyInput(handler));
	}

	private void tick() {
		handler.tick();

		for (int i = 0; i < handler.oList.size(); i++) {
			if (handler.oList.get(i).getId() == ObjectId.Player) {
				c.tick(handler.oList.get(i));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		///////////// DRAWINGS//////////////////////

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		g2d.translate(c.getX(), c.getY()); // begin of camera
		handler.render(g);
		g2d.translate(-c.getX(), -c.getY()); // end of camera
		///////////////
		g.dispose();
		bs.show();
	}

	private void loadLevel(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();

		System.out.println("width " + w + " height " + h);

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = img.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ObjectId.Block));
				}
				
				if(red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Player(xx * 32, yy * 32, handler, ObjectId.Player));
				}
			}
		}
	}

	public static void main(String[] args) {
		new Window(800, 600, "Lava Runner", new LavaRunner());
	}

}
