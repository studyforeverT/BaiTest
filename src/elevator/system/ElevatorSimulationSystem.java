package elevator.system;

import elevator.system.instance.ElevatorSimulationInstance;
import elevator.system.instance.IElevatorSimulationInstance;

public class ElevatorSimulationSystem {
	
	private static IElevatorSimulationInstance instance = null;
	
	public static IElevatorSimulationInstance getInstance() {
		if(instance == null) {
			createInstance();
		}
		
		return instance;
	}
	
	private static void createInstance() {
		instance = new ElevatorSimulationInstance();
	}

}
