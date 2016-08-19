package FXUI;

import Settings.BrowserSettings;
import Settings.GetPropertyValues;
import Settings.UpdateConfig;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by Ihor on 7/15/2016.
 */
public class GeneratePopupBox {
    public static Optional<ButtonType> exceptionResponse;
    public static Optional<ButtonType> confirmationResponse;
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

    public static String[] timeouts = {
            "10",
            "20",
            "30",
            "60",};
    public static String currentTimeout = "";
    public static String currentUser = "";
    public static String userTypeToCreate = "";

    public static void exceptionPopupBox(Exception exception) {
        String exceptionMessage = "";
        exceptionMessage += "Test has been stopped unexpectedly.\n" + "\n";
        exceptionMessage += "Reason:\n";
        exceptionMessage += exception.getClass().getSimpleName() + "\n" + "\n";

        final String finalExceptionMessage = exceptionMessage;
        Platform.runLater(() -> {
            Alert exceptionDialog = new Alert(Alert.AlertType.INFORMATION);
            exceptionDialog.getDialogPane().setId("exception-dialog");
            exceptionDialog.setTitle("Failed. " + ProgressBar.currentProgress + "%. Running time: " + ExecutionTimeCounter.executionTime);
            exceptionDialog.setHeaderText("You are not lucky enough today.");
            exceptionDialog.setContentText(finalExceptionMessage);
            exceptionDialog.initStyle(StageStyle.UTILITY);

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
                File f = new File("C:/appFiles/styles/DialogBoxes.css");
                DialogPane dialogPane = exceptionDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }

            exceptionResponse = exceptionDialog.showAndWait();
        });
    }

    public static void failedPopupBox() {
        Platform.runLater(() -> {
            Alert exceptionDialog = new Alert(Alert.AlertType.INFORMATION);
            exceptionDialog.getDialogPane().setId("exception-dialog");
            exceptionDialog.setTitle("Test Failed. Running time: " + ExecutionTimeCounter.executionTime);
            exceptionDialog.setHeaderText("Test was failed because of some unexpectedly reasons.");
            exceptionDialog.setContentText(Controller.driverWarning[0] + Controller.driverExceptionMessage[0]);
            exceptionDialog.initStyle(StageStyle.UTILITY);

            try {
                File f = new File("C:/appFiles/styles/DialogBoxes.css");
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
            Alert successDialog = new Alert(Alert.AlertType.INFORMATION);
            successDialog.getDialogPane().setId("success-dialog");
            successDialog.setTitle("Complete." + ProgressBar.currentProgress + "%. Running time: " + ExecutionTimeCounter.executionTime);
            successDialog.setHeaderText("Oh boy, you are lucky.");
            successDialog.setContentText(resultMessage);
            successDialog.initStyle(StageStyle.UTILITY);

            try {
                File f = new File("C:/appFiles/styles/DialogBoxes.css");
                DialogPane dialogPane = successDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }

            successDialog.showAndWait();
        });
    }

    public static void indentifyPopupBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Igor");
        choices.add("Vika");
        choices.add("Oksanka");
        choices.add("Natasha");

        ChoiceDialog<String> identifyDialog = new ChoiceDialog<>(GetPropertyValues.user, choices);
        identifyDialog.getDialogPane().setId("indentify-dialog");
        identifyDialog.setTitle("Person identification");
        identifyDialog.setHeaderText("Please select who are you\nto identify Authorize.NET credentials");
        identifyDialog.setContentText("I'm ");
        identifyDialog.initStyle(StageStyle.UTILITY);

        identifyDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "Igor")){
                BrowserSettings.authApiLoginId = "3y8Z2fk5Z3n";
                BrowserSettings.authTransactionKey = "2s25qyDYe249uTRx";
            } else if(Objects.equals(newValue, "Vika")){
                BrowserSettings.authApiLoginId = "3Ud359XbX6";
                BrowserSettings.authTransactionKey = "67FK5537ng85nAPw";
            } else if(Objects.equals(newValue, "Oksanka")){
                BrowserSettings.authApiLoginId = "2L6UmL7rE";
                BrowserSettings.authTransactionKey = "36Sc34JmhE9m493t";
            } else if(Objects.equals(newValue, "Natasha")){
                BrowserSettings.authApiLoginId = "3z42Rqc3pMrX";
                BrowserSettings.authTransactionKey = "5gt38eVNu529t6ZP";
            }
            currentUser = newValue;
        });

        try {
            File f = new File("C:/appFiles/styles/DialogBoxes.css");
            DialogPane dialogPane = identifyDialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
// Traditional way to get the response value.
        Optional<String> result = identifyDialog.showAndWait();
        if (result.isPresent()){
            try {
                UpdateConfig.updateUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("Person select cancelled");
    }

    public static void userTypePopupBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Merchant");
        choices.add("Merchant Admin");
        choices.add("Merchandiser");
        choices.add("Picker");
        choices.add("Packer");
        choices.add("Shipper");
        choices.add("CSR");
        choices.add("CSR Manager");
        choices.add("Purchase Manager");
        choices.add("Warehouse Manager");

        ChoiceDialog<String> identifyDialog = new ChoiceDialog<>("Merchant", choices);
        identifyDialog.getDialogPane().setId("users-dialog");
        identifyDialog.setTitle("User Type Selecting");
        identifyDialog.setHeaderText("Please select type of user\nwhich you would like to create");
//        identifyDialog.setContentText("I would like to choose ");
        identifyDialog.initStyle(StageStyle.UTILITY);

        identifyDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "Merchant")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Merchant Admin")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Merchandiser")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Picker")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Packer")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Shipper")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "CSR")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "CSR Manager")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Purchase Manager")){
                System.out.println(newValue);
            } else if(Objects.equals(newValue, "Warehouse Manager")){
                System.out.println(newValue);
            }
        });

        try {
            File f = new File("C:/appFiles/styles/DialogBoxes.css");
            DialogPane dialogPane = identifyDialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
// Traditional way to get the response value.
        Optional<String> result = identifyDialog.showAndWait();
        if (result.isPresent()){
            userTypeToCreate = result.get();
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("User Type selecting cancelled");
    }

    public static void creditCardsPopupBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Visa");
        choices.add("Master Card");
        choices.add("Discover");
        choices.add("American Express");

        ChoiceDialog<String> creditCardsDialog = new ChoiceDialog<>("Visa", choices);
        creditCardsDialog.getDialogPane().setId("visa-credit-cards-dialog");
        creditCardsDialog.setTitle("Select Credit Card type");
        creditCardsDialog.setHeaderText("Choose preferred Card type:");
        creditCardsDialog.setContentText("");
        creditCardsDialog.initStyle(StageStyle.UTILITY);

        creditCardsDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(Objects.equals(newValue, "Visa")){
                    creditCardsDialog.getDialogPane().setId("visa-credit-cards-dialog");
                    Controller.testCardNumber = BrowserSettings.visaTestCardNumber;
                } else if(Objects.equals(newValue, "Master Card")){
                    creditCardsDialog.getDialogPane().setId("mc-credit-cards-dialog");
                    Controller.testCardNumber = BrowserSettings.masterCardTestCardNumber;
                } else if(Objects.equals(newValue, "Discover")){
                    creditCardsDialog.getDialogPane().setId("discover-credit-cards-dialog");
                    Controller.testCardNumber = BrowserSettings.discoverTestCardNumber;
                } else if(Objects.equals(newValue, "American Express")){
                    creditCardsDialog.getDialogPane().setId("ae-credit-cards-dialog");
                    Controller.testCardNumber = BrowserSettings.masterCardTestCardNumber;
                }
        });

        try {
            File f = new File("C:/appFiles/styles/DialogBoxes.css");
            DialogPane dialogPane = creditCardsDialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
// Traditional way to get the response value.
        Optional<String> result = creditCardsDialog.showAndWait();

        if (result.isPresent()){
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("CC cancelled");
    }

    public static void magentoPopupBox() {
        List<String> choices = new ArrayList<>();
        Collections.addAll(choices, magentos);

        ChoiceDialog<String> magentoDialog = new ChoiceDialog<>("qatestlab01", choices);
        magentoDialog.getDialogPane().setId("magento-dialog");
        magentoDialog.setTitle("Select Magento Environment");
        magentoDialog.setHeaderText("Select Magento which you\n" +
                "would like to sync with " + Controller.environmentComboBoxValue);
        magentoDialog.setContentText("I want to choose ");
        magentoDialog.initStyle(StageStyle.UTILITY);

        try {
            File f = new File("C:/appFiles/styles/DialogBoxes.css");
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
        confirmationAlert.getDialogPane().setId("confirmation-dialog");
        confirmationAlert.setTitle("Lucky Confirmation Dialog");
        confirmationAlert.setHeaderText("Test is starting now.");
        confirmationAlert.setContentText(infoMessage);
        confirmationAlert.initStyle(StageStyle.UTILITY);

        try {
            File f = new File("C:/appFiles/styles/DialogBoxes.css");
            DialogPane dialogPane = confirmationAlert.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            dialogPane.getStyleClass().add("myDialog");
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }

        confirmationResponse = confirmationAlert.showAndWait();
    }

    public static void configPopupBox() {
        Platform.runLater(() -> {
            List<String> choices = new ArrayList<>();
            Collections.addAll(choices, timeouts);

            ChoiceDialog<String> configDialog = new ChoiceDialog<>(String.valueOf(GetPropertyValues.timeoutProperty), choices);
            configDialog.getDialogPane().setId("config-dialog");
            configDialog.setTitle("Configurations");
            configDialog.setHeaderText("Configure Secret App");
            configDialog.setContentText("Preferred Timeout (sec.): ");
            configDialog.initStyle(StageStyle.UTILITY);

            try {
                File f = new File("C:/appFiles/styles/DialogBoxes.css");
                DialogPane dialogPane = configDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }
// Traditional way to get the response value.
            Optional<String> result = configDialog.showAndWait();
            if (result.isPresent()){
                currentTimeout = result.get();
                BrowserSettings.timeoutVariable = Integer.valueOf(currentTimeout);
                try {
                    UpdateConfig.updateTimeout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else System.out.println("Select Magento cancelled");
        });
    }

    public static void aboutPopupBox() {
        Platform.runLater(() -> {
            Alert exceptionDialog = new Alert(Alert.AlertType.INFORMATION);
            exceptionDialog.getDialogPane().setId("about-dialog");
            exceptionDialog.setTitle("About");
            exceptionDialog.setHeaderText("Hi there! It's About of Secret App");
            exceptionDialog.setContentText("This application is developed to make QA life easier " +
                    "while doing routine things...");
            exceptionDialog.initStyle(StageStyle.UTILITY);

            try {
                File f = new File("C:/appFiles/styles/DialogBoxes.css");
                DialogPane dialogPane = exceptionDialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                dialogPane.getStyleClass().add("myDialog");
            } catch (Exception e){
                System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
            }

            exceptionResponse = exceptionDialog.showAndWait();
        });
    }
}