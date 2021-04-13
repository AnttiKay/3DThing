package app.dataStructures;

public class Triangle {
    private Matrix[] angles;
    private int index = 0;

    public Triangle(Matrix[] vertexes) {
        angles = new Matrix[3];
        this.angles = vertexes;
    }

    public Triangle() {
        angles = new Matrix[3];
    }

    public Matrix[] getAngles() {
        return angles;
    }

    public void setAngles(Matrix[] angles) {
        this.angles = angles;
    }
    public void addVertice(Matrix m){
        angles[index] = m;
        index++; 
    }

    public Matrix getVertice(int index){
        return angles[index];
    }
}
