package elevator.graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite implements ISprite {
	
	private int size;
	private int x, y;
	private int[] pixels;
	
	public Sprite(SpriteSheet sheet, int x, int y, int size) {
		this.size = size;
		this.pixels = new int[this.size * this.size];
		this.x = x * size;
		this.y = y * size;
		sheet.loadSprite(this.x, this.y, this.size, this.pixels);
	}
	
	public Sprite(String path) {
		this.loadDirectly(path);
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public int getPixel(int x, int y) {
		return this.pixels[x + y * this.size];
	}
	
	private void loadDirectly(String path) {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			this.pixels = new int[w * h];
			this.size = w;
			image.getRGB(0, 0, w, h, this.pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
