import java.util.Scanner;

public class AirplaneMenu {
	
	private Airplane airplane;
	
	public AirplaneMenu() {
		airplane = new Airplane();
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);
		boolean running = true;
		while (running) {
			System.out.println("A)dd S)how Q)uit");
			String userInput = in.nextLine();
			if (userInput.equalsIgnoreCase("Q")) {
				running = false;
			} else if (userInput.equalsIgnoreCase("A")) {
				System.out.println("F)irst E)conomy");
				String classLevel = in.nextLine();
				if (classLevel.equalsIgnoreCase("F")) {
					System.out.println("Passengers? (1-2)");
					int numOfPass = in.nextInt();
					in.nextLine();
					if (numOfPass == 2) {
						if (!airplane.addPassengers("F", 2, "")) {
							System.out.println("No seats available!");
						}
					} else if (numOfPass == 1) {
						System.out.println("A)isle  W)indow");
						String seat = in.nextLine();
						if (!airplane.addPassengers("F", 1, seat)) {
							System.out.println("No seats available!");
						}
					}
					
				} else if (classLevel.equalsIgnoreCase("E")) {
					System.out.println("Passengers? (1-3)");
					int numOfPass = in.nextInt();
					in.nextLine();
					if (numOfPass == 3) {
						if (!airplane.addPassengers("E", 3, "")) {
							System.out.println("No seats available!");
						}
					} else if (numOfPass == 2) {
						System.out.println("A)isle  W)indow");
						String seat = in.nextLine();
						if (!airplane.addPassengers("E", 2, seat)) {
							System.out.println("No seats available!");
						}
					} else if (numOfPass == 1) {
						System.out.println("A)isle C)enter W)indow");
						String seat = in.nextLine();
						if (!airplane.addPassengers("E", 1, seat)) {
							System.out.println("No seats available!");
						}
					}
					
				}
			} else if (userInput.equalsIgnoreCase("S")) {
				System.out.println(airplane.getSeating());
			}
		}
	}

}
