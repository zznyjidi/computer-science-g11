import java.util.Scanner;

public class AverageMark {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Init Variable
		double total = 0;
		int count = -1; // In the first round, the count++ will put count back to 0
		double input = 0;

		// Ask for input
		while (input != -1) {
			total += input;
			count++;
			System.out.print("Input a Mark(-1 to End): ");
			input = scan.nextDouble();
		}

		// Close the Scanner
		scan.close();

		// Output
		if (count < 0) {
			double classAverage = total / count;
			System.out.println("Class Average: " + classAverage);
		} else {
			System.out.println("No mark inputted. ");
		}
	}

}
