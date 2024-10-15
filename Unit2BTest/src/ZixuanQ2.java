import java.util.Random;
import java.util.Scanner;

/**
 * Guessing Game
 * Generate a random number between 1-100, let the user guess in 5 tries. 
 */
public class ZixuanQ2 {

	public static void main(String[] args) {
		Random random = new Random();

		// Generate Random Number & Declare Variables
		int randomNumber = random.nextInt(100);
		int guessCount = 0;
		int answer = -1;

		// Create Scanner
		Scanner scan = new Scanner(System.in);

		// Print Header
		System.out.println(
			"Welcome to my Guessing Game! \n" + 
			"You will have 5 chances to guess random Number from 1-100 (inclusive) \n"
		);

		// Loop Until 5 Guesses or Guessed Correctly
		while (guessCount < 5 && answer != randomNumber) {
			// Ask for input
			System.out.printf("Enter guess #%d: ", guessCount + 1);
			answer = scan.nextInt();

			// Check Answer
			if (answer == randomNumber) {
				// Success Prompt
				System.out.println("\nYou won! :)");
				System.out.printf("The correct number is %d%n", randomNumber);
			} else {
				guessCount++;
				// Hints
				if (answer > randomNumber) {
					System.out.printf("Your guess was too high, you have %d more attempt(s)%n%n", 5 - guessCount);
				} else {
					System.out.printf("Your guess was too low, you have %d more attempt(s)%n%n", 5 - guessCount);
				}
			}
		}

		// Failed Prompt
		if (guessCount == 5) {
			System.out.println("Sorry you loss :(");
			System.out.printf("The correct number was %d%n", randomNumber);
		}

		// Close the Scanner
		scan.close();
	}

}
