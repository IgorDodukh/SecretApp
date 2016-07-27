package FXUI;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Created by Ihor on 7/13/2016.
 */
public class FieldsValidation {

    public static void loginPassValidation(String loginValue, String password, TextField loginField,
                                           PasswordField passwordField, Label loginLabel, Label passwordLabel) {
        if (loginField.getText().isEmpty()){
            loginField.setStyle("-fx-text-box-border: #ff3021;");
            loginLabel.setTextFill(Color.web("#ff3021"));
            Controller.loginFilled = false;
        } else if (!loginField.getText().isEmpty()) {
            loginField.setStyle("-fx-text-box-border: #c7c6c2;");
            loginLabel.setTextFill(Color.web("#2d6ca2"));
            Controller.loginFilled = true;
        }
        if (passwordField.getText().isEmpty()) {
            passwordField.setStyle("-fx-text-box-border: #ff3021;");
            passwordLabel.setTextFill(Color.web("#ff3021"));
            Controller.passFilled = false;
        } else if (!passwordField.getText().isEmpty()) {
            passwordField.setStyle("-fx-text-box-border: #c7c6c2;");
            passwordLabel.setTextFill(Color.web("#2d6ca2"));
            Controller.passFilled = true;
        }
    }
}
