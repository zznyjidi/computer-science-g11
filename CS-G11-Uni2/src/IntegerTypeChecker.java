import java.util.Scanner;
/**
 * Check if a integer is negative, positive or zero
 */

public class IntegerTypeChecker {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Init Variables
		int input;
		String numberType;

		// Ask for a integer
		System.out.print("Choose any integer value: ");
		input = scan.nextInt();

		// Close the Scanner
		scan.close();

		// Determine number type
		if (input > 0) {
			numberType = "positive";
		} else if (input < 0) {
			numberType = "negative";
		} else {
			numberType = "zero";
		}

		// Output
		System.out.printf("You entered %d, that integer is %s. ", input, numberType);
	}

}
