package application.fxui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Created by Ihor on 8/20/2016. All rights reserved!
 */
class FieldsListener {
    public static boolean isNameBlank;
    private static boolean isVariableBlank;

    private static boolean firstNameBlank;
    private static boolean lastNameBlank;
    private static boolean skuBlank;
    private static boolean productNameBlank;
    private static boolean warehouseBlank;
    private static boolean binBlank;
    private static boolean supplierBlank;
    private static boolean loginBlank;
    private static boolean passwordBlank;

    private static boolean productQtyBlank;
    private static boolean zipCodeBlank;
    static void multipleFieldsValidation(TextField textField, Label fieldLabel, Label warningLabel, Node button) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean isNewValueBlank = newValue.trim().isEmpty();

            if(fieldLabel.getText().contains("First")){
                firstNameBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Last")) {
                lastNameBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Product SKU")) {
                skuBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Product Name")) {
                productNameBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Warehouse")) {
                warehouseBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Bin")) {
                binBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Supplier")) {
                supplierBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Login")) {
                loginBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Password")) {
                passwordBlank = isNewValueBlank;
            }

            if(fieldLabel.getText().contains("quantity")){
                productQtyBlank = isNewValueBlank;
            } else if (fieldLabel.getText().contains("Zip")) {
                zipCodeBlank = isNewValueBlank;
            }
            isNameBlank = firstNameBlank || lastNameBlank || skuBlank || productNameBlank || warehouseBlank ||
                    supplierBlank || binBlank || loginBlank || passwordBlank;


            isVariableBlank = productQtyBlank || zipCodeBlank;

            enableButtonValidation(isNameBlank || isVariableBlank, warningLabel, newValue, button);

            if(isNewValueBlank){
                textField.setStyle("-fx-text-box-border: #ff3021;");
                fieldLabel.setTextFill(Color.web("#ff3021"));
            } else if (!isNewValueBlank) {
                textField.setStyle("-fx-text-box-border: #c7c6c2;");
                if(fieldLabel.getText().contains("Login") || fieldLabel.getText().contains("Password")){
                    fieldLabel.setTextFill(Color.web("#2d6ca2"));
                } else if (!fieldLabel.getText().contains("Login") || !fieldLabel.getText().contains("Password")) {
                    fieldLabel.setTextFill(Color.web("#000"));
                }
            }
        });
    }

    private static void enableButtonValidation(boolean validationParameter, Label warningLabel, String newValue, Node button) {
        /**
         * This method allows to avoid enabling the confirmation button for multiple fields validation.
         * Makes the button disabled when try to fill one of multiple blank fields.
         * */
        if(warningLabel.isVisible()){
            if(!validationParameter){
                warningLabel.setVisible(newValue.trim().isEmpty());
                button.setDisable(newValue.trim().isEmpty());
            }
        } else if (!warningLabel.isVisible()){
            warningLabel.setVisible(newValue.trim().isEmpty());
            button.setDisable(newValue.trim().isEmpty());
        }
    }
}