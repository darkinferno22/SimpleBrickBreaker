package bb.shapes.powerups;

import bb.events.PowerUp;
import bb.events.TimerTaskEndPowerups;
import bb.main.DrawFunctions;
import bb.main.RunGame;
import bb.shapes.Circle;

@SuppressWarnings("serial")
public class BigBalls extends PowerUpBase {

	public BigBalls(int x, int y) {
		super("src/images/Smorc.png", 10, 4, x, y);
	}

	@Override
	public void onPaddleCollision() {
		if (this.getBounds().intersects(DrawFunctions.paddle.getRect())) {
			this.fallSpeed = 0;
			this.setBounds(0, 0, 0, 0);
			this.setActivated(true);
			if (!RunGame.bigBall) {
				for (Circle ball : DrawFunctions.ballsInPlay) {
					ball.setRadius(ball.getRadius() + 10);
				}
				RunGame.bigBall = true;
			} 
			
		}
		PowerUp.powerUpTimer.schedule(new TimerTaskEndPowerups(), this.time*1000);
	}

	@Override
	public void onPowerUpEnd() {
		for (Circle ball : DrawFunctions.ballsInPlay) {
			ball.setRadius(ball.getRadius() - 10);
		}
		RunGame.bigBall = false;

	}

	@Override
	public void onSpawn() {

	}

}
