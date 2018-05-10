
public class RecursionTester {

	public static void main(String[] args) {
		printIsoscelesTriangle(5);

	}
	
	public static void printIsoscelesTriangle(int n) {
		if (n < 1) {
			return;
		}
		printIsoscelesTriangle(n-1);
		printBase(n);
		System.out.println();
	}
	
	public static void printBase(int n) {
		if (n < 1) {
			return;
		}
		printBase(n-1);
		System.out.print("~");
	}

}
