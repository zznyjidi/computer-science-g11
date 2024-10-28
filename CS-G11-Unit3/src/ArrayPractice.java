import java.util.Scanner;

public class ArrayPractice {
    public static void main(String[] args) {
        int[] numberArray = new int[10];

        numberArray[0] = 17;
        numberArray[9] = 31;

        @SuppressWarnings("unused")
        char[] letterArray = {'C', 'c', 'x', 'd'};

        // Example 1A - Get Input 
        Scanner scan = new Scanner(System.in);

        String[] nameArray = new String[10];
        for (int i = 0; i < nameArray.length; i++) {
            System.out.printf("Enter Student #%d: ", i);
            nameArray[i] = scan.nextLine();
        }

        scan.close();

        // Example 1B - Output to Console
        for (String name: nameArray) {
            System.out.print(name + " ");
        }
        System.out.println();
    }
}
