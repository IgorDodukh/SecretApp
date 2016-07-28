package FXUI;

import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 * Created by Ihor on 7/29/2016.
 */
public class ChangeLabelValue {
    public void changeWaitingLabelValue(Label label, int value) {
        Task<Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                updateMessage("Test is running... ");
                return null;
            }
        };

        label.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(e -> {
            label.textProperty().unbind();
            // this message will be seen.
            label.setText("Test is running... " + String.valueOf(value) + "%");
        });
    }
}
