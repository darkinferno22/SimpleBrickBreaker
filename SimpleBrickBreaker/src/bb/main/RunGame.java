package bb.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import bb.events.KeyListenerComponent;
import bb.levels.LevelBase;
import bb.levels.LevelLayouts;
import bb.levels.NormalLevel;
import bb.levels.OneBrickLevel;
import bb.shapes.Shape;

public class RunGame {

	public static GameFrame gf;
	public static BufferStrategy bs;
	public static boolean done;
	public static Graphics2D g2;
	public static StartGamePanel startPanel;
	public static int frame = 0;
	static LevelBase n;
	
	public static void setup() {
		
		gf = new GameFrame(800, 1280, "BrickBreaker");
		
		gf.setVisible(true);
		gf.createBufferStrategy(2);
		bs = gf.getBufferStrategy();
		g2 = (Graphics2D) bs.getDrawGraphics();
		
		startPanel = new StartGamePanel();
		gf.add(startPanel);
		
		
		LevelLayouts.init();
		n = new OneBrickLevel();
		
		DrawFunctions.setCurrentLevel(n);
		updateG2s();
		
		DrawFunctions.listen = new KeyListenerComponent(DrawFunctions.paddle);
		gf.addKeyListener(DrawFunctions.listen);
		DrawFunctions.drawF = 0;
	}
	
	public static void g2Setup(){
		g2.setBackground(Color.DARK_GRAY);
	}
	
	public static void updateG2s(){
		g2 = (Graphics2D) bs.getDrawGraphics();
		g2Setup();
		DrawFunctions.g2 = g2;
		LevelBase.g2 = g2;
		StartGamePanel.g2 = g2;
		Shape.g2 = g2;
	}

	public static void main(String[] args) {
		setup();
		System.out.println(DrawFunctions.g2.getBackground());
		DrawFunctions.clearScreen();
		while (!done) {
			updateG2s();
			switch (DrawFunctions.drawF) {
			case DrawFunctions.TITLE_SCREEN:
				DrawFunctions.titleScreen();
				break;
			case DrawFunctions.RENDER_LEVEL:
				DrawFunctions.renderLevel(n);
				break;
			case DrawFunctions.GAME_OVER:
				break;
			default:
				DrawFunctions.titleScreen();
			}
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g2.dispose();
			++frame;
		}
	}

}
