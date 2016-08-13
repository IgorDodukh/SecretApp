package FXUI;

import Settings.BrowserSettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Created by Ihor on 7/16/2016.
 */
public class TestStatus {

    public void startTest(Button startButton, Button stopButton, Label waitingLabel, ImageView waitingAnimation/*, ProgressBar progressBar*/) {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        waitingLabel.setVisible(true);
        waitingAnimation.setVisible(true);
//        progressBar.setVisible(true);
        Controller.addProgressValue = 0;
        Controller.resultMessage = "";
        BrowserSettings.totalResultMessage = "";
    }

    public void stopTest(Button startButton, Button stopButton, Label waitingLabel, ImageView waitingAnimation/*, ProgressBar progressBar*/) {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        waitingLabel.setVisible(false);
        waitingAnimation.setVisible(false);
//        progressBar.setVisible(false);
        Controller.addProgressValue = 0;

    }
}
