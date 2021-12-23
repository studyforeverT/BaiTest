package elevator.main;

import elevator.graphics.canvas.Display;
import elevator.system.ElevatorSimulationSystem;
import elevator.system.instance.IElevatorSimulationInstance;

public class Main {
	
	public static void main(String[] args) {
		createSystem();
		
		Display display = new Display();
		display.init();
		display.start();
	}
	
	private static void createSystem() {
		IElevatorSimulationInstance instance = ElevatorSimulationSystem.getInstance();
		instance.create();
		instance.instantiate();
		instance.enable();
	}

}
