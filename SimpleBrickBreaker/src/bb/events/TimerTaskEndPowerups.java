package bb.events;

import java.util.TimerTask;

public class TimerTaskEndPowerups  extends TimerTask{

	@Override
	public void run() {
		PowerUp.powerUpEnd();
		
	}

}
