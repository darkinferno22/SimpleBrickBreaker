package bb.shapes;

import java.awt.Color;

public abstract class BrickBase extends Shape {
	private int strength;
	int health;
	Color color;

	public BrickBase(int height, int width, int dx, int dy, int posX, int posY, int strength, Color color) {
		super(height, width, dx, dy, posX, posY);
		this.setStrength(strength);
		this.health = strength;
		this.color = color;
	}

	public BrickBase(int height, int width, int dx, int dy, int posX, int posY, int strength) {
		super(height, width, dx, dy, posX, posY);
		this.setStrength(strength);
		this.health = strength;
		this.color = Color.BLACK;
	}

	public BrickBase(int height, int width, int dx, int dy, int posX, int posY) {
		super(height, width, dx, dy, posX, posY);
		this.setStrength(1);
		this.health = 1;
		this.color = Color.BLACK;
	}

	public static void drawBrickCentered(BrickBase b, int x, int y, int width, int height) {

		g2.setColor(b.color);
		g2.fillRect((x + (width - b.getWidth()) / 2), (y + (height - b.getHeight()) / 2), b.getWidth(), b.getHeight());
		g2.drawString(b.strength + "", (x + (width - b.getWidth()) / 2) + 1, (y + (height - b.getHeight()) / 2) + 1);
		// g2.setColor(b.color);
		// g2.drawRect((x + b.getWidth())/2, (y + b.getHeight())/2,
		// b.getWidth(), b.getHeight());
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
}
