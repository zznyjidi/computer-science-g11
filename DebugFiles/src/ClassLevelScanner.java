import java.util.Scanner;

public class ClassLevelScanner {
    private static Scanner scan;
    public static void main(String[] args) {
        scan = new Scanner(System.in);

        scan.close();
    }
}
