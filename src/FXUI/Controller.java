package FXUI;

import Settings.BrowserSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class Controller extends Main {

//    private final ImageIcon luckyIcon = new ImageIcon("C:\\appFiles\\pic\\smile2.png");

    public WebDriver driver;

    private Exception exceptionValue;
    static boolean loginFilled;
    static boolean passFilled;
    static String resultMessage = "";
    public static int magentoIndex;
    public static String magentoIndexName = "";
    public static int addProgressValue = 0;
    private boolean exceptionStatus = false;
    private String testCardNumber = "";

    private TestStatus testStatus = new TestStatus();
    private BrowserSettings browserSettings = new BrowserSettings();
    private DropdownValueDeterminer dropdownValueDeterminer = new DropdownValueDeterminer();
    public ChangeLabelValue changeLabelValue = new ChangeLabelValue();

    private int browserComboBoxIndex;
    private int environmentComboBoxIndex;
    private int entityTypeComboBoxIndex;

    private ObservableList<String> browsers =
            FXCollections.observableArrayList(
                    "Google Chrome",
                    "Mozilla Firefox"
            );
    private ObservableList<String> entityTypes =
            FXCollections.observableArrayList(
                    "Configure Merchant",
                    "Create Customer",
                    "Create Product",
                    "Create Supplier",
                    "Create Warehouse & Bin",
                    "Sync Magento with FS",
                    "Reorder the last Order"
            );
    private ObservableList<String> environments =
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
    public ProgressBar progressBar;
    public Label waitingLabel;
    public Label buildVersion;
    public ImageView waitingAnimation;

    @FXML
    private void initialize(){
        browsersComboBox.setItems(browsers);
        browsersComboBox.getSelectionModel().select(0);

        entityTypeComboBox.setItems(entityTypes);
        entityTypeComboBox.getSelectionModel().select(0);

        environmentsComboBox.setItems(environments);
        environmentsComboBox.getSelectionModel().select(0);

        buildVersion.setText("Build Version: 1.45 beta");
    }

    public void clickStartButton() throws InterruptedException {
        String login = loginField.getText();
        String password = String.valueOf(passwordField.getCharacters());
        FieldsValidation.loginPassValidation(login, password, loginField, passwordField, loginLabel, passwordLabel);

        browserComboBoxIndex = browsersComboBox.getSelectionModel().getSelectedIndex();
        environmentComboBoxIndex = environmentsComboBox.getSelectionModel().getSelectedIndex();
        entityTypeComboBoxIndex = entityTypeComboBox.getSelectionModel().getSelectedIndex();

        if (loginFilled && passFilled){
            String infoMessage = "";
            infoMessage += "Selected Browser: " + browsersComboBox.getSelectionModel().getSelectedItem() + "\n";
            infoMessage += "Selected Test: " + entityTypeComboBox.getSelectionModel().getSelectedItem() + "\n";
            infoMessage += "Selected Environment: " + environmentsComboBox.getSelectionModel().getSelectedItem() + "\n\n";
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

            Optional<ButtonType> response = confirmationAlert.showAndWait();

            if (response.get() == ButtonType.OK) {
                final String[] driverWarning = {""};
                final String[] driverExceptionMessage = {""};

                Task<Void> task = new Task<Void>() {
                    @Override public Void call() throws InterruptedException {
                        updateMessage("Test is running... ");
                        return null;
                    }
                };

                waitingLabel.textProperty().bind(task.messageProperty());

                task.setOnSucceeded(e -> {
                    waitingLabel.textProperty().unbind();
                    // this message will be seen.
                    waitingLabel.setText("Test is running... " + String.valueOf(addProgressValue) + "%");
                });

                Thread thread = new Thread(task);
                thread.setDaemon(true);
                thread.start();

                testStatus.startTest(startButton, stopButton, waitingLabel, waitingAnimation);
                System.out.println("Test Started");

                ExecutionTimeCounter.startCounter();

                Runnable runnable = () -> {
                    try {
                        if (browserComboBoxIndex == 0) {
                            driverWarning[0] += "Chrome";
                            System.setProperty("webdriver.chrome.driver", "C:\\appFiles\\drivers\\chromedriver.exe");
                            driver = new ChromeDriver();

                        } else if (browserComboBoxIndex == 1) {
                            driverWarning[0] += "Firefox";
                            driver = new FirefoxDriver();
                        }
                    } catch (Exception e1) {
                        exceptionStatus = true;
                        testStatus.stopTest(startButton, stopButton, waitingLabel, waitingAnimation);
//                    if (!Objects.equals(e1.getClass().getSimpleName(), "SessionNotCreatedException")){
//                        driverExceptionMessage[0] += " session has been stopped unexpectedly.";
//                    } else if (!Objects.equals(e1.getClass().getSimpleName(), "IllegalStateException")){
//                        driverExceptionMessage[0] += " WebDriver was not found";
//                    } else {
//                        driverExceptionMessage[0] += " browser has been stopped unexpectedly.";
//                    }
//                        Alert exceptionAlert = new Alert(Alert.AlertType.INFORMATION);
//                        exceptionAlert.setTitle("Test Failed. Running time: " + ExecutionTimeCounter.executionTime);
//                        exceptionAlert.setHeaderText("Test was failed because of some unexpectedly reasons.");
//                        exceptionAlert.setContentText(driverWarning[0] + driverExceptionMessage[0]);
//                        try {
//                            File f = new File("C:/appFiles/exceptionDialog.css");
//                            DialogPane dialogPane = exceptionAlert.getDialogPane();
//                            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
//                            dialogPane.getStyleClass().add("myDialog");
//                        } catch (Exception e){
//                            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
//                        }
                    }
                    browserSettings.setUp(environmentComboBoxIndex, browserComboBoxIndex, driver);
                    try {
                        dropdownValueDeterminer.entityTypeDropdown(entityTypeComboBoxIndex, login, password, testCardNumber, driver);
                    } catch (Exception e1) {
                        exceptionValue = e1;
                        exceptionStatus = true;
                        if (!Objects.equals(e1.getClass().getSimpleName(), "NoSuchWindowException")) {
                            browserSettings.tearDown(driver);
                        }
// Show exception popup box
                    } finally {
                        ExecutionTimeCounter.stopCounter();
                        if (exceptionStatus) {
                            testStatus.stopTest(startButton, stopButton, waitingLabel, waitingAnimation);
                            GeneratePopupBox.exceptionPopupBox(exceptionValue);
                        }
                    }
                    if (!exceptionStatus) {
                        browserSettings.tearDown(driver);
                        testStatus.stopTest(startButton, stopButton, waitingLabel, waitingAnimation);
                        GeneratePopupBox.successPopupBox(resultMessage);
                    }
                };
                Thread thread1 = new Thread(runnable);
                thread1.start();
            }else if (response.get() == ButtonType.CANCEL || response.get() == ButtonType.CLOSE) {
                System.out.println("No/Close button");}
        }
    }

    public void clickStopButton() {
        testStatus.stopTest(startButton, stopButton, waitingLabel, waitingAnimation);

        try {
            driver.close();
        } catch (Exception e){
                System.out.println(e.getCause().toString());
        }
    }
}
