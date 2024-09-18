import java.util.Scanner;

/**
 * Bookstore average book sale calculator
 */
public class ZixuanQ2 {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);
		
		// Ask for Sale
		double totalSale = 0;
		System.out.print("Enter the number of books sold on day #1: ");
		totalSale += scan.nextDouble();
		System.out.print("Enter the number of books sold on day #2: ");
		totalSale += scan.nextDouble();
		System.out.print("Enter the number of books sold on day #3: ");
		totalSale += scan.nextDouble();
		System.out.print("Enter the number of books sold on day #4: ");
		totalSale += scan.nextDouble();
		
		// Calculate & Output Average
		double averageSale = totalSale / 4;
		System.out.println("\nYour Average daily book sales are: " + averageSale);
		
		// Close Scanner
		scan.close();
	}

}
