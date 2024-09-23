import java.util.Scanner;

public class MarkCalaculator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What is your mark? ");
		int mark = scan.nextInt();
		
		if (mark < 0) {
			System.err.println("Invalid Mark: Mark can't be less than 0. ");
		} else if(mark < 60) {
			System.out.println("Your mark is F. ");
		} else if (mark < 70) {
			System.out.println("Your mark is D. ");
		} else if (mark < 80) {
			System.out.println("Your mark is C. ");
		} else if (mark < 90) {
			System.out.println("Your mark is B. ");
		} else if (mark <= 100) {
			System.out.println("Your mark is A. ");
		} else {
			System.err.println("Invalid Mark: Mark can't be more than 100. ");
		}
		
		scan.close();
	}

}
