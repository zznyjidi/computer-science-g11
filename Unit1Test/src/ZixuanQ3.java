import java.util.Scanner;

/**
 * Simple 2 Number Calculator
 */
public class ZixuanQ3 {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);
		
		// Ask for number
		System.out.print("Enter an integer: ");
		int num1 = scan.nextInt();
		System.out.print("Enter another integer: ");
		int num2 = scan.nextInt();
		
		// Calculate and Output
		System.out.println(
			"\nThe sum of these two numbers are: " + (num1 + num2) +
			"\nThe difference of these two numbers are: " + (num1 - num2) +
			"\nThe product of these two numbers are: " + (num1 * num2) +
			"\nThe quotient of these two numbers are: " + ((double)num1 / (double)num2) +
			"\n" + num1 + " to the exponet " + num2 + " is: " + (int)Math.pow(num1, num2)
		);
		
		// Close the Scanner
		scan.close();
	}

}
