import java.util.Scanner;

public class SquareTable {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("How many squares do you want? ");
		int squaresCount = scan.nextInt();

		scan.close();

		System.out.println("Number\t\tNumber Squared");
		System.out.println("-------------------------");
		for	(int i = 0; i < squaresCount; i++) {
			System.out.printf("%d\t\t%d\n", i + 1, (int)Math.pow(i + 1, 2));
		}
	}

}
