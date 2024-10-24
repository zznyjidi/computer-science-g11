/**
 * Calculate and Print all perfect numbers under 1000
 */
public class PerfectNumbers {

	public static void main(String[] args) {
		int i;
		for (i = 2; i <= 1000; i++) {
			// Check if the number if perfect
			if (isPerfectNumber(i)) {
				// Print the number
				System.out.printf("%d is perfect. %nFactors: ", i);
				// Print the number's factor
				printFactors(i);
				System.out.println("\n");
			}
		}
	}

	// Check if the number is a Perfect Number
	private static boolean isPerfectNumber(int number) {
		// Init Variable
		int i, factorSum = 0;
		// Add all factors to factorSum
		for (i = 1; i < number; i++) {
			if (number % i == 0) {
				factorSum += i;
			}
		}
		// Return if the sum is equal to the number. 
		return (number == factorSum);
	}

	// Print all the factors of the number
	private static void printFactors(int number) {
		// Init Variable
		int i;
		// Print all factors
		for (i = 1; i < number; i++) {
			if (number % i == 0) {
				System.out.printf("%d ", i);
			}
		}
	}

}
