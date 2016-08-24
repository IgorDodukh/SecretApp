package FXUI;

import Pages.InventoryPage;
import Settings.BrowserSettings;
import Tests.*;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

/**
 * Created by Ihor on 7/16/2016. All rights reserved!
 */
public class DropdownValueDeterminer {
    private final ConfigureMerchant configureMerchant = new ConfigureMerchant();
    private final AddNewCustomer addNewCustomer = new AddNewCustomer();
    private final AddProductWithInventory addProductWithInventory = new AddProductWithInventory();
    private final AddWarehouseAndBin addWarehouseAndBin = new AddWarehouseAndBin();
    private final AddSupplier addSupplier = new AddSupplier();
    private final MakeReorder makeReorder = new MakeReorder();
    private final ConfigureChannel configureChannel = new ConfigureChannel();
    private final AddUsers addUsers = new AddUsers();


    public void entityTypeDropdown(int entityTypeComboBoxIndex, String login, String password, String testCardNumber, WebDriver driver) throws InterruptedException {
        Controller.resultMessage += "Test has been finished.\n";
        if (entityTypeComboBoxIndex == 1) {
            BrowserSettings.progressVariable = 3;
            configureMerchant.setupNewMerchant(login, password, driver);
            Controller.resultMessage += "Merchant '" + login + "' has been configured";
        } else if (entityTypeComboBoxIndex == 2) {
            BrowserSettings.progressVariable = 4;
            addNewCustomer.jira3675(login, password, testCardNumber, driver);
            Controller.resultMessage += "New Customer has been created\n";
            Controller.resultMessage += "\nCustomer name is:\n" + AddNewCustomer.createdFirstName + " " + AddNewCustomer.createdLastName;

        } else if (entityTypeComboBoxIndex == 3) {
            BrowserSettings.progressVariable = 2;
            addProductWithInventory.jira3015(login, password, driver);
            Controller.resultMessage += "New Product has been created\n";
            Controller.resultMessage += "\nProduct SKU is: " + AddProductWithInventory.createdProductSKU;
            Controller.resultMessage += "\nProduct Bin name is: " + InventoryPage.createdBin;
            Controller.resultMessage += "\nProduct qty is: " + BrowserSettings.inventoryQty;
        } else if (entityTypeComboBoxIndex == 5) {
            BrowserSettings.progressVariable = 5;
            addWarehouseAndBin.jira3006(login, password, driver);
            Controller.resultMessage += "New Warehouse and Bin have been created\n";
            Controller.resultMessage += "\nWarehouse name is: " + AddWarehouseAndBin.createdWarehouseName;
            Controller.resultMessage += "\nBin name is: " + AddWarehouseAndBin.createdBinName;
        } else if (entityTypeComboBoxIndex == 4) {
            BrowserSettings.progressVariable = 4;
            addSupplier.jira3012(login, password, driver);
            Controller.resultMessage += "New Supplier has been created\n";
            Controller.resultMessage += "\nSupplier name is: " + AddSupplier.createdSupplierName;
        } else if (entityTypeComboBoxIndex == 6) {
            BrowserSettings.progressVariable = 5;
            makeReorder.makeReorder(login, password, driver);
            Controller.resultMessage += "New Order has been created\n";
            Controller.resultMessage += "\nOrder Number is: " + BrowserSettings.orderNumber;
        } else if (entityTypeComboBoxIndex == 0) {
            BrowserSettings.progressVariable = 5;
            configureChannel.configureMagentoChannel(login, password, driver);
            Controller.resultMessage += "\nMagento "+ Controller.magentoIndexName;
            Controller.resultMessage += " has been synced with " + Controller.environmentComboBoxValue;
        } else if (entityTypeComboBoxIndex == 7) {
            BrowserSettings.progressVariable = 4;
            if(Objects.equals(GeneratePopupBox.userTypeToCreate, "Merchant")) {
                addUsers.addTenant(login, password);
            }
            Controller.resultMessage += "New " + GeneratePopupBox.userTypeToCreate + " has been created";
        }
    }
}
