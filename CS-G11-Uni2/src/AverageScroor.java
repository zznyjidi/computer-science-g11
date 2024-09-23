import java.util.Scanner;

public class AverageScroor {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int totalMark = 0, markCount = 0;
		String input = "";
		while (true) {
			System.out.print("Add a mark (Type end to stop): ");
			input = scan.nextLine();
			if (input.equals("end")) {
				if (markCount == 0) {
					System.out.println("You didn't input any mark! ");
				} else {
					break;
				}
			} else {
				try {
					int mark = Integer.parseInt(input);
					if (mark > 100 || mark < 0) {
						System.out.println(mark + " is not a valid mark! ");
					} else {
						totalMark += mark;
						markCount++;
					}
				} catch (java.lang.NumberFormatException e) {
					System.out.println(e.getMessage() + " is not a valid mark! ");
				}
			}
		}
		
		scan.close();
		
		double averageMark = (double)totalMark / markCount;
		System.out.println(averageMark);
	}

}
