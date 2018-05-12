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

		// draw the projectiles for each player in different colors
		// eventually replace with unique icons loaded from image files
		canvas.setColor(Color.CYAN);
		for (Ammo ammo : p1Projectiles) {
			canvas.draw(ammo);
		}
		canvas.fill(ship1);
		

		canvas.setColor(Color.RED);
		for (Ammo ammo : p2Projectiles) {
			canvas.draw(ammo);
		}
		canvas.fill(ship2);
		
		

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
			
			// move all pieces
			ship1.moveTick();
			ship2.moveTick();
			for (Ammo ammo : p1Projectiles) {
				ammo.moveTick();
			}
			for (Ammo ammo : p2Projectiles) {
				ammo.moveTick();
			}
			
			repaint();

			// check all collisions
			for (Ammo p1 : p1Projectiles) {
				if (ship2.contains(p1.getBounds2D())) {
					ship2.takeDamage(p1.getDamage());
				}
			}
			for (Ammo p2 : p2Projectiles) {
				if (ship1.contains(p2.getBounds2D())) {
					ship1.takeDamage(p2.getDamage());
				}
			}
			
			if (ship1.getHealth()<0 || ship2.getHealth()<0) {
				gameTickTimer.stop();
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
			if (e.getKeyChar()=='s') {
				ship1.setAccelBackward(true);
			}
			if (e.getKeyChar()=='a') {
				ship1.setTurnLeft(true);
			}
			if (e.getKeyChar()=='d') {
				ship1.setTurnRight(true);
			}
			if (e.getKeyCode()==38) {
				ship2.setAccelForward(true);
			}
			if (e.getKeyCode()==40) {
				ship2.setAccelBackward(true);
			}
			if (e.getKeyCode()==37) {
				ship2.setTurnLeft(true);
			}
			if (e.getKeyCode()==39) {
				ship2.setTurnRight(true);
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased: " + e.getKeyChar());
			
			if (e.getKeyChar()=='w') {
				ship1.setAccelForward(false);
			}
			if (e.getKeyChar()=='s') {
				ship1.setAccelBackward(false);
			}
			if (e.getKeyChar()=='a') {
				ship1.setTurnLeft(false);
			}
			if (e.getKeyChar()=='d') {
				ship1.setTurnRight(false);
			}
			if (e.getKeyCode()==38) {
				ship2.setAccelForward(false);
			}
			if (e.getKeyCode()==40) {
				ship2.setAccelBackward(false);
			}
			if (e.getKeyCode()==37) {
				ship2.setTurnLeft(false);
			}
			if (e.getKeyCode()==39) {
				ship2.setTurnRight(false);
			}

		}

	}
}
