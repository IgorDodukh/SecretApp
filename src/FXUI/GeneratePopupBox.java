package FXUI;

import Settings.BrowserSettings;
import Settings.GetPropertyValues;
import Settings.UpdateConfig;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by Ihor on 7/15/2016. All rights reserved!
 */
public class GeneratePopupBox {

    private static final AppStyles appStyles = new AppStyles();

    public static Optional<ButtonType> exceptionResponse;
    public static Optional<ButtonType> confirmationResponse;
    private static final String[] magentos = {
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

    public static void exceptionPopupBox(Exception exception) {
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

            appStyles.setDialogStyle(exceptionDialog);

            exceptionResponse = exceptionDialog.showAndWait();
        });
    }

    public static void failedPopupBox() {
        Platform.runLater(() -> {
            Alert failedDialog = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(failedDialog, "sad.png");
            failedDialog.setTitle("Test Failed. Running time: " + ExecutionTimeCounter.executionTime);
            failedDialog.setHeaderText("Test was failed because of some unexpectedly reasons.");
            failedDialog.setContentText(Controller.driverWarning[0] + Controller.driverExceptionMessage[0]);
            failedDialog.initStyle(StageStyle.UTILITY);

            appStyles.setDialogStyle(failedDialog);

            exceptionResponse = failedDialog.showAndWait();
        });
    }

    public static void failedConnectionPopupBox() {
        Platform.runLater(() -> {
            Alert failedDialog = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(failedDialog, "sad.png");
            failedDialog.setTitle("Connection warning");
            failedDialog.setHeaderText("Test was not starter.");
            failedDialog.setContentText("Please check your internet connection.");
            failedDialog.initStyle(StageStyle.UTILITY);

            appStyles.setDialogStyle(failedDialog);

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

            appStyles.setDialogStyle(successDialog);

            successDialog.showAndWait();
        });
    }

    public static void identifyPopupBox() {
        List<String> choices = new ArrayList<>();
        choices.add("Igor");
        choices.add("Vika");
        choices.add("Oksanka");
        choices.add("Natasha");

        ChoiceDialog<String> identifyDialog = new ChoiceDialog<>(GetPropertyValues.user, choices);
        appStyles.setDialogLogo(identifyDialog, "hmm.png");
        identifyDialog.setTitle("Person identification");
        identifyDialog.setHeaderText("Please select who are you\nto identify Authorize.NET credentials");
        identifyDialog.setContentText("I'm ");
        identifyDialog.initStyle(StageStyle.UTILITY);

        appStyles.setDialogStyle(identifyDialog);

// Traditional way to get the response value.
        Optional<String> result = identifyDialog.showAndWait();
        if (result.isPresent()){
            if(Objects.equals(result.get(), "Igor")){
                currentAuthApiLoginId = "3y8Z2fk5Z3n";
                currentAuthTransactionKey = "2s25qyDYe249uTRx";
            } else if(Objects.equals(result.get(), "Vika")){
                currentAuthApiLoginId = "3Ud359XbX6";
                currentAuthTransactionKey = "67FK5537ng85nAPw";
            } else if(Objects.equals(result.get(), "Oksanka")){
                currentAuthApiLoginId = "2L6UmL7rE";
                currentAuthTransactionKey = "36Sc34JmhE9m493t";
            } else if(Objects.equals(result.get(), "Natasha")){
                currentAuthApiLoginId = "3z42Rqc3pMrX";
                currentAuthTransactionKey = "5gt38eVNu529t6ZP";
            }
            currentUser = result.get();
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

        ChoiceDialog<String> userTypesDialog = new ChoiceDialog<>("Merchant", choices);
        appStyles.setDialogLogo(userTypesDialog, "users_group.png");
        userTypesDialog.setTitle("User Type Selecting");
        userTypesDialog.setHeaderText("Please select type of user\nwhich you would like to create");
        userTypesDialog.initStyle(StageStyle.UTILITY);

        userTypesDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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

        appStyles.setDialogStyle(userTypesDialog);

// Traditional way to get the response value.
        Optional<String> result = userTypesDialog.showAndWait();
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
        appStyles.setDialogLogo(creditCardsDialog, "visa.png");
        creditCardsDialog.setTitle("Select Credit Card type");
        creditCardsDialog.setHeaderText("Choose preferred Card type:");
        creditCardsDialog.setContentText("");
        creditCardsDialog.initStyle(StageStyle.UTILITY);

        creditCardsDialog.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(Objects.equals(newValue, "Visa")){
                    appStyles.setDialogLogo(creditCardsDialog, "visa.png");
                } else if(Objects.equals(newValue, "Master Card")){
                    appStyles.setDialogLogo(creditCardsDialog, "mastercard.png");
                } else if(Objects.equals(newValue, "Discover")){
                    appStyles.setDialogLogo(creditCardsDialog, "discover.png");
                } else if(Objects.equals(newValue, "American Express")){
                    appStyles.setDialogLogo(creditCardsDialog, "American-Express.png");
                }
        });

        appStyles.setDialogStyle(creditCardsDialog);

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
        appStyles.setDialogLogo(magentoDialog, "magento-logo.png");
        magentoDialog.setTitle("Select Magento Environment");
        magentoDialog.setHeaderText("Select Magento which you\n" +
                "would like to sync with " + Controller.environmentComboBoxValue);
        magentoDialog.setContentText("I want to choose ");
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

    public static void confirmationPopupBox () {
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

    public static void configVariablesPopupBox() {
        Platform.runLater(() -> {
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

            int selectedTimeout = timeouts.indexOf(GetPropertyValues.timeoutProperty);
            int selectedRandomLength = randomValue.indexOf(GetPropertyValues.randomValueProperty);

            ComboBox<String> timeoutsComboBox = new ComboBox<>();
            timeoutsComboBox.setItems(timeouts);
            timeoutsComboBox.getSelectionModel().select(selectedTimeout);

            ComboBox<String> randomValueComboBox = new ComboBox<>();
            randomValueComboBox.setItems(randomValue);
            randomValueComboBox.getSelectionModel().select(selectedRandomLength);

            TextField appFilesPathField = new TextField();
            appFilesPathField.setText(AppStyles.mainPath.replace("\\","/"));
            appFilesPathField.setDisable(true);

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
            appStyles.setDialogStyle(configDialog);

// Traditional way to get the response value.
            Optional<ButtonType> result = configDialog.showAndWait();
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                String oldPath = AppStyles.mainPath.replace("\\", "/");

                currentTimeout = timeoutsComboBox.getSelectionModel().getSelectedItem();
                BrowserSettings.timeoutVariable = Integer.valueOf(currentTimeout);

                currentRandomLength = randomValueComboBox.getSelectionModel().getSelectedItem();
                BrowserSettings.randomValueLength = Integer.valueOf(currentRandomLength);

                currentMainPath = appFilesPathField.getText();

                System.out.println("Old path: " + oldPath);
                System.out.println("New path: " + currentMainPath);

                try {
                    AppStyles.mainPath = currentMainPath;
                    UpdateConfig.updateSystemVariables();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!Objects.equals(oldPath, currentMainPath)){
                    GeneratePopupBox.relaunchPopupBox();
                }
            } else System.out.println("Configuration popup box cancelled");
        });
    }

    public static void configNamesPopupBox() {
        Platform.runLater(() -> {
            Dialog configDialog = new Dialog();
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

            ButtonType loginButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            configDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            Node saveButton = configDialog.getDialogPane().lookupButton(loginButtonType);
            saveButton.setDisable(false);

            configDialog.getDialogPane().setContent(configNamesGrid);

            FieldsListener.namesSettingsFieldListener(customerFirstNameField, customerFirstNameLabel, warningLabel, saveButton);
            FieldsListener.namesSettingsFieldListener(customerLastNameField, customerLastNameLabel, warningLabel, saveButton);
            FieldsListener.namesSettingsFieldListener(productSKUField, productSKULabel, warningLabel, saveButton);
            FieldsListener.namesSettingsFieldListener(productNameField, productNameLabel, warningLabel, saveButton);
            FieldsListener.namesSettingsFieldListener(warehouseNameField, warehouseNameLabel, warningLabel, saveButton);
            FieldsListener.namesSettingsFieldListener(supplierNameField, supplierNameLabel, warningLabel, saveButton);
            FieldsListener.namesSettingsFieldListener(binNameField, binNameLabel, warningLabel, saveButton);

            appStyles.setDialogStyle(configDialog);


// Traditional way to get the response value.
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
        });
    }

    public static void aboutPopupBox() {
        Platform.runLater(() -> {
            Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
            appStyles.setDialogLogo(aboutDialog, "hi.png");
            aboutDialog.setTitle("About");
            aboutDialog.setHeaderText("Hi there! It's About of Secret App");
            aboutDialog.setContentText("This application is developed to make QA life easier " +
                    "while doing routine things...\n\n");
            aboutDialog.initStyle(StageStyle.UTILITY);

            // Create expandable Exception.

            String newChanges =
                    " - Configuration changes:\n" +
                    "\t - 'Default Names' config (allow to change default names for creatable 0objects)\n" +
                    "\t - 'System Variables' config allow to change:\n" +
                    "\t\t- default timeout value \n" +
                    "\t\t- number of digits for random number which is added to the end of the created object's name\n" +
                    "\t\t- default path to 'appFiles' folder" +
                    " - Add warning Popup Box in case when 'appFiles' folder doesn't exist by specified path\n" +
                    " - Add tooltip for titles on the 'Variables Configuration' popup box\n" +
                    " - Change 'OK' button name to 'Save' on the configuration dialogs\n" +
                    " - Partial refactoring (may occur unexpected bugs)\n";

            Label label = new Label("Last release: '#1.72 beta' includes the following new features:");

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

            appStyles.setDialogStyle(aboutDialog);

            aboutDialog.showAndWait();
        });
    }

    public static void warningPopupBox(String fileName) {
        Alert warningDialog = new Alert(Alert.AlertType.WARNING);
        warningDialog.setTitle("Warning");
        warningDialog.setHeaderText("Whoops... Application files not found.");
        warningDialog.setContentText(fileName + "\n\n" +
                "Make sure your 'appFiles' folder " +
                "is placed here: " + AppStyles.mainPath);
        warningDialog.initStyle(StageStyle.UTILITY);

        warningDialog.showAndWait();
    }

    public static void relaunchPopupBox() {
        Alert relaunchDialog = new Alert(Alert.AlertType.WARNING);
        relaunchDialog.setTitle("Warning");
        relaunchDialog.setHeaderText("I see that you have changed path to 'appFiles' directory.");
        relaunchDialog.setContentText("Your current path is: " + AppStyles.mainPath + "\n This app will be re-launched now to apply changes.");
        relaunchDialog.initStyle(StageStyle.UTILITY);

        confirmationResponse = relaunchDialog.showAndWait();

        if (confirmationResponse != null) {
            System.exit(0);
        }
    }
}
