import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item extends MovableGamePiece {
	final int itemID;
	static final int size = 50;

	public Item(int xLoc, int yLoc, int itemIDIn) {
		super(xLoc, yLoc, Math.PI/2, 0, size, size);
		itemID = itemIDIn;
		try {
			setImage(loadIcon());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage loadIcon() throws IOException {
		if (itemID == ArenaComponent.LaserBeamID) {
			return ImageIO.read(new File("FinalProjectParallaxGameGraphics/laserbeam.png"));
		} else {
			return ImageIO.read(new File("FinalProjectParallaxGameGraphics/homingMissile.png"));
		}
	}

}
