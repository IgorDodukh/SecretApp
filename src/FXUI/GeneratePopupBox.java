package FXUI;

import Settings.BrowserSettings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ihor on 7/15/2016.
 */
public class GeneratePopupBox {
    private static final ImageIcon success = new ImageIcon("C:\\appFiles\\pic\\success.png");
    private static final ImageIcon sad = new ImageIcon("C:\\appFiles\\pic\\sad.png");
    private static final ImageIcon hmm = new ImageIcon("C:\\appFiles\\pic\\hmm.png");

    public static void exceptionPopupBox(Exception exception) {
        JTextArea ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);
        scrollPane.setPreferredSize(new Dimension(270, 200));

        String exceptionMessage = "";
        exceptionMessage += "You are not lucky enough today.\n" + "\n";
        exceptionMessage += "Test has been stopped unexpectedly.\n" + "\n";
        exceptionMessage += "Reason:\n";
        exceptionMessage += exception.getClass().getSimpleName() + "\n" + "\n";

        ta.setText(BrowserSettings.totalResultMessage);
        Object[] exceptionLog = {
                exceptionMessage,
                "Execution log:\n", scrollPane,
        };

        JOptionPane.showConfirmDialog(
                null,
                exceptionLog,
                "Failed. " + /*Controller.waitingLabel.getText().replaceAll("Test is running... ","") +*/ ". Running time: " + ExecutionTimeCounter.executionTime,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                sad);
        BrowserSettings.totalResultMessage = "";
    }

    public static void successPopupBox(String resultMessage) {
        JOptionPane.showMessageDialog(
                null,
                resultMessage,
                "Complete."/* + SimpleGUI.waitingLabel.getText().replaceAll("Test is running... ","")*/ + " Running time: " + ExecutionTimeCounter.executionTime,
                JOptionPane.PLAIN_MESSAGE,
                success);
        BrowserSettings.totalResultMessage = "";
    }

    public static void hmmPopupBox(String transactionWarning) {
        JOptionPane.showMessageDialog(null,
                transactionWarning + "\nOk, I'll give you another try.",
                "Warning",
                JOptionPane.PLAIN_MESSAGE, hmm);
    }
}
