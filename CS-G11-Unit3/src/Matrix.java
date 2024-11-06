public class Matrix {
    int width, length;
    double[][] matrixArray;

    // Constructor
    public Matrix(double[][] matrixArray) {
        this.matrixArray = matrixArray;
        this.width = matrixArray[0].length;
        this.length = matrixArray.length;
    }

    // Getter/Setters
    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public double[][] getMatrixArray() {
        return matrixArray;
    }

    public double getMatrixEntry(int x, int y) {
        if (x < length && y < width)
            return this.matrixArray[x][y];
        else
            throw new IndexOutOfBoundsException();
    }

    public void setMatrixEntry(int x, int y, double value) {
        if (x < length && y < width)
            this.matrixArray[x][y] = value;
        else
            throw new IndexOutOfBoundsException();
    }

    // toString
    @Override
    public String toString() {
        String arrayString = "[";
        for (int x = 0; x < matrixArray.length; x++) {
            double[] matrixLine = matrixArray[x];
            arrayString += "[";
            for (int y = 0; y < matrixLine.length; y++) {
                arrayString += matrixLine[y];
                if (y != matrixLine.length - 1)
                    arrayString += ", ";
            }
            arrayString += "]";
            if (x != matrixArray.length - 1) 
                arrayString += ", ";
        }
        arrayString += "]";
        return "Matrix [matrixArray=" + arrayString + "]";
    }

    // Utility Method
    public Matrix matrixAddition(Matrix anotherMatrix) throws WrongMatrixSizeException {
        if (this.length == anotherMatrix.getLength() && this.width == anotherMatrix.getWidth()) {
            Matrix resultMatrix = new Matrix(new double[length][width]);
            for (int x = 0; x < this.length; x++) {
                for (int y = 0; y < this.width; y++) {
                    resultMatrix.setMatrixEntry(
                        x, y, 
                        this.matrixArray[x][y] 
                        + anotherMatrix.getMatrixEntry(x, y)
                    );
                }
            }
            return resultMatrix;
        } else {
            throw new WrongMatrixSizeException("Size for anotherMatrix doesn't match");
        }
    }

    public Matrix matrixMultiplication(Matrix anotherMatrix) throws WrongMatrixSizeException {
        if (this.length == anotherMatrix.getWidth() && this.width == anotherMatrix.getLength()) {
            int newMatrixLength = length, newMatrixWidth = anotherMatrix.getWidth();
            Matrix resultMatrix = new Matrix(new double[newMatrixLength][newMatrixWidth]);
            for (int x = 0; x < newMatrixLength; x++) {
                for (int y = 0; y < newMatrixWidth; y++) {
                    double currentEntry = 0;
                    for (int i = 0; i < this.width; i++) {
                        currentEntry += this.getMatrixEntry(x, i) * anotherMatrix.getMatrixEntry(i, y);
                    }
                    resultMatrix.setMatrixEntry(x, y, currentEntry);
                }
            }
            return resultMatrix;
        } else {
            throw new WrongMatrixSizeException("Size for anotherMatrix doesn't match");
        }
    }

}
