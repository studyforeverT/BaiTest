package elevator.graphics;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import elevator.graphics.canvas.Display;
import elevator.graphics.sprites.FillSprite;
import elevator.graphics.sprites.IFillSprite;
import elevator.graphics.sprites.ISprite;
import elevator.graphics.sprites.Sprite;
import elevator.graphics.sprites.SpriteSheet;
import elevator.graphics.sprites.SpriteSheets;

public class GraphicsManager implements IGraphicsManager {
	
	private Map<SpriteSheets, SpriteSheet> sheetMap;
	private int[] pixels;
	private int width, height;

	@Override
	public void init() {
		this.width = Display.WIDTH;
		this.height = Display.HEIGHT;
		this.pixels = new int[this.width * this.height];
		this.sheetMap = new HashMap<SpriteSheets, SpriteSheet>();
		this.createSpriteSheets();
	}

	@Override
	public void enable() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		for(int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = 0x00DDDD;
		}
	}

	@Override
	public void renderGraphics(Graphics g) {
		
	}

	@Override
	public ISprite loadSprite(String path) {
		return new Sprite(path);
	}

	@Override
	public ISprite loadSprite(SpriteSheets spriteSheet, int x, int y, int size) {
		return new Sprite(this.sheetMap.get(spriteSheet), x, y, size);
	}
	
	@Override
	public IFillSprite loadFillSprite(SpriteSheets spriteSheet, int x, int y, int size, int oldColor, int... newColors) {
		return new FillSprite(this.sheetMap.get(spriteSheet), x, y, size, oldColor, newColors);
	}

	@Override
	public void renderSprite(int xp, int yp, ISprite sprite) {
		int size = sprite.getSize();
		for(int y = 0; y < size; y++) {
			int ya = y + yp;
			for(int x = 0; x < size; x++) {
				int xa = x + xp;
				if(xa < 0 || xa > this.width || ya < 0 || ya > this.height) continue;
				int col = sprite.getPixel(x, y);
				if(col != 0xFFFF00FF) this.pixels[xa + ya * this.width] = col;
			}
		}
	}
	
	@Override
	public void renderFillSprite(int xp, int yp, IFillSprite sprite, int index) {
		int size = sprite.getSize();
		int oldColor = sprite.getOldColor();
		int newColor = sprite.getNewColor(index);
		for(int y = 0; y < size; y++) {
			int ya = y + yp;
			for(int x = 0; x < size; x++) {
				int xa = x + xp;
				if(xa < 0 || xa > this.width || ya < 0 || ya > this.height) continue;
				int col = sprite.getPixel(x, y);
				if(col != 0xFFFF00FF) {
					if(col==oldColor) col = newColor;
					this.pixels[xa + ya * this.width] = col;
				}
			}
		}
		
	}
	
	@Override
	public int getScreenPixel(int x, int y) {
		return this.pixels[x + y * this.width];
	}
	
	private void createSpriteSheets() {
		this.sheetMap.put(SpriteSheets.TestSheet, new SpriteSheet("/textures/penguin.png", 80));
		this.sheetMap.put(SpriteSheets.Elevator, new SpriteSheet("/textures/elevator.png", 200));
		this.sheetMap.put(SpriteSheets.Buttons, new SpriteSheet("/textures/buttons.png", 50));
	}

	
	


}
