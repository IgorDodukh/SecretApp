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
 * Created by Ihor on 6/27/2016.
 */
public class AddSupplierPage extends BrowserSettings {
    private WebDriver driver;

    public AddSupplierPage(WebDriver driver) {
        this.driver = driver;
    }

    private By supplierAccountNumberFieldLocator = By.xpath("//input[@id='acct_no']");
    private By supplierNameFieldLocator = By.xpath("//input[@id='supplier_name']");
    private By supplierURLFieldLocator = By.xpath("//input[@id='siteURL']");
    private By supplierAddressFieldLocator = By.xpath("//input[@id='address']");
    private By supplierZipFieldLocator = By.xpath("//input[@id='zip']");

    private By supplierEmailToFieldLocator = By.xpath("//input[@id='email_to']");
    private By supplierEmailBCCFieldLocator = By.xpath("//input[@id='email_bcc']");
    private By supplierEmailCCFieldLocator = By.xpath("//input[@id='email_cc']");

    private By contactFirstNameFieldLocator = By.xpath("//input[@id='first_name']");
    private By contactLastNameFieldLocator = By.xpath("//input[@id='last_name']");
    private By contactPhoneFieldLocator = By.xpath("//input[@id='phone']");
    private By contactEmailFieldLocator = By.xpath("//input[@id='email']");
    private By contactJobTitleFieldLocator = By.xpath("//input[@id='job_title']");
    private By contactFaxFieldLocator = By.xpath("//input[@id='fax']");

    private By saveAndCloseContextualButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private By popupBoxMessageLocator = By.xpath("//div[@id='dydacomp_messagebox']");
    private By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");


    public void addSupplierContactInfo(String accountNumber, String name, String url, String address1, String zip, String email, String first, String last) throws InterruptedException {
        totalResultMessage += "Adding Supplier info:\n";
        totalResultMessage += " - Add Supplier Account Number\n";
        driver.findElement(supplierAccountNumberFieldLocator).sendKeys(accountNumber);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Name\n";
        driver.findElement(supplierNameFieldLocator).sendKeys(name);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier URL\n";
        WebElement urlField = driver.findElement(supplierURLFieldLocator);
        urlField.click();
        Thread.sleep(500);
        urlField.sendKeys(url);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Address\n";
        driver.findElement(supplierAddressFieldLocator).sendKeys(address1);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Zip code\n";
        driver.findElement(supplierZipFieldLocator).sendKeys(zip);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Email To\n";
        WebElement emailToField = driver.findElement(supplierEmailToFieldLocator);
        emailToField.click();
        emailToField.sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Email BCC\n";
        driver.findElement(supplierEmailBCCFieldLocator).sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Email CC\n";
        driver.findElement(supplierEmailCCFieldLocator).sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Contact First Name\n";
        driver.findElement(contactFirstNameFieldLocator).sendKeys(first);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Contact Last Name\n";
        driver.findElement(contactLastNameFieldLocator).sendKeys(last);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Contact Job Title\n";
        driver.findElement(contactJobTitleFieldLocator).sendKeys("QA");
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Contact Phone\n";
        driver.findElement(contactPhoneFieldLocator).sendKeys("8888888888");
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Contact Email\n";
        driver.findElement(contactEmailFieldLocator).sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Contact Fax\n";
        driver.findElement(contactFaxFieldLocator).sendKeys("4444444444");
        ProgressBar.addProgressValue(progressVariable);
    }

    public void saveSupplier() {
        totalResultMessage += "Saving new Supplier:\n";
        totalResultMessage += " - Click 'Save and Close' button\n";
        driver.findElement(saveAndCloseContextualButtonLocator).click();
        ProgressBar.addProgressValue(2);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupBoxMessageLocator));
        ProgressBar.addProgressValue(progressVariable);

//        totalResultMessage += " - Confirm success popup\n";
////        String currentPopupMessage = driver.findElement(popupBoxMessageLocator).getText();
////        Assert.assertEquals(currentPopupMessage, saveSupplierPopupMessage, "Unexpected popup message");
//        driver.findElement(popupOkBtnLocator).click();
    }
}
