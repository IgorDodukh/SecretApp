package FXUI;

import API.Settings.RequestsSender;
import Settings.BrowserSettings;
import Settings.GetPropertyValues;
import Settings.UpdateConfig;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static Settings.GetPropertyValues.getTimeoutProperty;

/**
 * Created by Ihor on 7/15/2016. All rights reserved!
 */
public class GeneratePopupBox {

    private static final AppStyles appStyles = new AppStyles();

    public static Optional<ButtonType> exceptionResponse;
    public static Optional<ButtonType> confirmationResponse;
    private static final String[] magentoAdminPanels = {
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

    private static final ObservableList<String> timeouts =
            FXCollections.observableArrayList(
                    "10",
                    "20",
                    "30",
                    "60");

    private static final ObservableList<String> randomValue =
            FXCollections.observableArrayList(
                    "1",
                    "2",
                    "3",
                    "4",
                    "5");
    public static String currentTimeout = "";
    public static String currentRandomLength = "";
    public static String currentUser = "";
    public static String userTypeToCreate = "";
    public static String currentCustomerFirstName = "";
    public static String currentCustomerLastName = "";
    public static String currentProductSKU = "";
    public static String currentProductName = "";
    public static String currentWarehouseName = "";
    public static String currentSupplierName = "";
    public static String currentBinName = "";
    public static String currentMainPath = "";
    public static String currentAuthApiLoginId = "";
    public static String currentAuthTransactionKey = "";

    static void exceptionPopupBox(Exception exception) {
        String exceptionMessage = "";
        exceptionMessage += "Test has been stopped unexpectedly.\n" + "\n";
        exceptionMessage += "Reason:\n";
        exceptionMessage += exception.getClass().getSimpleName() + "\n" + "\n";

        final String finalExceptionMessage = exceptionMessage;
        Platform.runLater(() -> {
            Alert exceptionDialog = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(exceptionDialog, "sad.png");
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

            TextArea textArea = new TextArea(BrowserSettings.getTotalResultMessage() + "\n" + exceptionText);
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
                appStyles.setDialogStyle(exceptionDialog);
            } catch (IOException e) {
                e.printStackTrace();
            }

            exceptionResponse = exceptionDialog.showAndWait();
        });
    }

    public static void failedPopupBox(String contentText) {
        Platform.runLater(() -> {
            Alert failedDialog = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(failedDialog, "sad.png");
            failedDialog.setTitle("Warning");
            failedDialog.setHeaderText("Bad news for you...");
            failedDialog.setContentText(contentText);
            failedDialog.initStyle(StageStyle.UTILITY);

            try {
                appStyles.setDialogStyle(failedDialog);
            } catch (IOException e) {
                e.printStackTrace();
            }
            exceptionResponse = failedDialog.showAndWait();
        });
    }

    public static void successPopupBox(String resultMessage) {
        Platform.runLater(() -> {
            Alert successDialog = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(successDialog, "success.png");
            successDialog.setTitle("Complete." + ProgressBar.currentProgress + "%. Running time: " + ExecutionTimeCounter.executionTime);
            successDialog.setHeaderText("Oh boy, you are lucky.");
            successDialog.setContentText(resultMessage);
            successDialog.initStyle(StageStyle.UTILITY);

            try {
                appStyles.setDialogStyle(successDialog);
            } catch (IOException e) {
                e.printStackTrace();
            }

            successDialog.showAndWait();
        });
    }

    static void identifyPopupBox() throws IOException {
        List<String> userNamesList = new ArrayList<>();
        userNamesList.add("Igor");
        userNamesList.add("Vika");
        userNamesList.add("Oksanka");
        userNamesList.add("Natasha");

        List<String> authApiLoginIDsList = new ArrayList<>();
        authApiLoginIDsList.add("3y8Z2fk5Z3n");
        authApiLoginIDsList.add("3Ud359XbX6");
        authApiLoginIDsList.add("2L6UmL7rE");
        authApiLoginIDsList.add("3z42Rqc3pMrX");

        List<String> authTransactionKeysList = new ArrayList<>();
        authTransactionKeysList.add("2s25qyDYe249uTRx");
        authTransactionKeysList.add("67FK5537ng85nAPw");
        authTransactionKeysList.add("36Sc34JmhE9m493t");
        authTransactionKeysList.add("5gt38eVNu529t6ZP");

        ChoiceDialog<String> identifyDialog = new ChoiceDialog<>(GetPropertyValues.user, userNamesList);
        appStyles.setDialogLogo(identifyDialog, "hmm.png");
        identifyDialog.setTitle("Person identification");
        identifyDialog.setHeaderText("Please select who are you\nto identify Authorize.NET credentials");
        identifyDialog.setContentText("I'm ");
        identifyDialog.initStyle(StageStyle.UTILITY);

        appStyles.setDialogStyle(identifyDialog);

// Traditional way to get the response value.
        Optional<String> result = identifyDialog.showAndWait();
        if (result.isPresent()){
            int userIndex = userNamesList.indexOf(result.get());
            currentAuthApiLoginId = authApiLoginIDsList.get(userIndex);
            currentAuthTransactionKey = authTransactionKeysList.get(userIndex);
            currentUser = result.get();
            try {
                UpdateConfig.updateUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("Person select cancelled");
    }

    static void userTypePopupBox() throws IOException {
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

        ChoiceDialog<String> userTypesDialog = new ChoiceDialog<>("Merchant", choices);
        appStyles.setDialogLogo(userTypesDialog, "users_group.png");
        userTypesDialog.setTitle("User Type Selecting");
        userTypesDialog.setHeaderText("Please select type of user\nwhich you would like to create");
        userTypesDialog.initStyle(StageStyle.UTILITY);

        userTypesDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(newValue, "Merchant")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Merchant Admin")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Merchandiser")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Picker")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Packer")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Shipper")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "CSR")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "CSR Manager")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Purchase Manager")){
                System.out.println("--" + newValue);
            } else if(Objects.equals(newValue, "Warehouse Manager")){
                System.out.println("--" + newValue);
            }
        });

        appStyles.setDialogStyle(userTypesDialog);

// Traditional way to get the response value.
        Optional<String> result = userTypesDialog.showAndWait();
        if (result.isPresent()){
            userTypeToCreate = result.get();
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("User Type selecting cancelled");
    }

    public static void listBox(ArrayList jArray) {
        Platform.runLater(() -> {
            Alert responseBody = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(responseBody, "hi.png");
            responseBody.setTitle("About");
            responseBody.setHeaderText("Hi there! It's About of Secret App");
            responseBody.setContentText("This application is developed to make QA life easier " +
                    "while doing routine things...\n\n");
            responseBody.initStyle(StageStyle.UTILITY);

            Label label = new Label("Last release: '#1.75 beta' includes the following new features:");

            ListView<String> listView = new ListView<>();
            ObservableList<String> items = FXCollections.observableArrayList ();

            int i = 0;
            for(Object item: jArray){
                items.add(i++, item.toString());
                System.out.println("item: " + item);
            }

            listView.setItems(items);
            listView.setMaxWidth(Double.MAX_VALUE);
            listView.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(listView, Priority.ALWAYS);
            GridPane.setHgrow(listView, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(listView, 0, 1);

// Set expandable Exception into the dialog pane.
            responseBody.getDialogPane().setExpandableContent(expContent);

            try {
                appStyles.setDialogStyle(responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }

            responseBody.showAndWait();
        });
    }

    static void responseResultsPopupBox(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new javafx.scene.text.Font("Arial", 20));

        TableView table = new TableView();

        RequestsSender.getResponseBody();

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
    static void creditCardsPopupBox() throws IOException {
        List<String> cardTypesList = new ArrayList<>();
        cardTypesList.add("Visa");
        cardTypesList.add("Master Card");
        cardTypesList.add("Discover");
        cardTypesList.add("American Express");

        List<String> cardLogosList = new ArrayList<>();
        cardLogosList.add("visa.png");
        cardLogosList.add("mastercard.png");
        cardLogosList.add("discover.png");
        cardLogosList.add("American-Express.png");

        ChoiceDialog<String> creditCardsDialog = new ChoiceDialog<>("Visa", cardTypesList);
        appStyles.setDialogLogo(creditCardsDialog, "visa.png");
        creditCardsDialog.setTitle("Select Credit Card type");
        creditCardsDialog.setHeaderText("Choose preferred Card type:");
        creditCardsDialog.setContentText("");
        creditCardsDialog.initStyle(StageStyle.UTILITY);

        creditCardsDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                appStyles.setDialogLogo(creditCardsDialog, cardLogosList.get(cardTypesList.indexOf(newValue)));
        });

        appStyles.setDialogStyle(creditCardsDialog);

// Traditional way to get the response value.
        Optional<String> result = creditCardsDialog.showAndWait();

        if (result.isPresent()){
            if(Objects.equals(result.get(), "Visa")){
                Controller.cardNumber = BrowserSettings.visaTestCardNumber;
            } else if(Objects.equals(result.get(), "Master Card")){
                Controller.cardNumber = BrowserSettings.masterCardTestCardNumber;
            } else if(Objects.equals(result.get(), "Discover")){
                Controller.cardNumber = BrowserSettings.discoverTestCardNumber;
            } else if(Objects.equals(result.get(), "American Express")){
                Controller.cardNumber = BrowserSettings.americanExpressTestCardNumber;
            }
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("CC cancelled");
    }

    static void magentoPopupBox() throws IOException {
        List<String> choices = new ArrayList<>();
        Collections.addAll(choices, magentoAdminPanels);

        ChoiceDialog<String> magentoDialog = new ChoiceDialog<>("qatestlab01", choices);
        appStyles.setDialogLogo(magentoDialog, "magento-logo.png");
        magentoDialog.setTitle("Select Magento Environment");
        magentoDialog.setHeaderText("Select Magento which you\n" +
                "would like to sync with " + Controller.environmentComboBoxValue);
        magentoDialog.setContentText("I want to choose... ");
        magentoDialog.initStyle(StageStyle.UTILITY);

        appStyles.setDialogStyle(magentoDialog);

// Traditional way to get the response value.
        Optional<String> result = magentoDialog.showAndWait();
        if (result.isPresent()){
            Controller.magentoIndex = choices.indexOf(result.get());
            Controller.magentoIndexName = result.get();
            GeneratePopupBox.confirmationPopupBox();
        } else System.out.println("Select Magento cancelled");
    }

    static void confirmationPopupBox() throws IOException {
        String infoMessage = "";
        infoMessage += "Selected Browser: " + Controller.browserComboBoxValue + "\n";
        infoMessage += "Selected Test: " + Controller.entityComboBoxValue + "\n";
        infoMessage += "Selected Environment: " + Controller.environmentComboBoxValue + "\n\n";
        infoMessage += "Performing the test will take some time. Please wait!\n" +
                "Make a cup of tea or hug somebody. He (or she) will be happy :)\n\n";

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        appStyles.setDialogLogo(confirmationAlert, "smile2.png");
        confirmationAlert.setTitle("Lucky Confirmation Dialog");
        confirmationAlert.setHeaderText("Test is starting now.");
        confirmationAlert.setContentText(infoMessage);
        confirmationAlert.initStyle(StageStyle.UTILITY);

        appStyles.setDialogStyle(confirmationAlert);

        confirmationResponse = confirmationAlert.showAndWait();
    }

    static void configVariablesPopupBox() throws IOException {
        Alert configDialog = new Alert(Alert.AlertType.CONFIRMATION);
        appStyles.setDialogLogo(configDialog, "conf.png");

        configDialog.setTitle("Variables Configurations");
        configDialog.setHeaderText("Configure your system variables\n(hover over titles for more details)");
        configDialog.initStyle(StageStyle.UTILITY);

        Label timeoutsLabel = new Label("Preferred waiting timeout (sec.): ");
        timeoutsLabel.setTooltip(new Tooltip("This timeout will be used each time when test need\n" +
                "to wait loading the page or appearing of web element"));

        Label randomValueLabel = new Label("Random value for names contain digit(s): ");
        randomValueLabel.setTooltip(new Tooltip("This random value is added to the end of the name for each\n" +
                "creatable object (Product name, Customer name, etc.) "));

        Label appFilesPathLabel = new Label("Default path to 'appFiles' folder: ");
        appFilesPathLabel.setTooltip(new Tooltip("Please use the following format:\nC:/Program Files/appFiles"));

        int selectedTimeout = timeouts.indexOf(getTimeoutProperty());
        int selectedRandomLength = randomValue.indexOf(GetPropertyValues.randomValueProperty);

        ComboBox<String> timeoutsComboBox = new ComboBox<>();
        ComboBoxesHandler.comboBoxSetItems(timeoutsComboBox, timeouts, selectedTimeout);

        ComboBox<String> randomValueComboBox = new ComboBox<>();
        ComboBoxesHandler.comboBoxSetItems(randomValueComboBox, randomValue, selectedRandomLength);

        TextField appFilesPathField = new TextField();
        appFilesPathField.setText(System.getProperty("user.dir"));
        appFilesPathField.setTooltip(new Tooltip(System.getProperty("user.dir")));
        appFilesPathField.setEditable(false);

        GridPane configsGrid = new GridPane();
        configsGrid.vgapProperty().setValue(15);
        configsGrid.setMaxWidth(Double.MAX_VALUE);

        configsGrid.add(timeoutsLabel, 0, 0);
        configsGrid.add(timeoutsComboBox, 1, 0);

        configsGrid.add(randomValueLabel, 0, 1);
        configsGrid.add(randomValueComboBox, 1, 1);

        configsGrid.add(appFilesPathLabel, 0, 2);
        configsGrid.add(appFilesPathField, 1, 2);

        ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        configDialog.getButtonTypes().set(0, saveButton);

        configDialog.getDialogPane().setContent(configsGrid);
        try {
            appStyles.setDialogStyle(configDialog);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<ButtonType> result = configDialog.showAndWait();
        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            String oldPath = AppStyles.resourcesPath.replace("\\", "/");

            currentTimeout = timeoutsComboBox.getSelectionModel().getSelectedItem();
            BrowserSettings.timeoutVariable = Integer.valueOf(currentTimeout);

            currentRandomLength = randomValueComboBox.getSelectionModel().getSelectedItem();
            BrowserSettings.randomValueLength = Integer.valueOf(currentRandomLength);

            currentMainPath = appFilesPathField.getText();
            try {
                AppStyles.resourcesPath = currentMainPath;
                UpdateConfig.updateSystemVariables();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!Objects.equals(oldPath, currentMainPath)){
                GeneratePopupBox.relaunchPopupBox();
            }
        } else System.out.println("Configuration popup box cancelled");
    }

    static void configNamesPopupBox() {

        Dialog<ButtonType> configDialog = new Dialog<>();
        appStyles.setDialogLogo(configDialog, "conf.png");
        configDialog.setTitle("Names Configurations");
        configDialog.setHeaderText("Set default names for creatable objects");
        configDialog.setContentText("");
        configDialog.initStyle(StageStyle.UTILITY);

        Label warningLabel = new Label("Fill all fields before saving changes.");
        warningLabel.setStyle("-fx-text-fill: #FF3021;");
        warningLabel.setVisible(false);

        Label customerFirstNameLabel = new Label("Customer First Name format: ");
        customerFirstNameLabel.setCache(false);

        Label customerLastNameLabel = new Label("Customer Last Name format: ");
        customerLastNameLabel.setCache(false);

        Label productSKULabel = new Label("Product SKU format: ");
        productSKULabel.setCache(false);

        Label productNameLabel = new Label("Product Name format: ");
        productNameLabel.setCache(false);

        Label warehouseNameLabel = new Label("Warehouse Name format: ");
        warehouseNameLabel.setCache(false);

        Label supplierNameLabel = new Label("Supplier Name format: ");
        supplierNameLabel.setCache(false);

        Label binNameLabel = new Label("Bin Name format: ");
        binNameLabel.setCache(false);

        TextField customerFirstNameField = new TextField(GetPropertyValues.customerFirstName);
        TextField customerLastNameField = new TextField(GetPropertyValues.customerLastName);
        TextField productSKUField = new TextField(GetPropertyValues.productSKU);
        TextField productNameField = new TextField(GetPropertyValues.productName);
        TextField warehouseNameField = new TextField(GetPropertyValues.warehouseName);
        TextField supplierNameField = new TextField(GetPropertyValues.supplierName);
        TextField binNameField = new TextField(GetPropertyValues.binName);

        GridPane configNamesGrid = new GridPane();
        configNamesGrid.vgapProperty().setValue(15);
        configNamesGrid.setMaxWidth(Double.MIN_VALUE);

        configNamesGrid.add(warningLabel, 0, 0);

        configNamesGrid.add(customerFirstNameLabel, 0, 1);
        configNamesGrid.add(customerFirstNameField, 1, 1);

        configNamesGrid.add(customerLastNameLabel, 0, 2);
        configNamesGrid.add(customerLastNameField, 1, 2);

        configNamesGrid.add(productSKULabel, 0, 3);
        configNamesGrid.add(productSKUField, 1, 3);

        configNamesGrid.add(productNameLabel, 0, 4);
        configNamesGrid.add(productNameField, 1, 4);

        configNamesGrid.add(supplierNameLabel, 0, 5);
        configNamesGrid.add(supplierNameField, 1, 5);

        configNamesGrid.add(warehouseNameLabel, 0, 6);
        configNamesGrid.add(warehouseNameField, 1, 6);

        configNamesGrid.add(binNameLabel, 0, 7);
        configNamesGrid.add(binNameField, 1, 7);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        configDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        Node saveButton = configDialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setDisable(false);

        configDialog.getDialogPane().setContent(configNamesGrid);

        FieldsListener.multipleFieldsValidation(customerFirstNameField, customerFirstNameLabel, warningLabel, saveButton);
        FieldsListener.multipleFieldsValidation(customerLastNameField, customerLastNameLabel, warningLabel, saveButton);
        FieldsListener.multipleFieldsValidation(productSKUField, productSKULabel, warningLabel, saveButton);
        FieldsListener.multipleFieldsValidation(productNameField, productNameLabel, warningLabel, saveButton);
        FieldsListener.multipleFieldsValidation(warehouseNameField, warehouseNameLabel, warningLabel, saveButton);
        FieldsListener.multipleFieldsValidation(supplierNameField, supplierNameLabel, warningLabel, saveButton);
        FieldsListener.multipleFieldsValidation(binNameField, binNameLabel, warningLabel, saveButton);

        try {
            appStyles.setDialogStyle(configDialog);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<ButtonType> result = configDialog.showAndWait() ;

        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            currentCustomerFirstName = customerFirstNameField.getText();
            currentCustomerLastName = customerLastNameField.getText();
            currentProductSKU = productSKUField.getText();
            currentProductName = productNameField.getText();
            currentSupplierName = supplierNameField.getText();
            currentWarehouseName = warehouseNameField.getText();
            currentBinName = binNameField.getText();

            try {
                UpdateConfig.updateNames();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Configuration popup box cancelled");
    }

    static void aboutPopupBox() {
        Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
        appStyles.setDialogLogo(aboutDialog, "hi.png");
        aboutDialog.setTitle("About");
        aboutDialog.setHeaderText("Hi there! It's About of Secret App");
        aboutDialog.setContentText("This application is developed to make QA life easier " +
                    "while doing routine things...\n\n");
        aboutDialog.initStyle(StageStyle.UTILITY);

        String newChanges =
                " - Login/Password validation logic changed\n" +
                " - 'Cancel' buttons are colored in red on dialog boxes\n" +
                " - Add progress value to the main window(it was existing feature)\n" +
                " - FIXED BUG: validating fields on the 'Default Names' config dialog\n" +
                " - NEW BUG: progress value sometimes starts not from 0 \n" +
                " - Partial refactoring (may occur unexpected new bugs)\n\n" +
                "Updates from the latest releases you can find in the 'Release Notes' file";

        Label label = new Label("Last release: '#1.75 beta' includes the following new features:");

        TextArea textArea = new TextArea(newChanges);
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
        aboutDialog.getDialogPane().setExpandableContent(expContent);

        try {
            appStyles.setDialogStyle(aboutDialog);
        } catch (IOException e) {
            e.printStackTrace();
        }

        aboutDialog.showAndWait();
    }

    public static void warningPopupBox(String fileName) {
        Alert warningDialog = new Alert(Alert.AlertType.WARNING);
        warningDialog.setTitle("Warning");
        warningDialog.setHeaderText("Whoops... Application files not found.");
        warningDialog.setContentText(fileName + "\n\n" +
                "Make sure your 'appFiles' folder " +
                "is placed here: " + AppStyles.resourcesPath);
        warningDialog.initStyle(StageStyle.UTILITY);

        warningDialog.showAndWait();
    }

    private static void relaunchPopupBox() {
        Alert relaunchDialog = new Alert(Alert.AlertType.WARNING);
        relaunchDialog.setTitle("Warning");
        relaunchDialog.setHeaderText("I see that you have changed path to 'appFiles' directory.");
        relaunchDialog.setContentText("Your current path is: " + AppStyles.resourcesPath + "\n This app will be re-launched now to apply changes.");
        relaunchDialog.initStyle(StageStyle.UTILITY);

        confirmationResponse = relaunchDialog.showAndWait();

        if (confirmationResponse != null) {
            System.exit(0);
        }
    }
}
