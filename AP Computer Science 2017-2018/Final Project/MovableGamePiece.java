import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class MovableGamePiece extends Ellipse2D.Double {
	protected Vector velocity;
	private BufferedImage image;
	
	public MovableGamePiece(int xLoc, int yLoc, double angle, double speed, double width, double height) {
		super(xLoc, yLoc, width, height);
		velocity = new Vector(angle, speed);
		try {
			image = loadIcon();
		} catch (IOException e) {
			System.out.println("cannot assign graphic to a gamepiece!");
			e.printStackTrace();
		}
	}
	public void moveTick() {
		x+=velocity.getXMagnitude();
		y-=velocity.getYMagnitude();
	}
	
	public void changeSpeed(double speedChange) {
		velocity.changeMagnitude(speedChange);
	}
	
	public void changeAngle(double angleChange) {
		velocity.changeAngle(angleChange);
	}
	
	public double getVelocityAngle() {
		return velocity.getAngle();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage imgIn) {
		image = imgIn;
	}
	
	abstract public BufferedImage loadIcon() throws IOException;

}
