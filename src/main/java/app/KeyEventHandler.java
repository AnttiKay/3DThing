package app;

import java.util.ArrayList;

import app.dataStructures.vectors.Vec3;
import app.transformations.Camera;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;

public class KeyEventHandler {
    private ArrayList<String> keysPressed = new ArrayList<>();
    private Camera camera;
    private Scene scene;

    private double lastXDelta, lastYDelta;
    private double xposDelta, yposDelta;
    private final double mouseSensitivity = 0.1;
    private boolean ignoreMouseEvent = false;
    private int mouseDeltaX, mouseDeltaY;

    public KeyEventHandler(Scene s, Camera c) {
        this.camera = c;
        this.scene = s;

        // on button press we add the keycode of the button to the list.
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                String code = event.getCode().toString();
                if (!keysPressed.contains(code)) {
                    System.out.println("Key pressed: " + code);
                    keysPressed.add(code);
                }

            }

        });
        // on button press we add the keycode of the button to the list.
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                String code = event.getCode().toString();
                keysPressed.remove(code);

            }

        });
        scene.setOnMouseMoved(event -> {
            double windowX = scene.getWindow().getX();
            double windowY = scene.getWindow().getY();
            if (ignoreMouseEvent) {
                ignoreMouseEvent = false;
                return;
            }

            xposDelta += -Math.round(event.getScreenX() - (windowX + (scene.getWidth() / 2.0)));
            yposDelta += Math.round((event.getScreenY() - (windowY + (scene.getHeight() / 2.0))));

            System.out.println("x: " + mouseDeltaX + " y: " + mouseDeltaY);
            handleMouseEvents();

            ignoreMouseEvent = true;
            moveCursor(windowX + scene.getWidth() / 2, windowY + scene.getHeight() / 2);
        });

    }

    public void handleKeyPresses(long deltaTime) {

        double cameraSpeed = camera.getCameraSpeed() * deltaTime;
        Vec3 cameraPos = camera.getCameraPosition();

        if (keysPressed.contains("W")) {
            
            cameraPos = new Vec3(cameraPos.addition(camera.getNormalizedCameraDirection().multiplicationByNumber(cameraSpeed)));
        }
        if (keysPressed.contains("S")) {
            cameraPos = new Vec3(cameraPos.substract(camera.getNormalizedCameraDirection().multiplicationByNumber(cameraSpeed)));
        }
        if (keysPressed.contains("A")) {

            cameraPos = new Vec3(cameraPos.substract(camera.getNormalizedCameraRight().multiplicationByNumber(cameraSpeed)));
        }
        if (keysPressed.contains("D")) {

            cameraPos = new Vec3(cameraPos.addition(camera.getNormalizedCameraRight().multiplicationByNumber(cameraSpeed)));
        }
        camera.setCameraPosition(cameraPos);
    }

    public void handleMouseEvents() {
        Vec3 cameraFront = camera.getCameraFront();
        double yaw = camera.getYaw();
        double pitch = camera.getPitch();

        // System.out.println("old x: "+lastX+" new x: "+xpos+" old y: "+lastY + " new
        // y: "+ypos);

        double xoffset = xposDelta - lastXDelta;
        double yoffset = lastYDelta - yposDelta;
        lastXDelta = xposDelta;
        lastYDelta = yposDelta;

        xoffset *= mouseSensitivity;
        yoffset *= mouseSensitivity;

        System.out.println("xoff: " + xoffset + " yoff: " + yoffset);

        yaw += xoffset;
        pitch += yoffset;

        if (pitch > 89.0f) {
            pitch = 89.0f;
        }
        if (pitch < -89.0f) {
            pitch = -89.0f;
        }

        cameraFront.setX(Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        cameraFront.setY(Math.sin(Math.toRadians(pitch)));
        cameraFront.setZ(Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));

        camera.setCameraFront(cameraFront);
        camera.setPitch(pitch);
        camera.setYaw(yaw);
        // move the cursor to the centre of the screen

    }

    public ArrayList<String> getKeysPressed() {
        return keysPressed;
    }

    public void moveCursor(double screenX, double screenY) {
        Robot robot = new Robot();

        if (!keysPressed.contains("ESCAPE")) {
            robot.mouseMove(screenX, screenY);
        }

    }

}
