package FXUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class Controller extends Main {

//    private final ImageIcon luckyIcon = new ImageIcon("C:\\appFiles\\pic\\smile2.png");

    static boolean loginFilled;
    static boolean passFilled;
    static String resultMessage = "";
    public static int magentoIndex;
    public static String magentoIndexName = "";
    public static int addProgressValue = 0;

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

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("I have a great message for you!");

        if (loginFilled && passFilled){
            waitingLabel.setText("Test is running... 0%");
            String infoMessage = "";
            infoMessage += "Selected Browser: " + browsersComboBox.getSelectionModel().getSelectedItem() + "\n";
            infoMessage += "Selected Test: " + entityTypeComboBox.getSelectionModel().getSelectedItem() + "\n";
            infoMessage += "Selected Environment: " + environmentsComboBox.getSelectionModel().getSelectedItem() + "\n\n";
            infoMessage += "Performing the test will take some time. Please wait!\nMake a cup of tea or hug somebody. He (or she) will be happy :)\n\n";

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Lucky Confirmation Dialog");
            confirmationAlert.setHeaderText("Test is starting now.");
            confirmationAlert.setContentText(infoMessage);

            Optional<ButtonType> response = confirmationAlert.showAndWait();

            if (response.get() == ButtonType.OK) {
                System.out.println("Yes button");
            } else System.out.println("No/Close button");
        }
    }
}
