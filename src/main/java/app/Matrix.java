package app;

public class Matrix {
    private double[] matrix;
    private int rows; // Dimensions of the matrix
    private int columns;

    public Matrix(int rows, int columns) {
        this.matrix = new double[rows * columns];
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix multiply(Matrix m) {
        int i, j, k;
        double[] outMatrix = new double[rows * m.getColumns()]; // new Matrix size rows x columns

        if (columns != m.getRows()) {// if matrixes cannot be multiplied returns null
            return null;
        }
        // b = columnK + rowK * columns; // index in a matrix
        // a = columnJ + rowJ * m.getColumns(); //index in b matrix
        int q = 0;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < m.getColumns(); j++) {
                for (k = 0; k < m.getRows(); k++) {
                    // System.out.println("i: " + i + " j: " + j + " k: " + k);
                    // System.out.println(matrix[k + i * columns] + " * " + m.getMatrix()[j + k *
                    // m.getColumns()]);
                    outMatrix[q] += matrix[k + i * columns] * m.getMatrix()[j + k * m.getColumns()];

                }
                q++;
            }
        }
        Matrix newMatrix = new Matrix(rows, m.getColumns());
        newMatrix.setMatrix(outMatrix);
        return newMatrix;
    }

    public double[] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            if (rows == counter) {
                sb.append(" " + matrix[i] + "\n\n");
                counter = 0;
            } else {
                sb.append(" " + matrix[i] + " ");
            }
            counter++;
        }
        return sb.toString();
    }

}
