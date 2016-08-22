package FXUI;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * Created by Ihor on 7/29/2016. All rights reserved!
 */
public  class ChangeLabelValue {
    public static int currentValue = 1;
    public static void changeWaitingLabelValue(Label label, int value) {
        Platform.runLater(() -> {
            Task<Void> task = new Task<Void>() {
                @Override public Void call() throws InterruptedException {
                    updateMessage(String.valueOf(value) + "%");
                    return null;
                }
            };

            System.out.println("---Value:" + value + "|---Current:" + currentValue);
            if (!Objects.equals(currentValue, value)) {
                label.textProperty().bind(task.messageProperty());
                System.out.println("---Value:" + value + "|---Current:" + currentValue);
                task.setOnSucceeded(e -> {
                    label.textProperty().unbind();
                    // this message will be seen.
                    label.setText(String.valueOf(value) + "%");
                });
            } else if(Objects.equals(currentValue, value)){
                System.out.println("---Value:" + value + "|---Current:" + currentValue);
            }
            currentValue = value;
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        });
    }
}
