import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Create and Display a Array of Avatar the Last Airbender Characters
 * and search the array by Nation and Age.
 * By Zixuan Zhao
 */
public class Application {
    public static void main(String[] args) {
        // Create Array
        ATLA[] characterArray = new ATLA[8];

        // Fill, sort and display the Array
        fillArray(characterArray);
        sortArrayByAge(characterArray);
        displayTitle();
        displayArray(characterArray);

        // Create Scanner
        Scanner scan = new Scanner(System.in);

        // Find characters with the same nation
        String targetNation;
        do {
            System.out.print("Enter nation to see how many characters share the same nation: ");
            targetNation = scan.nextLine();
            if (!((targetNation.equalsIgnoreCase("Earth") || targetNation.equalsIgnoreCase("Fire") || 
                targetNation.equalsIgnoreCase("Water") || targetNation.equalsIgnoreCase("Air"))))
                System.err.println("Nation must be Earth, Fire, Water or Air! ");
        } while (!((targetNation.equalsIgnoreCase("Earth") || targetNation.equalsIgnoreCase("Fire") || 
            targetNation.equalsIgnoreCase("Water") || targetNation.equalsIgnoreCase("Air"))));
        int characterCountByNation = countByNation(characterArray, targetNation);
        if (characterCountByNation == 0)
            System.out.printf("No character is from the %s nation. %n", targetNation);
        else if (characterCountByNation == 1)
            System.out.printf("%d character is from the %s nation. %n", characterCountByNation, targetNation);
        else
            System.out.printf("%d characters are form the %s nation. %n", characterCountByNation, targetNation);

        // Find characters with the same age
        int targetAge;
        do {
            System.out.print("Enter age to see how many characters are the same age: ");
            targetAge = scan.nextInt();
            if (targetAge < 0) 
                System.err.println("Age can't be negative! ");
        } while (targetAge < 0);
        int characterCountByAge = countByAge(characterArray, targetAge);
        if (characterCountByAge == 0)
            System.out.printf("No character is %d years old. %n", targetAge);
        else if (characterCountByAge == 1)
            System.out.printf("%d character is %d years old. %n", characterCountByAge, targetAge);
        else
            System.out.printf("%d characters are %d years old. %n", characterCountByAge, targetAge);

        // Close the Scanner
        scan.close();
    }

    // Fill Array
    private static void fillArray(ATLA[] characterArray) {
        characterArray[0] = new ATLA("Zuko", 16, "Fire");
        characterArray[1] = new ATLA("Suki", 15, "Earth");
        characterArray[2] = new ATLA("Toph", 12, "Earth");
        characterArray[3] = new ATLA("Azula", 14, "Fire");
        characterArray[4] = new ATLA("Sokka", 15, "Water");
        characterArray[5] = new ATLA("Katara", 14, "Water");
        characterArray[6] = new ATLA("Aang", 12, "Air");
        characterArray[7] = new ATLA("Iroh", 55, "Fire");
    }

    // Show Title
    private static void displayTitle() {
        System.out.println("ATLA CHARACTERS");
        System.out.println("================");
    }

    // Sort ATLA Array by Age
    private static void sortArrayByAge(ATLA[] characterArray) {
        Arrays.sort(characterArray, Comparator.comparing(ATLA::getAge));
    }

    // Print Array
    private static void displayArray(ATLA[] characterArray) {
        for (ATLA character : characterArray) {
            System.out.println(character);
        }
    }

    // Find the number of Character with the same Nation
    private static int countByNation(ATLA[] characterArray, String targetNation) {
        int count = 0;
        for (ATLA character : characterArray) {
            if (character.getNation().equalsIgnoreCase(targetNation))
                count++;
        }
        return count;
    }

    // Find the number of Character with the same Age
    private static int countByAge(ATLA[] characterArray, int targetAge) {
        int count = 0;
        for (ATLA character : characterArray) {
            if (character.getAge() == targetAge)
                count++;
        }
        return count;
    }
}
