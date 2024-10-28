public class Array2D {
    public static void main(String[] args) {
        int[][] coordinateTable = {
                { 5, 2 },
                { 8, 7 },
                { 3, 9 },
                { 1, 0 }
        };
        printArray(coordinateTable);
    }

    public static void printArray(int[][] array2D) {
        System.out.println("[");
        for (int x = 0; x < array2D.length; x++) {
            System.out.print("  [");
            for (int y = 0; y < array2D[0].length; y++) {
                System.out.print(array2D[x][y]);
                if (y != array2D[0].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (x != array2D.length - 1) {
                System.out.println(", ");
            } else {
                System.out.println();
            }
        }
        System.out.println("]");
    }
}
