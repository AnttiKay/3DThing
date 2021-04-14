package app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import app.dataStructures.Matrix;
import app.transformations.Transformations;

public class MatrixTest {
       @Test
       public void matrixMultiplicationTest(){
        Matrix m1 = Transformations.offSetTransformation(new Matrix(3, 1, new double[]{10,10,10}));
        System.out.println(m1.toString());
        
        Matrix m2 = new Matrix(4, 1);
        m2.setMatrix(new double[]{2,2,2,1});
        System.out.println(m2.toString());

        Matrix c = m1.multiply(m2);
        System.out.println(c.toString());
    }
    
    @Test
    public void matrixSubstractionTest3x1(){
        Matrix m1 = new Matrix(3, 1);
        m1.setMatrix(new double[]{3,4,5});

        Matrix m2 = new Matrix(3, 1);
        m2.setMatrix(new double[]{2,2,2});
        
        assertTrue(Arrays.equals(new double[]{1,2,3}, m1.substract(m2).getMatrix()));
    }
    @Test
    public void matrixAdditionTest3x1(){
        Matrix m1 = new Matrix(3, 1);
        m1.setMatrix(new double[]{4,4,4});

        Matrix m2 = new Matrix(3, 1);
        m2.setMatrix(new double[]{2,2,2});
        
        assertTrue(Arrays.equals(new double[]{6,6,6}, m1.addition(m2).getMatrix()));
    }
    @Test
    public void matrixSubstractionDifferentSizeTest(){
        Matrix m1 = new Matrix(3, 1);
        m1.setMatrix(new double[]{3,4,5});

        Matrix m2 = new Matrix(4, 1);
        m2.setMatrix(new double[]{2,2,2,1});
        
        assertEquals(null, m1.substract(m2));
    }
    @Test
    public void matrixMultiplicationByNumberTest(){
        Matrix m1 = new Matrix(3, 1);
        m1.setMatrix(new double[]{3,4,5});

        double multiplier = 2;
        //System.out.println(m1.multiplicationByNumber(multiplier).toString());
        
        assertTrue(Arrays.equals(new double[]{6,8,10}, m1.multiplicationByNumber(multiplier).getMatrix()));
    }
    

}
