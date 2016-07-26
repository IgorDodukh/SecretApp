package Pages;

import Settings.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ihor on 6/29/2016.
 */
public class BinsPage extends BrowserSettings {
    private WebDriver driver;

    public BinsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addBinButtonLocator = By.xpath("//button[@id='add_bin']");
    private By addBinNameFieldLocator = By.xpath("//input[@id='txtBinName']");
    private By addBinPickBinDropdownLocator = By.xpath("//select[@id='selectBinType']/option[2]");
    private static By addBinWarehouseDropdownLocator = By.xpath("//div[@id='txtWarehouseNameNew']//ul[1]");
    private By addBinPriorityFieldLocator = By.xpath("//input[@id='binPriority']");
    private By addBinProductSKUFieldLocator = By.xpath("//input[@id='productInfo_Sku']");
    private By addBinProductSKUTooltipLocator = By.xpath("//li[@class='ui-menu-item']/a[1]");
    private By addBinLowLevelFieldLocator = By.xpath("//input[@id='productInfo_LowLevel']");

    private By saveAndCloseProductButtonLocator = By.xpath("//a[@id='btnSaveClose']");
    private By popupBoxMessageLocator = By.xpath("//*[@id='dydacomp_messagebox']");
    private By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");


// --Commented out by Inspection START (7/14/2016 10:08 PM):
//    public void clickAddBinButton() {
//        totalResultMessage += "Click 'Add Bin' button\n";
//        driver.findElement(addBinButtonLocator).click();
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Bin creating form was not loaded");
//        wait.until(ExpectedConditions.elementToBeClickable(addBinNameFieldLocator));
//    }
// --Commented out by Inspection STOP (7/14/2016 10:08 PM)

// --Commented out by Inspection START (7/14/2016 10:08 PM):
//    public void addBinInfo() {
//        totalResultMessage += "Adding Bin info:\n";
//        totalResultMessage += " - Add Bin name\n";
//        driver.findElement(addBinNameFieldLocator).sendKeys(binName);
//
//        totalResultMessage += " - Select Bin type\n";
//        driver.findElement(addBinPickBinDropdownLocator).click();
//
//        totalResultMessage += " - Add Bin priority\n";
//        driver.findElement(addBinPriorityFieldLocator).sendKeys(binPriority);
//
//        totalResultMessage += " - Select first Warehouse\n";
//        driver.findElement(addBinWarehouseDropdownLocator).click();
//        final String productWHName = driver.findElement(addBinWarehouseDropdownLocator).getText();
//
//        totalResultMessage += " - Add Product SKU\n";
//        driver.findElement(addBinProductSKUFieldLocator).sendKeys(productSku);
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Product was not found");
//        wait.until(ExpectedConditions.elementToBeClickable(addBinProductSKUTooltipLocator));
//        driver.findElement(addBinProductSKUTooltipLocator).click();
//
//        totalResultMessage += " - Add Bin low level\n";
//        driver.findElement(addBinLowLevelFieldLocator).sendKeys("0");
//    }
// --Commented out by Inspection STOP (7/14/2016 10:08 PM)

// --Commented out by Inspection START (7/14/2016 10:08 PM):
//    public void saveBin() {
//        totalResultMessage += "Saving new Bin:\n";
//        totalResultMessage += " - Click 'Save and Close' button\n";
//        driver.findElement(saveAndCloseProductButtonLocator).click();
//
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(popupBoxMessageLocator));
//
//        totalResultMessage += " - Confirm success popup\n";
//        driver.findElement(popupOkBtnLocator).click();
//    }
// --Commented out by Inspection STOP (7/14/2016 10:08 PM)
}
