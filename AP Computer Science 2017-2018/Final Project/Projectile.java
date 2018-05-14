import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Ammo {
	private final static double size = 10, damage = 200, speed = 20;
	
	
	public Projectile(int xLoc, int yLoc, double angle) {
		super(xLoc, yLoc, angle, speed, size, size, damage);
	}


	@Override
	public BufferedImage loadIcon() throws IOException {
		return ImageIO.read(new File("FinalProjectParallaxGameGraphics/blueEnergyBall.png"));
	}

}
