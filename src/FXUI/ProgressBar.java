package FXUI;

/**
 * Created by Ihor on 7/17/2016.
 */
public class ProgressBar {
    static int currentProgress;
    public static void addProgressValue(int i) {
        currentProgress = Controller.addProgressValue + i;
        Controller.addProgressValue = currentProgress;
    }
}
