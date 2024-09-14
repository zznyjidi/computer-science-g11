import java.util.Scanner;
/**
 * Use Scanner to get Data
 */
public class UserInput {

	public static void main(String[] args) {
		// Scanner Object
		Scanner scan = new Scanner(System.in);

		// Get name
		System.out.print("What is your name? > ");
		String name = scan.nextLine();

		// Get Weekly Work Hours
		System.out.print("How many hours did you work this week? > ");
		int workHours = scan.nextInt();

		// Get Hourly Pay
		System.out.print("What is your hourly pay rate? > ");
		double hourlyPay = scan.nextDouble();

		// Calculate & Print out
		System.out.println(
			"Hello, " + name + "! \n" +
			"Your gross pay is $" + (workHours * hourlyPay)
		);

		//Close Scanner
		scan.close();
	}

}
