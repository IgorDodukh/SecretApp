package FXUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Ihor on 8/16/2016. All rights reserved!
 */
class KeysListener {
    static void startButtonKeyListener(Node element, Controller controller) {
        element.setOnKeyPressed(key -> clickStartButton(key, controller));
    }

    private static void clickStartButton(KeyEvent key, Controller controller) {

        if(!FieldsListener.isNameBlank) {
            if (Objects.equals(key.getCode().toString(), "ENTER")) {
                try {
                    controller.clickStartButton();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void notSupportedResource(Controller controller, ComboBox<String> resourceComboBox, ComboBox<String> requestComboBox) {
        requestComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedResourceIndex = resourceComboBox.getSelectionModel().getSelectedIndex();
            if(selectedResourceIndex == 0 || selectedResourceIndex == 3 || selectedResourceIndex == 5){
                if(requestComboBox.getSelectionModel().getSelectedIndex() == 1){
                    controller.notSupportedRequest(true);
                } else controller.notSupportedRequest(false);
            }
        });
        resourceComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(requestComboBox.getSelectionModel().getSelectedIndex() == 1){
                if(newValue.contains("Order") || newValue.contains("Supplier") || newValue.contains("Bin")){
                    controller.notSupportedRequest(true);
                } else controller.notSupportedRequest(false);
            }
        });
    }

    static void spinnerEnabler(Label responseLabel, ImageView waitingSpinner, Button sendButton) {
        responseLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (Objects.equals(t1, "")) {
                    waitingSpinner.setVisible(true);
                    sendButton.setDisable(true);
                } else {
                    waitingSpinner.setVisible(false);
                    sendButton.setDisable(false);
                }
            }
        });

    }


}
