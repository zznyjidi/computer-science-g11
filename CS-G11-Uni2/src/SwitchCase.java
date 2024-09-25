import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		char choice = scan.next().charAt(0);

		switch (choice) {
			case 'a':
				System.out.println("Case A");
				break;

			case 'b':
				System.out.println("Case B");
				break;

			case 'c':
				System.out.println("Case C");
				break;

			default:
				System.out.println("Unknown Case");
				break;
		}
		scan.close();
	}

}
