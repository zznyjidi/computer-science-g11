import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MemberTest {
    static Member[] membersArray = new Member[7];
    static Scanner scan;

    public static void main(String[] args) {
        displayTitle();
        fillArray(membersArray);
        displayArray(membersArray);
        scan = new Scanner(System.in);
        searchByMajor(membersArray);
        searchByAge(membersArray);
        scan.close();
    }

    private static void displayTitle() {
        System.out.println("Power to Change--Student List");
        System.out.println("-------------------------------");
    }

    private static void fillArray(Member[] membersArray) {
        membersArray[0] = new Member("Maritza", "Mendoza", 19, "Actuarial Science");
        membersArray[1] = new Member("Troy", "Okeke", 21, "Social Work");
        membersArray[2] = new Member("Lulu", "Dion", 19, "Undecided Major");
        membersArray[3] = new Member("Philemon", "Salli", 25, "Social Work");
        membersArray[4] = new Member("Sokka", "Momo", 22, "Gender & Women Studies");
        membersArray[5] = new Member("Chidi", "Okafor", 23, "Electrical Engineering");
        membersArray[6] = new Member("Saitama", "Genos", 20, "Global Health");
    }

    private static void displayArray(Member[] membersArray) {
        // Sort Array
        Arrays.sort(membersArray, Comparator.comparing(Member::getMajor));
        // Print Array
        for (Member member : membersArray) {
            System.out.println(member);
        }
    }

    private static void searchByMajor(Member[] membersArray) {
        // Get target Major
        System.out.print("What major do you want? ");
        String targetMajor = scan.nextLine();

        // Count Members with targetMajor
        int count = 0;
        for (Member member : membersArray) {
            if (member.getMajor().equals(targetMajor)) {
                count++;
            }
        }

        // Print Message
        if (count > 1)
            System.out.printf("There are %d students in %s major. %n", count, targetMajor);
        else if (count == 1)
            System.out.printf("There is %d student in %s major. %n", count, targetMajor);
        else
            System.out.printf("There is no student in %s major. %n", targetMajor);
    }

    private static void searchByAge(Member[] membersArray) {
        // Get target Age
        System.out.print("What age do you want? ");
        int targetAge = scan.nextInt();

        // Print Members with targetAge
        for (Member member : membersArray) {
            if (member.getAge() == targetAge)
                System.out.println(member);
        }
    }
}
