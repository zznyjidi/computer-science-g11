import java.util.Scanner;

public class MarkGreeter {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What is you mark? ");
		int mark = scan.nextInt();
		
		if (mark > 100 || mark < 0) {
			System.out.println("Invalid Mark! ");
		} else if (mark >= 95) {
			System.out.println("You got over 95%! ");
		} else {
			System.out.println("You got less thatn 95%. ");
		}
		
		scan.close();
	}

}
