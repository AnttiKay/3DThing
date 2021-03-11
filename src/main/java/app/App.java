public class App {
    public static void main(String[] args) throws Exception {
        Matrix m1 = new Matrix(2, 2);
        m1.setMatrix(new double[] { 1, 2, 3, 4 });

        Matrix m2 = new Matrix(2, 2);
        m2.setMatrix(new double[] { 4, 3, 2, 1 });

        Matrix c = m1.multiply(m2);

       // System.out.println(c.toString());

        m1 = new Matrix(4, 4);
        m1.setMatrix(new double[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});

        m2 = new Matrix(4, 4);
        m2.setMatrix(new double[]{16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1});

        c = m1.multiply(m2);

        System.out.println(c.toString());
    }
}
