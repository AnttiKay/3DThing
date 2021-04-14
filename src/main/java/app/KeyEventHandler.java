package app;

import java.util.ArrayList;

import app.dataStructures.vectors.Vec3;
import app.transformations.Camera;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler {
    private ArrayList<String> keysPressed = new ArrayList<>();
    private Camera camera;

    public KeyEventHandler(Scene s, Camera c) {
        this.camera = c;

        // on button press we add the keycode of the button to the list.
        s.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
        s.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                String code = event.getCode().toString();
                keysPressed.remove(code);

            }

        });
    }

    public void handleKeyPresses(long deltaTime) {

        double cameraSpeed = camera.getCameraSpeed() * deltaTime;
        Vec3 cameraPos = camera.getCameraPosition();

        if (keysPressed.contains("W")) {
            camera.setCameraPosition(
                    cameraPos.addition(camera.getNormalizedCameraDirection().multiplicationByNumber(cameraSpeed)));
        }
        if (keysPressed.contains("S")) {
            camera.setCameraPosition(
                    cameraPos.substract(camera.getNormalizedCameraDirection().multiplicationByNumber(cameraSpeed)));
        }
        if (keysPressed.contains("A")) {
            camera.setCameraPosition(
                    cameraPos.substract(camera.getNormalizedCameraRight().multiplicationByNumber(cameraSpeed)));
        }
        if (keysPressed.contains("D")) {
            camera.setCameraPosition(
                    cameraPos.addition(camera.getNormalizedCameraRight().multiplicationByNumber(cameraSpeed)));
        }
    }

    public ArrayList<String> getKeysPressed() {
        return keysPressed;
    }
}
