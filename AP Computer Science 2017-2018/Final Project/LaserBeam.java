import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LaserBeam extends Ammo {
	private final static double width = 10000, height = 20, damage = 600;
	
	public LaserBeam(int xLoc, int yLoc, double angle, double speed) {
		super(xLoc, yLoc, angle, speed, width, height, damage);
	}

	@Override
	public BufferedImage loadIcon() throws IOException {
		return ImageIO.read(new File("FinalProjectParallaxGameGraphics/fighterJet.png"));
	}

}
