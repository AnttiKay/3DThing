package app.dataStructures.vectors;

import java.security.InvalidParameterException;

public class Vec4 extends AbstractVector {

    public Vec4() {
        super(4, 1);
    }

    public Vec4(double x, double y, double z, double w) {
        super(4, 1);
        super.setMatrix(new double[] { x, y, z, w });
    }

    public Vec4(double[] vector4) {
        super(4, 1);
        if (vector4.length == 4) {
            super.setMatrix(vector4);
        } else
            throw new InvalidParameterException();
    }

    public void setVector(double x, double y, double z, double w) {
        super.setMatrix(new double[] { x, y, z, w });
    }

    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public double getZ() {
        return super.getZ();
    }

    public double getW() {
        return super.getW();
    }

    public void setX(double x) {
        super.setX(x);
    }

    public void setY(double y) {
        super.setY(y);
    }

    public void setZ(double z) {
        super.setZ(z);
    }

    public void setW(double w) {
        super.setW(w);
    }

    public double normalize() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2) + Math.pow(getW(), 2));
    }
}
