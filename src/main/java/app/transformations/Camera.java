package app.transformations;

import java.util.Set;

import app.dataStructures.Matrix;
import app.dataStructures.vectors.Vec3;

public class Camera {

    private final double cameraSpeed = 0.0025;

    private Vec3 cameraPosition = new Vec3();
    private Vec3 cameraTarget = new Vec3();
    private Vec3 normalizedCameraDirection = new Vec3();
    private Vec3 normalizedCameraRight = new Vec3();
    private Vec3 normalizedCameraUp = new Vec3();

    private double pitch = 0;
    private double yaw = -90;
    private Vec3 cameraFront = new Vec3();

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
        // System.out.println(result.toString());
        this.normalizedCameraDirection = result;
        return result;
    }

    public Vec3 getCameraRight(Vec3 up) {
        // cameraUp = crossProduct / |crossProduct|
        // |a| = a vectors length

        Vec3 crossProduct = up.crossProduct(getCameraDirection());
        Matrix normalizedCameraRight = crossProduct.divisionByNumber(crossProduct.normalize());

        Vec3 result = new Vec3(normalizedCameraRight.getMatrix());

        // System.out.println(result.toString());
        this.normalizedCameraRight = result;
        return result;
    }

    public Vec3 getCameraUp(Vec3 up) {
        Vec3 result = getCameraDirection().crossProduct(getCameraRight(up));
        this.normalizedCameraUp = result;
        return result;
    }

    public Matrix cameraLookAt(Vec3 cameraPosition, Vec3 cameraTarget, Vec3 upDirection) {
        this.cameraPosition = cameraPosition;
        this.cameraTarget = cameraTarget;
        double[] cameraLocation = cameraPosition.getMatrix();
        double[] cameraDirection = getCameraDirection().getMatrix();
        double[] cameraRight = getCameraRight(upDirection).getMatrix();
        double[] cameraUp = getCameraUp(upDirection).getMatrix();

        double[] lookTransform = new double[] { cameraRight[0], cameraRight[1], cameraRight[2], 0, cameraUp[0],
                cameraUp[1], cameraUp[2], 0, cameraDirection[0], cameraDirection[1], cameraDirection[2], 0, 0, 0, 0,
                1 };

        Matrix offset = new Matrix(4, 4);
        offset.setMatrix(new double[] { -cameraLocation[0], -cameraLocation[1], -cameraLocation[2] });
        Matrix cameraLocationTransform = Transformations.offSetTransformation(offset);

        Matrix lookMatrix = new Matrix(4, 4);
        lookMatrix.setMatrix(lookTransform);

        return Transformations.applyTranformation(lookMatrix, cameraLocationTransform);
    }

    public Vec3 getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Vec3 cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public void setCameraPosition(Matrix cameraPosition) {

        this.cameraPosition = new Vec3(cameraPosition.getMatrix());
    }

    public double getCameraSpeed() {
        return cameraSpeed;
    }

    public Vec3 getNormalizedCameraDirection() {
        return normalizedCameraDirection;
    }

    public Vec3 getNormalizedCameraUp() {
        return normalizedCameraUp;
    }

    public Vec3 getNormalizedCameraRight() {
        return normalizedCameraRight;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }
    public double getPitch() {
        return pitch;
    }
    public void setYaw(double yaw) {
        this.yaw = yaw;
    }
    public double getYaw() {
        return yaw;
    }
    public Vec3 getCameraFront() {
        return cameraFront;
    }
    public void setCameraFront(Vec3 cameraFront) {
        this.cameraFront = cameraFront;
    }
}
