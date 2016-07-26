package Tests;

import Pages.*;
import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by igor on 27.05.16.
 */
public class ConfigureMerchant extends BrowserSettings {
    @Test
    public void setupNewMerchant(String email, String merchantPassword, WebDriver driver) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openSetUpPage();

        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.setShipaheadSetting();

        ThirdPartyConnectionsPage thirdPartyConnectionsPage = new ThirdPartyConnectionsPage(driver);
        thirdPartyConnectionsPage.openThirdPartyPage();
        thirdPartyConnectionsPage.configureAuthorizeAccount(authApiLoginId, authTransactionKey);
        thirdPartyConnectionsPage.configureUPSAccount(upsUserName, upsPassword, upsLicenseNumber, upsShipperNumber);
        thirdPartyConnectionsPage.configureUSPSAccount(uspsAccountId, uspsPassPhrase);
        thirdPartyConnectionsPage.saveThirdPartyConnectionSettings();

        mainPage.openMainPage();
        mainPage.openShippingMethodsPage();

        ShippingMethodsPage shippingMethodsPage = new ShippingMethodsPage(driver);
        shippingMethodsPage.openShippingMethodCreatingForm();
        shippingMethodsPage.createUPSGroundShippingMethod(upsGroundMethodName, shippingMethodPrice);
    }
}
