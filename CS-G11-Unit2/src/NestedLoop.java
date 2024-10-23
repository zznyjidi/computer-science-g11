import java.util.Scanner;

public class NestedLoop {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int totalMark, inputCount;
		int input = 0;
		while (input != -2) {
			input = 0;
			totalMark = 0;
			inputCount = -1; // Will be 0 in first iteration
			while (input != -1 && input != -2) {
				totalMark += input;
				inputCount++;
				System.out.print("Input a mark(-1 to finish, -2 to quit): ");
				input = scan.nextInt();
			}
			if (inputCount > 0) {
				System.out.printf("Average Mark: %.2f%n", (double)totalMark / inputCount);
			} else {
				System.out.println("No Mark Entered. ");
			}
		}

		scan.close();
	}
}
