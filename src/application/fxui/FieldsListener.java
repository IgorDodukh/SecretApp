package application.fxui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Created by Ihor on 8/20/2016. All rights reserved!
 */
class FieldsListener {
    static boolean isNameBlank = false;
    private static boolean firstNameBlank = false;
    private static boolean lastNameBlank = false;
    private static boolean skuBlank = false;
    private static boolean productNameBlank = false;
    private static boolean warehouseBlank = false;
    private static boolean binBlank = false;
    private static boolean supplierBlank = false;
    private static boolean loginBlank = false;
    private static boolean passwordBlank = false;
    static void multipleFieldsValidation(TextField textField, Label fieldLabel, Label warningLabel, Node button) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.trim().isEmpty()){
                if(fieldLabel.getText().contains("First")){
                    firstNameBlank = true;
                } else if (fieldLabel.getText().contains("Last")) {
                    lastNameBlank = true;
                } else if (fieldLabel.getText().contains("Product SKU")) {
                    skuBlank = true;
                } else if (fieldLabel.getText().contains("Product Name")) {
                    productNameBlank = true;
                } else if (fieldLabel.getText().contains("Warehouse")) {
                    warehouseBlank = true;
                } else if (fieldLabel.getText().contains("Bin")) {
                    binBlank = true;
                } else if (fieldLabel.getText().contains("Supplier")) {
                    supplierBlank = true;
                } else if (fieldLabel.getText().contains("Login")) {
                    loginBlank = true;
                } else if (fieldLabel.getText().contains("Password")) {
                    passwordBlank = true;
                }

                textField.setStyle("-fx-text-box-border: #ff3021;");
                fieldLabel.setTextFill(Color.web("#ff3021"));
            } else if (!newValue.trim().isEmpty()) {
                if(fieldLabel.getText().contains("First")){
                    firstNameBlank = false;
                } else if (fieldLabel.getText().contains("Last")) {
                    lastNameBlank = false;
                } else if (fieldLabel.getText().contains("Product SKU")) {
                    skuBlank = false;
                } else if (fieldLabel.getText().contains("Product Name")) {
                    productNameBlank = false;
                } else if (fieldLabel.getText().contains("Warehouse")) {
                    warehouseBlank = false;
                } else if (fieldLabel.getText().contains("Bin")) {
                    binBlank = false;
                } else if (fieldLabel.getText().contains("Supplier")) {
                    supplierBlank = false;
                } else if (fieldLabel.getText().contains("Login")) {
                    loginBlank = false;
                } else if (fieldLabel.getText().contains("Password")) {
                    passwordBlank = false;
                }

                textField.setStyle("-fx-text-box-border: #c7c6c2;");
                if(fieldLabel.getText().contains("Login") || fieldLabel.getText().contains("Password")){
                    fieldLabel.setTextFill(Color.web("#2d6ca2"));
                } else if (!fieldLabel.getText().contains("Login") || !fieldLabel.getText().contains("Password")) {
                    fieldLabel.setTextFill(Color.web("#000"));
                }
            }
            isNameBlank = firstNameBlank || lastNameBlank || skuBlank || productNameBlank || warehouseBlank ||
                    supplierBlank || binBlank || loginBlank || passwordBlank;

/**
 * This additional conditions allow to avoid enabling the button for multiple fields validation
 * Make the button disabled when try to fill one of blank fields
 * */
            if(warningLabel.isVisible()){
                if(!isNameBlank){
                    warningLabel.setVisible(newValue.trim().isEmpty());
                    button.setDisable(newValue.trim().isEmpty());
                }
            } else if (!warningLabel.isVisible()){
                warningLabel.setVisible(newValue.trim().isEmpty());
                button.setDisable(newValue.trim().isEmpty());
            }
        });
    }
}