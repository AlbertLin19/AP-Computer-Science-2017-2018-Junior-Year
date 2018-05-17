import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class ArenaComponent extends JComponent {

	static final int ProjectileID = 1, LaserBeamID = 2, HomingMissileID = 3;

	ArrayList<Ammo> p1Projectiles = new ArrayList<Ammo>();
	ArrayList<Ammo> p2Projectiles = new ArrayList<Ammo>();
	Spaceship ship1, ship2;

	BufferedImage backgroundImage;
	
	Timer gameTickTimer;

	public ArenaComponent() {
		this.addKeyListener(new KeyBinder());

		gameTickTimer = new Timer(40, new GameTickTimer());
		
		// load background image
		try {
			backgroundImage = ImageIO.read(new File("FinalProjectParallaxGameGraphics/spaceBackground.jpg"));
		} catch (IOException e) {
			System.out.println("ERROR! COULD NOT LOAD AWESOME BACKGROUND PICTURE");
			e.printStackTrace();
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		Font myFont = new Font("Game Font", Font.BOLD, 20);
		canvas.setFont(myFont);
		canvas.fillRect(0, 0, getWidth(), getHeight());
		
		if (backgroundImage != null) {
			canvas.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		} else {
			canvas.drawString("GRAPHICS FILE NOT FOUND! MAKE SURE YOU HAVE THE GRAPHICS FOLDER INSIDE THE PROJECT", getWidth()/2-300, getHeight()/2);
		}

		AffineTransform old = canvas.getTransform();
		
		// draw the projectiles for each player in different colors
		// eventually replace with unique icons loaded from image files
		
		canvas.setColor(Color.CYAN);
		for (Ammo ammo : p1Projectiles) {
			AffineTransform transform = AffineTransform.getRotateInstance(-ammo.getVelocityAngle()+Math.PI/2, ammo.getCenterX(), ammo.getCenterY());
			canvas.setTransform(transform);
			canvas.drawImage(ammo.getIcon(), (int) ammo.getX(), (int) ammo.getY(), (int) ammo.getWidth(), (int) ammo.getHeight(), null);
			canvas.draw(ammo);
			canvas.setTransform(old);
		}
		
		AffineTransform transform1 = AffineTransform.getRotateInstance(-ship1.getVelocityAngle()+Math.PI/2, ship1.getCenterX(), ship1.getCenterY());
		canvas.setTransform(transform1);
		canvas.drawImage(ship1.getIcon(), (int) (ship1.getX()-ship1.getWidth()/2), (int) ship1.getY(), (int) ship1.getWidth()*2, (int) ship1.getHeight(), null);
		canvas.setColor(Color.CYAN);
		canvas.draw(ship1);
		canvas.setTransform(old);
		
		canvas.setColor(Color.RED);
		for (Ammo ammo : p2Projectiles) {
			AffineTransform transform = AffineTransform.getRotateInstance(-ammo.getVelocityAngle()+Math.PI/2, ammo.getCenterX(), ammo.getCenterY());
			canvas.setTransform(transform);
			canvas.drawImage(ammo.getIcon(), (int) ammo.getX(), (int) ammo.getY(), (int) ammo.getWidth(), (int) ammo.getHeight(), null);
			canvas.draw(ammo);
			canvas.setTransform(old);
		}
		
		AffineTransform transform2 = AffineTransform.getRotateInstance(-ship2.getVelocityAngle()+Math.PI/2, ship2.getCenterX(), ship2.getCenterY());
		canvas.setTransform(transform2);
		canvas.drawImage(ship2.getIcon(), (int) (ship2.getX()-ship2.getWidth()/2), (int) ship2.getY(), (int) ship2.getWidth()*2, (int) ship2.getHeight(), null);
		canvas.setColor(Color.RED);
		canvas.draw(ship2);
		canvas.setTransform(old);
		
		//drawing health bars, shield bars, and game info
		int barHeight = 30;
		int barWidth = 300;
		int x1 = 200;
		int x2 = this.getWidth()-x1-barWidth;
		int y = this.getHeight()-100;
		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(x1, y, barWidth, barHeight);
		canvas.fillRect(x2, y, barWidth, barHeight);
		
		int healthLength1 = (int) ((ship1.getHealth()/(ship1.healthMax+ship1.shieldMax))*barWidth);
		int healthLength2 = (int) ((ship2.getHealth()/(ship2.healthMax+ship2.shieldMax))*barWidth);
		int shieldLength1 = (int) ((ship1.getShield()/(ship1.healthMax+ship1.shieldMax))*barWidth);
		int shieldLength2 = (int) ((ship2.getShield()/(ship2.healthMax+ship2.shieldMax))*barWidth);
		canvas.setColor(Color.GREEN);
		canvas.fillRect(x1, y, healthLength1, barHeight);
		canvas.fillRect(x2, y, healthLength2, barHeight);
		canvas.setColor(Color.CYAN);
		canvas.fillRect(x1+healthLength1, y, shieldLength1, barHeight);
		canvas.fillRect(x2+healthLength2, y, shieldLength2, barHeight);
		
		//showing ammo count
		
		
		int spacing = 30;
		int size = 16;
		int offset = 10;
		
		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(x1, y-2*offset, barWidth, 2*offset);
		canvas.fillRect(x2, y-2*offset, barWidth, 2*offset);
		
		canvas.setColor(Color.ORANGE);
		for (int i = 0; i < ship1.getAmmoCount(); i++) {
			canvas.fillOval(x1+i*spacing, y-offset-size/2, size, size);
		}
		for (int i = 0; i < ship2.getAmmoCount(); i++) {
			canvas.fillOval(x2+i*spacing, y-offset-size/2, size, size);
		}
		
	}

	public void startGame() {
		gameTickTimer.stop();
		removeAllPieces();
		ship1 = new Spaceship(100, 100, Math.PI*3/2, 0);
		ship2 = new Spaceship(getWidth()-100, getHeight()-200, Math.PI/2, 0);
		
		gameTickTimer.start();
	}

	// defining the game tick actions

	class GameTickTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//accelerate ships
			ship1.changeVelocityTick();
			ship2.changeVelocityTick();
			
			// move all pieces and remove out-of-bounds
			ship1.moveTick();
			ship2.moveTick();
			for (int i = 0; i < p1Projectiles.size(); i++) {
				p1Projectiles.get(i).moveTick();
				if (!inFrame(p1Projectiles.get(i))) {
					p1Projectiles.remove(i);
					i--;
				}
			}
			for (int i = 0; i < p2Projectiles.size(); i++) {
				p2Projectiles.get(i).moveTick();
				if (!inFrame(p2Projectiles.get(i))) {
					p2Projectiles.remove(i);
					i--;
				}
			}
			
			keepShipsInFrame();
			
			repaint();

			// check all collisions
			for (int i = 0; i < p1Projectiles.size(); i++) {
				if (ship2.contains(p1Projectiles.get(i).getBounds2D())) {
					ship2.takeDamage(p1Projectiles.remove(i).getDamage());
					i--;
				}
			}
			for (int i = 0; i < p2Projectiles.size(); i++) {
				if (ship1.contains(p2Projectiles.get(i).getBounds2D())) {
					ship1.takeDamage(p2Projectiles.remove(i).getDamage());
					i--;
				}
			}
			
			if (ship1.getHealth()<0) {
				ship1.x = 100000;
				ship1.y = 100000;
			}
			if (ship2.getHealth()<0) {
				ship2.x = 100000;
				ship2.y = 100000;
			}

		}

	}

	private void removeAllPieces() {
		// clear all projectiles
		for (int i = 0; i < p1Projectiles.size(); i++) {
			p1Projectiles.remove(0);
		}
		for (int i = 0; i < p2Projectiles.size(); i++) {
			p2Projectiles.remove(0);
		}
		
		//nullify the ships
		ship1 = null;
		ship2 = null;
	}
	
	private boolean inFrame(MovableGamePiece obj) {
		return obj.getMinY()<this.getHeight() && obj.getMinX()<this.getWidth() && obj.getMaxX() > 0 && obj.getMaxY() > 0;
	}
	
	private void keepShipsInFrame() {
		if (ship1.getMinX()-ship1.getHeight() < 0) {
			ship1.x = ship1.getHeight();
		} else if (ship1.getMaxX() > getWidth()) {
			ship1.x = getWidth()-ship1.width;
		}
		if (ship1.getMinY() < 0) {
			ship1.y = 0;
		} else if (ship1.getMaxY() > getHeight()) {
			ship1.y = getHeight()-ship1.height;
		}
		
		if (ship2.getMinX()-ship2.getHeight() < 0) {
			ship2.x = ship2.getHeight();
		} else if (ship2.getMaxX() > getWidth()) {
			ship2.x = getWidth()-ship2.width;
		}
		if (ship2.getMinY() < 0) {
			ship2.y = 0;
		} else if (ship2.getMaxY() > getHeight()) {
			ship2.y = getHeight()-ship2.height;
		}
		
	}
	
	//these accessor methods are simply to make sure that the returned constants are consistent
	public int getWidth() {
		return ParallaxGameRunner.arenaWidth;
	}
	
	public int getHeight() {
		return ParallaxGameRunner.arenaHeight;
	}

	// defining all key bindings
	class KeyBinder implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("keyTyped: " + e.getKeyChar());

		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed: " + e.getKeyCode());
			
			if (e.getKeyChar()=='w') {
				ship1.setAccelForward(true);
			}
			else if (e.getKeyChar()=='s') {
				ship1.setAccelBackward(true);
			}
			else if (e.getKeyChar()=='a') {
				ship1.setTurnLeft(true);
			}
			else if (e.getKeyChar()=='d') {
				ship1.setTurnRight(true);
			}
			else if (e.getKeyCode()==38) {
				ship2.setAccelForward(true);
			}
			else if (e.getKeyCode()==40) {
				ship2.setAccelBackward(true);
			}
			else if (e.getKeyCode()==37) {
				ship2.setTurnLeft(true);
			}
			else if (e.getKeyCode()==39) {
				ship2.setTurnRight(true);
			}
			
			else if (e.getKeyCode()==16) {
				int ID = ship1.useAmmo();
				if (ID == ProjectileID) {
					p1Projectiles.add(new Projectile((int) ship1.getCenterX(), (int) ship1.getCenterY(), ship1.getVelocityAngle()));
				} else if (ID == LaserBeamID) {
					//TODO: implement
				} else if (ID == HomingMissileID) {
					//TODO: implement
				}
			}
			
			else if (e.getKeyCode()==32) {
				int ID = ship2.useAmmo();
				if (ID == ProjectileID) {
					p2Projectiles.add(new Projectile((int) ship2.getCenterX(), (int) ship2.getCenterY(), ship2.getVelocityAngle()));
				} else if (ID == LaserBeamID) {
					//TODO: implement
				} else if (ID == HomingMissileID) {
					//TODO: implement
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased: " + e.getKeyChar());
			
			if (e.getKeyChar()=='w') {
				ship1.setAccelForward(false);
			}
			else if (e.getKeyChar()=='s') {
				ship1.setAccelBackward(false);
			}
			else if (e.getKeyChar()=='a') {
				ship1.setTurnLeft(false);
			}
			else if (e.getKeyChar()=='d') {
				ship1.setTurnRight(false);
			}
			else if (e.getKeyCode()==38) {
				ship2.setAccelForward(false);
			}
			else if (e.getKeyCode()==40) {
				ship2.setAccelBackward(false);
			}
			else if (e.getKeyCode()==37) {
				ship2.setTurnLeft(false);
			}
			else if (e.getKeyCode()==39) {
				ship2.setTurnRight(false);
			}

		}

	}
}
