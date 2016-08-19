package Tests;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.ManageMerchantPage;
import Settings.BrowserSettings;
import org.testng.annotations.Test;

/**
 * Created by Ihor on 8/17/2016.
 */
public class AddUsers extends BrowserSettings {

    @Test
    public void addTenant(String email, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, password);

        MainPage mainPage = new MainPage(driver);
        mainPage.openUsersPage();

        ManageMerchantPage manageMerchantPage = new ManageMerchantPage(driver);
        manageMerchantPage.openAddMerchantForm();
        manageMerchantPage.addMerchantData();
    }
}
