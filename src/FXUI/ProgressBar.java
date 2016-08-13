package FXUI;

import javafx.scene.control.Label;

/**
 * Created by Ihor on 7/17/2016.
 */
public class ProgressBar {
    static int currentProgress;

    public static void addProgressValue(int i) {
        currentProgress = Controller.addProgressValue + i;
//        Controller.addProgressValue = Controller.addProgressValue + i;
        Controller.addProgressValue = currentProgress;
//        ChangeLabelValue.changeWaitingLabelValue(Controller.addProgressValue);
    }

    public static void updateWaitingLabel(Label label) {
//        Controller controller = new Controller();
//        Platform.runLater(() -> {
//            Task<Void> task = new Task<Void>() {
//                @Override public Void call() throws InterruptedException {
//                    updateMessage("Test is running... ");
//                    return null;
//                }
//            };
//
//            label.textProperty().bind(task.messageProperty());
//            task.setOnSucceeded(e -> {
//                label.textProperty().unbind();
//                // this message will be seen.
//                label.setText("Test is running... " + String.valueOf(Controller.addProgressValue) + "%");
//            });
//
//            Thread thread = new Thread(task);
//            thread.setDaemon(true);
//            thread.start();
//        });
    }


/**/
//
//        task2.setOnSucceeded(e -> {
//            controller.waitingLabel.textProperty().unbind();
//            // this message will be seen.
//            controller.waitingLabel.setText("Test is running... " + String.valueOf(Controller.addProgressValue) + "%");
//        });

//        Controller.progressBar.paintImmediately(0, 0, 200, 25);
//          Controller.addProgressValue = Controller.addProgressValue + i;
//        Controller.progressBar.setValue(Controller.addProgressValue);
//        Controller.waitingLabel.setText("Test is running... " + Controller.addProgressValue + "%");

//        controller.waitingLabel.textProperty().setValue("Test is running... " + Controller.addProgressValue + "%");
    }
//}
