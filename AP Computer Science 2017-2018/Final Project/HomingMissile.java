import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class HomingMissile extends Ellipse2D.Double {
	private final static double size = 10;
	private Vector velocity;
	
	public HomingMissile(int xLoc, int yLoc, double angle, double speed) {
		super(xLoc, yLoc, size, size);
		velocity = new Vector(angle, speed);
	}
	
	public void moveTick() {
		x+=velocity.getXMagnitude();
		y+=velocity.getYMagnitude();
	}

}
