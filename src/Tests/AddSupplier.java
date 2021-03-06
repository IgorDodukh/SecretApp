package Tests;

import Pages.AddSupplierPage;
import Pages.LoginPage;
import Pages.MainPage;
import Settings.BrowserSettings;
import Settings.GenerateRandomData;
import Settings.GetPropertyValues;
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
                createdSupplierName = GetPropertyValues.supplierName + "_" + new GenerateRandomData().generateRandomNumber(getRandomValueLength()),
                supplierURL,
                supplierAddress,
                addressZip,
                createdSupplierName + "@dydacomp.biz",
                AddNewCustomer.createdFirstName,
                AddNewCustomer.createdLastName);
        addSupplierPage.saveSupplier();
    }
}
