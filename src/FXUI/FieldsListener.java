package FXUI;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Created by Ihor on 8/20/2016. All rights reserved!
 */
class FieldsListener {
    static boolean isBlank = false;
    static boolean firstNameBlank = false;
    static boolean lastNameBlank = false;
    static boolean skuBlank = false;
    static boolean productNameBlank = false;
    static boolean warehouseBlank = false;
    static boolean binBlank = false;
    static boolean supplierBlank = false;
    public static void namesSettingsFieldListener(TextField textField, Label fieldLabel, Label warningLabel, Node button) {
//        textField.setOnKeyPressed(event -> {
//            System.out.println(event.getCode());
//            isBlank = GeneratePopupBox.customerFirstNameBlank || GeneratePopupBox.customerLastNameBlank;
//            if ((Objects.equals(event.getCode(), KeyCode.BACK_SPACE) || Objects.equals(event.getCode(), KeyCode.DELETE)) && textField.getText().length() <= 1) {
//                textField.setStyle("-fx-text-box-border: #ff3021;");
//                fieldLabel.setTextFill(Color.web("#ff3021"));
//                warningLabel.setVisible(true);
//                fieldLabel.setCache(true);
//                if(fieldLabel.getText().contains("First")){
//                    GeneratePopupBox.customerFirstNameBlank = true;
//                } else if(fieldLabel.getText().contains("Last")){
//                    GeneratePopupBox.customerLastNameBlank = true;
//                }
//                System.out.println(isBlank + "--red");
//            } else if (textField.getText().length() > (-3) && (event.getCode().isDigitKey() ||event.getCode().isLetterKey())){
//                textField.setStyle("-fx-text-box-border: #c7c6c2;");
//                fieldLabel.setTextFill(Color.web("#000"));
//                fieldLabel.setCache(false);
//                if(fieldLabel.getText().contains("First")){
//                    GeneratePopupBox.customerFirstNameBlank = false;
//                } else if(fieldLabel.getText().contains("Last")){
//                    GeneratePopupBox.customerLastNameBlank = false;
//                }
//                System.out.println(isBlank + "--white");
//                if(!isBlank){
//                    warningLabel.setVisible(false);
//                }
//            }
//        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(fieldLabel.getText());

            if(newValue.trim().isEmpty()){
                if(fieldLabel.getText().contains("First")){
                    firstNameBlank = true;
                } else if (fieldLabel.getText().contains("Last")) {
                    lastNameBlank = true;
                } else if (fieldLabel.getText().contains("Product SKU")) {
                    skuBlank = true;
                } else if (fieldLabel.getText().contains("Product Name")) {
                    productNameBlank = true;
                } else if (fieldLabel.getText().contains("warehouse")) {
                    warehouseBlank = true;
                } else if (fieldLabel.getText().contains("bin")) {
                    binBlank = true;
                } else if (fieldLabel.getText().contains("supplier")) {
                    supplierBlank = true;
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
                } else if (fieldLabel.getText().contains("warehouse")) {
                    warehouseBlank = false;
                } else if (fieldLabel.getText().contains("bin")) {
                    binBlank = false;
                } else if (fieldLabel.getText().contains("supplier")) {
                    supplierBlank = false;
                }

                textField.setStyle("-fx-text-box-border: #c7c6c2;");
                fieldLabel.setTextFill(Color.web("#000"));
            }
            isBlank = firstNameBlank || lastNameBlank || skuBlank || productNameBlank || warehouseBlank ||
            supplierBlank || binBlank;

            if(warningLabel.isVisible()){
                if(!isBlank){
                    warningLabel.setVisible(newValue.trim().isEmpty());
                    button.setDisable(newValue.trim().isEmpty());
                }
            } else if (!warningLabel.isVisible()){
                warningLabel.setVisible(newValue.trim().isEmpty());
                button.setDisable(newValue.trim().isEmpty());
            }

            System.out.println(String.valueOf(isBlank));
        });
    }
}