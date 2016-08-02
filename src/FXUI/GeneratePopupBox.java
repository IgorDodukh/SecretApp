package FXUI;

import Settings.BrowserSettings;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Pair;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by Ihor on 7/15/2016.
 */
public class GeneratePopupBox {
    private static final ImageIcon hmm = new ImageIcon("C:\\appFiles\\pic\\hmm.png");

    public static Optional<ButtonType> exceptionResponse;
    public static Optional<ButtonType> confirmationResponse = Optional.ofNullable(ButtonType.CANCEL);
    public static Optional<Pair<String, String>> authorizeResponse;
    private static String[] magentos = {
            "qatestlab01",
            "qatestlab02",
            "qatestlab03",
            "qatestlab04",
            "qatestlab05",
            "qatestlab06",
            "qatestlab07",
            "qatestlab08",
            "qatestlab09",
            "qatestlab10",
            "hercules",};

    public static void exceptionPopupBox(Exception exception) {
        String exceptionMessage = "";
        exceptionMessage += "Test has been stopped unexpectedly.\n" + "\n";
        exceptionMessage += "Reason:\n";
        exceptionMessage += exception.getClass().getSimpleName() + "\n" + "\n";

        final String finalExceptionMessage = exceptionMessage;
        Platform.runLater(() -> {
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

            TextArea textArea = new TextArea(BrowserSettings.totalResultMessage + "\n" + exceptionText);
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

            exceptionResponse = exceptionDialog.showAndWait();
        });
    }

    public static void successPopupBox(String resultMessage) {
        Platform.runLater(() -> {
            Alert exceptionDialog = new Alert(Alert.AlertType.INFORMATION);
            exceptionDialog.setTitle("Complete." + " Running time: " + ExecutionTimeCounter.executionTime);
            exceptionDialog.setHeaderText("Oh boy, you are lucky.");
            exceptionDialog.setContentText(resultMessage);

            try {
                File f = new File("C:/appFiles/successDialog.css");
                DialogPane dialogPane = exceptionDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }

            exceptionDialog.showAndWait();
        });
    }

    public static void hmmPopupBox(String transactionWarning) {
        Platform.runLater(() -> {
            Alert hmmDialog = new Alert(Alert.AlertType.INFORMATION);
            hmmDialog.setTitle("Warning");
            hmmDialog.setHeaderText("Hey, you are inattentive.");
            hmmDialog.setContentText(transactionWarning + "\nOk, I'll give you another try.");

            try {
                File f = new File("C:/appFiles/hmmDialog.css");
                DialogPane dialogPane = hmmDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }

            hmmDialog.showAndWait();
        });

/*        JOptionPane.showMessageDialog(null,
                transactionWarning + "\nOk, I'll give you another try.",
                "Warning",
                JOptionPane.PLAIN_MESSAGE, hmm);*/
    }

    public static void indentifyPopupBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Igor");
        choices.add("Vika");
        choices.add("Oksanka");
        choices.add("Natasha");

        ChoiceDialog<String> identifyDialog = new ChoiceDialog<>("Igor", choices);
        identifyDialog.setTitle("Person identification");
        identifyDialog.setHeaderText("Please select who are you:");
        identifyDialog.setContentText("I'm ");

        try {
            File f = new File("C:/appFiles/identifyDialog.css");
            DialogPane dialogPane = identifyDialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
// Traditional way to get the response value.
        Optional<String> result = identifyDialog.showAndWait();
        if (result.isPresent()){
            if(Objects.equals(result.get(), "Igor")){
                BrowserSettings.authApiLoginId = "3y8Z2fk5Z3n";
                BrowserSettings.authTransactionKey = "2s25qyDYe249uTRx";
            } else if(Objects.equals(result.get(), "Vika")){
                BrowserSettings.authApiLoginId = "3Ud359XbX6";
                BrowserSettings.authTransactionKey = "67FK5537ng85nAPw";
            } else if(Objects.equals(result.get(), "Oksanka")){
                BrowserSettings.authApiLoginId = "2L6UmL7rE";
                BrowserSettings.authTransactionKey = "36Sc34JmhE9m493t";
            } else if(Objects.equals(result.get(), "Natasha")){
                BrowserSettings.authApiLoginId = "3z42Rqc3pMrX";
                BrowserSettings.authTransactionKey = "5gt38eVNu529t6ZP";
            }
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("Person select cancelled");
    }

    public static void authorizePopupBox() {
        final boolean[] idFieldBlank = new boolean[1];
        final boolean[] keyFieldBlank = new boolean[1];
        Platform.runLater(() -> {
            // Create the custom dialog.
            Dialog<Pair<String, String>> authorizeDialog = new Dialog<>();
            authorizeDialog.setTitle("Authorize.Net credentials");
            authorizeDialog.setHeaderText("Confirm your Authorize.Net credentials here");

// Set the icon (must be included in the project).
            try {
                File f = new File("C:/appFiles/authorizeDialog.css");
                DialogPane dialogPane = authorizeDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }

// Set the button types.
            ButtonType loginButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            authorizeDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField apiLoginID = new TextField();
            apiLoginID.setPromptText("111");
            TextField transactionKey = new TextField();
            transactionKey.setPromptText("222");

            grid.add(new Label("API Login ID:"), 0, 0);
            grid.add(apiLoginID, 1, 0);
            grid.add(new Label("Transaction Key:"), 0, 1);
            grid.add(transactionKey, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
            Node loginButton = authorizeDialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
            apiLoginID.textProperty().addListener((observable, oldValue, newValue) -> {
                    loginButton.setDisable(newValue.trim().isEmpty());

                idFieldBlank[0] = newValue.trim().isEmpty();
            });

            transactionKey.textProperty().addListener((observable, oldValue, newValue) -> {
                    loginButton.setDisable(newValue.trim().isEmpty());

                keyFieldBlank[0] = newValue.trim().isEmpty();
            });

            authorizeDialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//            Platform.runLater(apiLoginID::requestFocus);

// Convert the result to a username-password-pair when the login button is clicked.
            authorizeDialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(apiLoginID.getText(), transactionKey.getText());
                }
                return null;
            });

            authorizeResponse = authorizeDialog.showAndWait();
        });

//        JTextField field1 = new JTextField();
//        JTextField field2 = new JTextField();
//        field1.setText(BrowserSettings.authApiLoginId);
//        field2.setText(BrowserSettings.authTransactionKey);
//        Object[] message = {
//                "API Login ID:", field1,
//                "Transaction Key:                    ", field2,
//        };
//        popupOption = JOptionPane.showConfirmDialog(
//                null,
//                message,
//                "Authorize.Net credentials",
//                JOptionPane.OK_CANCEL_OPTION,
//                0,
//                authorize);
//
////  Authorize credentials fields validation
//        String transactionWarning = "It seems you forgot to fill ";
//        if (field1.getText().length() > 0){
//            BrowserSettings.authApiLoginId = field1.getText();
//        } else {
//            transactionFailed = true;
//            transactionWarning += "'API Login ID'";
//        }
//        if (field2.getText().length() > 0){
//            BrowserSettings.authTransactionKey = field2.getText();
//            transactionWarning += " field.";
//        } else {
//            if(transactionFailed){
//                transactionWarning += " and 'Transaction Key' fields.";
//            } else {
//                transactionWarning += "'Transaction Key' field.";
//                transactionFailed = true;
//            }
//        }
//
//        if (popupOption == JOptionPane.YES_OPTION){
//            if (transactionFailed){
//                GeneratePopupBox.hmmPopupBox(transactionWarning);
//            }
//        }
    }

    public static void creditCardsPopupBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Visa");
        choices.add("Master Card");
        choices.add("Discover");
        choices.add("American Express");

        ChoiceDialog<String> creditCardsDialog = new ChoiceDialog<>("Visa", choices);
        creditCardsDialog.setTitle("Select Credit Card type");
        creditCardsDialog.setHeaderText("Choose preferred Card type:");
        creditCardsDialog.setContentText("");

        try {
            File f = new File("C:/appFiles/creditCardsDialog.css");
            DialogPane dialogPane = creditCardsDialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
// Traditional way to get the response value.
        Optional<String> result = creditCardsDialog.showAndWait();
        if (result.isPresent()){
            if(Objects.equals(result.get(), "Visa")){
                Controller.testCardNumber = BrowserSettings.visaTestCardNumber;
            } else if(Objects.equals(result.get(), "Master Card")){
                Controller.testCardNumber = BrowserSettings.masterCardTestCardNumber;
            } else if(Objects.equals(result.get(), "Discover")){
                Controller.testCardNumber = BrowserSettings.discoverTestCardNumber;
            } else if(Objects.equals(result.get(), "American Express")){
                Controller.testCardNumber = BrowserSettings.americanExpressTestCardNumber;
            }
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("CC cancelled");
    }

    public static void magentoPopupBox() {
        List<String> choices = new ArrayList<>();
        Collections.addAll(choices, magentos);

        ChoiceDialog<String> magentoDialog = new ChoiceDialog<>("qatestlab01", choices);
        magentoDialog.setTitle("Select Magento Environment");
        magentoDialog.setHeaderText("Select Magento which you\nwould like" +
                " to sync with " + Controller.environmentComboBoxValue);
        magentoDialog.setContentText("");

        try {
            File f = new File("C:/appFiles/magentoDialog.css");
            DialogPane dialogPane = magentoDialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
// Traditional way to get the response value.
        Optional<String> result = magentoDialog.showAndWait();
        if (result.isPresent()){
            Controller.magentoIndex = choices.indexOf(result.get());
            Controller.magentoIndexName = result.get();
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("Select Magento cancelled");
    }

    public static void confirmationPopupBox () {
        String infoMessage = "";
        infoMessage += "Selected Browser: " + Controller.browserComboBoxValue + "\n";
        infoMessage += "Selected Test: " + Controller.entityComboBoxValue + "\n";
        infoMessage += "Selected Environment: " + Controller.environmentComboBoxValue + "\n\n";
        infoMessage += "Performing the test will take some time. Please wait!\nMake a cup of tea or hug somebody. He (or she) will be happy :)\n\n";

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Lucky Confirmation Dialog");
        confirmationAlert.setHeaderText("Test is starting now.");
        confirmationAlert.setContentText(infoMessage);

        try {
            File f = new File("C:/appFiles/confirmationDialog.css");
            DialogPane dialogPane = confirmationAlert.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }

        confirmationResponse = confirmationAlert.showAndWait();
    }
}