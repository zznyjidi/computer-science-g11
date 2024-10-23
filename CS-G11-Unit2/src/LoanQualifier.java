import java.util.Scanner;

public class LoanQualifier {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("What is your salary? ");
		double salary = scan.nextDouble();

		System.out.print("How long did you work for? ");
		double workYears = scan.nextDouble();

		scan.close();

		if (salary >= 30000 && workYears >= 2) {
			System.out.println("You are qualified for the loan. ");
		} else {
			System.out.println("You are not qualified for the loan. ");
		}
	}

}
