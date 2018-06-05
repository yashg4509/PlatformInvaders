package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import framework.KeyInput;
import framework.ObjectId;
import objects.Block;

public class RunBounceJump extends Canvas implements Runnable {

	private static final long serialVersionUID = -599111449181499216L;
	private boolean running = false;
	private Thread thread;
	BufferedImageLoader loader = new BufferedImageLoader();

	public static int WIDTH, HEIGHT;
	
	public static Random r = new Random();
	static long startTime;
	
	int initEnemyCount = 1;
	int enemyX = r.nextInt(800);

	private BufferedImage level = null;
	private Image background;

	Handler handler;
	public static Camera camera;
	public static Window win;
	
	private Player p;
	
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
		startTime = System.nanoTime();
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
			
			if(p.x%200 == 0) {
				handler.addObject(new Enemy(enemyX, 100, ObjectId.Enemy, handler));
			}
		}
	}

	private void init() {
		WIDTH = getWidth();

		HEIGHT = getHeight();

		level = loader.loadImage("/lvl1.png");

		handler = new Handler();

		camera = new Camera(0, 0);
		
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		loadLevel(level);
		
		p = new Player(100, 200, handler, ObjectId.Player);

		 handler.addObject(p);
		
		
		for (int i = initEnemyCount; i < initEnemyCount; i++) {
			handler.addObject(new Enemy(enemyX, 100, ObjectId.Enemy, handler));
		}
		
		this.addKeyListener(new KeyInput(handler));
		
	}
	

	private void tick() {
			handler.tick();

		for (int i = 0; i < handler.oList.size(); i++) {
			if (handler.oList.get(i).getId() == ObjectId.Player) {
				camera.tick(handler.oList.get(i));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////////////
	
		//Draw Here
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		
		
		g2d.translate(camera.getX(), camera.getY());//begin of camera
		
		handler.render(g);
		
		
		g2d.translate(-camera.getX(), -camera.getY());//end of camera
		
		//////////////////////////////////
		
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
				
			}
		}
	}
	
	public static void main(String[] args) {
		win = new Window(800, 600, "Run. Bounce. Jump.", new RunBounceJump());
	}

}
