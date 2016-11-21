package bb.shapes;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import bb.events.Collision;
import bb.events.CollisionListener;
import bb.levels.LevelBase;
import bb.main.DrawFunctions;
import bb.main.Player;
import bb.main.RunGame;
import bb.shapes.powerups.BigBalls;

public class Circle extends Shape implements CollisionListener {

	public boolean inPlay = true;
	static Random random = new Random();
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
	public static String checkBrickCollision(Circle c, LevelBase l) {

		String r = "";

		for (BrickBase[] a1 : l.brickArray) {

			for (int i = 0; i < a1.length; i++) {
				if (a1[i] == null) {
					continue;
				}
				BrickBase brick = a1[i];
				Shape.updatePosition(brick);
				
				if (c.getCircle().getBounds().intersects(brick.getRect())) {
					if(random.nextInt(2) == 0){
						DrawFunctions.powerUpList.add(new BigBalls(brick.getPosX(), brick.getPosY()));
					}
					brick.setStrength(brick.getStrength() - 1);
					if (DrawFunctions.withinDistance(c.getPosX() + c.getRadius(), brick.getPosX(), 5)) {
						r += "1";
					} else if (DrawFunctions.withinDistance(c.getPosX() - c.getRadius(), brick.posX + brick.getWidth(),
							30)) {
						r += "2";
					}
					if (DrawFunctions.withinDistance(c.getPosY() + c.getRadius(), brick.getPosY(), 5)) {
						r += "3";
					} else if (DrawFunctions.withinDistance(c.getPosY() - c.getRadius(),
							brick.getPosY() + brick.getHeight(), 30)) {
						System.out.println("Bottom hits top");
						r += "4";
					}
					System.out.println(r);
				}
			}
		}
		return r;
	}

	@Override
	public void draw(Color c) {
		g2.setColor(c);
		g2.drawOval(this.getPosX(), this.getPosY(), this.getRadius(), this.getRadius());
		g2.drawImage(DrawFunctions.kappa, this.getPosX(), this.getPosY(), this.getRadius(), this.getRadius(), this);

	}

	@Override
	public void clear() {
		g2.setColor(RunGame.g2.getBackground());
		g2.fillOval(this.getPosX(), this.getPosY(), this.getRadius()+1, this.getRadius()+1);
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
			break;
		case 3:
			this.setDy(-this.getDy());
			Player.loseALife();
			DrawFunctions.start = true;
			break;
		case 4:
			System.out.println("Flipped vertically");
			this.setDy(-this.getDy());
			break;
		default:
			break;

		}

		String s = checkBrickCollision(this, DrawFunctions.getCurrentLevel());
		System.out.println(s);
		if (s != "") {

			if (s.contains("1") || s.contains("2")) {
				this.setDx(-this.getDx());
			}
			if (s.contains("3") || s.contains("4")) {
				System.out.println("VErT");
				this.setDy(-this.getDy());
			}
			return;
		}
		// paddle collision
		if (Circle.checkCollision(this, DrawFunctions.paddle)) {
			int ballPaddleDifference = -((DrawFunctions.paddle.getPosX() + DrawFunctions.paddle.getWidth() / 2)
					- this.getPosX());
			this.setDy(-dy);

			this.setDx(ballPaddleDifference / 6 + DrawFunctions.paddle.getDx() / 4);
			return;
		}

	}
}
