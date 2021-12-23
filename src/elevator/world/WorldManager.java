package elevator.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import elevator.entity.Entity;
import elevator.entity.IEntity;
import elevator.entity.animation.CycleType;
import elevator.entity.animation.ISpriteAnimation;
import elevator.entity.animation.SpriteAnimation;
import elevator.entity.entities.TestEntity;
import elevator.entity.entities.elevator.DirectionRequestType;
import elevator.entity.entities.elevator.Elevator;
import elevator.entity.entities.elevator.ElevatorAlgorithm;
import elevator.graphics.sprites.SpriteSheets;
import elevator.system.ElevatorSimulationSystem;

public class WorldManager implements IWorldManager {

	private List<IEntity> entities;
	private Elevator elevator;
	
	@Override
	public void init() {
		this.entities = new ArrayList<IEntity>();
		this.createBaseEntities();
	}

	@Override
	public void enable() {

	}

	@Override
	public void update() {
		for(IEntity entity : this.entities) {
			entity.update();
		}
		
//		this.testElevator();
	}

	@Override
	public void render() {
		for(IEntity entity : this.entities) {
			entity.render();
		}
	}

	@Override
	public void renderGraphics(Graphics g) {
		
	}
	
	private void createBaseEntities() {
		this.elevator = new Elevator(ElevatorAlgorithm.Basic, 3, 300, 500, ElevatorSimulationSystem.getInstance().getInputManager().getMouse());
		this.entities.add(this.elevator);
	}
	
	private void testElevator() {
		int currentFloor = this.elevator.getCurrentFloor();
		int maxFloor = this.elevator.getNumberOfFloors();
		if(currentFloor < maxFloor) {
			this.elevator.addFloorRequest(maxFloor, DirectionRequestType.Down);
		}
		else {
			this.elevator.addFloorRequest(1, DirectionRequestType.Up);
		}
	}
	

}
