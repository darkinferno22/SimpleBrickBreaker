package bb.levels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bb.main.DrawFunctions;
import bb.main.Player;

public class LivesPanel extends JPanel{
	public LivesPanel(){
		this.setBounds(0, 0, 80, 80);
	}
	
	public void draw(){
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		DrawFunctions.g2.drawString(Player.lives + "", 20, 20);
	}
}
