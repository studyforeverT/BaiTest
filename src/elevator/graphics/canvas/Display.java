package elevator.graphics.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import elevator.input.Mouse;
import elevator.system.ElevatorSimulationSystem;

public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	private Thread thread;
	private JFrame frame;
	private String title = "Elevator";
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		
		Mouse mouse = ElevatorSimulationSystem.getInstance().getInputManager().getMouse();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
	}
	
	public void init() {
		this.frame.setTitle(title);
		this.frame.add(this);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(true);
		this.frame.setVisible(true);
	}
	
	public synchronized void start() {
		this.running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}
	
	public synchronized void stop() {
		this.running = false;
		try {
			this.thread.join();
		} catch(InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int frames = 0;
		
		while(this.running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				this.update();
				this.render();
				delta--;
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				this.frame.setTitle(this.title + " | " + frames + " fps");
				frames = 0;
				timer += 1000;
			}
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		ElevatorSimulationSystem.getInstance().render();
		
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				this.pixels[x + y * WIDTH] = ElevatorSimulationSystem.getInstance().getGraphicsManager().getScreenPixel(x, y);
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);

		ElevatorSimulationSystem.getInstance().renderGraphics(g);
		
		g.dispose();
		bs.show();
	}
	
	public void update() {
		ElevatorSimulationSystem.getInstance().update();
	}

}
