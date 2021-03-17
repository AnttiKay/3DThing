package app;

public class Camera {

    private Matrix cameraPosition = new Matrix(3, 1);
    private Matrix cameraTarget = new Matrix(3, 1);

    public Camera(double[] cameraPosition, double[] cameraTarget) {
        this.cameraPosition.setMatrix(cameraPosition);
        this.cameraTarget.setMatrix(cameraTarget);
    }

    public Matrix getCameraDirection() {
        // |a| == magnitude of a vector
        // |a| = √( x2 + y2 )

        Matrix direction = cameraTarget.substract(cameraPosition);
        double vectorMagnitude = Math.sqrt(Math.pow(direction.getMatrix()[0], 2) + Math.pow(direction.getMatrix()[1], 2)
                + Math.pow(direction.getMatrix()[2], 2));
        return direction.divisionByNumber(vectorMagnitude);
    }

    public Matrix getCameraRight(Matrix up) {
        //cameraUp = crossProduct / |crossProduct|
        // |a| = a vectors length

        Matrix crossProduct = up.crossProduct(getCameraDirection());
        return crossProduct.divisionByNumber(Transformations.normalize3x1(crossProduct));
    }

    public Matrix getCameraUp(Matrix up){
        return  getCameraDirection().crossProduct(getCameraRight(up));
    }

    public Matrix cameraLookAt(Matrix cameraPosition, Matrix cameraTarget, Matrix upDirection){
        this.cameraPosition = cameraPosition;
        this.cameraTarget = cameraTarget;
        double[] cameraLocation = cameraPosition.getMatrix();
        double[] cameraDirection = getCameraDirection().getMatrix();  
        double[] cameraRight = getCameraRight(upDirection).getMatrix(); 
        double[] cameraUp = getCameraUp(upDirection).getMatrix();

        double[] lookTransform = new double[]{cameraRight[0],cameraRight[1],cameraRight[2],0,
                                                 cameraUp[0],   cameraUp[1],   cameraUp[2],0,
                                          cameraDirection[0],cameraDirection[1],cameraDirection[2],0,
                                                          0,             0,             0,1  };


        Matrix offset = new Matrix(4, 4);
        offset.setMatrix(new double[]{-cameraLocation[0],-cameraLocation[1],-cameraLocation[2]});
        Matrix cameraLocationTransform = Transformations.offSetTransformation(offset);

        Matrix lookMatrix = new Matrix(4, 4);
        lookMatrix.setMatrix(lookTransform);
        
        return Transformations.applyTranformation(lookMatrix, cameraLocationTransform);
    }

}
