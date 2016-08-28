package FXUI;

import Settings.BrowserSettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Created by Ihor on 7/16/2016. All rights reserved!
 */
public class TestStatus {

    public void startTest(Button startButton, Button stopButton, Label waitingLabel, Label progressLabel, ImageView waitingAnimation/*, ProgressBar progressBar*/) {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        waitingLabel.setVisible(true);
        progressLabel.setVisible(true);
        waitingAnimation.setVisible(true);
//        progressBar.setVisible(true);
        Controller.setProgressValue(0);
        Controller.setResultMessage("");
        BrowserSettings.setTotalResultMessage("");
    }

    public void stopTest(Button startButton, Button stopButton, Label waitingLabel, Label progressLabel, ImageView waitingAnimation/*, ProgressBar progressBar*/) {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        waitingLabel.setVisible(false);
        progressLabel.setVisible(false);
        waitingAnimation.setVisible(false);
//        progressBar.setVisible(false);
        Controller.setProgressValue(0);

    }
}
