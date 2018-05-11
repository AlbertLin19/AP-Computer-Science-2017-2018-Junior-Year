import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Spaceship extends MovableGamePiece {

	final static double healthMax = 1000, shieldMax = 200, maxSpeed = 8, width = 120, height = 40;
	private double health, damageMult, shield;
	
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
		return ammoInventory.remove(0);
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
	
}
