package FXUI;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Ihor on 8/16/2016.
 */
public class KeysListener {
    public static void comboboxKeyListener(ComboBox comboBox, Controller controller) {
        comboBox.setOnKeyPressed(key -> {
            if(Objects.equals(key.getCode().toString(), "ENTER")){
                try {
                    controller.clickStartButton();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void textFieldKeyListener(TextField textField, Controller controller) {
        textField.setOnKeyPressed(key -> {
            if(Objects.equals(key.getCode().toString(), "ENTER")){
                try {
                    controller.clickStartButton();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void passFieldKeyListener(PasswordField passwordField, Controller controller) {
        passwordField.setOnKeyPressed(key -> {
            if(Objects.equals(key.getCode().toString(), "ENTER")){
                try {
                    controller.clickStartButton();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void buttonsKeyListener(Button button, Controller controller) {
        button.setOnKeyPressed(key -> {
            if(Objects.equals(key.getCode().toString(), "ENTER")){
                try {
                    controller.clickStartButton();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
