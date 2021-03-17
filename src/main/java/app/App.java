package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private int HEIGHT = 600;
    private int WIDTH = 600;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("3DThing");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(HEIGHT,WIDTH);
        root.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        Entity cube = new Entity();
        cube.setVertices(new double[]{
            
            -0.5,-0.5,-0.5,
            0.5,-0.5,-0.5,
            0.5,0.5,-0.5,
            0.5,0.5,-0.5,
            -0.5,0.5,-0.5,
            -0.5,-0.5,-0.5,
            
            -0.5,-0.5,0.5,
            0.5,-0.5,0.5,
            0.5,0.5,0.5,
            0.5,0.5,0.5,
            -0.5,0.5,0.5,
            -0.5,-0.5,0.5,
            
            -0.5,0.5,0.5,
            -0.5,0.5,-0.5,
            -0.5,-0.5,-0.5,
            -0.5,-0.5,-0.5,
            -0.5,-0.5,0.5,
            -0.5,0.5,0.5,
            
            0.5,0.5,0.5,
            0.5,0.5,-0.5,
            0.5,-0.5,-0.5,
            0.5,-0.5,-0.5,
            0.5,-0.5,0.5,
            0.5,0.5,0.5,
            
            -0.5,-0.5,-0.5,
            0.5,-0.5,-0.5,
            0.5,-0.5,0.5,
            0.5,-0.5,0.5,
            -0.5,-0.5,0.5,
            -0.5,-0.5,-0.5,
            
            -0.5,0.5,-0.5,
            0.5,0.5,-0.5,
            0.5,0.5,0.5,
            0.5,0.5,0.5,
            -0.5,0.5,0.5,
            -0.5,0.5,-0.5});

        


        Camera camera = new Camera(new double[]{0,0,3}, new double[]{0,0,0});
        //Matrix lookAtMatrix = camera.cameraLookAt(new Matrix(3, 1,new double[]{0,0,3}), new Matrix(3, 1,new double[]{0,0,0}), new Matrix(3, 1,new double[]{0,1,0}));
        
        PerspectiveMatrix perspective = new PerspectiveMatrix(-0.1, -100, 45);// near and far values
        Matrix perspectiveMatrix = perspective.getPerspectiveMatrix();

        Triangle[] triangles = cube.getTriangles();

        /* for( Triangle t : triangles){
            for(Matrix m : t.getAngles()){
                System.out.println(m.toString());
            }
        } */
        final long timeStart = System.currentTimeMillis();
        Timeline renderLoop = new Timeline();
        renderLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), // 60 fps
        new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(0, 0, HEIGHT, WIDTH);
                double tick = (System.currentTimeMillis() - timeStart) / 1000.0; 

                int camRadius = 5;
                double camX = Math.sin(tick) * camRadius;
                double camZ = Math.cos(tick) * camRadius;
                Matrix lookAtMatrix = camera.cameraLookAt(new Matrix(3, 1,new double[]{camX,0,camZ}), new Matrix(3, 1,new double[]{0,0,0}), new Matrix(3, 1,new double[]{0,1,0}));
                
                for(Triangle triangle : triangles){
                    double x,y;
                    Triangle t = new Triangle();
                    for(Matrix vertex : triangle.getAngles()){
                        boolean skip = false;

                        Matrix viewSpaceMatrix = lookAtMatrix.multiply(vertex);

                        Matrix vertexViewSpaceMatrix = perspectiveMatrix.multiply(viewSpaceMatrix);

                        double[] vertexViewSpace = vertexViewSpaceMatrix.getMatrix();

                        vertexViewSpace[0] = vertexViewSpace[0]/vertexViewSpace[3];
                        vertexViewSpace[1] = vertexViewSpace[1]/vertexViewSpace[3];
                        vertexViewSpace[2] = vertexViewSpace[2]/vertexViewSpace[3];

                        for(int i = 0; i< 3;i++){
                            if(vertexViewSpace[i] < -1 || vertexViewSpace[i] > 1){
                                skip = true;
                                System.out.println("Skipping vertex");
                            }
                        }

                        if(!skip){
                             x = vertexViewSpace[0] *(WIDTH/2) + (WIDTH/2);
                             y = -vertexViewSpace[1] *(HEIGHT/2) + (HEIGHT/2);
                             
                            t.addVertice(new Matrix(2, 1, new double[]{x,y}));
                            
                            double radius = 2;
                            // drawing a dot in the correct spot on the screen
                            gc.strokeOval(x-radius, y-radius, radius*2, radius*2);
                        }
                        
                    }
                    // Drawing lines between triangles vertices
                    gc.strokeLine(t.getVertice(0).getX(), t.getVertice(0).getY(), t.getVertice(1).getX(), t.getVertice(1).getY());
                    gc.strokeLine(t.getVertice(1).getX(), t.getVertice(1).getY(), t.getVertice(2).getX(), t.getVertice(2).getY());
                    gc.strokeLine(t.getVertice(2).getX(), t.getVertice(2).getY(), t.getVertice(0).getX(), t.getVertice(0).getY());
                }
                
            }


            
        });

        renderLoop.getKeyFrames().add(kf);
        renderLoop.play();

        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
