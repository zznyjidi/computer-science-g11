import java.util.Scanner;

/**
 * Find the largest number
 */
public class ZixuanQ1 {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Ask for 3 Numbers
		System.out.print("Enter a number: ");
		double num1 = scan.nextDouble();

		System.out.print("Enter a number: ");
		double num2 = scan.nextDouble();

		System.out.print("Enter a number: ");
		double num3 = scan.nextDouble();

		// Close the Scanner
		scan.close();

		// Compare Number
		if (num1 == num2 && num2 == num3) {
			System.out.println("There is no largest number because they are all the same! ");
		} else {
			double largestNumber = num1;
			if (num2 > largestNumber) {
				largestNumber = num2;
			} 
			if (num3 > largestNumber) {
				largestNumber = num3;
			}
			System.out.println("The largest number is " + largestNumber + ". ");
		}
	}

}
