package bb.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	int height, width;
	String title;
	public static final String GAME_TITLE = "Simple BrickBreaker";
	public static final int BRICK_SPACING = 50, BRICK_SPACING_VERT = 15;
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public GameFrame(int height, int width, String title){
		//JFrame functions
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setTitle(title);
		//Set variables
		this.width = width;
		this.height = height;
		this.title = title;
	}
	public GameFrame(int height, int width){
		//JFrame Functions
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setTitle(GAME_TITLE);
		
		//Set variables
		this.width = width;
		this.height = height;
		this.title = GAME_TITLE;
	}
	
	public void mouseClicked(MouseEvent e){
		if(DrawFunctions.drawF == 0){
			DrawFunctions.drawF = 1;
		}
	}
}
