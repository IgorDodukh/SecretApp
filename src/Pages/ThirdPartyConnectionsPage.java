package Pages;

import Settings.BrowserSettings;
import FXUI.ProgressBar;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Objects;

/**
 * Created by igor on 27.05.16.
 */
public class ThirdPartyConnectionsPage extends BrowserSettings {
    private WebDriver driver;

    public ThirdPartyConnectionsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By authorizeLoginFieldLocator = By.xpath("//input[@id='passwordAuthorizenetLoginId']");
    private By authorizeKeyFieldLocator = By.xpath("//input[@id='passwordTransactionKey']");
    private By authorizeTestButtonLocator = By.xpath("//input[@id='btnPaymentGatewaryTestConnect']");
//    private By authorizeTestResultPopupLocator = By.xpath("//div[@id='ThirdPartyConnectionsManagement']");
    private By confirmPopupButtonLocator = By.xpath("//div[1]/button[@class='primary-button']");

    private By carrierGetawayTabLocator = By.xpath("//a[@id='leftNav_item_2']");
    private By upsConfigurationCheckboxLocator = By.xpath("//input[@id='upsConnectionsChk']");
    private By upsUserNameFieldLocator = By.xpath("//input[@id='txtUPSUserName']");
    private By upsPasswordFieldLocator = By.xpath("//input[@id='passwordUPSPassword']");
    private By upsShipperNumberFieldLocator = By.xpath("//input[@id='txtUPSShipperNumber']");
    private By upsLicenseNumberFieldLocator = By.xpath("//input[@id='txtUPSAccessLicenseNumber']");

    private By upsTestButtonLocator = By.xpath("//input[@id='btnUPSTestConnect']");
    private By testResultPopupLocator = By.xpath("//div[@id='ThirdPartyConnectionsManagement']");

    private By uspsConfigurationCheckboxLocator = By.xpath("//input[@id='uspsConnectionsChk']");
    private By uspsAccountIdFieldLocator = By.xpath("//input[@id='txtUSPSAccountID']");
    private By uspsPasswordFieldLocator = By.xpath("//input[@id='passwordUSPSPassword']");

    private By uspsTestButtonLocator = By.xpath("//input[@id='btnUSPSTestConnect']");

    private By saveAndCloseContextualButtonLocator = By.xpath("//*[@id='btnSaveAndClose']/div[2]");
    private By saveContextualButtonLocator = By.xpath("//*[@id='btnSave']/div[2]");
    private By popupBoxMessageLocator = By.xpath("(//div[@id='warehouseMessageBox']//*)[1]");
    private By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

    private By setUpButtonLocator = By.xpath("//ul[@id='topRightNav']/li[2]");
    private By thirdPartyButtonLocator = By.xpath("//ul[@id='topRightNav']/li[2]/ul/li[6]");



    public void openThirdPartyPage() {
        totalResultMessage += "Open Third Party Connections page\n";
        totalResultMessage += " - Click 'Set Up' button\n";
        driver.findElement(setUpButtonLocator).click();
        totalResultMessage += " - Click '3rd Party Connections' button\n";
        driver.findElement(thirdPartyButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Third Party' page was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(authorizeLoginFieldLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void configureAuthorizeAccount(String apiLoginId, String transactionKey) {
        totalResultMessage += "Set 'Authorize' credentials:\n";
        totalResultMessage += " - Add API login ID\n";
        WebElement loginField = driver.findElement(authorizeLoginFieldLocator);
        loginField.clear();
        loginField.sendKeys(apiLoginId);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Transaction Key\n";
        WebElement keyField = driver.findElement(authorizeKeyFieldLocator);
        keyField.clear();
        keyField.sendKeys(transactionKey);
        ProgressBar.addProgressValue(progressVariable);

//        log("Make Test for Authorize");
//        driver.findElement(authorizeTestButtonLocator).click();
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Authorize test result popup was not found");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(testResultPopupLocator));
//
//        String currentPopupMessage = driver.findElement(testResultPopupLocator).getText();
//        Assert.assertEquals(currentPopupMessage, "The connection test was successful.", "Authorize test result unexpected popup message.");
//
//        driver.findElement(confirmPopupButtonLocator).click();
    }

    public void configureUPSAccount(String userName, String password, String licenseNumber, String shipperNumber) {
        totalResultMessage += "Select Carrier Getaway Tab\n";
        driver.findElement(carrierGetawayTabLocator).click();

        totalResultMessage += "Set UPS credentials:\n";
        totalResultMessage += " - Click UPS checkbox\n";
        ProgressBar.addProgressValue(progressVariable);

        String upsIsChecked = driver.findElement(upsConfigurationCheckboxLocator).getAttribute("checked");

        if(!Objects.equals(upsIsChecked, "true")){
            driver.findElement(upsConfigurationCheckboxLocator).click();
            totalResultMessage += "UPS checkbox was not selected\n";
        } else {
            totalResultMessage += "UPS checkbox was selected\n";
        }
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("'UPS User Name' field was not found");
        WebElement element2 = wait3.until(ExpectedConditions.elementToBeClickable(upsUserNameFieldLocator));
        Assert.assertEquals(element2.isDisplayed(), true, "'UPS User Name' field was not loaded");

        totalResultMessage += " - Enter UPS username\n";
        WebElement userNameField = driver.findElement(upsUserNameFieldLocator);
        userNameField.clear();
        userNameField.sendKeys(userName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Enter UPS password\n";
        WebElement passwordField = driver.findElement(upsPasswordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Enter UPS License Number\n";
        WebElement licenseNumberField = driver.findElement(upsLicenseNumberFieldLocator);
        licenseNumberField.clear();
        licenseNumberField.sendKeys(licenseNumber);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Enter UPS Shipper Number\n";
        WebElement shipperNumberField = driver.findElement(upsShipperNumberFieldLocator);
        shipperNumberField.clear();
        shipperNumberField.sendKeys(shipperNumber);
        ProgressBar.addProgressValue(progressVariable);

//          Test for UPS connection

//        log("Make Test for UPS");
//        driver.findElement(upsTestButtonLocator).click();
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("UPS test result popup was not found");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(testResultPopupLocator));
//
//        String currentPopupMessage = driver.findElement(testResultPopupLocator).getText();
//        Assert.assertEquals(currentPopupMessage, "The connection test was successful.", "UPS test result unexpected popup message.");
//
//        driver.findElement(confirmPopupButtonLocator).click();
    }

    public void configureUSPSAccount(String accountId, String passPhrase) {
        totalResultMessage += "Click USPS checkbox\n";

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("USPS checkbox is not clickable");
        wait2.until(ExpectedConditions.elementToBeClickable(uspsConfigurationCheckboxLocator));

        totalResultMessage += "Click USPS checkbox\n";
        String uspsIsChecked;
        uspsIsChecked = driver.findElement(uspsConfigurationCheckboxLocator).getAttribute("checked");
        ProgressBar.addProgressValue(progressVariable);

        if(!Objects.equals(uspsIsChecked, "true")){
            driver.findElement(uspsConfigurationCheckboxLocator).click();
            totalResultMessage += "USPS checkbox was not selected\n";
        } else {
            totalResultMessage += "USPS checkbox was selected\n";
        }
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'USPS Account ID' field was not found");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(uspsAccountIdFieldLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'USPS Account ID' field was not loaded");

        totalResultMessage += " - Enter USPS Account ID\n";
        WebElement accountIdField = driver.findElement(uspsAccountIdFieldLocator);
        accountIdField.clear();
        accountIdField.sendKeys(accountId);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Enter USPS Pass Phrase\n";
        WebElement passPhraseField = driver.findElement(uspsPasswordFieldLocator);
        passPhraseField.clear();
        passPhraseField.sendKeys(passPhrase);
        ProgressBar.addProgressValue(progressVariable);

//        log("Make Test for USPS");
//        driver.findElement(uspsTestButtonLocator).click();
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("USPS test result popup was not found");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(testResultPopupLocator));
//
//        String currentPopupMessage = driver.findElement(testResultPopupLocator).getText();
//        Assert.assertEquals(currentPopupMessage, "The connection test was successful.", "USPS test result unexpected popup message");
//        Thread.sleep(5000);
//
//        driver.findElement(confirmPopupButtonLocator).click();
//        Thread.sleep(5000);

    }

    public void saveThirdPartyConnectionSettings () {
        totalResultMessage += "Click 'Save and Close' button\n";
        driver.findElement(saveAndCloseContextualButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupOkBtnLocator));
        ProgressBar.addProgressValue(3);

        totalResultMessage += "Confirm popup message\n";
        driver.findElement(popupOkBtnLocator).click();
        ProgressBar.addProgressValue(3);
    }
}
