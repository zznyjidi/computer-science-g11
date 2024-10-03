import java.util.Scanner;

/**
 * Rock Paper Scissors
 */
public class ZixuanQ2 {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Ask for 2 inputs
		System.out.print("Player 1: Rock, Paper or Scissors? ");
		String player1 = scan.nextLine();
		System.out.print("Player 2: Rock, Paper or Scissors? ");
		String player2 = scan.nextLine();

		// Close the Scanner
		scan.close();

		// Compare plays
		if (player1.equalsIgnoreCase("rock")) {
			if (player2.equalsIgnoreCase("scissors")) {
				System.out.println("Player 1 wins! ");
			} else if (player2.equalsIgnoreCase("paper")) {
				System.out.println("Player 2 wins! ");
			} else if (player2.equalsIgnoreCase("rock")) {
				System.out.println("Tied. ");
			} else {
				System.out.println("Player 2 has a Invalid Input. ");
			}
		} else if (player1.equalsIgnoreCase("paper")) {
			if (player2.equalsIgnoreCase("rock")) {
				System.out.println("Player 1 wins! ");
			} else if (player2.equalsIgnoreCase("scissors")) {
				System.out.println("Player 2 wins! ");
			} else if (player2.equalsIgnoreCase("paper")) {
				System.out.println("Tied. ");
			} else {
				System.out.println("Player 2 has a Invalid Input. ");
			}
		} else if (player1.equalsIgnoreCase("scissors")) {
			if (player2.equalsIgnoreCase("paper")) {
				System.out.println("Player 1 wins! ");
			} else if (player2.equalsIgnoreCase("rock")) {
				System.out.println("Player 2 wins! ");
			} else if (player2.equalsIgnoreCase("scissors")) {
				System.out.println("Tied. ");
			} else {
				System.out.println("Player 2 has a Invalid Input. ");
			}
		} else {
			System.out.println("Player 1 has a Invalid Input. ");
		}

	}

}
