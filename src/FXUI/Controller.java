package FXUI;

import API.Settings.EnvSettings;
import API.Settings.JsonReader;
import API.Tests.AuthResource.AuthPOST;
import API.Tests.ProductsResource.ProductsResource;
import Settings.BrowserSettings;
import Settings.ReadConfigMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static FXUI.ComboBoxesHandler.additionalDialogDeterminer;
import static FXUI.ExecutionTimeCounter.startCounter;
import static FXUI.ExecutionTimeCounter.stopCounter;
import static FXUI.GeneratePopupBox.*;
import static FXUI.InternetConnection.getFailedContentText;
import static Settings.GetPropertyValues.loginProperty;
import static Settings.GetPropertyValues.passProperty;
import static Settings.UpdateConfig.updateCredentials;

public class Controller extends Main {
    public static WebDriver driver;

    private String responseBody;
//
//    public Controller( String responseStatus, String responseBody ) {
////        progressLabel.setText(responseStatus);
////        this.progressLabel = progressLabel;
//        this.responseStatus = responseStatus;
//        this.responseBody = responseBody;
//        System.out.println();
//    }

    public void enableSpinner(boolean enable) {
        waitingAnimation.setVisible(enable);
    }

    public static int magentoIndex;
    public static String magentoIndexName = "";
    public static String cardNumber = "";
    public static String browserComboBoxValue = "";
    public static String entityComboBoxValue = "";
    public static String environmentComboBoxValue = "";
    public static String login;
    public static String password;
    public static int environmentComboBoxIndex;
    static boolean loginFilled = true;
    static boolean passFilled = true;
    private static String resultMessage = "";
    private static int progressValue = 0;
    private static boolean exceptionStatus = false;
    private static String driverWarning = "";
    private static String driverExceptionMessage = "";

    private final AppStatus appStatus = new AppStatus();
    private final BrowserSettings browserSettings = new BrowserSettings();
    private final ComboBoxesHandler comboBoxesHandler = new ComboBoxesHandler();

    private final ObservableList<String> browsersList =
            FXCollections.observableArrayList(
                    "Google Chrome",
                    "Mozilla Firefox"
            );
    private final ObservableList<String> entityTypesList =
            FXCollections.observableArrayList(
                    "Sync Magento with FS",
                    "Configure Merchant",
                    "Create Customer",
                    "Create Product",
                    "Create Supplier",
                    "Create Warehouse & Bin",
                    "Reorder the last Order",
                    "Create User"
            );

    public final ObservableList<String> apiResourcesList =
            FXCollections.observableArrayList(
                    "Orders",
                    "Customers",
                    "Products",
                    "Suppliers",
                    "Warehouses",
                    "Bins"
            );

    private final ObservableList<String> environmentsList =
            FXCollections.observableArrayList(
                    "QA01", "QA03", "QA05", "Production (for mad guys)"
            );
    private final ObservableList<String> requestTypesList =
            FXCollections.observableArrayList(
                    "GET", "POST"/*, "PUT", "DELETE"*/
            );

    public ComboBox<String> browsersComboBox;
    public ComboBox<String> entityTypeComboBox;
    public ComboBox<String> environmentsComboBox;
    public ComboBox<String> requestsComboBox;
    public ComboBox<String> apiEntityTypeComboBox;

    public Label loginLabel;
    public Label passwordLabel;
    public Label waitingLabel;
    public Label progressLabel;
    public Label validationLabel;
    public Label buildVersion;
    public Label requestTypeLabel;
    public Label browserTypeLabel;
    public Label entityTypeLabel;
    public Label environmentLabel;
    public Label apiResourceLabel;

    public TextField loginField;
    public PasswordField passwordField;

    public Button startButton;
    public Button sendButton;
    public Button stopButton;
    //    public ProgressBar progressBar;

    public ImageView waitingAnimation;
    public ImageView companyLogo;

    public MenuItem menuConfigs;
    public MenuItem closeMenuButton;
    public MenuItem aboutButton;
    public MenuItem namesConfigs;

    public MenuBar myMenuBar;
    public ToggleButton apiSwitcher;

    private Exception exceptionValue;
    private boolean stopButtonClicked = false;
    private InternetConnection internetConnection = new InternetConnection();
    private EnvSettings envSettings = new EnvSettings();
    private AuthPOST authPOST = new AuthPOST();
    private ProductsResource productsResource = new ProductsResource();
    private JsonReader jsonReader = new JsonReader();
    private int browserComboBoxIndex;
    private int dropdownIndex;

    public static int getSelectedResourceIndex() {
        return selectedResourceIndex;
    }

    public void setSelectedResourceIndex(int selectedResourceIndex) {
        Controller.selectedResourceIndex = selectedResourceIndex;
    }

    //TODO complete recognition the request type in analogy with resource type
    private static int selectedResourceIndex;

    public static int getSelectedRequestTypeIndex() {
        return selectedRequestTypeIndex;
    }

    public static void setSelectedRequestTypeIndex(int selectedRequestTypeIndex) {
        Controller.selectedRequestTypeIndex = selectedRequestTypeIndex;
    }

    private static int selectedRequestTypeIndex;

    public String getResponseStatus() {
        return responseStatus;
    }

    public static void setResponseStatus(String responseStatus) {
        Controller.responseStatus = responseStatus;
    }

    private static String responseStatus = "";


    static String getResultMessage() {
        return resultMessage;
    }

    static void setResultMessage(String resultMessage) {
        Controller.resultMessage = resultMessage;
    }

    static String getDriverWarning() {
        return driverWarning;
    }

    static void setDriverWarning(String driverWarning) {
        Controller.driverWarning = driverWarning;
    }

    private static boolean isExceptionStatus() {
        return exceptionStatus;
    }

    static void setExceptionStatus(boolean exceptionStatus) {
        Controller.exceptionStatus = exceptionStatus;
    }

    static String getDriverExceptionMessage() {
        return driverExceptionMessage;
    }

    static void setDriverExceptionMessage(String driverExceptionMessage) {
        Controller.driverExceptionMessage = driverExceptionMessage;
    }

    static int getProgressValue() {
        return progressValue;
    }

    public static void setProgressValue(int addProgressValue) {
        Controller.progressValue = addProgressValue;
    }

    @FXML
    private void initialize() throws IOException, ParseException {
        FieldsListener.multipleFieldsValidation(loginField, loginLabel, validationLabel, startButton);
        FieldsListener.multipleFieldsValidation(passwordField, passwordLabel, validationLabel, startButton);

        //TODO: implement installation by installer
        ComboBoxesHandler.comboBoxSetItems(browsersComboBox, browsersList, 0);
        ComboBoxesHandler.comboBoxSetItems(entityTypeComboBox, entityTypesList, 0);
        ComboBoxesHandler.comboBoxSetItems(apiEntityTypeComboBox, apiResourcesList, 0);
        ComboBoxesHandler.comboBoxSetItems(environmentsComboBox, environmentsList, 0);
        ComboBoxesHandler.comboBoxSetItems(requestsComboBox, requestTypesList, 0);

        AppStyles.setButtonsStyle(startButton);
        AppStyles.setButtonsStyle(stopButton);
        AppStyles.setButtonsStyle(sendButton);
        AppStyles.setMenuBarStyle(myMenuBar);
        AppStyles.setToggleButtonStyle(apiSwitcher);

        companyLogo.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "fslogo.png"));
        waitingAnimation.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "spinner.gif"));

        buildVersion.setText("Build Version: 2.0 beta");

        ReadConfigMain.main();
        loginField.setText(loginProperty);
        passwordField.setText(passProperty);

        if (Objects.equals(loginField.getText(), "") || Objects.equals(passwordField.getText(), ""))
            startButton.setDisable(true);

        closeMenuButton.setOnAction(t -> System.exit(0));
        aboutButton.setOnAction(t -> aboutPopupBox());

//Add UI Elements listener
        KeysListener.startButtonKeyListener(browsersComboBox, this);
        KeysListener.startButtonKeyListener(entityTypeComboBox, this);
        KeysListener.startButtonKeyListener(environmentsComboBox, this);
        KeysListener.startButtonKeyListener(loginField, this);
        KeysListener.startButtonKeyListener(passwordField, this);
        KeysListener.startButtonKeyListener(startButton, this);


    }

    public void clickConfigsButton() throws IOException {
        ReadConfigMain.main();
        configVariablesPopupBox();
    }

    public void clickConfigNamesMenu() throws IOException {
        ReadConfigMain.main();
        configNamesPopupBox();
    }

    public void clickSendButton() throws ParseException {
        setResponseStatus("");
        String requestValue = apiEntityTypeComboBox.getValue();
        System.out.println("Request type: " + requestsComboBox.getValue());
        System.out.println("Resource type: " + requestValue);
        Runnable runnableTest = () -> {
            setSelectedResourceIndex(apiEntityTypeComboBox.getSelectionModel().getSelectedIndex());
            try {
                if (getSelectedResourceIndex() == 2) {
                    System.out.println("sending GET products");
                    if()
                    productsResource.sendGet();
                } else System.out.println("New Request");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(runnableTest);
        thread1.start();
    }

    private void updateApiModeView(Boolean value) {
        browserTypeLabel.setVisible(!value);
        entityTypeLabel.setVisible(!value);
        browsersComboBox.setVisible(!value);
        entityTypeComboBox.setVisible(!value);
        startButton.setVisible(!value);

        progressLabel.setVisible(value);
        requestsComboBox.setVisible(value);
        apiEntityTypeComboBox.setVisible(value);
        requestTypeLabel.setVisible(value);
        apiResourceLabel.setVisible(value);
        sendButton.setVisible(value);
    }

//    public void requestProgressSpinner(boolean enable) {
//        this.waitingAnimation = waitingAnimation;
////        progressLabel.setVisible(enable);
////        progressLabel.setText(responseStatus);
////        GeneratePopupBox.successPopupBox(responseBody);
//        waitingAnimation.setVisible(enable);
//    }

    public void clickApiSwitcher() throws IOException, ParseException {
//        appStatus.requestWaitingAnimation(sendButton, waitingAnimation, true);
        setResponseStatus("");
        List<String> authKeysList = new ArrayList<>();
        authKeysList.add("username");
        authKeysList.add("password");

        List<String> credentialsList = new ArrayList<>();
        credentialsList.add(loginField.getText());
        credentialsList.add(passwordField.getText());

        if (apiSwitcher.isSelected()) {
            environmentComboBoxIndex = environmentsComboBox.getSelectionModel().getSelectedIndex();
            envSettings.setupVariables();
            if (internetConnection.checkInternetConnection()) {

//                appStatus.startTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);

                Task dynamicTimeTask = updateResponseStatus();

                progressLabel.textProperty().bind(dynamicTimeTask.messageProperty());
                Thread t3 = new Thread(dynamicTimeTask);
                t3.setName("Test Time Updater");
                t3.setDaemon(true);
                t3.start();

                authPOST.authorisationPOST();
                apiSwitcher.textFillProperty().setValue(Paint.valueOf("#FFA500"));
                updateApiModeView(true);
//                    appStatus.requestWaitingAnimation(sendButton, waitingAnimation, true);
                jsonReader.changeJsonFields(envSettings.getAuthJsonPath(), authKeysList, credentialsList);

            } else {
                failedPopupBox(getFailedContentText() + "\nAPI mode can't be used without Internet connection.");
                apiSwitcher.setSelected(false);
            }
        } else if (!apiSwitcher.isSelected()) {
            setResponseStatus("");
            updateApiModeView(false);
            apiSwitcher.textFillProperty().setValue(Paint.valueOf("#fff"));
        }

//        appStatus.requestWaitingAnimation(sendButton, waitingAnimation, false);

//        setEnvironmentComboBoxIndex(environmentsComboBox.getSelectionModel().getSelectedIndex());
//        appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
    }

    public synchronized void clickStartButton() throws IOException {
        boolean internetExist = internetConnection.checkInternetConnection();

        ReadConfigMain.main();
        stopButtonClicked = false;
        login = loginField.getText();
        password = String.valueOf(passwordField.getCharacters());

        browserComboBoxIndex = browsersComboBox.getSelectionModel().getSelectedIndex();
        environmentComboBoxIndex = environmentsComboBox.getSelectionModel().getSelectedIndex();
//        setEnvironmentComboBoxIndex(environmentsComboBox.getSelectionModel().getSelectedIndex());
        dropdownIndex = entityTypeComboBox.getSelectionModel().getSelectedIndex();

        startButton.setDisable(true);
        updateCredentials();

        browserComboBoxValue = browsersComboBox.getSelectionModel().getSelectedItem();
        entityComboBoxValue = entityTypeComboBox.getSelectionModel().getSelectedItem();
        environmentComboBoxValue = environmentsComboBox.getSelectionModel().getSelectedItem();

        additionalDialogDeterminer(dropdownIndex);

        if (confirmationResponse == null) {
            startButton.setDisable(false);
            System.out.println("Popup canceled");
        } else if (confirmationResponse.get() == ButtonType.OK && internetExist) {
            startCounter();

            Task dynamicTimeTask = updateProgressLabel();

            progressLabel.textProperty().bind(dynamicTimeTask.messageProperty());
            Thread t2 = new Thread(dynamicTimeTask);
            t2.setName("Test Time Updater");
            t2.setDaemon(true);
            t2.start();

            Runnable runnableTest = () -> {
                comboBoxesHandler.webDriverDeterminer(browserComboBoxIndex, stopButtonClicked);
                if (!isExceptionStatus()) {
                    appStatus.startTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                    System.out.println("Test Started");
                    browserSettings.setUp(environmentComboBoxIndex, browserComboBoxIndex, driver);
                    try {
                        comboBoxesHandler.testTypeDeterminer(dropdownIndex, login, password, cardNumber, driver);
                    } catch (Exception e1) {
                        appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                        exceptionValue = e1;
                        setExceptionStatus(true);
                        if (!Objects.equals(e1.getClass().getSimpleName(), "NoSuchWindowException"))
                            browserSettings.tearDown(driver);
// Show exception popup box
                    } finally {
                        stopCounter();
                        if (isExceptionStatus()) {
                            appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                            if (!stopButtonClicked) exceptionPopupBox(exceptionValue);
                        }
                    }
                    if (!isExceptionStatus()) {
                        browserSettings.tearDown(driver);
                        appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                        successPopupBox(getResultMessage());
                    }
                } else appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
            };
            Thread thread1 = new Thread(runnableTest);
            thread1.start();
//            Thread thread2 = new Thread(runnableProgress);
//            thread2.start();
        } else if (confirmationResponse.get() == ButtonType.OK && !internetExist) {
            failedPopupBox(getFailedContentText());
            appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
        } else if (confirmationResponse.get() == ButtonType.CANCEL || confirmationResponse.get() == ButtonType.CLOSE) {
            startButton.setDisable(false);
            System.out.println("No/Close button");
        }
        confirmationResponse = null;
        setDriverWarning("");
        setDriverExceptionMessage("");
        setExceptionStatus(false);
//        }
    }

    private Task updateProgressLabel() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    updateMessage(String.valueOf(getProgressValue()) + "%");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return null;
            }
        };
    }

    private Task updateResponseStatus() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    updateMessage(String.valueOf(getResponseStatus()));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return null;
            }
        };
    }

    public void clickStopButton() {
        appStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
        stopButtonClicked = true;
        Runnable runnable2 = () -> {
            try {
                driver.close();
                System.out.println("'Stop' button clicked");
            } catch (Exception e) {
                System.out.println("Exception caught");
            }
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }
}
