package bb.shapes;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import bb.events.Collision;
import bb.events.CollisionListener;
import bb.levels.LevelBase;
import bb.main.DrawFunctions;
import bb.main.RunGame;

public class Circle extends Shape implements CollisionListener {

	public boolean inPlay = true;

	// Clone constructor
	public Circle(Circle c, int posX, int posY) {
		super(c, posX, posY);
		circle = new Ellipse2D.Double(this.posX, this.posY, this.getRadius(), this.getRadius());
		Collision.addListener(this);
	}

	public Circle(int radius, int dx, int dy, int posX, int posY) {
		super(radius, dx, dy, posX, posY);
		circle = new Ellipse2D.Double(this.posX, this.posY, this.getRadius(), this.getRadius());
		Collision.addListener(this);
	}

	public Circle(int radius, int dx, int dy, int posX, int posY, boolean inPlay) {
		super(radius, dx, dy, posX, posY);
		circle = new Ellipse2D.Double(this.posX, this.posY, this.getRadius(), this.getRadius());
		this.inPlay = inPlay;
		Collision.addListener(this);
	}

	/*
	 * @return 1 = right wall 2 = left wall 3 = bottom 4 = top
	 */
	public static int checkWallCollision(Circle c) {

		if (c.posX + c.getRadius() >= RunGame.gf.getWidth()) {
			return 1;
		} else if (c.getPosX() - c.getRadius() <= 0) {
			return 2;
		} else if (c.getPosY() + c.getRadius() >= RunGame.gf.getHeight()) {
			System.out.println("Past vert boundary");
			return 3;
		} else if (c.getPosY() - c.getRadius() <= 0) {
			System.out.println("Past vert boundary");
			return 4;
		}

		return 0;
	}

	/*
	 * {
	 * 
	 * @return 1 = right side of ball hits left side of brick 2 = left side of
	 * ball hits right side of wall 3 = bottom side of ball hits top side of
	 * brick 4 = top side of ball hits bottom side of brick
	 */
	public static int checkBrickCollision(Circle c, LevelBase l) {
		for (BrickBase[] a1 : l.brickArray) {

			for (int i = 0; i < a1.length; i++) {
				if (a1[i] == null) {
					continue;
				}
				BrickBase brick = a1[i];
				Shape.updatePosition(brick);
				if (c.getCircle().intersects(brick.getRect())) {
					brick.setStrength(brick.getStrength() - 1);
					if (DrawFunctions.withinDistance(c.getPosX() + c.getRadius(), brick.getPosX(), c.getDx() * 2)) {
						return 1;
					} else if (DrawFunctions.withinDistance(c.getPosX() - c.getRadius(), brick.posX + brick.getWidth(),
							50)) {
						return 2;
					} else if (DrawFunctions.withinDistance(c.getPosY() + c.getRadius(), brick.getPosY(),
							c.getDy() * 2)) {
						return 3;
					} else if (DrawFunctions.withinDistance(c.getPosY() - c.getRadius(),
							brick.getPosY() + brick.getHeight(), Math.abs(c.getDy()) * 5)) {
						System.out.println("Bottom hits top");
						return 4;
					}

				} else {
					g2.setColor(Color.ORANGE);
					DrawFunctions.g2.draw(brick.getRect().getBounds2D());
				}
			}
		}
		return 0;
	}

	@Override
	public void draw(Color c) {
		g2.setColor(c);
		g2.fillOval(this.getPosX(), this.getPosY(), this.getRadius(), this.getRadius());

	}

	@Override
	public void clear() {
		g2.setColor(RunGame.g2.getBackground());
		g2.fillOval(this.getPosX(), this.getPosY(), this.getRadius(), this.getRadius());
	}

	public static boolean checkCollision(Circle s1, Shape s2) {
		if (s1.circle.getBounds2D().intersects(s2.rect.getBounds2D())) {
			return true;
		}

		return false;
	}

	@Override
	public void ballCollision() {

		switch (checkWallCollision(this)) {
		case 0:
			break;
		case 1:
		case 2:
			System.out.println("Flipped Horizontally");
			this.setDx(-this.getDx());
			return;
		case 3:
		case 4:
			System.out.println("Flipped vertically");
			this.setDy(-this.getDy());
			return;
		default:
			return;

		}

		switch (checkBrickCollision(this, DrawFunctions.getCurrentLevel())) {
		case 0:
			break;
		case 1:
		case 2:
			System.out.println("Flipped Horizontally");
			this.setDx(-this.getDx());
			return;
		case 3:
		case 4:
			System.out.println("Flipped vertically");
			this.setDy(-this.dy);
			return;
		default:
			return;

		}

		// paddle collision
		int ballPaddleDifference = -((DrawFunctions.paddle.getPosX() + DrawFunctions.paddle.getWidth() / 2)
				- this.getPosX());
		this.setDy(-dy);

		this.setDx(ballPaddleDifference / 8 + DrawFunctions.paddle.getDx()/5);

	}
}
