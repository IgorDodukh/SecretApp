package Tests;

import Pages.AddCustomerPage;
import Pages.LoginPage;
import Pages.MainPage;
import Settings.BrowserSettings;
import Settings.GenerateRandomData;
import Settings.GetPropertyValues;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by igor on 17.04.16. All rights reserved!
 */
public class AddNewCustomer extends BrowserSettings{
    public static String createdFirstName = "Some";
    public static String createdLastName = "Name";
    @Test
    public void addNewCustomer(String email, String password, String cardNumber, WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, password);

        MainPage mainPage = new MainPage(driver);
        mainPage.openAddCustomerPage();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.addCustomerInfo(
                createdFirstName = GetPropertyValues.customerFirstName + "_" + new GenerateRandomData().generateRandomNumber(getRandomValueLength()),
                createdLastName = GetPropertyValues.customerLastName + "_" + new GenerateRandomData().generateRandomNumber(getRandomValueLength()),
                customerEmail, phone);
        addCustomerPage.addBillingAddress(addressFirstName, addressLastName, addressLine1, addressZip);
        addCustomerPage.addShippingAddress();
        addCustomerPage.addCreditCard(cardNumber);
        addCustomerPage.saveNewCustomer();
    }
}
