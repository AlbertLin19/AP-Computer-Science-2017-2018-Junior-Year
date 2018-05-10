import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GUIRunner {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(1000, 1000);
		
		//optional to stop resizing
		frame.setResizable(false);
		
		frame.setTitle("Collect your mouse data here!");
		
		
		
		//makes the program exit when frame is closed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new FaceComponent());
		
		//always at the end of main
		//because better to finish all customizations before showing
		frame.setVisible(true);
		//the stuff that should go into main should really only have the JFrame stuff
		//other stuff should be elsewhere
		
		//recordMouseTracing(frame);
		
	}
	
	public static void recordMouseTracing(JFrame frame) {
		int timerDelay = 100;
		Timer timer = new Timer(timerDelay, new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Point p = MouseInfo.getPointerInfo().getLocation();
	            p = new Point(p.x - frame.getLocation().x, p.y - frame.getLocation().y);
	            System.out.print("{"+(int)p.getX()+", "+(int)p.getY()+"}, ");

	        }
	    });
	    timer.start();
	}

}
