package FXUI;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Ihor on 8/16/2016. All rights reserved!
 */
public class KeysListener {
    public static void startButtonKeyListener(Node element, Controller controller) {
        element.setOnKeyPressed(key -> clickStartButton(key, controller));
    }

    public static void clickStartButton(KeyEvent key, Controller controller) {
        if(Objects.equals(key.getCode().toString(), "ENTER")){
            try {
                controller.clickStartButton();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
