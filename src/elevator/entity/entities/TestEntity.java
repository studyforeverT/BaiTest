package elevator.entity.entities;

import elevator.entity.animation.AnimatedEntity;
import elevator.entity.animation.ISpriteAnimation;
import elevator.graphics.canvas.Display;

public class TestEntity extends AnimatedEntity {
	
	private int anim;

	public TestEntity(ISpriteAnimation spriteAnimation) {
		super(spriteAnimation);
	}
	
	@Override
	public void update() {
		super.update();
		this.anim++;
		this.x = (int) (200 * Math.cos(this.anim / 100.0) + Display.WIDTH / 2);
		this.y = (int) (200 * Math.sin(this.anim / 100.0) + Display.HEIGHT / 2);
	}

}
