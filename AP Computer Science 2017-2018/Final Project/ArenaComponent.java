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
	ArrayList<Shape> objects = new ArrayList<Shape>();
	Spaceship ship1, ship2;
	
	public ArenaComponent() {
		this.addKeyListener(new KeyBinder());
		ship1 = new Spaceship();
		ship2 = new Spaceship();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		Font myFont = new Font("Error Font", Font.BOLD, 20);
		canvas.setFont(myFont);
		
		canvas.setColor(Color.CYAN);
		for (Shape obj : objects) {
			canvas.draw(obj);
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("C:\\Projects\\MavenSandbox\\src\\main\\resources\\img.jpg"));
		} catch (IOException e) {
			canvas.drawString("ERROR! COULD NOT LOAD AWESOME BACKGROUND PICTURE", 400, 800);
			e.printStackTrace();
		}
	}
	
	public void moveShip(int shipNumber, int dX, int dY) {
		if (shipNumber == 1) {
			Point newPoint = new Point((int) ship1.getX()+dX, (int) ship1.getY()+dY);
			ship1.setLocation(newPoint);
		} else if (shipNumber == 2) {
			
		}
	}

	public void startGame() {
		for (int i = 0; i<objects.size(); i++) {
			objects.remove(0);
		}
		Spaceship ship1 = new Spaceship();
		Spaceship ship2 = new Spaceship();
		
		
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
