package FXUI;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Ihor on 8/16/2016. All rights reserved!
 */
public class KeysListener {
    public static void comboBoxKeyListener(ComboBox comboBox, Controller controller) {
        comboBox.setOnKeyPressed(key -> clickStartButton(key, controller));
    }

    public static void textFieldKeyListener(TextField textField, Controller controller) {
        textField.setOnKeyPressed(key -> clickStartButton(key, controller));
    }

    public static void passFieldKeyListener(PasswordField passwordField, Controller controller) {
        passwordField.setOnKeyPressed(key -> clickStartButton(key, controller));
    }

    public static void buttonsKeyListener(Button button, Controller controller) {
        button.setOnKeyPressed(key -> clickStartButton(key, controller));
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
