package elevator.entity.animation;

import elevator.graphics.sprites.ISprite;

public interface ISpriteAnimation {
	
	ISprite getCurrentSprite();
	
	boolean update();
	
	void reset();

}
