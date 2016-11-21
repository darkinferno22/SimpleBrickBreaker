package bb.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import bb.main.DrawFunctions;
import bb.main.RunGame;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel implements MouseListener{
	public GameOverPanel(){
		this.setBounds(0, 0, RunGame.gf.getWidth(), RunGame.gf.getHeight());
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		DrawFunctions.drawF = 0;
		RunGame.gf.add(RunGame.startPanel);
		RunGame.clip.stop();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
