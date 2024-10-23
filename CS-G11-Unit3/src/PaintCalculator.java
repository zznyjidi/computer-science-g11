import java.util.Scanner;

/**
 * PaintCalculator
 */
public class PaintCalculator {

    public static void main(String[] args) {
        int numFloor, numRoom;
        double roomLength, roomWidth;

        Scanner scan = new Scanner(System.in);

        System.out.print("How many floors are in the building? ");
        numFloor = scan.nextInt();

        System.out.print("How many rooms are in the floor? ");
        numRoom = scan.nextInt();

        System.out.print("What is the Length of the Room? ");
        roomLength = scan.nextDouble();

        System.out.print("What is the Width of the Room? ");
        roomWidth = scan.nextDouble();

        scan.close();

        System.out.printf("You need %.2f gallons of paint. %n", calculateArea(numFloor, numRoom, roomLength, roomWidth));
    }

    public static double calculateArea(int numFloor, int numRoom, double roomLength, double roomWidth) {
        final int COVERAGE = 350;
        return Math.round((numFloor * calculateAreaPerFloor(numRoom, roomLength, roomWidth)) / COVERAGE);
    }

    private static double calculateAreaPerFloor(int numRoom, double roomLength, double roomWidth) {
        return numRoom * calculateAreaPerRoom(roomLength, roomWidth);
    }

    private static double calculateAreaPerRoom(double roomLength, double roomWidth) {
        return 2 * calculateAreaPerWall(roomLength) + 2 * calculateAreaPerWall(roomWidth);
    }

    
    private static double calculateAreaPerWall(double roomDimension) {
        final double HEIGHT = 10;
        return HEIGHT * roomDimension;
    }    
}
