import java.util.Scanner;
/**
 * Greeting with name
 */
public class ZixuanQ1 {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);
		
		// Ask for name
		System.out.print("What is your name?: ");
		String name = scan.nextLine();
		// Greeting the user
		System.out.println("Welcome to Java, " + name + "! We hope you have a magnificent day! ");
		
		// Close Scanner
		scan.close();
	}

}
