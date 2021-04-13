package app.transformations;

import app.dataStructures.Matrix;

public class Transformations {
    private static final Matrix baseTransform = new Matrix(4, 4,new double[]{   1,0,0,0,
                                                                                0,1,0,0,
                                                                                0,0,1,0,
                                                                                0,0,0,1});



    public static Matrix applyTranformation(Matrix transformation, Matrix vector) {
        return transformation.multiply(vector);
    }

    public static Matrix offSetTransformation(Matrix offset){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix();


        array[3] = offset.getMatrix()[0];
        array[7] = offset.getMatrix()[1];
        array[11] = offset.getMatrix()[2];
        m.setMatrix(array);

        return m;
    }
    public static Matrix scaleTransformation(Matrix scale){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();
        //uniform scaling sx = sy = sz

        array[0] = scale.getMatrix()[0];
        array[5] = scale.getMatrix()[1];
        array[10] = scale.getMatrix()[2];
        m.setMatrix(array);

        return m;
    }
    public static Matrix rotateXTransform(double angle){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();

        array[5] = Math.cos(angle);
        array[6] = -Math.sin(angle);
        array[9] = Math.sin(angle);
        array[10] = Math.cos(angle);

        
        m.setMatrix(array);
        return m;
    }
    public static Matrix rotateYTransform(double angle){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();

        array[0] = Math.cos(angle);
        array[2] = Math.sin(angle);
        array[8] = -Math.sin(angle);
        array[10] = Math.cos(angle);

        
        m.setMatrix(array);
        return m;
    }
    public static Matrix rotateZTransform(double angle){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();

        array[0] = Math.cos(angle);
        array[1] = -Math.sin(angle);
        array[4] = Math.sin(angle);
        array[5] = Math.cos(angle);

        
        m.setMatrix(array);
        return m;
    }

    public static double normalize3x1(Matrix m){
        return Math.sqrt(Math.pow(m.getMatrix()[0], 2)+Math.pow(m.getMatrix()[1], 2)+Math.pow(m.getMatrix()[2], 2));
    }

}
