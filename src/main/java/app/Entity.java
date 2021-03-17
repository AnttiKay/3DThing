package app;

public class Entity {

    private double[] vertices;
    private Matrix[] angles;
    private Triangle[] triangles;

    public double[] getVertices() {
        return vertices;
    }

    public void setVertices(double[] vertices) {
        this.vertices = vertices;
    }

    public Triangle[] getTriangles() {
        triangles = new Triangle[vertices.length / 9];
        angles = new Matrix[3];
        int counter = 0;
        for (int i = 0; i < triangles.length; i++) {
            angles = new Matrix[3];
            for (int j = 0; j < 9; j += 3) {
                int k = i * 9 + j;
                angles[counter] = new Matrix(4, 1, new double[] { vertices[k], vertices[k + 1], vertices[k + 2], 1 });
                
                //System.out.println( angles[counter].toString());
                counter++;
            }
            counter = 0;
            triangles[i] = new Triangle(angles);
            //System.out.println("Inserting Triangle");
            
        }

        return triangles;
    }

}
