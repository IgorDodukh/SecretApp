package Pages;

import Settings.BrowserSettings;
import Settings.GenerateRandomData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ihor on 8/18/2016.
 */
public class ManageMerchantPage extends BrowserSettings {
    private WebDriver driver;

    GenerateRandomData generateRandomData = new GenerateRandomData();
    String randomInt = generateRandomData.generateRandomNumber(8);
    public ManageMerchantPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addMerchantButtonLocator = By.xpath("//button[@id='createMerchant']");
    private By merchantNameFieldLocator = By.xpath("//input[@id='MerchantName']");
    private By merchantBusinessNameFieldLocator = By.xpath("//input[@id='txtBusinessName']");
    private By merchantFirstNameFieldLocator = By.xpath("//input[@id='FirstName']");
    private By merchantLastNameFieldLocator = By.xpath("//input[@id='LastName']");
    private By merchantPhoneFieldLocator = By.xpath("//input[@id='txtCompanyPhone']");
    private By merchantZipFieldLocator = By.xpath("//input[@id='txtZipCode']");
    private By merchantAddressFieldLocator = By.xpath("//input[@id='txtAddress1']");
    private By merchantSaveAndCloseButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private By merchantSavePopupLocator = By.xpath("//span[@id='ui-dialog-title-dydacomp_messagebox']");
    private By merchantOkButtonPopupLocator = By.xpath("//span[@autotest-id='btnOK']");


    public void openAddMerchantForm() {
        totalResultMessage += "Open 'Add Merchant' form\n";
        driver.findElement(addMerchantButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Merchant' form was not opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(merchantNameFieldLocator));
    }

    public void addMerchantData() {
        totalResultMessage += "Add new Merchant data\n";
        driver.findElement(merchantNameFieldLocator).sendKeys("fsqa01+" + randomInt + "@dydacomp.biz");

        driver.findElement(merchantBusinessNameFieldLocator).sendKeys("NewMerchant" + randomInt);
        driver.findElement(merchantFirstNameFieldLocator).sendKeys("New");
        driver.findElement(merchantLastNameFieldLocator).sendKeys("Merchant");
        driver.findElement(merchantPhoneFieldLocator).sendKeys(generateRandomData.generateRandomNumber(10));
        driver.findElement(merchantZipFieldLocator).sendKeys("10120");
        driver.findElement(merchantAddressFieldLocator).click();
        driver.findElement(merchantAddressFieldLocator).sendKeys("Some Address 555");
        driver.findElement(merchantSaveAndCloseButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Saved Successfully' popup box was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(merchantSavePopupLocator));

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'Ok' Button was not opened");
        wait2.until(ExpectedConditions.elementToBeClickable(merchantOkButtonPopupLocator));

        driver.findElement(merchantOkButtonPopupLocator).click();

        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("New Merchant was not saved");
        wait3.until(ExpectedConditions.elementToBeClickable(addMerchantButtonLocator));
    }
}
