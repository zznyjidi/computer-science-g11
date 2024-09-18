import java.util.Scanner;

public class SooryaHW_20240913 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("What item do you want? ");
		String item = scan.nextLine();

		while (!item.equals("no")) {
			System.out.println("Do you want another item (item/no)");
			item = scan.nextLine();
		}
		scan.close();
	}

}
