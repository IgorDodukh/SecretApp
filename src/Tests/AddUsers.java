package Tests;

import Pages.CorporateMailBox;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.ManageUsersPage;
import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by Ihor on 8/17/2016. All rights reserved!
 */
public class AddUsers extends BrowserSettings {

    @Test
    public void addMerchant(String email, String password, WebDriver driver) throws InterruptedException {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.loginMerchant(email, password);
//
//        MainPage mainPage = new MainPage(driver);
//        mainPage.openMerchantsPage();
//
//        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
//        manageUsersPage.openAddMerchantForm();
//        manageUsersPage.addMerchantData();

        CorporateMailBox corporateMailBox = new CorporateMailBox(driver);
        corporateMailBox.openMailBox();
        corporateMailBox.openInvitationEmail();
//        corporateMailBox.setNewMerchantPassword();
        Thread.sleep(20000);
    }

    @Test
    public void addMerchantAdmin(String email, String password, WebDriver driver) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, password);

        MainPage mainPage = new MainPage(driver);
        mainPage.openUsersPermissionsPage();

        ManageUsersPage manageUsersPage = new ManageUsersPage(driver);
        manageUsersPage.openGroupCreatingForm();
        manageUsersPage.addNewGroupWithUser();
        manageUsersPage.saveNewGroup();
    }
}
