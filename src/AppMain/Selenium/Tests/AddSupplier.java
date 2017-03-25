package AppMain.Selenium.Tests;

import AppMain.Selenium.Pages.AddSupplierPage;
import AppMain.Selenium.Pages.LoginPage;
import AppMain.Selenium.Pages.MainPage;
import AppMain.Selenium.Settings.BrowserSettings;
import AppMain.Selenium.Settings.GenerateRandomData;
import AppMain.Selenium.Settings.GetPropertyValues;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by Ihor on 6/27/2016. All rights reserved!
 */
public class AddSupplier extends BrowserSettings {

    public static String createdSupplierName = "";
    @Test
    public void addSupplier(String email, String merchantPassword, WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openSuppliersPage();
        mainPage.openAddSupplierPage();

        AddSupplierPage addSupplierPage = new AddSupplierPage(driver);
        addSupplierPage.addSupplierContactInfo(
                supplierAccountNumber,
                createdSupplierName = GetPropertyValues.supplierName + "_" + new GenerateRandomData().generateRandomNumber(randomValueLength),
                supplierURL,
                supplierAddress,
                addressZip,
                createdSupplierName + "@dydacomp.biz",
                AddNewCustomer.createdFirstName,
                AddNewCustomer.createdLastName);
        addSupplierPage.saveSupplier();
    }
}
