
public class Matrix {
    int width, length;
    double[] matrixArray;

    public Matrix(int width, int length, double[] matrixArray) {
        this.width = width;
        this.length = length;
        this.matrixArray = matrixArray;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public double[] getMatrixArray() {
        return matrixArray;
    }

    public void setMatrixArray(double[] matrixArray) {
        this.matrixArray = matrixArray;
    }
    
    
}
