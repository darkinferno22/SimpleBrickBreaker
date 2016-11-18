package bb.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import bb.events.Collision;
import bb.events.KeyListenerComponent;
import bb.levels.LevelBase;
import bb.levels.LevelLayouts;
import bb.levels.RandomLevel;
import bb.shapes.BrickBase;
import bb.shapes.Circle;
import bb.shapes.Paddle;
import bb.shapes.Shape;

public class DrawFunctions {

	final static String TITLE_TEXT = "BRICKBREAKER";

	static Random random = new Random();
	static Font titleFont = new Font("title font", Font.BOLD | Font.ITALIC, 40);
	static BufferStrategy bs = RunGame.bs;
	static GameFrame gf = RunGame.gf;
	public static Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
	static FontMetrics fm;
	
	static KeyListenerComponent listen;
	
	public static Paddle paddle = new Paddle(10);

	static Random rand = new Random();
	
	private static LevelBase currentLevel;

	static Circle ball = new Circle(20, 0, 0, RunGame.gf.getWidth() / 2, RunGame.gf.getHeight() / 2);

	static Circle ball1;

	Circle ball2;

	Circle ball3;
	static boolean start = true;
	static int drawF;
	static final int TITLE_SCREEN = 0, RENDER_LEVEL = 1, GAME_OVER = 2;

	// Title Screen Drawing
	public static void titleScreen() {
		fm = g2.getFontMetrics(titleFont);
		int titleTextWidth = fm.stringWidth(TITLE_TEXT);

		do {
			DrawFunctions.clearScreen();
			Shape.updatePosition(paddle);
			do {
				RunGame.startPanel.setLocation((RunGame.gf.getWidth() - RunGame.startPanel.getWidth()) / 2, 300);
				RunGame.startPanel.drawString();
				g2 = (Graphics2D) bs.getDrawGraphics();
				g2.setFont(titleFont);
				g2.setColor(Color.BLACK);
				g2.drawRect(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
				g2.fillRect(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
				g2.drawString(GameFrame.GAME_TITLE, (gf.getWidth() - titleTextWidth) / 2, gf.getHeight() / 6);
				g2.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());

	}

	// Render Level Drawing
	public static void renderLevel(LevelBase l) {

		do {

			if (start) {
				RunGame.updateG2s();
				System.out.println(currentLevel);
				LevelBase.renderLevel(currentLevel);
				
				listen = null;
				
				
				paddle = null;
				paddle = new Paddle(20);
				listen = new KeyListenerComponent(paddle);
				RunGame.gf.addKeyListener(listen);
				ball1 = new Circle(ball, RunGame.gf.getWidth() / 2, RunGame.gf.getWidth() / 2);
				ball1.draw(Color.red);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				start = false;
				ball1.setDx(random.nextInt(20) - 10);
				ball1.setDy(-5);
			}
			RunGame.updateG2s();
			
			ball1.clear();
			paddle.clear();
			// System.out.println(new Point(ball1.getPosX(), ball1.getPosY()));
			Shape.updatePosition(ball1);
			Shape.updatePosition(paddle);
			if (Circle.checkWallCollision(ball1) != 0) {
				System.out.println("Collision");
				Collision.respondToCollision();
				Shape.updatePosition(ball1);
			} else if (Circle.checkBrickCollision(ball1, currentLevel) != 0) {
				System.out.println("Brick Collision");
				Collision.respondToCollision();
				LevelBase.renderLevel(currentLevel);
				Shape.updatePosition(ball1);
				
			} else if(Circle.checkCollision(ball1, paddle)){
				System.out.println("Paddle collision");
				Collision.respondToCollision();
				Shape.updatePosition(ball1);
			}
			
			if(winCheck()){
				int rows = rand.nextInt(3) + 1;
				int columns = rand.nextInt(9) + 2;
				start = true;
				currentLevel = null;
				currentLevel = new RandomLevel(rows, columns, 1.0, LevelLayouts.brickTypes);
				//currentLevel = new LevelBase(rows, columns, 70, 50, 1, LevelLayouts.randomLayout(rows, columns, LevelLayouts.brickTypes));
				System.out.println(currentLevel);
			}

			do {
				RunGame.updateG2s();
				paddle.draw(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
				ball1.draw(Color.ORANGE);
				g2.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	public static void clearScreen() {
		RunGame.updateG2s();
		g2.clearRect(0, 0, RunGame.gf.getWidth(), RunGame.gf.getHeight());
	}
	
	public static boolean winCheck(){
		for(BrickBase[] a : currentLevel.brickArray){
			for(BrickBase b : a){
				if(b != null){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public static LevelBase getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(LevelBase currentLevel) {
		DrawFunctions.currentLevel = currentLevel;
	}
	
	public static boolean withinDistance(int a, int b, int distance){
		if(Math.abs(a-b) <= distance){
			return true;
		}
		
		return false;
	}
}
