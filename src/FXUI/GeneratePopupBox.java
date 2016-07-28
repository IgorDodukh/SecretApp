package FXUI;

import Settings.BrowserSettings;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Ihor on 7/15/2016.
 */
public class GeneratePopupBox extends Main {
    private static final ImageIcon success = new ImageIcon("C:\\appFiles\\pic\\success.png");
    private static final ImageIcon sad = new ImageIcon("C:\\appFiles\\pic\\sad.png");
    private static final ImageIcon hmm = new ImageIcon("C:\\appFiles\\pic\\hmm.png");

    public static void exceptionPopupBox(Exception exception) {
        javafx.scene.control.TextArea textArea = new TextArea();
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
//        ScrollPane scrollPane = new ScrollPane(ta);
        scrollPane.setContent(textArea);
        scrollPane.setPrefViewportWidth(300);
        scrollPane.setPrefViewportHeight(400);

        /////////////////////


        /////////////////////


        String exceptionMessage = "";
        exceptionMessage += "Test has been stopped unexpectedly.\n" + "\n";
        exceptionMessage += "Reason:\n";
        exceptionMessage += exception.getClass().getSimpleName() + "\n" + "\n";

        textArea.setText(BrowserSettings.totalResultMessage);
        Object[] exceptionLog = {
                exceptionMessage,
                "Execution log:\n", scrollPane,
        };

        final String finalExceptionMessage = exceptionMessage;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert exceptionDialog = new Alert(Alert.AlertType.INFORMATION);
                exceptionDialog.setTitle("Failed. " + ". Running time: " + ExecutionTimeCounter.executionTime);
                exceptionDialog.setHeaderText("You are not lucky enough today.");
                exceptionDialog.setContentText(finalExceptionMessage);

// Create expandable Exception.
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                exception.printStackTrace(pw);
                String exceptionText = sw.toString();

                Label label = new Label("The exception stacktrace was:");

                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);

                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);

                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
                exceptionDialog.getDialogPane().setExpandableContent(expContent);

                try {
                    File f = new File("C:/appFiles/exceptionDialog.css");
                    DialogPane dialogPane = exceptionDialog.getDialogPane();
                    dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                    dialogPane.getStyleClass().add("myDialog");
                } catch (Exception e){
                    System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
                }

                exceptionDialog.showAndWait();
            }
        });
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
