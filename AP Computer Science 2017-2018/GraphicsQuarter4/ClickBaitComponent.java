import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class ClickBaitComponent extends JComponent {
	private String gamemode;
	private int highScore;
	private int timeSeconds;
	private int currentScore;
	private Rectangle box;

	public ClickBaitComponent() {
		gamemode = "Normal";
		highScore =  0;
		currentScore = 0;
		timeSeconds = 0;
		box = new Rectangle(10, 10, 65, 65);
	}

	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		Font myFont = new Font("Score Font", Font.BOLD, 20);
		canvas.setFont(myFont);
		canvas.drawString("High Score: " + highScore, 10, 650);
		canvas.drawString("Gamemode: " + gamemode, 450, 650);
		canvas.drawString("Current Score: " + currentScore, 580, 30);
		canvas.drawString("Time Left: " + (10-timeSeconds), 300, 30);
		canvas.setColor(Color.CYAN);
		canvas.fill(box);

	}

	public boolean checkPoint(Point p) {
		return box.contains(p);
	}
	
	public void setGamemode(String gamemode) {
		this.gamemode = gamemode;
		this.repaint();
	}
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
		this.repaint();
	}
	
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
		this.repaint();
	}

	public void changeBoxLocNormal() {
		box.setSize(65, 65);
		box.setLocation((int) (Math.random()*(780-box.width)), (int) (Math.random()*(700-box.height)));
		this.repaint();
	}

	public void setCurrentTime(int timeSeconds) {
		this.timeSeconds = timeSeconds;
		this.repaint();
		
	}

	public int getHighScore() {
		return highScore;
	}

	public void changeBoxLocEasy() {
		if (box.width*2 < 780 && box.height*2 < 700)
		box.setSize(box.width*2, box.height*2);
		box.setLocation((int) (Math.random()*(780-box.width)), (int) (Math.random()*(700-box.height)));
		this.repaint();
		
	}

	public void changeBoxLocImpossible(Point p) {
		box.setSize(65, 65);
		if (p.x < box.getLocation().x) {
			if (box.getLocation().x + 80 < 780-box.width)
			box.translate(80, 0);
			else
			box.setLocation(350, 320);
		} else {
			if (box.getLocation().x - 80 > 0)
			box.translate(-80, 0);
			else
			box.setLocation(350, 320);
		}
		if (p.y < box.getLocation().y) {
			if (box.getLocation().y + 80 < 700-box.height)
			box.translate(0, 80);
			else
			box.setLocation(350, 320);
		} else {
			if (box.getLocation().y - 80 > 0)
			box.translate(0, -80);
			else
			box.setLocation(350, 320);
		}
		this.repaint();
		
	}

	public boolean checkPointImpossible(Point p) {
		Point center = new Point((int) (box.x+box.getWidth()/2), (int) (box.y+box.getHeight()/2));
		if (p.distance(center) <= 65) {
			return true;
		} else {
			return false;
		}
	}

}
