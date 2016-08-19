package FXUI;

import Settings.BrowserSettings;
import Tests.*;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

/**
 * Created by Ihor on 7/16/2016.
 */
public class DropdownValueDeterminer {
    private ConfigureMerchant configureMerchant = new ConfigureMerchant();
    private AddNewCustomer addNewCustomer = new AddNewCustomer();
    private AddProductWithInventory addProductWithInventory = new AddProductWithInventory();
    private AddWarehouseAndBin addWarehouseAndBin = new AddWarehouseAndBin();
    private CreateSupplier createSupplier = new CreateSupplier();
    private MakeReorder makeReorder = new MakeReorder();
    private ConfigureChannel configureChannel = new ConfigureChannel();
    private AddUsers addUsers = new AddUsers();


    public void entityTypeDropdown(int entityTypeComboBoxIndex, String login, String password, String testCardNumber, WebDriver driver) throws InterruptedException {
        Controller.resultMessage += "Test has been finished.\n";
        if (entityTypeComboBoxIndex == 1) {
            BrowserSettings.progressVariable = 3;
            configureMerchant.setupNewMerchant(login, password, driver);
            Controller.resultMessage += "Merchant '" + login + "'\nhas been configured";
        } else if (entityTypeComboBoxIndex == 2) {
            BrowserSettings.progressVariable = 4;
            addNewCustomer.jira3675(login, password, testCardNumber, driver);
            Controller.resultMessage += "New Customer has been created\n";
            Controller.resultMessage += "\nCustomer name is:\n" + BrowserSettings.firstName + " " + BrowserSettings.lastName;

        } else if (entityTypeComboBoxIndex == 3) {
            BrowserSettings.progressVariable = 2;
            addProductWithInventory.jira3015(login, password, driver);
            Controller.resultMessage += "New Product has been created\n";
            Controller.resultMessage += "\nProduct SKU is: " + BrowserSettings.productSku;
            Controller.resultMessage += "\nProduct Bin name is: " + BrowserSettings.binName;
            Controller.resultMessage += "\nProduct qty is: " + BrowserSettings.inventoryQty;
        } else if (entityTypeComboBoxIndex == 5) {
            BrowserSettings.progressVariable = 5;
            addWarehouseAndBin.jira3006(login, password, driver);
            Controller.resultMessage += "New Warehouse and Bin have been created\n";
            Controller.resultMessage += "\nWarehouse name is: " + BrowserSettings.warehouseName;
            Controller.resultMessage += "\nBin name is: " + BrowserSettings.newBinName;
        } else if (entityTypeComboBoxIndex == 4) {
            BrowserSettings.progressVariable = 4;
            createSupplier.jira3012(login, password, driver);
            Controller.resultMessage += "New Supplier has been created\n";
            Controller.resultMessage += "\nSupplier name is: " + BrowserSettings.supplierName;
        } else if (entityTypeComboBoxIndex == 6) {
            BrowserSettings.progressVariable = 5;
            makeReorder.makeReorder(login, password, driver);
            Controller.resultMessage += "New Order has been created\n";
            Controller.resultMessage += "\nOrder Number is: " + BrowserSettings.orderNumber;
        } else if (entityTypeComboBoxIndex == 0) {
            BrowserSettings.progressVariable = 4;
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
