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


        Timeline renderLoop = new Timeline();
        renderLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), // 60 fps
        
        new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                for(int i = 0; i<100; i++){
                    gc.clearRect(0, 0, HEIGHT, WIDTH);

                    gc.strokeLine(Math.random()*WIDTH, Math.random()*HEIGHT, Math.random()*WIDTH, Math.random()*HEIGHT);
                }
                
            }


            
        });

        renderLoop.getKeyFrames().add(kf);
        renderLoop.play();

        stage.show();
    }
    public static void main(String[] args) throws Exception {
       /*  Transformations t = new Transformations();
        Matrix m1 = new Matrix(4, 1);
        m1.setMatrix(new double[] { 1, 2, 3, 1 });

        Matrix offset = new Matrix(4, 1);
        offset.setMatrix(new double[] { 10, 10, 10, 1 });

        Matrix c = t.applyTranformation(t.offSetTransformation(offset), m1);
        System.out.println(c.toString());

        Matrix m2 = new Matrix(2, 2);
        m2.setMatrix(new double[] { 4, 3, 2, 1 });

        // System.out.println(c.toString());

        m1 = new Matrix(4, 4);
        m1.setMatrix(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });

        m2 = new Matrix(4, 4);
        m2.setMatrix(new double[] { 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 });

        //c = m1.multiply(m2);

        // System.out.println(c.toString()); */
        launch(args);
    }

    
}
