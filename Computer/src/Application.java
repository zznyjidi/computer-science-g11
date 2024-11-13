import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        Computer[] computers = {
            new Computer(new CPU("AMD", "9950X3D", 16, 32)),
            new Computer(new CPU("AMD", "9800X3D", 8, 16, 
                4.7, 5.2, 
                640, 8, 96))
        };
        printArray(computers);
        sortByModel(computers);
        System.out.println("--------------");
        printArray(computers);
    }

    private static void sortByModel(Computer[] computers) {
        Arrays.sort(computers, (x, y) -> x.getCpu().getModel().compareTo(y.getCpu().getModel()));
    }

    private static void printArray(Computer[] computers) {
        for (Computer computer : computers) {
            System.out.println(computer);
        }
    }
}
