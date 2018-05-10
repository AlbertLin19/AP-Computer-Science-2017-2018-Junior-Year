import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClickBaitRunner {
	
	static Timer timer;
	static int currentScore = 0;
	static boolean firstClick = true;
	static boolean running = false;
	static int gamemode = 0;
	static double timeSeconds = 0.0;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("The Click Bait Game");
		frame.setSize(800, 800);
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gamePanel = new JPanel();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		JButton easy = new JButton("Super Easy");
		JButton normal = new JButton("Normal");
		JButton impossible = new JButton("Literally Impossible");
		buttonPanel.add(easy);
		buttonPanel.add(normal);
		buttonPanel.add(impossible);

		JButton restarter = new JButton("Start");
		restarter.setPreferredSize(new Dimension(300, 30));

		ClickBaitComponent canvas = new ClickBaitComponent();
		canvas.setPreferredSize(new Dimension(780, 700));

		gamePanel.add(canvas);
		gamePanel.add(restarter);
		gamePanel.add(buttonPanel);

		// GUI formatted above
		
		//defining what the timer should do automatically
		class TimerListenerNormal implements ActionListener {
			final double delay = 0.75;
			double timeInc = delay;
			int secondsIncrement = 1;
			public void actionPerformed(ActionEvent event) {
				timeSeconds+=0.1;
				if (running && timeSeconds <= 10) {
					if ((int) timeSeconds >= secondsIncrement) {
						canvas.setCurrentTime(secondsIncrement);
						secondsIncrement++;
					}
					if (timeSeconds >= timeInc) {
						canvas.changeBoxLocNormal();
						timeInc+=delay;
						firstClick = true;
					}
				} else {
					
					canvas.setCurrentTime(10);
					if (canvas.getHighScore() < currentScore) {
						canvas.setHighScore(currentScore);
					}
					running = false;
					timer.stop();
				}
				
			}
		}
		
		class TimerListenerEasy implements ActionListener {
			int secondsIncrement = 1;
			public void actionPerformed(ActionEvent event) {
				timeSeconds+=0.1;
				if (running && timeSeconds <= 10) {
					if ((int) timeSeconds >= secondsIncrement) {
						canvas.setCurrentTime(secondsIncrement);
						secondsIncrement++;
					}
				} else {
					
					canvas.setCurrentTime(10);
					if (canvas.getHighScore() < currentScore) {
						canvas.setHighScore(currentScore);
					}
					running = false;
					timer.stop();
				}
				
			}
		}
		
		class TimerListenerImpossible implements ActionListener {
			int secondsIncrement = 1;
			public void actionPerformed(ActionEvent event) {
				timeSeconds+=0.05;
				if (running && timeSeconds <= 10) {
					if ((int) timeSeconds >= secondsIncrement) {
						canvas.setCurrentTime(secondsIncrement);
						secondsIncrement++;
					}
				} else {
					
					canvas.setCurrentTime(10);
					if (canvas.getHighScore() < currentScore) {
						canvas.setHighScore(currentScore);
					}
					running = false;
					timer.stop();
				}
				
				Point p = MouseInfo.getPointerInfo().getLocation();
	            p = new Point(p.x - canvas.getLocationOnScreen().x, p.y - canvas.getLocationOnScreen().y);
	            if(canvas.checkPointImpossible(p) && running) {
					canvas.changeBoxLocImpossible(p);
				}
			}
		}

		//defining the different types of canvas listeners
		class CanvasListenerNormal implements MouseListener {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if(canvas.checkPoint(e.getPoint()) && firstClick && running) {
					firstClick = false;
					currentScore++;
					canvas.setCurrentScore(currentScore);
				}
			}

			public void mouseReleased(MouseEvent e) {
			}

		}
		
		class CanvasListenerEasy implements MouseListener {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if(canvas.checkPoint(e.getPoint()) && running) {
					currentScore++;
					canvas.setCurrentScore(currentScore);
					canvas.changeBoxLocEasy();
				}
			}

			public void mouseReleased(MouseEvent e) {
			}

		}
		
		class CanvasListenerImpossible implements MouseListener {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

		}
		
		class BListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				if (timer != null && timer.isRunning()) {
					timer.stop();
				}
				for (MouseListener mouseList : canvas.getMouseListeners()) {
					canvas.removeMouseListener(mouseList);
				}
				restarter.setText("Restart");
				currentScore = 0;
				timeSeconds = 0.0;
				canvas.setCurrentScore(0);
				canvas.setCurrentTime(0);
				running = true;
				if (gamemode == 0) {
					canvas.addMouseListener(new CanvasListenerNormal());
					canvas.changeBoxLocNormal();
					timer = new Timer(100, new TimerListenerNormal());
					timer.start();
				} else if (gamemode == 1) {
					canvas.addMouseListener(new CanvasListenerEasy());
					canvas.changeBoxLocNormal();
					timer = new Timer(100, new TimerListenerEasy());
					timer.start();
				} else if (gamemode == 2) {
					canvas.addMouseListener(new CanvasListenerImpossible());
					canvas.changeBoxLocNormal();
					timer = new Timer(50, new TimerListenerImpossible());
					timer.start();
				}
			}
		}
		restarter.addActionListener(new BListener());
		
		class OptionListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				JButton pressed = (JButton) event.getSource();
				
				if (pressed.getText().equals("Super Easy")) {
					canvas.setGamemode("Super Easy");
					gamemode = 1;
				} else if (pressed.getText().equals("Normal")) {
					canvas.setGamemode("Normal");
					gamemode = 0;
				} else if (pressed.getText().equals("Literally Impossible")) {
					canvas.setGamemode("Literally Impossible");
					gamemode = 2;
				}
			}
		}
		
		easy.addActionListener(new OptionListener());
		normal.addActionListener(new OptionListener());
		impossible.addActionListener(new OptionListener());
				
				
		frame.add(gamePanel);
		frame.setVisible(true);
	}
	
	

}
