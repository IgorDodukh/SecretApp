package Pages;

import FXUI.Controller;
import FXUI.GeneratePopupBox;
import Settings.BrowserSettings;
import Settings.GenerateRandomData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

/**
 * Created by Ihor on 8/18/2016. All rights reserved!
 */
public class ManageUsersPage extends BrowserSettings {
    private final WebDriver driver;

    private final GenerateRandomData generateRandomData = new GenerateRandomData();
    private final String randomInt = generateRandomData.generateRandomNumber(randomValueLength);
    public ManageUsersPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By addMerchantButtonLocator = By.xpath("//button[@id='createMerchant']");
    private final By merchantNameFieldLocator = By.xpath("//input[@id='MerchantName']");
    private final By merchantBusinessNameFieldLocator = By.xpath("//input[@id='txtBusinessName']");
    private final By merchantFirstNameFieldLocator = By.xpath("//input[@id='FirstName']");
    private final By merchantLastNameFieldLocator = By.xpath("//input[@id='LastName']");
    private final By merchantPhoneFieldLocator = By.xpath("//input[@id='txtCompanyPhone']");
    private final By merchantZipFieldLocator = By.xpath("//input[@id='txtZipCode']");
    private final By merchantAddressFieldLocator = By.xpath("//input[@id='txtAddress1']");
    private final By merchantSaveAndCloseButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private final By merchantSavePopupLocator = By.xpath("//span[@id='ui-dialog-title-dydacomp_messagebox']");
    private final By merchantOkButtonPopupLocator = By.xpath("//span[@autotest-id='btnOK']");

    private final By groupsTabButtonLocator = By.xpath("//input[@id='tabGroups']");
    private final By addGroupButtonLocator = By.xpath("//button[@id='addGroup']");

    private final By groupNameFieldLocator = By.xpath("//input[@id='groupName']");
    private final By groupDescriptionFieldLocator = By.xpath("//input[@id='groupDescripts']");

    private final By csrGroupLocator = By.xpath("//div[@tooltipid='CsrDescription']/div[@class='text']");
    private final By csrManagerGroupLocator = By.xpath("//div[@tooltipid='CsrManagerDescription']/div[@class='text']");
    private final By warehouseManagerGroupLocator = By.xpath("//div[@tooltipid='WarehouseManagerDescription']/div[@class='text']");
    private final By purchasingManagerGroupLocator = By.xpath("//div[@tooltipid='PurchasingManagerDescription']/div[@class='text']");
    private final By merchandiserGroupLocator = By.xpath("//div[@tooltipid='MerchandiserDescription']/div[@class='text']");
    private final By shipperGroupLocator = By.xpath("//div[@tooltipid='ShipperDescription']/div[@class='text']");
    private final By pickerGroupLocator = By.xpath("//div[@tooltipid='PickerDescription']/div[@class='text']");
    private final By packerGroupLocator = By.xpath("//div[@tooltipid='PackerDescription']/div[@class='text']");

    private final By saveAndCloseContextualButtonLocator = By.xpath("//*[@id='btnSaveAndClose']/div[2]");



    public void openAddMerchantForm() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Add Merchant' form\n");
        driver.findElement(addMerchantButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Merchant' form was not opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(merchantNameFieldLocator));
    }

    public void addMerchantData() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Add new Merchant data\n");
        String newMerchantEmail = "fsqa" + Controller.environmentComboBoxValue.replace("QA", "") + "+" + randomInt + "@dydacomp.biz";
        driver.findElement(merchantNameFieldLocator).sendKeys(newMerchantEmail);

        driver.findElement(merchantBusinessNameFieldLocator).sendKeys("NewMerchant" + randomInt);
        driver.findElement(merchantFirstNameFieldLocator).sendKeys("New");
        driver.findElement(merchantLastNameFieldLocator).sendKeys("Merchant");
        driver.findElement(merchantPhoneFieldLocator).sendKeys(generateRandomData.generateRandomNumber(10));
        driver.findElement(merchantZipFieldLocator).sendKeys("10120");
        driver.findElement(merchantAddressFieldLocator).click();
        Thread.sleep(500);
        driver.findElement(merchantAddressFieldLocator).sendKeys("Some Address 555");
        driver.findElement(merchantSaveAndCloseButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Saved Successfully' popup box was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(merchantSavePopupLocator));

        Thread.sleep(1000);
//        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'Ok' Button was not opened");
//        wait2.until(ExpectedConditions.elementToBeClickable(merchantOkButtonPopupLocator));
//
//        driver.findElement(merchantOkButtonPopupLocator).click();
//
//        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("New Merchant was not saved");
//        wait3.until(ExpectedConditions.elementToBeClickable(addMerchantButtonLocator));
    }

    public void openGroupCreatingForm() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open Groups grid\n");
        Thread.sleep(2000);
        driver.findElement(groupsTabButtonLocator).click();
        driver.findElement(addGroupButtonLocator).click();

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'Manage Merchant' page was not loaded");
        wait2.until(ExpectedConditions.elementToBeClickable(groupNameFieldLocator));
    }

    public void addNewGroupWithUser() {
        String groupName = GeneratePopupBox.userTypeToCreate + "-" + new GenerateRandomData().generateRandomNumber(randomValueLength);
        driver.findElement(groupNameFieldLocator).sendKeys(groupName);
        driver.findElement(groupDescriptionFieldLocator).sendKeys(groupName);

        if (Objects.equals(GeneratePopupBox.userTypeToCreate, "Merchandiser")) {
            driver.findElement(merchandiserGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "Picker")) {
            driver.findElement(pickerGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "Packer")) {
            driver.findElement(packerGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "Shipper")) {
            driver.findElement(shipperGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "CSR")) {
            driver.findElement(csrGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "CSR Manager")) {
            driver.findElement(csrManagerGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "Purchase Manager")) {
            driver.findElement(purchasingManagerGroupLocator).click();
        } else if (Objects.equals(GeneratePopupBox.userTypeToCreate, "Warehouse Manager")) {
            driver.findElement(warehouseManagerGroupLocator).click();
        }
    }

    public void saveNewGroup() {
        driver.findElement(saveAndCloseContextualButtonLocator).click();
    }
}
