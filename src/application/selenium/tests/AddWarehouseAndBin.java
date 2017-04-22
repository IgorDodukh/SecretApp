package application.selenium.tests;

import application.selenium.pages.AddWarehousePage;
import application.selenium.pages.LoginPage;
import application.selenium.pages.MainPage;
import application.selenium.settings.BrowserSettings;
import application.selenium.settings.GenerateRandomData;
import application.selenium.settings.GetPropertyValues;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by igor on 18.04.16. All rights reserved!
 */
public class AddWarehouseAndBin extends BrowserSettings {

    public static String createdWarehouseName = "";
    public static String createdBinName = "";
    @Test
    public void addWarehouseAndBin(String email, String merchantPassword, WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openAddWarehousePage();

        AddWarehousePage addWarehousePage = new AddWarehousePage(driver);
        addWarehousePage.addWarehouseInfo(
                createdWarehouseName = GetPropertyValues.warehouseName + "_" + new GenerateRandomData().generateRandomNumber(randomValueLength),
                warehouseContactName, phone, startPickupTime, endPickupTime, addressLine1, addressZip);
        addWarehousePage.addWarehouseBin(createdBinName = GetPropertyValues.binName + "_" + new GenerateRandomData().generateRandomNumber(randomValueLength));
        addWarehousePage.saveWarehouse();
    }
}