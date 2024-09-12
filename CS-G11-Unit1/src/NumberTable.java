/**
 * Print Number Power Table
 */
public class NumberTable {

	public static void main(String[] args) {
		// Print Header
		System.out.println("number\tsquare\tcube");
		// Print Values
		printTableLine(0, 10);
	}

	private static void printTableLine(int currentNumber, int endNumber) {
		// Print Current Line
		System.out.println(currentNumber + "\t" + (int)Math.pow(currentNumber, 2) + "\t" + (int)Math.pow(currentNumber, 3));
		// Call printTableLine for next line if endNumber is not reached
		if (currentNumber < endNumber) {
			printTableLine(currentNumber+1, endNumber);
		}
	}
}
