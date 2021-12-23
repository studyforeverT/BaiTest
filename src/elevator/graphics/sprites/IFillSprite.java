package elevator.graphics.sprites;

public interface IFillSprite extends ISprite{
	int getOldColor();
	int getNewColor(int index);
}
