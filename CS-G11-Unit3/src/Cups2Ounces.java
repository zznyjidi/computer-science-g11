import java.util.Scanner;

public class Cups2Ounces {
    public static void main(String[] args) {
        double ounces = getOunces();
        double cup = ouncesToCups(ounces);
        displayResult(cup);
    }

    private static double getOunces() {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many ounces is it? ");
        double ounces = scan.nextDouble();
        scan.close();
        return ounces;
    }

    private static double ouncesToCups(double ounces) {
        return 8 * ounces;
    }

    private static void displayResult(double ounces) {
        System.out.printf("You need %.2f ounces. %n", ounces);
    }

}
