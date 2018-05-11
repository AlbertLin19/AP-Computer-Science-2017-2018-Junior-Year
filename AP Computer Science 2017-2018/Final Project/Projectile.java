

public class Projectile extends Ammo {
	private final static double size = 10, damage = 200;
	
	
	public Projectile(int xLoc, int yLoc, double angle, double speed) {
		super(xLoc, yLoc, angle, speed, size, size, damage);
	}

}
