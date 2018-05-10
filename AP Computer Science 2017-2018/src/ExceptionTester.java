import java.awt.Rectangle;

public class ExceptionTester {
	public static Rectangle rect = null;

	public static void main(String[] args) {
		try {
			rect.x = 0;
		} catch (NullPointerException e) {
			//e.printStackTrace();
			System.out.println("This is the first exception");
		} finally {
			int y = 3/0;
			System.out.println("Theoretically unreachable");
		}

	}

}
