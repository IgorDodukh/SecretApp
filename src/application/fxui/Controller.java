package application.fxui;

import application.api.settings.EnvSettings;
import application.api.settings.JsonReader;
import application.api.settings.RequestsBuilder;
import application.api.test.post.AuthPOST;
import application.configs.GetPropertyValues;
import application.configs.ReadConfigMain;
import application.selenium.settings.BrowserSettings;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static application.api.settings.RequestsBuilder.guidList;
import static application.api.settings.RequestsBuilder.postIndex;
import static application.api.settings.RequestsBuilder.responseList;
import static application.configs.GetPropertyValues.loginProperty;
import static application.configs.GetPropertyValues.passProperty;
import static application.configs.UpdateConfig.updateCredentials;
import static application.fxui.ComboBoxesHandler.additionalDialogDeterminer;
import static application.fxui.DialogBoxGenerator.*;
import static application.fxui.ExecutionTimeCounter.startCounter;
import static application.fxui.ExecutionTimeCounter.stopCounter;
import static application.fxui.InternetConnection.getFailedContentText;

public class Controller extends Main {
    public static WebDriver driver;

    public static int magentoIndex;
    public static String magentoIndexName = "";
    static String cardNumber = "";
    static String browserComboBoxValue = "";
    static String entityComboBoxValue = "";
    public static String environmentComboBoxValue = "";
    public static String login;
    public static String password;
    public static int environmentComboBoxIndex;
    private static String resultMessage = "";
    private static int progressValue = 0;
    private static boolean exceptionStatus = false;
    private static String driverWarning = "";
    private static String driverExceptionMessage = "";
    public static int itemsCreatingQty = 0;


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
                    "Create Product"
//                    "Create Supplier",
//                    "Create Warehouse & Bin",
//                    "Reorder the last Order",
//                    "Create User"
            );

    private final ObservableList<String> apiResourcesList = FXCollections.observableArrayList(
            "Orders",
            "Customers",
            "Products",
            "Suppliers",
            "Warehouses",
            "Warehouse Bins",
            "Sales Channels",
            "Shipping Methods"
//            "Event Subscriptions",
//            "Promotions"
    );

    static final ObservableList<String> viewResourceUrl = FXCollections.observableArrayList(
            "/Order/OrderViewing/GUID",
            "/Customer/ViewCustomerView?customerId=%5B%22GUID%22%5D",
            "/Product/ProductView?productId=%5B%7B%22ProductId%22:%22GUID%22%7D%5D",
            "/Product/SupplierViewing?supplierId=GUID",
            "/Inventory/WarehouseEditing?pageMode=View&warehouseId=GUID",
            "/Inventory/WarehouseBinEditing?pageMode=View&warehouseBinId=GUID",
            "/Configuration/ViewSalesChannel?channelIds=%5B%22GUID%22%5D",
            "/Configuration/ShippingMethodView?shippingMethodIds=%5B%22GUID%22%5D"

    );

    //TODO add new resources: SalesChannels(GET), ShippingMethods(GET, POST), EventSubscriptions(GET), Promotions

    private final ObservableList<String> environmentsList =
            FXCollections.observableArrayList(
                    "QA01", "QA03", "QA05", "Production (for mad guys)"
            );

    private final ObservableList<String> itemsQtyList =
            FXCollections.observableArrayList(
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
    public ComboBox<String> itemsQtyComboBox;


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
    public Label responseStatusLabel;
    public Label notSupportedLabel;
    public Label apiEnvironment;
    public Label batchItemsLabel;

    public Label apiLimitsLabel;
    public Label apiLimitsQtyLabel;
    public Label apiLimitsPageLabel;

    public TextField limitsQtyField;
    public TextField limitsPageField;

    public Label itemsQtyLabel;

    public TextField loginField;
    public PasswordField passwordField;

    public Button startButton;
    public Button sendButton;
    public Button stopButton;
    //    public ProgressBar progressBar;

    public ImageView waitingAnimation;
    public ImageView apiWaitingAnimation;
    public ImageView companyLogo;

    public MenuItem menuConfigs;
    public MenuItem closeMenuButton;
    public MenuItem aboutButton;
    public MenuItem namesConfigs;

    public MenuBar myMenuBar;
    public ToggleButton apiSwitcher;

    public Pane windowHeader;
    public Pane windowMiddle;
    public Pane windowFooter;

    public Pane apiMiddle;
    public Pane apiFooter;
    public Pane apiBackground;

    public AnchorPane appBackground;

    private Exception exceptionValue;
    private boolean stopButtonClicked = false;
    private InternetConnection internetConnection = new InternetConnection();
    private EnvSettings envSettings = new EnvSettings();
    private AuthPOST authPOST = new AuthPOST();
    private JsonReader jsonReader = new JsonReader();
    private RequestsBuilder requestsBuilder = new RequestsBuilder();
    private int browserComboBoxIndex;
    private int dropdownIndex;

    public static String getSelectedResourceValue() {
        return selectedResourceValue;
    }

    private void setSelectedResourceValue(String selectedResourceValue) {
        Controller.selectedResourceValue = selectedResourceValue;
    }

    private static String selectedResourceValue;

    static int getSelectedResourceIndex() {
        return selectedResourceIndex;
    }

    private void setSelectedResourceIndex(int selectedResourceIndex) {
        Controller.selectedResourceIndex = selectedResourceIndex;
    }

    //TODO complete recognition the request type in analogy with resource type
    private static int selectedResourceIndex;

    static int getSelectedEnvironmentIndex() {
        return selectedEnvironmentIndex;
    }

    private static void setSelectedEnvironmentIndex(int selectedEnvironmentIndex) {
        Controller.selectedEnvironmentIndex = selectedEnvironmentIndex;
    }

    private static int selectedEnvironmentIndex;

    public static int getSelectedRequestTypeIndex() {
        return selectedRequestTypeIndex;
    }

    private static void setSelectedRequestTypeIndex(int selectedRequestTypeIndex) {
        Controller.selectedRequestTypeIndex = selectedRequestTypeIndex;
    }

    private static int selectedRequestTypeIndex;

    private String getResponseStatus() {
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

    static void setProgressValue(int addProgressValue) {
        Controller.progressValue = addProgressValue;
    }

    static String getStylesFolderName() {
        return stylesFolderName;
    }

    private static void setStylesFolderName(String stylesFolderName) {
        Controller.stylesFolderName = stylesFolderName;
    }

    private static String stylesFolderName;

    @FXML
    private void initialize() throws IOException, ParseException {
        Controller.setStylesFolderName("styles");
        AppStyles.stylesResourcePath = AppStyles.resourcesPath + Controller.getStylesFolderName() + File.separator;

        FieldsListener.multipleFieldsValidation(loginField, loginLabel, validationLabel, startButton);
        FieldsListener.multipleFieldsValidation(loginField, loginLabel, validationLabel, apiSwitcher);
        FieldsListener.multipleFieldsValidation(passwordField, passwordLabel, validationLabel, startButton);



        //TODO: implement installation by installer
        ComboBoxesHandler.comboBoxSetItems(browsersComboBox, browsersList, 0);
        ComboBoxesHandler.comboBoxSetItems(entityTypeComboBox, entityTypesList, 0);
        ComboBoxesHandler.comboBoxSetItems(apiEntityTypeComboBox, apiResourcesList, 0);
        ComboBoxesHandler.comboBoxSetItems(environmentsComboBox, environmentsList, 0);
        ComboBoxesHandler.comboBoxSetItems(requestsComboBox, requestTypesList, 0);


        ReadConfigMain.main();
        generateItemsQtyList(Integer.valueOf(GetPropertyValues.maxItemsQtyProperty));
        ComboBoxesHandler.comboBoxSetItems(itemsQtyComboBox, itemsQtyList, 0);
        currentMaxItemsQty = (itemsQtyList.get(itemsQtyList.size()-1));

        setApplicationStyle();

        companyLogo.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "fslogo.png"));
        waitingAnimation.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "spinner.gif"));
        apiWaitingAnimation.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "spinner.gif"));

        buildVersion.setText("Build Version: 2.1");


        loginField.setText(loginProperty);
        passwordField.setText(passProperty);

        if (Objects.equals(loginField.getText(), "") || Objects.equals(passwordField.getText(), ""))
            startButton.setDisable(true);

        closeMenuButton.setOnAction(t -> System.exit(0));
        aboutButton.setOnAction(t -> {
            try {
                aboutPopupBox();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        KeysListener.spinnerEnabler(responseStatusLabel, apiWaitingAnimation, sendButton);
        KeysListener.notSupportedResource(this, apiEntityTypeComboBox, requestsComboBox);

        KeysListener.fieldsDisabler(requestsComboBox, limitsPageField);
        KeysListener.fieldsDisabler(requestsComboBox, limitsQtyField);

//Add UI Elements listener for Start button
        KeysListener.startButtonKeyListener(browsersComboBox, this);
        KeysListener.startButtonKeyListener(entityTypeComboBox, this);
        KeysListener.startButtonKeyListener(environmentsComboBox, this);
        KeysListener.startButtonKeyListener(loginField, this);
        KeysListener.startButtonKeyListener(passwordField, this);
        KeysListener.startButtonKeyListener(startButton, this);
        KeysListener.startButtonKeyListener(apiSwitcher, this);

        KeysListener.sendButtonKeyListener(requestsComboBox, this);
        KeysListener.sendButtonKeyListener(apiEntityTypeComboBox, this);
        KeysListener.sendButtonKeyListener(sendButton, this);
        KeysListener.sendButtonKeyListener(apiSwitcher, this);
    }

    private void generateItemsQtyList(int maxItemsQty) {
        for (int i = 0; i < maxItemsQty; i++){
            itemsQtyList.add(i, String.valueOf(i + 1));
        }
    }

    private void setApplicationStyle() throws IOException {
        AppStyles.setComboBoxStyle(browsersComboBox);
        AppStyles.setComboBoxStyle(entityTypeComboBox);
        AppStyles.setComboBoxStyle(environmentsComboBox);
        AppStyles.setComboBoxStyle(apiEntityTypeComboBox);
        AppStyles.setComboBoxStyle(requestsComboBox);
        AppStyles.setComboBoxStyle(itemsQtyComboBox);

        AppStyles.setButtonsStyle(startButton);
        AppStyles.setButtonsStyle(stopButton);
        AppStyles.setButtonsStyle(sendButton);
        AppStyles.setMenuBarStyle(myMenuBar);
        AppStyles.setMainViewStyle(windowHeader);
        AppStyles.setMainViewStyle(windowMiddle);
        AppStyles.setMainViewStyle(windowFooter);
        AppStyles.setToggleButtonStyle(apiSwitcher);
        AppStyles.setMainBackgroundStyle(appBackground);
    }

    public void clickConfigsButton() throws IOException {
        ReadConfigMain.main();
        configVariablesPopupBox();
    }

    public void clickConfigNamesMenu() throws IOException {
        ReadConfigMain.main();
        configNamesPopupBox();
    }

    void notSupportedRequest(boolean value) {
        sendButton.setDisable(value);
        notSupportedLabel.setVisible(value);
    }

    public void clickSendButton() throws IOException {
        ReadConfigMain.main();

        setSelectedResourceValue(apiEntityTypeComboBox.getValue());
        System.out.println("Request type: " + requestsComboBox.getValue());
        System.out.println("Resource type: " + selectedResourceValue);
        Platform.runLater(() -> {
            startCounter();
            setResponseStatus("");
            setSelectedResourceIndex(apiEntityTypeComboBox.getSelectionModel().getSelectedIndex());
            setSelectedRequestTypeIndex(requestsComboBox.getSelectionModel().getSelectedIndex());


            try {
                String targetUrl = EnvSettings.getEnvironmentUrl() + getSelectedResourceValue().replace(" ", "");
                String limitValue = limitsQtyField.getText();
                String pageValue = limitsPageField.getText();
                String pageLimitFilter = "?limit=" + limitValue + "&page=" + pageValue;

                if(getSelectedRequestTypeIndex() == 0){
                    System.out.println("Send GET");
                    targetUrl = targetUrl + pageLimitFilter;
                    requestsBuilder.jerseyGET(targetUrl);
                } else if (getSelectedRequestTypeIndex() == 1){
                    System.out.println("Send POST");
                    responseList = new ArrayList<>();
                    guidList = new ArrayList<>();
                    postIndex = 0;

                    itemsCreatingQty = Integer.valueOf(itemsQtyComboBox.getSelectionModel().getSelectedItem());
                    for (int i=0; i < itemsCreatingQty; i++ ) {
                        System.out.println("--Creating item: " + (i+1));
                        jsonReader.updateJsonForPOST(selectedResourceValue);
                        requestsBuilder.jerseyPOST(targetUrl, JsonReader.getReceivedJsonString());
                    }



                    if(RequestsBuilder.responseStatusCode == 200 || RequestsBuilder.responseStatusCode == 201){
                        System.out.println("SUCCESS POPUP");
                    } else System.out.println("FAILED POPUP");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Sending request was failed.");
            }

            Task updateResponseTask = updateResponseStatus();

            responseStatusLabel.textProperty().bind(updateResponseTask.messageProperty());
            Thread t3 = new Thread(updateResponseTask);
            t3.setName("Response status updater");
            t3.setDaemon(true);
            t3.start();

            stopCounter();
        });
    }

    private void updateApiModeView(Boolean value) {
        setSelectedEnvironmentIndex(environmentsComboBox.getSelectionModel().getSelectedIndex());

        browserTypeLabel.setVisible(!value);
        entityTypeLabel.setVisible(!value);
        browsersComboBox.setVisible(!value);
        entityTypeComboBox.setVisible(!value);
        startButton.setVisible(!value);

        windowFooter.setVisible(!value);
        windowMiddle.setVisible(!value);

        environmentLabel.setVisible(!value);
        environmentsComboBox.setVisible(!value);
        loginLabel.setVisible(!value);
        loginField.setVisible(!value);
        passwordLabel.setVisible(!value);
        passwordField.setVisible(!value);

        apiBackground.setVisible(value);
        apiEnvironment.setVisible(value);

        environmentsComboBox.setDisable(value);
        responseStatusLabel.setVisible(value);
        requestsComboBox.setVisible(value);
        apiEntityTypeComboBox.setVisible(value);
        requestTypeLabel.setVisible(value);
        apiResourceLabel.setVisible(value);

        sendButton.setVisible(value);
        loginField.setDisable(value);
        passwordField.setDisable(value);

        apiFooter.setVisible(value);
        apiMiddle.setVisible(value);

        apiLimitsLabel.setVisible(value);
        apiLimitsQtyLabel.setVisible(value);
        apiLimitsPageLabel.setVisible(value);

        limitsQtyField.setVisible(value);
        limitsPageField.setVisible(value);

        itemsQtyLabel.setVisible(value);
        itemsQtyComboBox.setVisible(value);
    }

    public void clickApiSwitcher() throws IOException, ParseException {
        //TODO resolve issue with authorization
        ReadConfigMain.main();
        List<String> authKeysList = new ArrayList<>();
        authKeysList.add("username");
        authKeysList.add("password");

        List<String> credentialsList = new ArrayList<>();
        credentialsList.add(loginField.getText());
        credentialsList.add(passwordField.getText());

        if (apiSwitcher.isSelected()) {
            environmentComboBoxValue = environmentsComboBox.getSelectionModel().getSelectedItem();

            Task updateEnvironmentTask = updateEnvironmentStatus();
            apiEnvironment.textProperty().bind(updateEnvironmentTask.messageProperty());
            Thread t = new Thread(updateEnvironmentTask);
            t.setName("Response status updater");
            t.setDaemon(true);
            t.start();

//            apiEnvironment.textProperty().setValue(environmentComboBoxValue);
            login = loginField.getText();
            password = String.valueOf(passwordField.getCharacters());
            updateCredentials();

            updateApiModeView(true);
            companyLogo.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "fsapi.png"));
            Controller.setStylesFolderName("apiStyles");
            AppStyles.stylesResourcePath = AppStyles.resourcesPath + Controller.getStylesFolderName() + File.separator;
            System.out.println("AppStyles.stylesResourcePath: " + AppStyles.stylesResourcePath);
            setApplicationStyle();
            setResponseStatus("");
            environmentComboBoxIndex = environmentsComboBox.getSelectionModel().getSelectedIndex();
            JsonReader.changeJsonFields(envSettings.getAuthJsonPath(), authKeysList, credentialsList);
            envSettings.setupVariables();
            if (internetConnection.checkInternetConnection()) {

                Task updateResponseTask = updateResponseStatus();

                responseStatusLabel.textProperty().bind(updateResponseTask.messageProperty());
                Thread t3 = new Thread(updateResponseTask);
                t3.setName("Response status updater");
                t3.setDaemon(true);
                t3.start();


                authPOST.authorisationPOST();
                apiSwitcher.textFillProperty().setValue(Paint.valueOf("#FFA500"));


            } else {
                failedPopupBox(getFailedContentText() + "\napplication.api mode can't be used without Internet connection.");
                apiSwitcher.setSelected(false);
            }

        } else if (!apiSwitcher.isSelected()) {
            updateApiModeView(false);
            companyLogo.setImage(new Image("file:///" + AppStyles.picturesResourcePath + "fslogo.png"));
            Controller.setStylesFolderName("styles");
            AppStyles.stylesResourcePath = AppStyles.resourcesPath + Controller.getStylesFolderName() + File.separator;
            System.out.println("AppStyles.stylesResourcePath: " + AppStyles.stylesResourcePath);
            setApplicationStyle();
            notSupportedRequest(false);

            apiSwitcher.textFillProperty().setValue(Paint.valueOf("#fff"));
        }
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

    private Task updateEnvironmentStatus() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    updateMessage(String.valueOf(environmentComboBoxValue));
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
