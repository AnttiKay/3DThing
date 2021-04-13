package app.dataStructures.vectors;

import java.security.InvalidParameterException;

public class Vec3 extends AbstractVector {

    public Vec3() {
        super(3, 1);
        super.setMatrix(new double[3]);
    }

    public Vec3(double x, double y, double z) {
        super(3, 1);
        super.setMatrix(new double[] { x, y, z });
    }

    public Vec3(double[] vector3) {
        super(3, 1);
        if (vector3.length == 3) {
            super.setMatrix(vector3);
        } else
            throw new InvalidParameterException();
    }

    public Vec3 crossProduct(Vec3 m) {
        Vec3 crossProduct = new Vec3();
        //System.out.println("vector a: " + this.toString());
        //System.out.println("vector b: " + m.toString());
        // cx = ay*bz − az*by
        // cy = az*bx − ax*bz
        // cz = ax*by − ay*bx

        crossProduct.setX(getY() * m.getZ() - getZ() * m.getY());
        crossProduct.setY(getZ() * m.getX() - getX() * m.getZ());
        crossProduct.setZ(getX() * m.getY() - getY() * m.getX());

        //System.out.println(crossProduct.toString());
        return crossProduct;
    }

    public void setVector(double x, double y, double z) {
        super.setMatrix(new double[] { x, y, z });
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

    public void setX(double x) {
        super.setX(x);
    }

    public void setY(double y) {
        super.setY(y);
    }

    public void setZ(double z) {
        super.setZ(z);
    }

    public double normalize() {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
