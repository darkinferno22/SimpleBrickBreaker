package bb.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class StartGamePanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Graphics2D g2 = (Graphics2D) RunGame.bs.getDrawGraphics();
	static Font f = new Font("Monospaced", Font.ITALIC, 40);
	static FontMetrics fm;

	public StartGamePanel() {

		this.setSize(400, 150);
		this.setLayout(null);
		this.setVisible(true);
		addMouseListener(this);
	}

	public void drawString() {
		fm = g2.getFontMetrics(f);
		g2.setColor(Color.black);
		g2.setFont(f);
		g2.drawString("Start", (int) ((this.getWidth() - fm.stringWidth("Start")) / 2 + this.getBounds().getMinX()),
				(int) ((this.getHeight() - fm.getHeight()) / 2 + this.getBounds().getMinY()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (DrawFunctions.drawF == 0) {
			DrawFunctions.drawF = 1;
			DrawFunctions.start = true;
		}
		
		RunGame.gf.removeKeyListener(DrawFunctions.listen);
		
		RunGame.gf.remove(this);
		
		
		DrawFunctions.clearScreen();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
