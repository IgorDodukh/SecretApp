package FXUI;

import Pages.InventoryPage;
import Settings.BrowserSettings;
import Tests.*;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
//import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static FXUI.DialogBoxGenerator.*;

/**
 * Created by Ihor on 7/16/2016. All rights reserved!
 */
public class ComboBoxesHandler{

    private final ConfigureMerchant configureMerchant = new ConfigureMerchant();
    private final AddNewCustomer addNewCustomer = new AddNewCustomer();
    private final AddProductWithInventory addProductWithInventory = new AddProductWithInventory();
    private final AddWarehouseAndBin addWarehouseAndBin = new AddWarehouseAndBin();
    private final AddSupplier addSupplier = new AddSupplier();
    private final MakeReorder makeReorder = new MakeReorder();
    private final ConfigureChannel configureChannel = new ConfigureChannel();
    private final AddUsers addUsers = new AddUsers();

    public void testTypeDeterminer(int dropdownIndex, String login, String password, String cardNumber, WebDriver driver)
            throws InterruptedException, NoSuchAlgorithmException {

        List<String> entityCreatedMessage = new ArrayList<>();
        entityCreatedMessage.add("New Customer has been created\n");

        Controller.setResultMessage(Controller.getResultMessage() + "Test has been finished.\n");
        if (dropdownIndex == 1) {
            BrowserSettings.progressVariable = 3;
            configureMerchant.setupNewMerchant(login, password, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "Merchant '" + login + "' has been configured");
        } else if (dropdownIndex == 2) {
            BrowserSettings.progressVariable = 4;
            addNewCustomer.addNewCustomer(login, password, cardNumber, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "New Customer has been created\n");
            Controller.setResultMessage(Controller.getResultMessage() + "\nCustomer name is:\n" + AddNewCustomer.createdFirstName + " " + AddNewCustomer.createdLastName);
        } else if (dropdownIndex == 3) {
            BrowserSettings.progressVariable = 2;
            addProductWithInventory.addProductWithInventory(login, password, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "New Product has been created\n");
            Controller.setResultMessage(Controller.getResultMessage() + "\nProduct SKU is: " + AddProductWithInventory.createdProductSKU);
            Controller.setResultMessage(Controller.getResultMessage() + "\nProduct Bin name is: " + InventoryPage.createdBin);
            Controller.setResultMessage(Controller.getResultMessage() + "\nProduct qty is: " + BrowserSettings.inventoryQty);
        } else if (dropdownIndex == 5) {
            BrowserSettings.progressVariable = 5;
            addWarehouseAndBin.addWarehouseAndBin(login, password, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "New Warehouse and Bin have been created\n");
            Controller.setResultMessage(Controller.getResultMessage() + "\nWarehouse name is: " + AddWarehouseAndBin.createdWarehouseName);
            Controller.setResultMessage(Controller.getResultMessage() + "\nBin name is: " + AddWarehouseAndBin.createdBinName);
        } else if (dropdownIndex == 4) {
            BrowserSettings.progressVariable = 4;
            addSupplier.addSupplier(login, password, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "New Supplier has been created\n");
            Controller.setResultMessage(Controller.getResultMessage() + "\nSupplier name is: " + AddSupplier.createdSupplierName);
        } else if (dropdownIndex == 6) {
            BrowserSettings.progressVariable = 5;
            makeReorder.makeReorder(login, password, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "New Order has been created\n");
            Controller.setResultMessage(Controller.getResultMessage() + "\nOrder Number is: " + BrowserSettings.orderNumber);
        } else if (dropdownIndex == 0) {
            BrowserSettings.progressVariable = 5;
            configureChannel.configureMagentoChannel(login, password, driver);
            Controller.setResultMessage(Controller.getResultMessage() + "\nMagento "+ Controller.magentoIndexName);
            Controller.setResultMessage(Controller.getResultMessage() + " has been synced with " + Controller.environmentComboBoxValue);
        } else if (dropdownIndex == 7) {
            BrowserSettings.progressVariable = 4;
            if(Objects.equals(DialogBoxGenerator.userTypeToCreate, "Merchant")) {
                System.out.println(DialogBoxGenerator.userTypeToCreate);
                addUsers.addMerchant(login, password, driver);
            } else if(Objects.equals(DialogBoxGenerator.userTypeToCreate, "Merchant Admin")) {
                System.out.println(DialogBoxGenerator.userTypeToCreate);
                addUsers.addMerchantAdmin(login, password, driver);
            } else if(Objects.equals(DialogBoxGenerator.userTypeToCreate, "Merchandiser")) {
                System.out.println(DialogBoxGenerator.userTypeToCreate);
                addUsers.addMerchantAdmin(login, password, driver);
            } else if(Objects.equals(DialogBoxGenerator.userTypeToCreate, "Picker")) {
                System.out.println(DialogBoxGenerator.userTypeToCreate);
                addUsers.addMerchantAdmin(login, password, driver);
            } else if(Objects.equals(DialogBoxGenerator.userTypeToCreate, "Packer")) {
                System.out.println(DialogBoxGenerator.userTypeToCreate);
                addUsers.addMerchantAdmin(login, password, driver);
            } else throw new NoSuchAlgorithmException("No such test exception");
            Controller.setResultMessage(Controller.getResultMessage() + "New " + DialogBoxGenerator.userTypeToCreate + " has been created");
        }
    }

    public static void additionalDialogDeterminer(int index) throws IOException {
        if (index == 1) identifyPopupBox();
        else if (index == 0) magentoPopupBox();
        else if (index == 2) creditCardsPopupBox();
        else if (index == 7) userTypePopupBox();
        else confirmationPopupBox();
    }

    public static void comboBoxSetItems(ComboBox<String> comboBox, ObservableList<String> values, int selectedValue) throws IOException {
        comboBox.setItems(values);
        comboBox.getSelectionModel().select(selectedValue);
        AppStyles.setComboBoxStyle(comboBox);
    }

    public void webDriverDeterminer(int browserComboBoxIndex, boolean stopButtonClicked) {

        try {
            if (browserComboBoxIndex == 0) {
                Controller.setDriverWarning(Controller.getDriverWarning() + "Chrome");
                System.setProperty("webdriver.chrome.driver", AppStyles.driversResourcePath + "chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                Controller.driver = new ChromeDriver(options);
            } else if (browserComboBoxIndex == 1) {
                Controller.setDriverWarning(Controller.getDriverWarning() + "Firefox");
                Controller.driver = new FirefoxDriver();
            }
        } catch (Exception e1) {
            Controller.setExceptionStatus(true);
            Controller.setDriverExceptionMessage(Controller.getDriverExceptionMessage() + " WebDriver was not found or has been stopped unexpectedly");
            if(!stopButtonClicked)
                failedPopupBox(Controller.getDriverWarning() + Controller.getDriverExceptionMessage());
        }
    }
}
