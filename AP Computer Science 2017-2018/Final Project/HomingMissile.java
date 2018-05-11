

public class HomingMissile extends Ammo {
	private final static double size = 10, damage = 400;
	
	public HomingMissile(int xLoc, int yLoc, double angle, double speed) {
		super(xLoc, yLoc, angle, speed, size, size, damage);
	}
	

}
