package bb.levels;

import java.util.ArrayList;
import java.util.Random;

import bb.shapes.BrickBase;
import bb.shapes.DoubleStrengthBrick;
import bb.shapes.NormalBrick;
import bb.shapes.Shape;

public class LevelLayouts {
	static Random rand = new Random();
	public static BrickBase[][] NORMAL_LAYOUT;
	public static BrickBase[][] ONE_BRICK_LAYOUT;
	public static ArrayList<BrickBase> brickTypes = new ArrayList<BrickBase>();

	public static void init() {
		// Normal Layout
		NORMAL_LAYOUT = new BrickBase[3][10];
		for (int i = 0; i < NORMAL_LAYOUT.length; i++) {
			for (int j = 0; j < NORMAL_LAYOUT[0].length; j++) {
				NORMAL_LAYOUT[i][j] = new NormalBrick();
				Shape.updatePosition(NORMAL_LAYOUT[i][j]);
			}
		}

		// One brick layout
		ONE_BRICK_LAYOUT = new BrickBase[1][1];
		ONE_BRICK_LAYOUT[0][0] = new NormalBrick();

		brickTypes.add(new NormalBrick());
		brickTypes.add(new DoubleStrengthBrick());
		brickTypes.add(null);
	}

	public static BrickBase[][] randomLayout(int height, int width, ArrayList<BrickBase> array) {

		BrickBase[][] layout = new BrickBase[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int type = rand.nextInt(array.size());
				switch (type) {
				case 1:
					layout[i][j] = new DoubleStrengthBrick();
					break;
				case 2:
					layout[i][j] = null;
				default:
					layout[i][j] = new NormalBrick();
					break;
				}
			}
		}
		
		return layout;

	}
}
