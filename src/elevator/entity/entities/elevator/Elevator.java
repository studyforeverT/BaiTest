package elevator.entity.entities.elevator;

import java.util.ArrayList;
import java.util.List;

import elevator.entity.Entity;
import elevator.input.ClickType;
import elevator.input.Mouse;

public class Elevator extends Entity {

	private static final int ElevatorCellMoveTime = 60;
	private static final int ElevatorFloorWaitTime = 180;
	
	private int x, y, numberOfFloors;
	private int currentFloor, currentCell, targetFloor;
	private int moveCount, doorCount;
	private ElevatorAlgorithm algorithm;
	private List<ElevatorCell> cells;
	private ElevatorMarker marker;
	private boolean[] upFloorRequests, downFloorRequests;
	private boolean doorsOpen;
	private ElevatorState elevatorState;
	private DirectionRequestType requestType;
	private Mouse mouse;
	
	public Elevator(ElevatorAlgorithm algorithm, int numberOfFloors, int x, int y, Mouse mouse) {
		super(null);
		this.cells = new ArrayList<ElevatorCell>();
		this.upFloorRequests = new boolean[numberOfFloors];
		this.downFloorRequests = new boolean[numberOfFloors];
		this.x = x;
		this.y = y;
		this.mouse = mouse;
		this.numberOfFloors = numberOfFloors;
		this.algorithm = algorithm;
		this.currentCell = 0;
		this.currentFloor = 1;
		this.elevatorState = ElevatorState.Idle;
		this.requestType = DirectionRequestType.None;
		this.createElevator();
	}
	
	@Override
	public void render() {
		for(ElevatorCell cell : this.cells) {
			cell.render();
		}
		
		this.marker.render();
	}
	
	@Override
	public void update() {
		switch(this.algorithm) {
		case Basic:
			this.updateBasic();
			break;
		}
		this.checkForButtonClick();
	}
	
	public void addFloorRequest(int floor, DirectionRequestType type) {
		if(floor >= 1 && floor <= this.numberOfFloors) {
			if(type == DirectionRequestType.Up) {
				this.upFloorRequests[floor - 1] = true;
			}
			else if(type == DirectionRequestType.Down) {
				this.downFloorRequests[floor - 1] = true;
			}
			
			ElevatorDoors doors = this.findDoors(floor);
			doors.pushButton(type);
		}
	}
	
	public int getNumberOfFloors() {
		return this.numberOfFloors;
	}
	
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	private ElevatorDoors findDoors(int floor) {
		for(int i = 0; i < this.cells.size(); i++) {
			ElevatorCell cell = this.cells.get(i);
			if(cell instanceof ElevatorDoors) {
				ElevatorDoors doors = (ElevatorDoors) cell;
				if(doors.getFloorNumber() == floor) {
					return doors;
				}
			}
		}
		
		return null;
	}
	
	private void updateBasic() {
		if(this.elevatorState != ElevatorState.Idle) {
			this.moveToFloor();
		}
		else if(this.doorsOpen) {
			this.handleOpenDoors();
		}
		else if(this.upFloorRequests[this.currentFloor - 1] || this.downFloorRequests[this.currentFloor - 1]) {
			this.doorCount = 0;
			this.doorsOpen = true;
			this.requestType = this.upFloorRequests[this.currentFloor - 1] ? DirectionRequestType.Up : DirectionRequestType.Down;
		}
		else {
			for(int i = 0; i < this.numberOfFloors; i++) {
				if(this.upFloorRequests[i] || this.downFloorRequests[i]) {
					this.targetFloor = i + 1;
					this.moveCount = 0;
					this.elevatorState = this.targetFloor > this.currentFloor ? ElevatorState.MovingUp : ElevatorState.MovingDown;
					this.requestType = this.upFloorRequests[i] ? DirectionRequestType.Up : DirectionRequestType.Down;
				}
			}
		}
	}
	
	private void handleOpenDoors() {
		ElevatorDoors doors = (ElevatorDoors) (this.cells.get(this.currentCell));
		if(!doors.areOpen()) {
			doors.open();
			doors.clearButton(this.requestType);
			if(this.requestType == DirectionRequestType.Up) {
				this.upFloorRequests[this.currentFloor - 1] = false;
			}
			else if(this.requestType == DirectionRequestType.Down) {
				this.downFloorRequests[this.currentFloor - 1] = false;
			}
		}
		this.doorCount++;
		if(this.doorCount >= ElevatorFloorWaitTime) {
			this.doorCount = 0;
			doors.close();
			this.doorsOpen = false;
		}
	}
	
	private void moveToFloor() {
		if(this.currentFloor == this.targetFloor) {
			this.elevatorState = ElevatorState.Idle;
			this.updateMarker();
		}
		else {
			this.moveCount++;
			this.updateMarker();
			if(this.moveCount >= ElevatorCellMoveTime) {
				this.moveCount = 0;
				this.currentCell += this.elevatorState == ElevatorState.MovingDown ? -1 : 1;
				int cellFloorNumber = this.cells.get(this.currentCell).getFloorNumber();
				if(cellFloorNumber != -1) {
					this.currentFloor = cellFloorNumber;
				}
			}
		}
	}
	
	private void updateMarker() {
		int y = this.y - this.currentCell * ElevatorCell.Size;
		y += (int) (this.moveCount * 1.0 / ElevatorCellMoveTime * ElevatorCell.Size * (this.elevatorState == ElevatorState.MovingUp ? -1 : 1));
		this.marker.setPosition(this.x, y);
	}
	
	private void createElevator() {
		for(int i = 0; i < this.numberOfFloors; i++) {
			this.cells.add(new ElevatorDoors(i + 1, this.x, this.y - i * ElevatorCell.Size * 2));
			this.cells.add(new ElevatorShaft(this.x,  this.y - i * ElevatorCell.Size * 2 - ElevatorCell.Size));
			if(i == 0) {
				this.marker = new ElevatorMarker(this.x, this.y);
			}
			this.upFloorRequests[i] = false;
			this.downFloorRequests[i] = false;
		}
	}
	private void checkForButtonClick() {
		if(this.mouse.getButton(false) == ClickType.LeftClick) {
			int mX = this.mouse.getX();
			int mY = this.mouse.getY();
			for(ElevatorCell cell:this.cells) {
				if(cell instanceof ElevatorDoors) {
					ElevatorDoors doors = (ElevatorDoors) cell;
					if(mX >= doors.getX()+doors.getSize() && mX <= doors.getX()+doors.getSize()+doors.getButtonSize()) {
						if(mY>doors.getY()&& mY<doors.getY()+doors.getSize()/2) {
							this.addFloorRequest(doors.getFloorNumber(), DirectionRequestType.Up);
						}else if(mY>doors.getY()+doors.getSize()/2 && mY<doors.getY()+doors.getSize()) {
							this.addFloorRequest(doors.getFloorNumber(), DirectionRequestType.Down);
						}
					}
				}
			}
		}
	}

}
