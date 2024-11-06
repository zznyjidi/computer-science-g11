public class MatrixTest {
    public static void main(String[] args) {
        double[][] m1Array = {{1, 2, 3}, {4, 5, 6}};
        double[][] m2Array = {{7, 8}, {9, 10}, {11, 12}};
        Matrix m1 = new Matrix(m1Array);
        Matrix m2 = new Matrix(m2Array);

        double[][] m3Array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] m4Array = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        Matrix m3 = new Matrix(m3Array);
        Matrix m4 = new Matrix(m4Array);

        try {
            System.out.println(m1.matrixMultiplication(m2));
            System.out.println(m3.matrixAddition(m4));
        } catch (WrongMatrixSizeException e) {
            e.printStackTrace();
        }
    }
}
