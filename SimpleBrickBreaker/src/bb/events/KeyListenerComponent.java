package bb.events;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

import bb.shapes.Paddle;

public class KeyListenerComponent extends JComponent implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public Paddle p;
	public static final int PADDLE_SPEED = 20;

	
	public KeyListenerComponent(Paddle pad) {
		this.p = pad;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 65) {
			System.out.println("A typed");
			p.setDx(-PADDLE_SPEED);
		}
		else if (e.getKeyCode() == 68) {
			System.out.println("D typed");
			p.setDx(PADDLE_SPEED);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

		System.out.println("Key released");
		p.setDx(0);

	}

}
