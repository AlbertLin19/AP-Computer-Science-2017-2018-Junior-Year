import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuessingGameRunner {

	public static int correctButton = (int) (Math.random() * 3) + 1;
	public static int gameStep = 1;

	public static void main(String[] args) {
		// setting up the game GUI
		JFrame frame = new JFrame("The Monty Hall Problem");
		frame.setSize(900, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setting up button panel
		final JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		
		//making complete game panel to add to frame
		final JLabel gameMessage = new JLabel("Welcome to the Monty Hall Problem! Pick a button.");
		gameMessage.setSize(900, 200);
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(2, 1));
		gamePanel.add(buttons);
		gamePanel.add(gameMessage);

		// defining the events for a click on the button
		class BListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				JButton pressed = (JButton) event.getSource();
				if (gameStep == 1) {
					int chosen = Integer.parseInt(pressed.getText());
					
					// select a remaining button to reveal as incorrect
					if (chosen != correctButton) {
						if (1 != chosen && 1 != correctButton) {
							buttons.remove(0);
							buttons.add(new JLabel("X Incorrect X"), 0);
						} else if (2 != chosen && 2 != correctButton) {
							buttons.remove(1);
							buttons.add(new JLabel("X Incorrect X"), 1);
						} else if (3 != chosen && 3 != correctButton) {
							buttons.remove(2);
							buttons.add(new JLabel("X Incorrect X"), 2);
						}

					} else {
						ArrayList<Integer> remaining = new ArrayList<Integer>();
						for (int i = 1; i <= 3; i++) {
							if (i != chosen) {
								remaining.add(i);
							}
						}
						int showIndex = remaining.get((int) (Math.random()*2))-1;
						buttons.remove(showIndex);
						buttons.add(new JLabel("X Incorrect X"), showIndex);
					}
					gameMessage.setText("An incorrect choice has been revealed. Click a remaining button to either choose to stay with your original choice or switch.");
					
					gameStep = 2;

				} else if (gameStep == 2) {
					buttons.remove(0);
					buttons.add(button1, 0);
					buttons.remove(1);
					buttons.add(button2, 1);
					buttons.remove(2);
					buttons.add(button3, 2);
					frame.repaint();
					
					
					if (Integer.parseInt(pressed.getText()) == correctButton) {
						gameMessage.setText("You won! Pick another button.");
						
					} else {
						gameMessage.setText("You lost! Pick another button");
					}
					correctButton = (int) (Math.random() * 3) + 1;
					
					gameStep = 1;

				}

			}
		}
		button1.addActionListener(new BListener());
		button2.addActionListener(new BListener());
		button3.addActionListener(new BListener());

		frame.add(gamePanel);
		frame.setVisible(true);

	}

}
