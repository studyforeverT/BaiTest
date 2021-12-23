package elevator.entity.animation;

import elevator.entity.Entity;

public class AnimatedEntity extends Entity {
	
	private ISpriteAnimation spriteAnimation;

	public AnimatedEntity(ISpriteAnimation spriteAnimation) {
		super(spriteAnimation.getCurrentSprite());
		this.spriteAnimation = spriteAnimation;
	}
	
	@Override
	public void update() {
		super.update();
		boolean spriteChanged = this.spriteAnimation.update();
		if(spriteChanged) {
			this.sprite = this.spriteAnimation.getCurrentSprite();
		}
	}

}
