package bb.levels;

import java.awt.Graphics2D;

import bb.main.DrawFunctions;
import bb.main.GameFrame;
import bb.main.RunGame;
import bb.shapes.BrickBase;

public abstract class LevelBase {

	public static final int BLOCK_HEIGHT = 60;

	int numBrickRows, numBrickColumns, brickWidth, brickHeight;
	double speed;
	public static Graphics2D g2 = RunGame.g2;
	public BrickBase[][] brickArray;

	public LevelBase(int numBrickRows, int numBrickColumns, double speed, BrickBase[][] brickArray) {
		super();
		this.numBrickRows = numBrickRows;
		this.numBrickColumns = numBrickColumns;
		this.speed = speed;
		this.brickArray = brickArray;
		this.brickWidth = RunGame.gf.getWidth() / numBrickColumns - 2 * GameFrame.BRICK_SPACING;
		this.brickHeight = RunGame.gf.getHeight() / (2 * numBrickRows) - GameFrame.BRICK_SPACING_VERT;
	}

	public static void renderLevel(LevelBase l) {

		// full width = block width * number of blocks + block space * number of
		// blocks + 1
		DrawFunctions.clearScreen();
		int blockWidth = (RunGame.gf.getWidth() - (GameFrame.BRICK_SPACING * (l.numBrickColumns + 1)))
				/ (l.numBrickColumns);

		for (int i = 0; i < l.numBrickRows; i++) {
			for (int j = 0; j < l.numBrickColumns; j++) {
				BrickBase current = l.brickArray[i][j];
				if (current != null && current.getStrength() >= 0) {
					int x = GameFrame.BRICK_SPACING * (j + 1) + blockWidth * j;
					int y = GameFrame.BRICK_SPACING_VERT * (i + 1) + (RunGame.gf.getHeight() / 2) / l.numBrickRows
							+ BLOCK_HEIGHT * i;
					BrickBase.drawBrickCentered(current, x, y, blockWidth, BLOCK_HEIGHT);
					current.setPosX((x + (blockWidth - current.getWidth()) / 2));
					current.setPosY(y + (BLOCK_HEIGHT - current.getHeight()) / 2);

				} else {
					if (current != null) {
						current.clear();
					}
					l.brickArray[i][j] = null;
				}

			}
		}
	}
}
