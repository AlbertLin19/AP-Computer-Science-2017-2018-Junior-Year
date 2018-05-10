import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawingComponent extends JComponent {
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		canvas.drawRect(20, 20, 75, 75);
		canvas.setColor(new Color(156, 212, 143));
		canvas.fillRect(100, 100, 300, 400);
		canvas.drawOval(100, 600, 100, 200);
		
		canvas.setColor(Color.BLUE);
		
		//to change the thickness of paint stroke
		canvas.setStroke(new BasicStroke(20));
		canvas.drawLine(0, 0, 800, 800);
		
		Font myFont = new Font("Kinney", Font.BOLD, 80);
		canvas.setFont(myFont);
		canvas.drawString("hey!!!", 300, 350);
		
		canvas.setColor(Color.ORANGE);
		//for triangles and other polygons
		int[] xCoords = {50, 100, 150};
		int[] yCoords = {50, 200, 150};
		//the third paramter is how many points to use from the array
		canvas.fillPolygon(xCoords, yCoords, 3);
		
		//check these ones out later: drawArc and fillArc, drawPolyLine
		
	
	}
	
}
