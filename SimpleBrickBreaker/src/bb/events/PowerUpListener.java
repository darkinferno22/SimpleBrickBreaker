package bb.events;

public interface PowerUpListener {
	void onPaddleCollision();
	void onPowerUpEnd();
	void onSpawn();
}
