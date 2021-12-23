package elevator.entity.entities.elevator;

public class ElevatorMarker extends ElevatorCell {

	public ElevatorMarker(int x, int y) {
		super(MarkerSprite);
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
