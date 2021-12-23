package elevator.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{
	private int mouseX=-1;
	private int mouseY=-1;
	private int mouseB=-1;
	private int mouseBLast=-1;
	public int getX() {
		return this.mouseX;
	}
	public int getY() {
		return this.mouseY;
	}
	public ClickType getButton(boolean hold) {
		if(!hold && this.mouseB == this.mouseBLast) {
			return ClickType.Unknown;
		}
		switch(this.mouseB) {
		case 1:
			return ClickType.LeftClick;
		case 2:
			return ClickType.SrollClick;
		case 3:
			return ClickType.RightClick;
		case 4:
			return ClickType.BackPage;
		case 5:
			return ClickType.ForwardPage;
		default:
			return ClickType.Unknown;
		}
	}
	public void update() {
		this.mouseBLast = this.mouseB;
	}
	public void resetButton() {
		this.mouseB = -1;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseB = e.getButton();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseB = -1;
	}

}
