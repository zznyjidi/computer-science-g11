import java.util.Scanner;
/**
 * Calculate Fruit Price 
 */
public class FruitPriceCalculator {

	public static void main(String[] args) {
		// Scanner Object
		Scanner scan = new Scanner(System.in);

		// Fruit Prices
		final double bananaPrice = 0.5;
		final double applePrice = 0.7;

		System.out.println("How many fruit do you want? ");

		// Get amount of Banana
		System.out.print("Bananas > ");
		int bananaAmount = scan.nextInt();

		// Get amount of Apple
		System.out.print("Apples > ");
		int appleAmount = scan.nextInt();

		//Calculations
		double bananaCost = bananaPrice * bananaAmount;
		double appleCost = applePrice * appleAmount;
		double totalCost = bananaCost + appleCost;

		//Print out
		System.out.println(
			bananaAmount + " bananas @ $" + bananaPrice + " = $" + bananaCost + "\n" +
			appleAmount + " apples @ $" + applePrice + " = $" + appleCost + "\n" +
			"The total is $" + totalCost
		);

		scan.close();
	}

}
