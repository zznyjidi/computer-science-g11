
public class KPH2MPH {

	public static void main(String[] args) {
		// Print Header
		System.out.println("KPH\tMPH");

		// Calculate and Print Table
		int i;
		for (i = 60; i <= 130; i+= 10) {
			System.out.printf("%d\t%.1f%n", i, i * 0.6214);
		}
	}

}
