package Tests;

import Pages.AddWarehousePage;
import Pages.LoginPage;
import Pages.MainPage;
import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by igor on 18.04.16.
 */
public class AddWarehouseAndBin extends BrowserSettings {

    @Test
    public void jira3006(String email, String merchantPassword, WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openAddWarehousePage();

        AddWarehousePage addWarehousePage = new AddWarehousePage(driver);
        addWarehousePage.addWarehouseInfo(warehouseName, warehouseContactName, phone, startPickupTime, endPickupTime, addressLine1, addressZip);
        addWarehousePage.addWarehouseBin(newBinName);
        addWarehousePage.saveWarehouse();
    }
}