package elevator.input;

public class InputManager implements IInputManager{
	private Mouse mouse;
	@Override
	public void init() {
		this.mouse = new Mouse();
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.mouse.update();
		
	}

	@Override
	public Mouse getMouse() {
		return this.mouse;
	}

}
