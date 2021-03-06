package Pages;

import Settings.BrowserSettings;
import FXUI.ProgressBar;
import Settings.GenerateRandomData;
import Settings.GetPropertyValues;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Ihor on 6/29/2016. All rights reserved!
 */
public class InventoryPage extends BrowserSettings {
    private final WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By productInventoryFilterByFieldLocator = By.xpath("//label/input");
    private final By firstProductLocator = By.xpath("//tr[1]/td[3]");
    private final By firstProductSKULocator = By.xpath("//tr[1]/td[2]");
    private final By firstProductAddButtonLocator = By.xpath("//tr[1]//button[@id='create_Inventory']");

    private final By lotNumberFieldLocator = By.xpath("//input[@id='txtLotNum']");
    private final By unitCostFieldLocator = By.xpath("//input[@id='txtUnitCost']");
    private final By binDropdownAddNewBinButtonLocator = By.xpath("//select[@id='bin_1']//option[2]");

    private final By addBinNameFieldLocator = By.xpath("//input[@id='txtBinName']");
    private final By addBinPickBinDropdownLocator = By.xpath("//select[@id='selectBinType']/option[2]");
    private final By addBinPriorityFieldLocator = By.xpath("//input[@id='binPriority']");
    private final By addBinQuantityFieldLocator = By.xpath("//input[@id='quantity_1']");
    private final By addBinQuantityValueLocator = By.xpath("//form/div[6]/*");
    private final By addBinNotesFieldLocator = By.xpath("//textarea[@id='txtComment']");

    private final By saveBinButtonLocator = By.xpath("//input[@id='save_BinAddClone']");

    private final By saveAndCloseProductButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private final By popupBoxMessageLocator = By.xpath("//*[@id='dydacomp_messagebox']");

    public static String createdBin = "";

    public void openAddInventoryForm(String productSku) {
        setTotalResultMessage(getTotalResultMessage() + "Search created Product\n");
        final Wait<WebDriver> wait = new WebDriverWait(driver, getTimeoutVariable()).withMessage("'Product Inventory' page popup was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(productInventoryFilterByFieldLocator));
        ProgressBar.addProgressValue(10);
        driver.findElement(productInventoryFilterByFieldLocator).sendKeys(productSku);
        driver.findElement(productInventoryFilterByFieldLocator).sendKeys(Keys.ENTER);
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, getTimeoutVariable()).withMessage("Product search was not finished");
        wait2.until(ExpectedConditions.elementToBeClickable(firstProductSKULocator));

        Assert.assertEquals(driver.findElement(firstProductSKULocator).getText(), productSku, "Found Product has not expected SKU");

        setTotalResultMessage(getTotalResultMessage() + "Select first found Product\n");
        driver.findElement(firstProductLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + "Click 'Add' button\n");
        driver.findElement(firstProductAddButtonLocator).click();
        final Wait<WebDriver> wait3 = new WebDriverWait(driver, getTimeoutVariable()).withMessage("Inventory adding form was not loaded");
        wait3.until(ExpectedConditions.elementToBeClickable(lotNumberFieldLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addInventoryInfo() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Adding Inventory info:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add Lot number\n");
        driver.findElement(lotNumberFieldLocator).sendKeys(inventoryLotNumber);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add unit cost\n");
        driver.findElement(unitCostFieldLocator).sendKeys(inventoryUnitCost);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Open 'Add Bin' form\n");
        driver.findElement(binDropdownAddNewBinButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, getTimeoutVariable()).withMessage("Bin creating form was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addBinNameFieldLocator));
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + "Adding Bin info:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add Bin name\n");
        driver.findElement(addBinNameFieldLocator).sendKeys(createdBin = GetPropertyValues.binName + new GenerateRandomData().generateRandomNumber(getRandomValueLength()));
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Select Bin type\n");
        driver.findElement(addBinPickBinDropdownLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Bin priority\n");
        driver.findElement(addBinPriorityFieldLocator).sendKeys(binPriority);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Save Bin\n");
        driver.findElement(saveBinButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        Thread.sleep(2000);
        final Wait<WebDriver> wait2 = new WebDriverWait(driver, getTimeoutVariable()).withMessage("Bin creating popup was not hidden");
        wait2.until(ExpectedConditions.elementToBeClickable(lotNumberFieldLocator));

        setTotalResultMessage(getTotalResultMessage() + " - Add qty value\n");
        driver.findElement(addBinQuantityFieldLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        Thread.sleep(2000);
        driver.findElement(addBinQuantityFieldLocator).sendKeys(inventoryQty);
        driver.findElement(addBinNotesFieldLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        String qtyValue = driver.findElement(addBinQuantityValueLocator).getText();
        Assert.assertEquals(qtyValue, inventoryQty + ".00", "Incorrect qty value is displayed");

        setTotalResultMessage(getTotalResultMessage() + " - Add Notes\n");
        driver.findElement(addBinNotesFieldLocator).sendKeys(inventoryNotes);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void saveInventory() {
        setTotalResultMessage(getTotalResultMessage() + "Saving inventory:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save and Close' button\n");
        driver.findElement(saveAndCloseProductButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, getTimeoutVariable()).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupBoxMessageLocator));

        ProgressBar.addProgressValue(10);

//        totalResultMessage += " - Confirm success popup\n";
//        driver.findElement(popupOkBtnLocator).click();
    }

}
