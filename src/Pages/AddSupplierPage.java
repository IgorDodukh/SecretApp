package Pages;

import Settings.BrowserSettings;
import FXUI.ProgressBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ihor on 6/27/2016. All rights reserved!
 */
public class AddSupplierPage extends BrowserSettings {
    private final WebDriver driver;

    public AddSupplierPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By supplierAccountNumberFieldLocator = By.xpath("//input[@id='acct_no']");
    private final By supplierNameFieldLocator = By.xpath("//input[@id='supplier_name']");
    private final By supplierURLFieldLocator = By.xpath("//input[@id='siteURL']");
    private final By supplierAddressFieldLocator = By.xpath("//input[@id='address']");
    private final By supplierZipFieldLocator = By.xpath("//input[@id='zip']");

    private final By supplierEmailToFieldLocator = By.xpath("//input[@id='email_to']");
    private final By supplierEmailBCCFieldLocator = By.xpath("//input[@id='email_bcc']");
    private final By supplierEmailCCFieldLocator = By.xpath("//input[@id='email_cc']");

    private final By contactFirstNameFieldLocator = By.xpath("//input[@id='first_name']");
    private final By contactLastNameFieldLocator = By.xpath("//input[@id='last_name']");
    private final By contactPhoneFieldLocator = By.xpath("//input[@id='phone']");
    private final By contactEmailFieldLocator = By.xpath("//input[@id='email']");
    private final By contactJobTitleFieldLocator = By.xpath("//input[@id='job_title']");
    private final By contactFaxFieldLocator = By.xpath("//input[@id='fax']");

    private final By saveAndCloseContextualButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private final By popupBoxMessageLocator = By.xpath("//div[@id='dydacomp_messagebox']");

    public void addSupplierContactInfo(String accountNumber,
                                       String name,
                                       String url,
                                       String address1,
                                       String zip,
                                       String email,
                                       String first,
                                       String last)
            throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Adding Supplier info:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Account Number\n");
        driver.findElement(supplierAccountNumberFieldLocator).sendKeys(accountNumber);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Name\n");
        driver.findElement(supplierNameFieldLocator).sendKeys(name);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier URL\n");
        WebElement urlField = driver.findElement(supplierURLFieldLocator);
        urlField.click();
        Thread.sleep(500);
        ProgressBar.addProgressValue(progressVariable);
        urlField.sendKeys(url);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Address\n");
        driver.findElement(supplierAddressFieldLocator).sendKeys(address1);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Zip code\n");
        driver.findElement(supplierZipFieldLocator).sendKeys(zip);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Email To\n");
        WebElement emailToField = driver.findElement(supplierEmailToFieldLocator);
        emailToField.click();
        emailToField.sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Email BCC\n");
        driver.findElement(supplierEmailBCCFieldLocator).sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Email CC\n");
        driver.findElement(supplierEmailCCFieldLocator).sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Contact First Name\n");
        driver.findElement(contactFirstNameFieldLocator).sendKeys(first);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Contact Last Name\n");
        driver.findElement(contactLastNameFieldLocator).sendKeys(last);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Contact Job Title\n");
        driver.findElement(contactJobTitleFieldLocator).sendKeys("QA");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Contact Phone\n");
        driver.findElement(contactPhoneFieldLocator).sendKeys("8888888888");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Contact Email\n");
        driver.findElement(contactEmailFieldLocator).sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Contact Fax\n");
        driver.findElement(contactFaxFieldLocator).sendKeys("4444444444");
        ProgressBar.addProgressValue(progressVariable);
    }

    public void saveSupplier() {
        setTotalResultMessage(getTotalResultMessage() + "Saving new Supplier:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save and Close' button\n");
        driver.findElement(saveAndCloseContextualButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, getTimeoutVariable()).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupBoxMessageLocator));
        ProgressBar.addProgressValue(progressVariable);

//        totalResultMessage += " - Confirm success popup\n";
////        String currentPopupMessage = driver.findElement(popupBoxMessageLocator).getText();
////        Assert.assertEquals(currentPopupMessage, saveSupplierPopupMessage, "Unexpected popup message");
//        driver.findElement(popupOkBtnLocator).click();
    }
}
