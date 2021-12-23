package elevator.graphics;

import elevator.graphics.sprites.IFillSprite;
import elevator.graphics.sprites.ISprite;
import elevator.graphics.sprites.SpriteSheets;
import elevator.system.subsystem.IRenderableSubsystem;

public interface IGraphicsManager extends IRenderableSubsystem {

	ISprite loadSprite(String path);
	
	ISprite loadSprite(SpriteSheets spriteSheet, int x, int y, int size);
	
	IFillSprite loadFillSprite(SpriteSheets spriteSheet, int x, int y, int size, int oldColor, int... newColors);
	
	void renderSprite(int xp, int yp, ISprite sprite);
	
	void renderFillSprite(int xp, int yp, IFillSprite sprite, int index);
	
	int getScreenPixel(int x, int y);

}
