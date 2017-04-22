package application.selenium.pages;

import application.selenium.settings.BrowserSettings;
import application.fxui.ProgressBar;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Objects;

/**
 * Created by igor on 27.05.16. All rights reserved!
 */
public class ThirdPartyConnectionsPage extends BrowserSettings {
    private final WebDriver driver;

    public ThirdPartyConnectionsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By authorizeLoginFieldLocator = By.xpath("//input[@id='passwordAuthorizenetLoginId']");
    private final By authorizeKeyFieldLocator = By.xpath("//input[@id='passwordTransactionKey']");

    private final By carrierGetawayTabLocator = By.xpath("//a[@id='leftNav_item_2']");
    private final By upsConfigurationCheckboxLocator = By.xpath("//input[@id='upsConnectionsChk']");
    private final By upsUserNameFieldLocator = By.xpath("//input[@id='txtUPSUserName']");
    private final By upsPasswordFieldLocator = By.xpath("//input[@id='passwordUPSPassword']");
    private final By upsShipperNumberFieldLocator = By.xpath("//input[@id='txtUPSShipperNumber']");
    private final By upsLicenseNumberFieldLocator = By.xpath("//input[@id='txtUPSAccessLicenseNumber']");

    private final By uspsConfigurationCheckboxLocator = By.xpath("//input[@id='uspsConnectionsChk']");
    private final By uspsAccountIdFieldLocator = By.xpath("//input[@id='txtUSPSAccountID']");
    private final By uspsPasswordFieldLocator = By.xpath("//input[@id='passwordUSPSPassword']");

    private final By saveAndCloseContextualButtonLocator = By.xpath("//*[@id='btnSaveAndClose']/div[2]");
    private final By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

    private final By setUpButtonLocator = By.xpath("//ul[@id='topRightNav']/li[2]");
    private final By thirdPartyButtonLocator = By.xpath("//ul[@id='topRightNav']/li[2]/ul/li[6]");



    public void openThirdPartyPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open Third Party Connections page\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Set Up' button\n");
        driver.findElement(setUpButtonLocator).click();
        setTotalResultMessage(getTotalResultMessage() + " - Click '3rd Party Connections' button\n");
        driver.findElement(thirdPartyButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Third Party' page was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(authorizeLoginFieldLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void configureAuthorizeAccount(String apiLoginId, String transactionKey) {
        setTotalResultMessage(getTotalResultMessage() + "Set 'Authorize' credentials:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add application.api login ID\n");
        WebElement loginField = driver.findElement(authorizeLoginFieldLocator);
        loginField.clear();
        loginField.sendKeys(apiLoginId);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Transaction Key\n");
        WebElement keyField = driver.findElement(authorizeKeyFieldLocator);
        keyField.clear();
        keyField.sendKeys(transactionKey);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void configureUPSAccount(String userName, String password, String licenseNumber, String shipperNumber) {
        setTotalResultMessage(getTotalResultMessage() + "Select Carrier Getaway Tab\n");
        driver.findElement(carrierGetawayTabLocator).click();

        setTotalResultMessage(getTotalResultMessage() + "Set UPS credentials:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click UPS checkbox\n");
        ProgressBar.addProgressValue(progressVariable);

        String upsIsChecked = driver.findElement(upsConfigurationCheckboxLocator).getAttribute("checked");

        if(!Objects.equals(upsIsChecked, "true")){
            driver.findElement(upsConfigurationCheckboxLocator).click();
            setTotalResultMessage(getTotalResultMessage() + "UPS checkbox was not selected\n");
        } else {
            setTotalResultMessage(getTotalResultMessage() + "UPS checkbox was selected\n");
        }
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("'UPS User Name' field was not found");
        WebElement element2 = wait3.until(ExpectedConditions.elementToBeClickable(upsUserNameFieldLocator));
        Assert.assertEquals(element2.isDisplayed(), true, "'UPS User Name' field was not loaded");

        setTotalResultMessage(getTotalResultMessage() + " - Enter UPS username\n");
        WebElement userNameField = driver.findElement(upsUserNameFieldLocator);
        userNameField.clear();
        userNameField.sendKeys(userName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Enter UPS password\n");
        WebElement passwordField = driver.findElement(upsPasswordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Enter UPS License Number\n");
        WebElement licenseNumberField = driver.findElement(upsLicenseNumberFieldLocator);
        licenseNumberField.clear();
        licenseNumberField.sendKeys(licenseNumber);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Enter UPS Shipper Number\n");
        WebElement shipperNumberField = driver.findElement(upsShipperNumberFieldLocator);
        shipperNumberField.clear();
        shipperNumberField.sendKeys(shipperNumber);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void configureUSPSAccount(String accountId, String passPhrase) {
        setTotalResultMessage(getTotalResultMessage() + "Click USPS checkbox\n");

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("USPS checkbox is not clickable");
        wait2.until(ExpectedConditions.elementToBeClickable(uspsConfigurationCheckboxLocator));

        setTotalResultMessage(getTotalResultMessage() + "Click USPS checkbox\n");
        String uspsIsChecked;
        uspsIsChecked = driver.findElement(uspsConfigurationCheckboxLocator).getAttribute("checked");
        ProgressBar.addProgressValue(progressVariable);

        if(!Objects.equals(uspsIsChecked, "true")){
            driver.findElement(uspsConfigurationCheckboxLocator).click();
            setTotalResultMessage(getTotalResultMessage() + "USPS checkbox was not selected\n");
        } else {
            setTotalResultMessage(getTotalResultMessage() + "USPS checkbox was selected\n");
        }
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'USPS Account ID' field was not found");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(uspsAccountIdFieldLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'USPS Account ID' field was not loaded");

        setTotalResultMessage(getTotalResultMessage() + " - Enter USPS Account ID\n");
        WebElement accountIdField = driver.findElement(uspsAccountIdFieldLocator);
        accountIdField.clear();
        accountIdField.sendKeys(accountId);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Enter USPS Pass Phrase\n");
        WebElement passPhraseField = driver.findElement(uspsPasswordFieldLocator);
        passPhraseField.clear();
        passPhraseField.sendKeys(passPhrase);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void saveThirdPartyConnectionSettings () {
        setTotalResultMessage(getTotalResultMessage() + "Click 'Save and Close' button\n");
        driver.findElement(saveAndCloseContextualButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupOkBtnLocator));
        ProgressBar.addProgressValue(3);

        setTotalResultMessage(getTotalResultMessage() + "Confirm popup message\n");
        driver.findElement(popupOkBtnLocator).click();
        ProgressBar.addProgressValue(3);
    }
}
