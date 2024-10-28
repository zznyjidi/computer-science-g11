import java.util.Arrays;

public class ArrayMethod {
    public static void main(String[] args) {
        int[] numberArray = { 5, 2, 3, -1, 0, 4, 1 };
        printArray(numberArray);

        // toIndex is exclusive
        Arrays.sort(numberArray, 3, 7);
        printArray(numberArray);

        Arrays.sort(numberArray);
        printArray(numberArray);

        // Binary Search
        System.out.println(Arrays.binarySearch(numberArray, 5));
        // Return Negative if doesn't exist
        // (-(insertionIndex + 1) if not exist or the index where it is found)
        System.out.println(Arrays.binarySearch(numberArray, 10));

        // Array Comparison
        int[] numberArray1 = { 5, 2, 3, -1, 0, 4, 1 };
        int[] numberArray2 = { 5, 2, 3, -1, 0, 4, 1 };
        System.out.println(numberArray1 == numberArray2); // false, compare mem addr
        System.out.println(Arrays.equals(numberArray1, numberArray2)); // true, compare content

        char[] charArray = { 'C', 'B', 'a', 'x', 'r' };
        printArray(charArray);

        Arrays.sort(charArray);
        printArray(charArray);
    }

    public static void printArray(int[] numberArray) {
        System.out.print("[");
        for (int i = 0; i < numberArray.length; i++) {
            System.out.print(numberArray[i]);
            if (i != numberArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printArray(char[] numberArray) {
        System.out.print("[");
        for (int i = 0; i < numberArray.length; i++) {
            System.out.print(numberArray[i]);
            if (i != numberArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
