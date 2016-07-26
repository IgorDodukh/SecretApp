package FXUI;

/**
 * Created by Ihor on 7/17/2016.
 */
public class ProgressBar {

    public static void addProgressValue(int i) {
//        Controller.progressBar.paintImmediately(0, 0, 200, 25);
        Controller.addProgressValue = Controller.addProgressValue + i;
//        Controller.progressBar.setValue(Controller.addProgressValue);
//        Controller.waitingLabel.setText("Test is running... " + Controller.addProgressValue + "%");
    }
}
