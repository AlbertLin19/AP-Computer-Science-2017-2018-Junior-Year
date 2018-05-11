import java.awt.geom.Ellipse2D;

public class LaserBeam extends Ammo {
	private final static double width = 10000, height = 20, damage = 600;
	
	public LaserBeam(int xLoc, int yLoc, double angle, double speed) {
		super(xLoc, yLoc, angle, speed, width, height, damage);
	}

}
