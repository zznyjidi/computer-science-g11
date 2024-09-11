/**
 * Calculate Sale Price for retail business
 */
public class SalePriceCalculator {

	public static void main(String[] args) {
		// Regular Price and Discount
		double regularPrice = 60.0;
		double discount = 0.2;
		// Sale Price
		double salePrice = regularPrice * (1-discount);
		System.out.println(
			"Regular Price: $" + regularPrice + "\n" +
			"Discount: " + discount*100 + "%\n" + 
			"Sale Price: $" + salePrice
		);
	}

}
