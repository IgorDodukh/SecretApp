package FXUI;

import Settings.BrowserSettings;
import Settings.ReadConfigMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
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

    private Exception exceptionValue;
    static boolean loginFilled = true;
    static boolean passFilled = true;

    private static String resultMessage = "";
    public static int magentoIndex;
    public static String magentoIndexName = "";
    private static int progressValue = 0;
    private static boolean exceptionStatus = false;
    private boolean stopButtonClicked = false;

    public static String cardNumber = "";

    public static String browserComboBoxValue = "";
    public static String entityComboBoxValue = "";
    public static String environmentComboBoxValue = "";

    private final TestStatus testStatus = new TestStatus();
    private final BrowserSettings browserSettings = new BrowserSettings();
    private final ComboBoxesHandler comboBoxesHandler = new ComboBoxesHandler();
    private InternetConnection internetConnection = new InternetConnection();

    private static String driverWarning = "";
    private static String driverExceptionMessage = "";

    public static String login;
    public static String password;

    private int browserComboBoxIndex;
    private int environmentComboBoxIndex;
    private int dropdownIndex;

    private final ObservableList<String> browsers =
            FXCollections.observableArrayList(
                    "Google Chrome",
                    "Mozilla Firefox"
            );
    private final ObservableList<String> entityTypes =
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
    private final ObservableList<String> environments =
            FXCollections.observableArrayList(
                    "QA01", "QA03", "QA05", "Production (for mad guys)"
            );

    public ComboBox<String> browsersComboBox;
    public ComboBox<String> entityTypeComboBox;
    public ComboBox<String> environmentsComboBox;
    public Label loginLabel;
    public Label passwordLabel;
    public TextField loginField;
    public PasswordField passwordField;
    public Button startButton;
    public Button stopButton;
//    public ProgressBar progressBar;
    public Label waitingLabel;
    public Label progressLabel;
    public Label validationLabel;
    public Label buildVersion;
    public ImageView waitingAnimation;
    public ImageView companyLogo;
    public MenuItem menuConfigs;
    public MenuItem closeMenuButton;
    public MenuBar myMenuBar;
    public MenuItem aboutButton;
    public MenuItem namesConfigs;

    public static String getResultMessage() {
        return resultMessage;
    }

    public static void setResultMessage(String resultMessage) {
        Controller.resultMessage = resultMessage;
    }

    public static String getDriverWarning() {
        return driverWarning;
    }

    public static void setDriverWarning(String driverWarning) {
        Controller.driverWarning = driverWarning;
    }

    public static boolean isExceptionStatus() {
        return exceptionStatus;
    }

    public static void setExceptionStatus(boolean exceptionStatus) {
        Controller.exceptionStatus = exceptionStatus;
    }

    public static String getDriverExceptionMessage() {
        return driverExceptionMessage;
    }

    public static void setDriverExceptionMessage(String driverExceptionMessage) {
        Controller.driverExceptionMessage = driverExceptionMessage;
    }

    public static int getProgressValue() {
        return progressValue;
    }

    public static void setProgressValue(int addProgressValue) {
        Controller.progressValue = addProgressValue;
    }

    @FXML
    private void initialize() throws IOException {
        FieldsListener.multipleFieldsValidation(loginField, loginLabel, validationLabel, startButton);
        FieldsListener.multipleFieldsValidation(passwordField, passwordLabel, validationLabel, startButton);

        //TODO: implement installation by installer
        ComboBoxesHandler.comboBoxSetItems(browsersComboBox, browsers, 0);
        ComboBoxesHandler.comboBoxSetItems(entityTypeComboBox, entityTypes, 0);
        ComboBoxesHandler.comboBoxSetItems(environmentsComboBox, environments, 0);

        AppStyles.setButtonsStyle(startButton);
        AppStyles.setButtonsStyle(stopButton);
        AppStyles.setMenuBarStyle(myMenuBar);

        companyLogo.setImage(new Image("file:///" + AppStyles.mainPath.replace("\\", "/") + "/pic/fslogo.png"));
        waitingAnimation.setImage(new Image("file:///" + AppStyles.mainPath.replace("\\", "/") + "/pic/spinner.gif"));

        buildVersion.setText("Build Version: 1.75 beta");

        ReadConfigMain.main();
        loginField.setText(loginProperty);
        passwordField.setText(passProperty);

        if(Objects.equals(loginField.getText(), "") || Objects.equals(passwordField.getText(), ""))
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

    public synchronized void clickStartButton() throws IOException {
        boolean internetExist = internetConnection.checkInternetConnection();

        ReadConfigMain.main();
        stopButtonClicked = false;
        login = loginField.getText();
        password = String.valueOf(passwordField.getCharacters());

        browserComboBoxIndex = browsersComboBox.getSelectionModel().getSelectedIndex();
        environmentComboBoxIndex = environmentsComboBox.getSelectionModel().getSelectedIndex();
        dropdownIndex = entityTypeComboBox.getSelectionModel().getSelectedIndex();

        startButton.setDisable(true);
        updateCredentials();

        browserComboBoxValue = browsersComboBox.getSelectionModel().getSelectedItem();
        entityComboBoxValue = entityTypeComboBox.getSelectionModel().getSelectedItem();
        environmentComboBoxValue = environmentsComboBox.getSelectionModel().getSelectedItem();

        additionalDialogDeterminer(dropdownIndex);

        if(confirmationResponse == null) {
            startButton.setDisable(false);
            System.out.println("Popup canceled");}
        else if (confirmationResponse.get() == ButtonType.OK && internetExist) {
            startCounter();

            Task dynamicTimeTask = updateProgressLabel();

            progressLabel.textProperty().bind(dynamicTimeTask.messageProperty());
            Thread t2 = new Thread(dynamicTimeTask);
            t2.setName("Test Time Updater");
            t2.setDaemon(true);
            t2.start();

            Runnable runnableTest = () -> {
                comboBoxesHandler.webDriverDeterminer(browserComboBoxIndex, stopButtonClicked);
                if(!isExceptionStatus()) {
                    testStatus.startTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                    System.out.println("Test Started");
                    browserSettings.setUp(environmentComboBoxIndex, browserComboBoxIndex, driver);
                    try {
                        comboBoxesHandler.testTypeDeterminer(dropdownIndex, login, password, cardNumber, driver);
                    } catch (Exception e1) {
                        testStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                        exceptionValue = e1;
                        setExceptionStatus(true);
                        if (!Objects.equals(e1.getClass().getSimpleName(), "NoSuchWindowException"))
                            browserSettings.tearDown(driver);
// Show exception popup box
                    } finally {
                        stopCounter();
                        if (isExceptionStatus()) {
                            testStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                            if (!stopButtonClicked) exceptionPopupBox(exceptionValue);
                        }
                    }
                    if (!isExceptionStatus()) {
                        browserSettings.tearDown(driver);
                        testStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
                        successPopupBox(getResultMessage());
                    }
                } else testStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
            };
            Thread thread1 = new Thread(runnableTest);
            thread1.start();
//            Thread thread2 = new Thread(runnableProgress);
//            thread2.start();

            } else if(confirmationResponse.get() == ButtonType.OK && !internetExist)
                failedPopupBox(getFailedContentText());
            else if (confirmationResponse.get() == ButtonType.CANCEL || confirmationResponse.get() == ButtonType.CLOSE) {
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

    public void clickStopButton() {
        testStatus.stopTest(startButton, stopButton, waitingLabel, progressLabel, waitingAnimation);
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
