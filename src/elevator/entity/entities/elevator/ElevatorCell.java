package elevator.entity.entities.elevator;

import elevator.entity.Entity;
import elevator.graphics.sprites.ISprite;
import elevator.graphics.sprites.SpriteSheets;
import elevator.system.ElevatorSimulationSystem;

public class ElevatorCell extends Entity {
	
	public static final int Size = 100;

	protected static final ISprite ClosedSprite = ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.Elevator, 0, 0, Size);
	protected static final ISprite OpenSprite = ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.Elevator, 1, 0, Size);
	protected static final ISprite ShaftSprite = ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.Elevator, 0, 1, Size);
	protected static final ISprite MarkerSprite = ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.Elevator, 1, 1, Size);

	public ElevatorCell(ISprite sprite) {
		super(sprite);
	}
	
	public int getFloorNumber() {
		return -1;
	}

}
