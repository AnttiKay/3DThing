package app.dataStructures;
public class Matrix {
    protected double[] matrix;
    private int rows; // Dimensions of the matrix
    private int columns;

    public Matrix(int rows, int columns) {
        this.matrix = new double[rows * columns];
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix(int rows, int columns, double[] matrix) {
        this.matrix = new double[rows * columns];
        this.rows = rows;
        this.columns = columns;
        this.matrix = matrix;
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

    public Matrix substract(Matrix m) {
        double[] outMatrix = new double[rows * columns]; // new Matrix size rows x columns

        if (columns != m.getColumns() || rows != m.getRows()) {// if matrixes cannot be substracted returns null
            return null;
        }

        for (int i = 0; i < matrix.length; i++) {
            outMatrix[i] = matrix[i] - m.getMatrix()[i];
            // System.out.println(outMatrix[i]);

        }
        Matrix newMatrix = new Matrix(rows, columns);
        newMatrix.setMatrix(outMatrix);
        return newMatrix;

    }

    public Matrix addition(Matrix m) {
        double[] outMatrix = new double[rows * columns]; // new Matrix size rows x columns

        if (columns != m.getColumns() || rows != m.getRows()) {// if matrixes cannot be added returns null
            return null;
        }

        for (int i = 0; i < matrix.length; i++) {
            outMatrix[i] = matrix[i] + m.getMatrix()[i];
            // System.out.println(outMatrix[i]);

        }
        Matrix newMatrix = new Matrix(rows, columns);
        newMatrix.setMatrix(outMatrix);
        return newMatrix;

    }

    public Matrix divisionByNumber(double number) {
        double[] outMatrix = new double[rows * columns]; // new Matrix size rows x columns
        double scalar = 1 / number;
        for (int i = 0; i < matrix.length; i++) {
            outMatrix[i] = scalar * matrix[i];
            // System.out.println(outMatrix[i]);

        }

        Matrix newMatrix = new Matrix(rows, columns);
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

    public double getX() {
        if (columns == 1) {
            if (rows >= 1) {
                return matrix[0];
            }
        }
        return 0;
    }

    public double getY() {
        if (columns == 1) {
            if (rows >= 2) {
                return matrix[1];
            }
        }
        return 0;
    }

    public double getZ() {
        if (columns == 1) {
            if (rows >= 3) {
                return matrix[2];
            }
        }
        return 0;
    }
    public double getW() {
        if (columns == 1) {
            if (rows >= 4) {
                return matrix[3];
            }
        }
        return 0;
    }

    public void setX(double x) {
        if (columns == 1) {
            if (rows >= 1) {
                matrix[0] = x;
            }
        }
    }
    public void setY(double y) {
        if (columns == 1) {
            if (rows >= 2) {
                matrix[1] = y;
            }
        }
    }
    public void setZ(double z) {
        if (columns == 1) {
            if (rows >= 3) {
                matrix[2] = z;
            }
        }
    }
    public void setW(double w) {
        if (columns == 1) {
            if (rows >= 4) {
                matrix[3] = w;
            }
        }
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
    public double normalize3x1(){
        return Math.sqrt(Math.pow(matrix[0], 2)+Math.pow(matrix[1], 2)+Math.pow(matrix[2], 2));
    }

}
