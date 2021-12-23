package elevator.graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	private final int SIZE;
	private int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		this.pixels = new int[this.SIZE * this.SIZE];
		this.load();
	}
	
	public void loadSprite(int x, int y, int size, int[] pixels) {
		for(int a = 0; a < size; a++) {
			for(int b = 0; b < size; b++) {
				pixels[b + a * size] = this.pixels[(x + b) + (y + a) * this.SIZE];
			}
		}
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(this.path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, this.pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
