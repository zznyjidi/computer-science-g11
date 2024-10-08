import java.util.Scanner;
/**
 * Calculate Salary when salary 
 * start at 1 penny and double everyday. 
 */
public class SalaryCalculator {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Ask for days worked until it is more than 0
		int daysWorked = 0;
		while (daysWorked <= 0) {
			System.out.print("Enter the number of days worked: ");
			daysWorked = scan.nextInt();
			if (daysWorked <= 0) {
				System.out.println("Days worked must be more than 0. ");
			}
		}

		// Close the Scanner
		scan.close();

		// Print Header
		System.out.println("\nDay\tSalary\tTotal Salary");
		// Calculate and Print Header
		double currentSalary = 0.01;
		double totalSalary = 0;
		for (int currentDay = 0; currentDay < daysWorked; currentDay++) {
			totalSalary += currentSalary;
			System.out.printf("%d\t$%.2f\t$%.2f\n", currentDay + 1, currentSalary, totalSalary);
			currentSalary *= 2;
		}
	}

}
