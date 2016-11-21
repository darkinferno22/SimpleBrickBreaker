package bb.main;

public class Player {
	public static int lives = 3;

	public static void setLives(int l) {
		lives = l;
	}
	public static void loseALife(){
		lives --;
		System.out.println("LIFE LOST");
	}
	public static int getLives() {
		return lives;
	}
}
