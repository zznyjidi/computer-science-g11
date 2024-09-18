import java.util.Scanner;

/**
 * Rectangular prism surface area & volume calculator
 */
public class ZixuanQ4 {

	public static void main(String[] args) {
		// Create Scanner
		Scanner scan = new Scanner(System.in);
		
		// Ask for data
		double length, width, height;
		System.out.print("What is the length of your prism? ");
		length = scan.nextDouble();
		System.out.print("Waht is the width of your prism? ");
		width = scan.nextDouble();
		System.out.print("Waht is the height of your prism? ");
		height = scan.nextDouble();
		
		// Calculations
		double surfaceArea, volume;
		surfaceArea = 2 * (length * width + width * height + height * length);
		volume = length * width * height;
		
		// Output
		System.out.println(
			"\nThe surface area of the prism is " + surfaceArea + " cm squared, " + 
			"and the volume of the prism is " + volume + " cm cubed. "
		);
		
		// Close the Scanner
		scan.close();
	}

}
