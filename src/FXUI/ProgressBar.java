package FXUI;

/**
 * Created by Ihor on 7/17/2016.
 */
public class ProgressBar {

    public static void addProgressValue(int i) {
        Controller.addProgressValue = Controller.addProgressValue + i;
        Controller controller = new Controller();

//        Task<Void> task2 = new Task<Void>() {
//            @Override public Void call() throws InterruptedException {
//                updateMessage("Test is running... " + String.valueOf(Controller.addProgressValue) + "%");
//                return null;
//            }
//        };
//
//        controller.waitingLabel.textProperty().bind(task2.messageProperty());
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
}
