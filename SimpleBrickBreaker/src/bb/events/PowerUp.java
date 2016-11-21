package bb.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUp {
	
	public static Timer powerUpTimer = new Timer();
	
	static List<PowerUpListener> pllist = new ArrayList<PowerUpListener>();
	
	public static void addListener(PowerUpListener pl){
		pllist.add(pl);
	}
	
	public static void powerUpPaddle(){
		for(PowerUpListener pl : pllist){
			pl.onPaddleCollision();
		}
	}
	
	public static void powerUpEnd(){
		for(PowerUpListener pl : pllist){
			pl.onPowerUpEnd();
		}
	}
	public static void spawn(){
		for(PowerUpListener pl : pllist){
			pl.onSpawn();
		}
	}
}
