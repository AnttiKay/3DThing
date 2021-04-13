package app.dataStructures.vectors;

import app.dataStructures.Matrix;

public abstract class AbstractVector extends Matrix {
    private double[] matrix;

    public AbstractVector(int rows, int columns) {
        super(rows, columns);
        this.matrix = super.getMatrix();
    }

    public double getMagnitude() {
        // |a| == magnitude of a vector
        // |a| = √( x2 + y2 )
        this.matrix = super.getMatrix();
        
        if (matrix.length == 4) {
            return Math.sqrt(
                    Math.pow(matrix[0], 2) + Math.pow(matrix[1], 2) + Math.pow(matrix[2], 2) + Math.pow(matrix[3], 2));
        } else if (matrix.length == 3) {
            return Math.sqrt(Math.pow(matrix[0], 2) + Math.pow(matrix[1], 2) + Math.pow(matrix[2], 2));
        } else if (matrix.length == 2) {
            return Math.sqrt(Math.pow(matrix[0], 2) + Math.pow(matrix[1], 2));
        } else if (matrix.length == 1) {
            return Math.sqrt(Math.pow(matrix[0], 2));
        }else{
            return 0;
        }

    }


}
