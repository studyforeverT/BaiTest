package elevator.system.instance;

import java.awt.Graphics;

import elevator.graphics.GraphicsManager;
import elevator.graphics.IGraphicsManager;
import elevator.input.IInputManager;
import elevator.input.InputManager;
import elevator.simulation.ISimulationManager;
import elevator.simulation.SimulationManager;
import elevator.world.IWorldManager;
import elevator.world.WorldManager;

public class ElevatorSimulationInstance implements IElevatorSimulationInstance {

	private IInputManager inputManager;
	private ISimulationManager simulationManager;
	private IGraphicsManager graphicsManager;
	private IWorldManager worldManager;
	
	public ElevatorSimulationInstance() {
		this.inputManager = null;
		this.simulationManager = null;
		this.graphicsManager = null;
		this.worldManager = null;
	}
	
	@Override
	public void create() {
		this.inputManager = new InputManager();
		this.graphicsManager = new GraphicsManager();
		this.worldManager = new WorldManager();
		this.simulationManager = new SimulationManager();
	}

	@Override
	public void instantiate() {
		this.inputManager.init();
		this.graphicsManager.init();
		this.worldManager.init();
		this.simulationManager.init();
	}

	@Override
	public void enable() {
		this.inputManager.enable();
		this.graphicsManager.enable();
		this.worldManager.enable();
		this.simulationManager.enable();
	}

	@Override
	public void update() {
		this.graphicsManager.update();
		this.worldManager.update();
		this.simulationManager.update();
		this.inputManager.update();
	}

	@Override
	public void render() {
		this.graphicsManager.render();
		this.worldManager.render();
		this.simulationManager.render();
	}

	@Override
	public void renderGraphics(Graphics g) {
		this.graphicsManager.renderGraphics(g);
		this.worldManager.renderGraphics(g);
		this.simulationManager.renderGraphics(g);
	}
	
	@Override
	public IInputManager getInputManager() {
		return this.inputManager;
	}
	
	@Override
	public ISimulationManager getSimulationManager() {
		return this.simulationManager;
	}

	@Override
	public IGraphicsManager getGraphicsManager() {
		return this.graphicsManager;
	}

	@Override
	public IWorldManager getWorldManager() {
		return this.worldManager;
	}

}
