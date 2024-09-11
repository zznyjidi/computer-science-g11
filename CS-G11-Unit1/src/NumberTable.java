
public class NumberTable {

	public static void main(String[] args) {
		System.out.println("number\tsquare\tcube");
		printTableLine(0, 10);
	}

	private static void printTableLine(int currentNumber, int endNumber) {
		System.out.println(currentNumber + "\t" + (int)Math.pow(currentNumber, 2) + "\t" + (int)Math.pow(currentNumber, 3));
		if (currentNumber < endNumber) {
			printTableLine(currentNumber+1, endNumber);
		}
	}
}
