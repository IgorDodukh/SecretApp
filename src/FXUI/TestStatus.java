package FXUI;

import javax.swing.*;

/**
 * Created by Ihor on 7/16/2016.
 */
public class TestStatus {

    public void startTest(JButton startButton, JLabel waitingLabel, JLabel waitingAnimation, JProgressBar progressBar) {
        startButton.setEnabled(false);
        waitingLabel.setVisible(true);
        waitingAnimation.setVisible(true);
        progressBar.setVisible(true);
//        Controller.progressBar.setValue(0);
        Controller.addProgressValue = 0;
        Controller.resultMessage = "";
    }

    public void stopTest(JButton startButton, JLabel waitingLabel, JLabel waitingAnimation, JProgressBar progressBar) {
        startButton.setEnabled(true);
        waitingLabel.setVisible(false);
        waitingAnimation.setVisible(false);
        progressBar.setVisible(false);
//        Controller.progressBar.setValue(0);
        Controller.addProgressValue = 0;

    }
}
