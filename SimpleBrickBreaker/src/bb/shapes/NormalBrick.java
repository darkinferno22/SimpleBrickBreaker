package bb.shapes;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class NormalBrick extends BrickBase{
	public NormalBrick(){
		super(50, 50, 0, 0, 0, 0, 1, Color.BLUE);
		this.rect = new Rectangle2D.Double(this.posX, this.posY, this.width, this.height);
	}
}
