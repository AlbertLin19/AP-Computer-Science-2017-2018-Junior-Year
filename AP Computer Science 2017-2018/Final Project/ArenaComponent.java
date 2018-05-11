import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ArenaComponent extends JComponent {
	
	static final int ProjectileID = 1, LaserBeamID = 2, HomingMissileID = 3;
	
	
	ArrayList<MovableGamePiece> p1Projectiles = new ArrayList<MovableGamePiece>();
	ArrayList<MovableGamePiece> p2Projectiles = new ArrayList<MovableGamePiece>();
	Spaceship ship1, ship2;
	
	public ArenaComponent() {
		this.addKeyListener(new KeyBinder());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		Font myFont = new Font("Error Font", Font.BOLD, 20);
		canvas.setFont(myFont);
		
		//draw the projectiles for each player in different colors
		//eventually replace with unique icons loaded from image files
		canvas.setColor(Color.CYAN);
		for (MovableGamePiece piece : p1Projectiles) {
			canvas.draw(piece);
		}
		
		canvas.setColor(Color.RED);
		for (MovableGamePiece piece : p2Projectiles) {
			canvas.draw(piece);
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("C:\\Projects\\MavenSandbox\\src\\main\\resources\\img.jpg"));
		} catch (IOException e) {
			canvas.drawString("ERROR! COULD NOT LOAD AWESOME BACKGROUND PICTURE", 400, 800);
			e.printStackTrace();
		}
	}
	

	public void startGame() {
		//clear all projecticles
		for (int i = 0; i<p1Projectiles.size(); i++) {
			p1Projectiles.remove(0);
		}
		for (int i = 0; i<p2Projectiles.size(); i++) {
			p2Projectiles.remove(0);
		}
		
		
	}
	
	//defining all key bindings
			class KeyBinder implements KeyListener {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			}
}
