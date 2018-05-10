import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Spaceship extends Rectangle {

	final double healthMax, damageMax, shieldMax, accelMax, speedMax;
	private double health, damage, shield, currentXSpeed, currentYSpeed;
	
	public Spaceship() {
		healthMax = 1000;
		damageMax = 200;
		shieldMax = 200;
		accelMax = 2;
		speedMax = 8;
		health = healthMax;
		damage = damageMax;
		shield = shieldMax;
		currentXSpeed = 0;
		currentYSpeed = 0;
		
		Timer shieldRefresher = new Timer(10000, new ShieldRefresher());
	}
	
	public void setHealth(double healthIn) {
		health = healthIn;
	}
	public void setDamage(double damageIn) {
		damage = damageIn;
	}
	public void setShield(double shieldIn) {
		shield = shieldIn;
	}
	
	public double getHealth() {
		return health;
	}
	public double getDamage() {
		return damage;
	}
	public double getShield() {
		return shield;
	}
	
	public void takeDamage(double damageTaken) {
		shield-=damageTaken;
		if (shield < 0) {
			health+=shield;
			shield = 0;
		}
	}
	
	class ShieldRefresher implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			shield = shieldMax;
			
		}
		
	}
	
}
