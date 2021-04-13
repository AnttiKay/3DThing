package app.transformations;

import app.dataStructures.Matrix;
import app.dataStructures.vectors.Vec3;

public class Camera {

    private Vec3 cameraPosition = new Vec3();
    private Vec3 cameraTarget = new Vec3();

    public Camera(Vec3 cameraPosition, Vec3 cameraTarget) {
        this.cameraPosition = cameraPosition;
        this.cameraTarget = cameraTarget;
    }

    public Vec3 getCameraDirection() {
        // |a| == magnitude of a vector
        // |a| = √( x2 + y2 )

        Matrix direction = cameraTarget.substract(cameraPosition);
        Matrix NormalizedCameraDirection = direction.divisionByNumber(direction.normalize3x1());

        Vec3 result = new Vec3(NormalizedCameraDirection.getMatrix());
        //System.out.println(result.toString());
        
        return result;
    }

    public Vec3 getCameraRight(Vec3 up) {
        //cameraUp = crossProduct / |crossProduct|
        // |a| = a vectors length

        Vec3 crossProduct = up.crossProduct(getCameraDirection());
        Matrix normalizedCameraRight = crossProduct.divisionByNumber(crossProduct.normalize());

        Vec3 result = new Vec3(normalizedCameraRight.getMatrix());

        //System.out.println(result.toString());
        return result;
    }

    public Vec3 getCameraUp(Vec3 up){
        return  getCameraDirection().crossProduct(getCameraRight(up));
    }

    public Matrix cameraLookAt(Vec3 cameraPosition, Vec3 cameraTarget, Vec3 upDirection){
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