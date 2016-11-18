package bb.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import bb.main.RunGame;

public class Shape {
	public static Graphics2D g2 = RunGame.g2;
	int height, width, dx, dy, posX, posY;
	protected int radius;
	Rectangle2D rect;
	Ellipse2D circle;
	// constructor

	public Shape(Shape s, int posX, int posY) {
		this.setRadius(s.getRadius());
		this.dx = s.dx;
		this.dy = s.dy;
		this.posX = posX;
		this.posY = posY;
		rect = new Rectangle2D.Double(this.posX, this.posY, this.width, this.height);
	}

	public Shape(int radius, int dx, int dy, int posX, int posY) {
		this.setRadius(radius);
		this.dx = dx;
		this.dy = dy;
		this.posX = posX;
		this.posY = posY;

		circle = new Ellipse2D.Double(this.posX, this.posY, this.getRadius(), this.getRadius());

	}

	public Shape(int height, int width, int dx, int dy, int posX, int posY) {
		// set basic variables
		this.height = height;
		this.width = width;
		this.dx = dx;
		this.dy = dy;
		this.posX = posX;
		this.posY = posY;

		rect = new Rectangle2D.Double(this.posX, this.posY, this.width, this.height);

	}

	/*
	 * 
	 * @param 1 for topLeft, 2 for topRight, 3 for bottomLeft, 4 for bottomRight
	 */
	public Point getCornerPos(int corner) {
		switch (corner) {
		case 1:
			return new Point(posX, posY);
		case 2:
			return new Point(posX + width, posY);
		case 3:
			return new Point(posX, posY + width);
		case 4:
			return new Point(posX + width, posY + height);
		default:
			return null;
		}
	}

	// Basic collision checking
	public static boolean checkCollision(Shape s1, Shape s2) {
		if (s1.rect.getBounds2D().intersects(s2.rect.getBounds2D())) {
			return true;
		}

		return false;
	}

	public static boolean checkWallCollision(Shape s) {
		if (s.posX < 0 || s.posX + s.getWidth() > (RunGame.gf.getWidth())) {
			if (s.posX < 0 && s.dx < 0) {
				s.setPosX(0);
			}
			if ((s.posX + s.getWidth()) > RunGame.gf.getWidth() && s.dx > 0) {
				s.setPosX(RunGame.gf.getWidth() - s.getWidth());
			}
			return true;
		} else if (s.posY <= 0 || s.posY >= RunGame.gf.getHeight()) {
			if (s.posY < 0) {
				s.posY = 0;
			}
			if (s.posY > RunGame.gf.getHeight()) {
				s.posY = RunGame.gf.getHeight();
			}
		}

		return false;
	}

	// change position
	public static void updatePosition(Shape s) {
		if (s != null) {
			s.posX += s.dx;
			s.posY += s.dy;
			s.rect = null;
			s.rect = new Rectangle2D.Double(s.posX, s.posY, s.width, s.height);
			s.circle = null;
			s.circle = new Ellipse2D.Double(s.posX, s.posY, s.getRadius(), s.getRadius());
		}
	}

	public void clear() {
		g2.clearRect(this.posX, this.posY, this.width, this.height);
	}
	
	public void draw(Color c){
		g2.setColor(c);
		g2.fillRect(this.posX, this.posY, this.width, this.height);
	}
	
	// Getters and Setters
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getPosX() {
		return this.posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Ellipse2D getCircle() {
		return circle;
	}

}
