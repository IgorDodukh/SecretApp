package Pages;

import Settings.BrowserSettings;
import FXUI.ProgressBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by igor on 21.04.16. All rights reserved!
 */
public class AddProductPage extends BrowserSettings{
    private final WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By productSkuFieldLocator = By.xpath("//input[@name='productInfo_Sku']");
    private final By productNameFieldLocator = By.xpath("//input[@name='productInfo_Name']");
    private final By productWeightFieldLocator = By.xpath("//input[@name='productInfo_WeightLB']");
    private final By productShortDescriptionLocator = By.xpath("//textarea[@id='productInfo_Desc']");

    private final By productPricingTabLocator = By.xpath("//li[@id='pricingTab']");
    private final By productRetailPriceLocator = By.cssSelector("#retail-price");

    private final By productSalesChannelsTabLocator = By.xpath("//li[@id='salesChannelsTab']");
    private final By salesChannelNameFieldLocator = By.xpath("//*[@id='control_autoCompleteSingle_1']");
    private final By salesChannelTooltipLocator = By.xpath("//li[@class='ui-menu-item']");
    private final By salesChannelAddButtonLocator = By.xpath("//*[@id='salesChannelsTable']//img[2]");

    private final By productSuppliersTabLocator = By.xpath("//li[@id='suppliersTab']");
    private final By addSupplierButtonLocator = By.xpath("//*[@id='add_supplier']");
    private final By selectSupplierCheckboxLocator = By.xpath("//input[@name='chxBox_supplier']");
    private final By addSelectedSupplierButtonLocator = By.xpath("//*[@id='add_selected_supplier']");
    private final By selectAddedSupplierLocator = By.xpath("//*[@id='SuppliersItemsTable']/tbody/tr");
    private final By openAddedSupplierToEditButtonLocator = By.xpath("//*[@id='rowActions']/input[1]");
    private final By warehouseTabLocator = By.xpath("//*[@id='li_Warehouse']");
    private final By unitCostFieldLocator = By.xpath("//*[@id='txtPrice']");
    private final By unitCostAddButtonLocator = By.xpath("//*[@id='add_item']");
    private final By supplierSaveOkButton = By.xpath("//*[@id='btnOkButton1']");
    private final By saveAndCloseProductButtonLocator = By.xpath("//*[@id='btnSaveAndClose']/div[2]");

    private final By productMessageBoxLocator = By.xpath("//*[@id='productMessageBox']");
    private final By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

    private final By inventoryMenuItemLocator = By.xpath("//nav/ul/li[4]");
    private final By siteLogoIconLocator = By.xpath("//img[@id='logoIcon']");

    public void addProductInfo(String sku, String name, String weight, String shortDescription) {
        setTotalResultMessage(getTotalResultMessage() + "Adding product info:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add product SKU\n");
        driver.findElement(productSkuFieldLocator).sendKeys(sku);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add product Name\n");
        driver.findElement(productNameFieldLocator).sendKeys(name);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add product weight\n");
        driver.findElement(productWeightFieldLocator).sendKeys(weight);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add product Short Description\n");
        driver.findElement(productShortDescriptionLocator).sendKeys(shortDescription);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addProductPrices(String retailPrice) {
        setTotalResultMessage(getTotalResultMessage() + "Adding product prices:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select 'Pricing' tab\n");
        driver.findElement(productPricingTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add product Retail Price\n");
        driver.switchTo().frame("pricingIframe");

        driver.findElement(productRetailPriceLocator).sendKeys(retailPrice);
        ProgressBar.addProgressValue(progressVariable);

        driver.switchTo().defaultContent();
    }

    public void addProductSalesChannel(String channelName) {
        setTotalResultMessage(getTotalResultMessage() + "Adding Sales Channel:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select 'Sales Channels' tab\n");
        driver.findElement(productSalesChannelsTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Type 'Call Center' name to the field\n");
        driver.findElement(salesChannelNameFieldLocator).sendKeys(channelName);
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Call Center' tooltip was not found");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(salesChannelTooltipLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'Call Center' tooltip is not displayed");

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Call Center' tooltip\n");
        driver.findElement(salesChannelTooltipLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Plus' icon\n");
        driver.findElement(salesChannelAddButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addProductSupplier(String unitCost) throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Adding Supplier:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select 'Suppliers' tab\n");
        driver.findElement(productSuppliersTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Add Supplier tab was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addSupplierButtonLocator));

        Thread.sleep(1000);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Supplier' button\n");
        driver.findElement(addSupplierButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        Thread.sleep(2000);

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Supplier' popup was not found");
        WebElement element2 = wait2.until(ExpectedConditions.elementToBeClickable(selectSupplierCheckboxLocator));
        Assert.assertEquals(element2.isDisplayed(), true, "Popup for the 'Add Supplier' form is not displayed");

        setTotalResultMessage(getTotalResultMessage() + " - Select Supplier from the list\n");
        driver.findElement(selectSupplierCheckboxLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        setTotalResultMessage(getTotalResultMessage() + " - Add selected Supplier\n");
        driver.findElement(addSelectedSupplierButtonLocator).click();
        driver.findElement(selectAddedSupplierLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        setTotalResultMessage(getTotalResultMessage() + " - Open Supplier in edit mode\n");
        driver.findElement(openAddedSupplierToEditButtonLocator).click();
        driver.findElement(warehouseTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Supplier Unit Cost\n");
        Thread.sleep(500);
        driver.findElement(unitCostFieldLocator).sendKeys(unitCost);
        driver.findElement(unitCostAddButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Save Supplier changes\n");
        Thread.sleep(500);
        driver.findElement(supplierSaveOkButton).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void saveProduct() {
        setTotalResultMessage(getTotalResultMessage() + "Saving Product:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save and Close' button\n");
        driver.findElement(saveAndCloseProductButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productMessageBoxLocator));

        setTotalResultMessage(getTotalResultMessage() + " - Confirm success popup\n");
        driver.findElement(popupOkBtnLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openInventoryPage() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Product Inventory' page\n");
        Thread.sleep(2000);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Product grid was not opened");
        wait.until(ExpectedConditions.elementToBeClickable(siteLogoIconLocator));
        driver.findElement(inventoryMenuItemLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }
}
