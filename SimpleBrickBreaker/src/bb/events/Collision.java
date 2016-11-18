package bb.events;

import java.util.ArrayList;
import java.util.List;

public class Collision {
	public static List<CollisionListener> clList = new ArrayList<CollisionListener>();

	public static void addListener(CollisionListener cl) {
		clList.add(cl);
	}

	public static void respondToCollision() {
		System.out.println("collision detected");
		for (CollisionListener cl : clList) {
			cl.ballCollision();
		}
	}
}
