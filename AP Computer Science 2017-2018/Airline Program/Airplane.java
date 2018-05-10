
public class Airplane {

	private boolean[][] firstClassLeft;
	private boolean[][] economyLeft;
	private boolean[][] firstClassRight;
	private boolean[][] economyRight;
	
	public Airplane() {
		firstClassLeft = new boolean[5][2];
		economyLeft = new boolean[15][3];
		firstClassRight = new boolean[5][2];
		economyRight = new boolean[15][3];
		
		for (boolean[] row : firstClassLeft) {
			for (boolean filled : row) {
				filled = false;
			}
		}
		
		for (boolean[] row : firstClassRight) {
			for (boolean filled : row) {
				filled = false;
			}
		}
		
		for (boolean[] row : economyLeft) {
			for (boolean filled : row) {
				filled = false;
			}
		}
		
		for (boolean[] row : economyRight) {
			for (boolean filled : row) {
				filled = false;
			}
		}
	}
	
	public boolean addPassengers(String classLevel, int numOfPass, String position) {
		if (classLevel.equalsIgnoreCase("F")) {
			if (numOfPass == 2) {
				for (int i = 0; i < 5; i++) {
					if (!firstClassLeft[i][0] && !firstClassLeft[i][1]) {
						firstClassLeft[i][0] = true;
						firstClassLeft[i][1] = true;
						i = 5;
						return true;
					} else if (!firstClassRight[i][0] && !firstClassRight[i][1]) {
						firstClassRight[i][0] = true;
						firstClassRight[i][1] = true;
						i = 5;
						return true;
					}
				}
				return false;
			} else if (numOfPass == 1) {
				if (position.equalsIgnoreCase("W")) {
					for (int i = 0; i < 5; i++) {
						if (!firstClassLeft[i][0]) {
							firstClassLeft[i][0] = true;
							i = 5;
							return true;
						} else if (!firstClassRight[i][1]) {
							firstClassRight[i][1] = true;
							i = 5;
							return true;
						}
					}
					return false;
				} else if (position.equalsIgnoreCase("A")) {
					for (int i = 0; i < 5; i++) {
						if (!firstClassLeft[i][1]) {
							firstClassLeft[i][1] = true;
							i = 5;
							return true;
						} else if (!firstClassRight[i][0]) {
							firstClassRight[i][0] = true;
							i = 5;
							return true;
						}
					}
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (classLevel.equalsIgnoreCase("E")) {
			
			if (numOfPass == 3) {
				for (int i = 0; i < 15; i++) {
					if (!economyLeft[i][0]&&!economyLeft[i][1]&&!economyLeft[i][2]) {
						economyLeft[i][0] = true;
						economyLeft[i][1] = true;
						economyLeft[i][2] = true;
						i = 15;
						return true;
					} else if (!economyRight[i][0]&&!economyRight[i][1]&&!economyRight[i][2]) {
						economyRight[i][0] = true;
						economyRight[i][1] = true;
						economyRight[i][2] = true;
						i = 15;
						return true;
					}
				}
				return false;
				
			} else if (numOfPass == 2) {
				if (position.equalsIgnoreCase("W")) {
					for (int i = 0; i < 15; i++) {
						if (!economyLeft[i][0]&&!economyLeft[i][1]) {
							economyLeft[i][0] = true;
							economyLeft[i][1] = true;
							i = 15;
							return true;
						} else if (!economyRight[i][1]&&!economyRight[i][2]) {
							economyRight[i][1] = true;
							economyRight[i][2] = true;
							i = 15;
							return true;
						}
					}
					return false;
				} else if (position.equalsIgnoreCase("A")) {
					for (int i = 0; i < 15; i++) {
						if (!economyLeft[i][1]&&!economyLeft[i][2]) {
							economyLeft[i][1] = true;
							economyLeft[i][2] = true;
							i = 15;
							return true;
						} else if (!economyRight[i][0]&&!economyRight[i][1]) {
							economyRight[i][0] = true;
							economyRight[i][1] = true;
							i = 15;
							return true;
						}
					}
					return false;
				} else {
					return false;
				}
			} else if (numOfPass ==1) {
				if (position.equalsIgnoreCase("W")) {
					for (int i = 0; i < 15; i++) {
						if (!economyLeft[i][0]) {
							economyLeft[i][0] = true;
							i = 15;
							return true;
						} else if (!economyRight[i][2]) {
							economyRight[i][2] = true;
							i = 15;
							return true;
						}
					}
					return false;
				} else if (position.equalsIgnoreCase("A")) {
					for (int i = 0; i < 15; i++) {
						if (!economyLeft[i][2]) {
							economyLeft[i][2] = true;
							i = 15;
							return true;
						} else if (!economyRight[i][0]) {
							economyRight[i][0] = true;
							i = 15;
							return true;
						}
					}
					return false;
				} else if (position.equalsIgnoreCase("C")){
					for (int i = 0; i < 15; i++) {
						if (!economyLeft[i][1]) {
							economyLeft[i][1] = true;
							i = 15;
							return true;
						} else if (!economyRight[i][1]) {
							economyRight[i][1] = true;
							i = 15;
							return true;
						}
					}
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public String getSeating() {
		String seating = "";
		for (int i = 0; i < 20; i++) {
			if (i<9) {
				seating+=" ";
			}
			seating+=i+1 + ":";
			if (i<5) {
				seating+=formatViewing(firstClassLeft, i);
				seating+=formatViewing(firstClassRight, i);
			} else {
				seating+=formatViewing(economyLeft, i-5);
				seating+=" ";
				seating+=formatViewing(economyRight, i-5);
			}
			seating+="\n";
		}
		return seating;
	}
	
	public String formatViewing(boolean[][] seats, int index) {
		String printOut = "";
		boolean[] row = seats[index];
		for (boolean taken : row) {
			if (taken) {
				printOut+="*";
				if (row.length<3) {
					printOut+=" ";
				}
			} else {
				printOut+=".";
				if (row.length<3) {
					printOut+=" ";
				}
			}
		}
		return printOut;
	}
}
