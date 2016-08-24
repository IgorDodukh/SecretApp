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
    GenerateRandomData generateRandomData = new GenerateRandomData();
    @Test
    public void jira3675(String email, String password, String cardNumber, WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, password);

        MainPage mainPage = new MainPage(driver);
        mainPage.openAddCustomerPage();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.addCustomerInfo(
                GetPropertyValues.customerFirstName + generateRandomData.generateRandomNumber(randomValueLength),
                GetPropertyValues.customerLastName + generateRandomData.generateRandomNumber(randomValueLength),
                customerEmail, phone);
        addCustomerPage.addBillingAddress(addressFirstName, addressLastName, addressLine1, addressZip);
        addCustomerPage.addShippingAddress();
        addCustomerPage.addCreditCard(cardNumber);
        addCustomerPage.saveNewCustomer();
    }
}
