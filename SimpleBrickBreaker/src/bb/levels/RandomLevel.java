package bb.levels;

import java.util.ArrayList;
import java.util.Random;

import bb.shapes.BrickBase;

public class RandomLevel extends LevelBase {
	static Random rand = new Random();

	public RandomLevel(int numBrickRows, int numBrickColumns, double speed, ArrayList<BrickBase> array) {
		super(numBrickRows, numBrickColumns, speed, LevelLayouts.randomLayout(numBrickRows, numBrickColumns, array));
		// TODO Auto-generated constructor stub
	}

}
