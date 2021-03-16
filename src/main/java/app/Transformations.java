package app;

public class Transformations {
    private  final Matrix baseTransform = new Matrix(4, 4);
    private  final Matrix offSetTransform = new Matrix(4, 4);

    public Transformations() {
        offSetTransform.setMatrix(new double[]{ 1,0,0,16,
                                                0,1,0,16,
                                                0,0,1,10,
                                                0,0,0,1}); 
        baseTransform.setMatrix(new double[]{   1,0,0,0,
                                                0,1,0,0,
                                                0,0,1,0,
                                                0,0,0,1});                                                                  
    }

    public Matrix applyTranformation(Matrix transformation, Matrix vector) {
        return transformation.multiply(vector);
    }

    public Matrix offSetTransformation(Matrix offset){
        Matrix m = new Matrix(4, 4);
        double[] array = offSetTransform.getMatrix().clone();


        array[3] = offset.getMatrix()[0];
        array[7] = offset.getMatrix()[1];
        array[11] = offset.getMatrix()[2];
        m.setMatrix(array);

        return m;
    }
    public Matrix scaleTransformation(Matrix scale){
        Matrix m = new Matrix(4, 4);
        double[] array = offSetTransform.getMatrix().clone();
        //uniform scaling sx = sy = sz

        array[0] = scale.getMatrix()[0];
        array[5] = scale.getMatrix()[1];
        array[10] = scale.getMatrix()[2];
        m.setMatrix(array);

        return m;
    }
    public Matrix rotateXTransform(double angle){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();

        array[5] = Math.cos(angle);
        array[6] = -Math.sin(angle);
        array[9] = Math.sin(angle);
        array[10] = Math.cos(angle);

        
        m.setMatrix(array);
        return m;
    }
    public Matrix rotateYTransform(double angle){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();

        array[0] = Math.cos(angle);
        array[2] = Math.sin(angle);
        array[8] = -Math.sin(angle);
        array[10] = Math.cos(angle);

        
        m.setMatrix(array);
        return m;
    }
    public Matrix rotateZTransform(double angle){
        Matrix m = new Matrix(4, 4);
        double[] array = baseTransform.getMatrix().clone();

        array[0] = Math.cos(angle);
        array[1] = -Math.sin(angle);
        array[4] = Math.sin(angle);
        array[5] = Math.cos(angle);

        
        m.setMatrix(array);
        return m;
    }

}
