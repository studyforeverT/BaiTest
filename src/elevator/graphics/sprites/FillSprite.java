package elevator.graphics.sprites;

public class FillSprite extends Sprite implements IFillSprite{
	
	private int oldColor;
	private int[] newColors;
	public FillSprite(SpriteSheet sheet, int x, int y, int size, int oldColor, int... newColors) {
		super(sheet, x, y, size);
		this.oldColor = oldColor;
		this.newColors = newColors;
	}

	@Override
	public int getOldColor() {
		return this.oldColor;
	}

	@Override
	public int getNewColor(int index) {
		return this.newColors[index];
	}

}
