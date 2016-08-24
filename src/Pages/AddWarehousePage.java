package Pages;

import FXUI.ProgressBar;
import Settings.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by igor on 18.04.16. All rights reserved!
 */
public class AddWarehousePage extends BrowserSettings {

    private final WebDriver driver;

    public AddWarehousePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By warehouseNameFieldLocator = By.xpath("//input[@id='warehouseName']");
    private final By warehouseContactNameFieldLocator = By.xpath("//input[@id='contactName']");
    private final By warehousePhoneFieldLocator = By.xpath("//input[@id='phoneNumber']");
    private final By pickingReadyTimeFieldLocator = By.xpath("//input[@id='pickingReadyTime']");
    private final By pickingCutoffTimeFieldLocator = By.xpath("//input[@id='pickingCutoffTime']");
    private final By addressFieldLocator = By.xpath("//input[@id='address_warehouse']");
    private final By zipFieldLocator = By.xpath("//input[@id='zip_warehouse']");
    private final By warehouseInfoTitleLocator = By.xpath("//*[@id='subTitle']/h2/strong");
    private final By binsTabLocator = By.xpath("//*[@id='leftNav']/ul/li[2]/a");
    private final By addWarehouseBinButtonLocator = By.xpath("//input[@id='add_bin']");
    private final By addBinPopupTitleLocator = By.xpath("//*[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable']");

    private final By newBinNameLocator = By.xpath("//*[@id='txtBinName']");
    private final By pickBinTypeLocator = By.xpath("//*[@id='selectBinType']/option[@value='2']");
    private final By saveNewBinBtnLocator = By.xpath("//*[@id='save_BinAddClone']");
    private final By newBinNameInBinsGridLocator = By.xpath("((//*[@id='warehouseBinsResult']//tr/*)[6])");

    private final By saveContextualButton = By.xpath("//a[@id='btnSave']");

    private final By popupBoxMessageLocator = By.xpath("(//div[@id='warehouseMessageBox']//*)[1]");
    private final By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

    public void addWarehouseInfo(
            String warehouseName,
            String contactName,
            String phone,
            String startTime,
            String endTime,
            String addressLine1,
            String zip) {
        totalResultMessage += "Adding Warehouse info:\n";
        totalResultMessage +=" - Add Warehouse Name\n";
        driver.findElement(warehouseNameFieldLocator).sendKeys(warehouseName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add contact name\n";
        driver.findElement(warehouseContactNameFieldLocator).sendKeys(contactName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add phone number\n";
        driver.findElement(warehousePhoneFieldLocator).sendKeys(phone);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Earliest Pickup Time\n";
        driver.findElement(pickingReadyTimeFieldLocator).sendKeys(startTime);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Latest Pickup Time\n";
        driver.findElement(pickingCutoffTimeFieldLocator).sendKeys(endTime);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add address line\n";
        driver.findElement(addressFieldLocator).sendKeys(addressLine1);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add zip code\n";
        driver.findElement(zipFieldLocator).sendKeys(zip);
        driver.findElement(warehouseInfoTitleLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addWarehouseBin (String binName) {
        totalResultMessage += "Select Bins tab\n";
        totalResultMessage += "Adding Bin:\n";
        driver.findElement(binsTabLocator).click();
        totalResultMessage += " - Open 'Add Bin' popup\n";
        driver.findElement(addWarehouseBinButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Bin' popup was not found");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addBinPopupTitleLocator));

        Assert.assertEquals(element.isDisplayed(), true, "Popup for the 'Add Bin' form is not displayed");

        totalResultMessage += " - Enter Bin name\n";
        driver.findElement(newBinNameLocator).sendKeys(binName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Select Bin type\n";
        driver.findElement(pickBinTypeLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += "Save new Bin\n";
        driver.findElement(saveNewBinBtnLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        Assert.assertEquals(driver.findElement(newBinNameInBinsGridLocator).getText(), binName, "Unexpected new created bin's name");
    }

    public void saveWarehouse() {
        totalResultMessage += "Save Warehouse\n";
        driver.findElement(saveContextualButton).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupBoxMessageLocator));

        totalResultMessage += "Confirm popup message\n";
        String currentMessage = driver.findElement(popupBoxMessageLocator).getText();
        ProgressBar.addProgressValue(progressVariable);
        Assert.assertEquals(currentMessage, saveWarehousePopupMessage, "Unexpected popup message");
        driver.findElement(popupOkBtnLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }
}