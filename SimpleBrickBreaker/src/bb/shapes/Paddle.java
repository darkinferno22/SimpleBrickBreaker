package bb.shapes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bb.main.RunGame;

public class Paddle extends Shape implements KeyListener{
	int speed;
	
	public Paddle(int dx) {
		super(20, RunGame.gf.getWidth()/10, 0, 0, (RunGame.gf.getWidth()-100)/2, RunGame.gf.getHeight()-100);
		speed = dx;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'){
			System.out.println("A typed");
			this.setDx(-speed);
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() =='D'){
			System.out.println("D typed");
			this.setDx(speed);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		System.out.println("Key released");
		this.setDx(0);
		
	}

	
	
}
