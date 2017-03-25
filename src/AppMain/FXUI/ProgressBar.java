package AppMain.FXUI;

/**
 * Created by Ihor on 7/17/2016. All rights reserved!
 */
public class ProgressBar {
    static int currentProgress;

    public static void addProgressValue(int i) {
        currentProgress = Controller.getProgressValue() + i;
        Controller.setProgressValue(currentProgress);
    }

//    public static void addResponseStatus(String status) {
//        Controller.setResponseStatusCode(status);
//    }
}
