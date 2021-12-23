package elevator.system.subsystem;

import java.awt.Graphics;

public interface IRenderableSubsystem extends ISubsystem {

	void render();
	
	void renderGraphics(Graphics g);
	
}
