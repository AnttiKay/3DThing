package app.transformations;

import app.dataStructures.Matrix;

public class PerspectiveMatrix {
    private double near;
    private double far;
    private double width;
    private double height;
    private double fov;
    private double aspectRatio = 16 / 9;


    // camera is at 0,0,0.  near is on z axis. further into negative on z further away something is 
    public PerspectiveMatrix(double near, double far, double fov) {
        this.near = near;
        this.far = far;
        this.fov = fov;
    }

    public Matrix getPerspectiveMatrix() {
        this.width = -2 * near * Math.tan(fov / 2);
        this.height = width/aspectRatio;

        double[] perspective = new double[]{2*near/width,0,0,0,
                                            0,2*near/height,0,0,
                                            0,0,-(far+near)/(far-near),(-2*far*near)/(far-near),
                                            0,0,-1,0};
    
        Matrix perspectiveMatrix = new Matrix(4, 4);
        perspectiveMatrix.setMatrix(perspective);
        return perspectiveMatrix;
    }


}
