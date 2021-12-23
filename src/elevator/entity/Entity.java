package elevator.entity;

import elevator.graphics.canvas.Display;
import elevator.graphics.sprites.ISprite;
import elevator.system.ElevatorSimulationSystem;

public class Entity implements IEntity {
	
	protected ISprite sprite;
	protected int x, y;
	
	public Entity(ISprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		ElevatorSimulationSystem.getInstance().getGraphicsManager().renderSprite(this.x, this.y, this.sprite);
	}

}
