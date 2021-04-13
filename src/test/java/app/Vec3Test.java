package app;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import app.dataStructures.vectors.Vec3;

public class Vec3Test {
    @Test
    public void vectorCrossProductTest(){
        Vec3 v1 = new Vec3(1,2,3);

        Vec3 v2 = new Vec3(1,5,7);
        
        //System.out.println(v1.crossProduct(v2).toString());
        assertTrue(Arrays.equals(new double[]{-1,-4,3}, v1.crossProduct(v2).getMatrix()));
    }
}
