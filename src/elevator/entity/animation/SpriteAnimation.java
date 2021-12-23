package elevator.entity.animation;

import elevator.graphics.sprites.ISprite;

public class SpriteAnimation implements ISpriteAnimation {
	
	private ISprite[] sprites;
	private int delay;
	private CycleType cycleType;
	private int counter;
	private int spriteIndex;
	private boolean increaseSpriteIndex;
	
	public SpriteAnimation(int delay, CycleType cycleType, ISprite... sprites) {
		this.delay = delay;
		this.cycleType = cycleType;
		this.sprites = sprites;
		this.counter = 0;
		this.spriteIndex = 0;
		this.increaseSpriteIndex = true;
	}

	@Override
	public ISprite getCurrentSprite() {
		return this.sprites[this.spriteIndex];
	}

	@Override
	public boolean update() {
		int lastSpriteIndex = this.spriteIndex;
		this.counter++;
		if(counter >= this.delay) {
			this.counter = 0;
			this.spriteIndex += (this.increaseSpriteIndex ? 1 : -1);
			if(this.spriteIndex < 0) {
				this.increaseSpriteIndex = true;
				this.spriteIndex = this.sprites.length > 1 ? 1 : 0;
			}
			else if(this.spriteIndex >= this.sprites.length ) {
				if(this.cycleType == CycleType.Circular) {
					this.spriteIndex = 0;
				}
				else if(this.cycleType == CycleType.BounceBack) {
					this.increaseSpriteIndex = false;
					this.spriteIndex = this.sprites.length > 1 ? this.sprites.length - 2 : 0;
				}
			}
		}
		
		return lastSpriteIndex != this.spriteIndex;
	}

	@Override
	public void reset() {
		this.counter = 0;
		this.spriteIndex = 0;
	}

}
