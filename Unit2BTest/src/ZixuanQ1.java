/**
 * Print a triangle with #
 */
public class ZixuanQ1 {

	public static void main(String[] args) {
		// Declare Variable
		int i, j;

		// For each line
		for (i = 0; i < 6; i++) {
			// # at the start
			System.out.print("#");
			// Spaces in the middle
			for (j = 0; j < i; j++) {
				System.out.print(" ");
			}
			// # at the end
			System.out.println("#");
		}
	}

}
