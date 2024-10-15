import java.util.Scanner;

/**
 *  Sum Calculator with do-while loop
 */
public class ZixuanBonus {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Declare Variable
		int inputNumber, sum = 0;
		// Ask for input and add to sum until negative number is given. 
		do {
			System.out.print("Add a Number (Negative to Exit): ");
			inputNumber = scan.nextInt();
			if (inputNumber >= 0) {
				sum += inputNumber;
			}
		} while (inputNumber >= 0);

		// Close the Scanner
		scan.close();

		// Print Sum if is is not 0
		if (sum == 0) {
			System.out.println("No Valid Number inputted. ");
		} else {
			System.out.printf("The sum of all number is %d%n", sum);
		}
	}

}
