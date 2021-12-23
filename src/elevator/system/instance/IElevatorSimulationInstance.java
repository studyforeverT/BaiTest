package elevator.system.instance;

import java.awt.Graphics;

import elevator.graphics.IGraphicsManager;
import elevator.input.IInputManager;
import elevator.simulation.ISimulationManager;
import elevator.world.IWorldManager;

public interface IElevatorSimulationInstance {
	
	void create();
	
	void instantiate();
	
	void enable();
	
	void update();
	
	void render();
	
	void renderGraphics(Graphics g);
	
	IInputManager getInputManager();
	
	ISimulationManager getSimulationManager();
	
	IGraphicsManager getGraphicsManager();
	
	IWorldManager getWorldManager();

}
