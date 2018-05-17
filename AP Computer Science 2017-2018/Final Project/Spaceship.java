import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Spaceship extends MovableGamePiece {

	final static double healthMax = 1000, shieldMax = 200, maxSpeed = 12, width = 40, height = 100, turnAccel = 0.2, accel = 0.6;
	private double health, damageMult, shield;
	private boolean accelForward, accelBackward, turnLeft, turnRight = false;
	
	private final static int maxAmmoCount = 5;
	private ArrayList<Integer> ammoInventory;
	
	public Spaceship(int xLoc, int yLoc, double orientationAngle, double speed) {
		//passing values to create the positioning info common to all MovableGamePiece objects
		super(xLoc, yLoc, orientationAngle, speed, width, height);
		
		//setting up characteristic variables
		damageMult = 1;
		
		//setting up hitpoints
		health = healthMax;
		shield = shieldMax;
		
		
		//filling up ammo inventory with default weapon
		ammoInventory = new ArrayList<Integer>();
		for (int i = 0; i < maxAmmoCount; i++) {
			ammoInventory.add(ArenaComponent.ProjectileID);
		}
		
		//setting up the object's constant refreshers
		Timer shieldRefresher = new Timer(10000, new ShieldRefresher());
		Timer ammoReloader = new Timer(3000, new AmmoReloader());

		shieldRefresher.start();
		ammoReloader.start();
		//should add in an instance variable to hold the image of the ship, and load the image file here
	}
	
	public double getHealth() {
		return health;
	}
	public double getDamageMult() {
		return damageMult;
	}
	
	public void takeDamage(double damageTaken) {
		shield-=damageTaken;
		if (shield < 0) {
			health+=shield;
			shield = 0;
		}
	}
	
	public int useAmmo() {
		if (ammoInventory.size()>0)
		return ammoInventory.remove(0);
		else
		return -1;
	}
	
	public void addAmmo(int ammoID) {
		ammoInventory.add(ammoID);
		while (ammoInventory.size()>5) {
			ammoInventory.remove(0);
		}
	}
	
	public void changeSpeed(double speedChange) {
		velocity.changeMagnitude(speedChange);
		if (velocity.getMagnitude()>maxSpeed) {
			velocity.setMagnitude(maxSpeed);
		} else if (velocity.getMagnitude()<-maxSpeed) {
			velocity.setMagnitude(-maxSpeed);
		}
		
	}
	
	public void changeVelocityTick() {
		if (accelForward && !accelBackward) {
			changeSpeed(accel);
		} else if (accelBackward && !accelForward) {
			changeSpeed(-accel);
		}
		
		if (turnLeft && !turnRight) {
			changeAngle(turnAccel);
		} else if (turnRight && !turnLeft) {
			changeAngle(-turnAccel);
		}
	
	}
	
	public void setAccelForward(boolean state) {
		accelForward = state;
	}
	public void setAccelBackward(boolean state) {
		accelBackward = state;
	}
	public void setTurnLeft(boolean state) {
		turnLeft = state;
	}
	public void setTurnRight(boolean state) {
		turnRight = state;
	}
	
	class ShieldRefresher implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			shield = shieldMax;
			
		}
		
	}
	
	class AmmoReloader implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (ammoInventory.size() < maxAmmoCount) {
				ammoInventory.add(ArenaComponent.ProjectileID);
			}
			
		}
		
	}

	@Override
	public BufferedImage loadIcon() throws IOException {
		return ImageIO.read(new File("FinalProjectParallaxGameGraphics/fighterJet.png"));
	}

	public double getShield() {
		return shield;
	}

	public int getAmmoCount() {
		return ammoInventory.size();
	}
	
}
