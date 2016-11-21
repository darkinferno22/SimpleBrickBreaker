package bb.shapes.powerups;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import bb.events.PowerUp;
import bb.events.PowerUpListener;
import bb.main.DrawFunctions;
import bb.main.RunGame;
import bb.shapes.Shape;

@SuppressWarnings("serial")
public abstract class PowerUpBase extends JComponent implements PowerUpListener{
	int time, fallSpeed, x, y;
	private boolean activated;
	BufferedImage img;
	
	
	public PowerUpBase(String imageURL, int time, int fallSpeed, int x, int y){
		PowerUp.addListener(this);
		try {
			img = ImageIO.read(new File(imageURL));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.time = time;
		this.fallSpeed = fallSpeed;
		this.x = x;
		this.y = y;
		this.setBounds(x, y, 20, 20);
		setActivated(false);
		
	}
	
	public void updatePosition(){
		this.setLocation(x, y + fallSpeed);
		this.y += fallSpeed;
	}
	
	public void onPaddleCollision(){
		DrawFunctions.powerUpList.remove(this);
		this.setActivated(true);
	}
	
	public void draw(){
		if(!isActivated()){
			RunGame.g2.drawImage(img, this.getBounds().x, this.getBounds().y, 20, 20, this);
		}
	}
	public void clear(){
		RunGame.g2.clearRect(x, y, 20, 20);
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
