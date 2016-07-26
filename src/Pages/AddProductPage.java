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
 * Created by igor on 21.04.16.
 */
public class AddProductPage extends BrowserSettings{
    private WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productDetailsTabTitleLocator = By.xpath("//section[@class='tabContainer columns']/h2/strong");
    private By productSkuFieldLocator = By.xpath("//input[@name='productInfo_Sku']");
    private By productNameFieldLocator = By.xpath("//input[@name='productInfo_Name']");
    private By productWeightFieldLocator = By.xpath("//input[@name='productInfo_WeightLB']");
    private By productShortDescriptionLocator = By.xpath("//textarea[@id='productInfo_Desc']");

    private By productPricingTabLocator = By.xpath("//li[@id='pricingTab']");
    private By productPricingTabTitleLocator = By.xpath("//*[@id='pricingModule']//h4/strong");
    private By productRetailPriceLocator = By.cssSelector("#retail-price");

    private By salesChannelsTabTitleLocator = By.xpath("//*[@id='subTitle']/h2");
    private By productSalesChannelsTabLocator = By.xpath("//li[@id='salesChannelsTab']");
    private By salesChannelNameFieldLocator = By.xpath("//*[@id='control_autoCompleteSingle_1']");
    private By salesChannelTooltipLocator = By.xpath("//li[@class='ui-menu-item']");
    private By salesChannelAddButtonLocator = By.xpath("//*[@id='salesChannelsTable']//img[2]");

    private By suppliersTabTitleLocator = By.xpath("//*[@id='subTitle']/h2/strong");
    private By productSuppliersTabLocator = By.xpath("//li[@id='suppliersTab']");
    private By addSupplierButtonLocator = By.xpath("//*[@id='add_supplier']");
    private By addSupplierPopupLocator = By.xpath("//*[@id='dydacomp_messagebox']");
    private By selectSupplierCheckboxLocator = By.xpath("//input[@name='chxBox_supplier']");
    private By addSelectedSupplierButtonLocator = By.xpath("//*[@id='add_selected_supplier']");
    private By selectAddedSupplierLocator = By.xpath("//*[@id='SuppliersItemsTable']/tbody/tr");
    private By openAddedSupplierToEditButtonLocator = By.xpath("//*[@id='rowActions']/input[1]");
    private By warehouseTabLocator = By.xpath("//*[@id='li_Warehouse']");
    private By unitCostFieldLocator = By.xpath("//*[@id='txtPrice']");
    private By unitCostAddButtonLocator = By.xpath("//*[@id='add_item']");
    private By supplierSaveOkButton = By.xpath("//*[@id='btnOkButton1']");
    private By viewCostLinkLocator = By.xpath("//*[@id='SuppliersItemsTable']/tbody/tr/td[3]/a");
    private By saveAndCloseProductButtonLocator = By.xpath("//*[@id='btnSaveAndClose']/div[2]");

    private By productMessageBoxLocator = By.xpath("//*[@id='productMessageBox']");
    private By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

    private By inventoryMenuItemLocator = By.xpath("//nav/ul/li[4]");
    private By productInventoryMenuItemLocator = By.xpath("//nav/ul/li[4]/ul/li[4]");
    private By productInventoryFilterByFieldLocator = By.xpath("//label/input");
    private By siteLogoIconLocator = By.xpath("//img[@id='logoIcon']");




    private By saveProductPopupMessage = By.xpath("(//div[@id='productMessageBox']//*)[1]");
    private By filterProductsFieldLocator = By.xpath("//*[@id='searchProductResult_filter']/label/input");
    private By productSkuInTheGridLocator = By.xpath("((//*[@id='searchProductResult'])//tbody/tr/*)[2]");
    private By productNameInTheGridLocator = By.xpath("((//*[@id='searchProductResult'])//tbody/tr/*)[3]");
    private By productRetailPriceInTheGridLocator = By.xpath("((//*[@id='searchProductResult'])//tbody/tr/*)[4]");

    public void addProductInfo(String sku, String name, String weight, String shortDescription) {
        totalResultMessage += "Adding product info:\n";
        totalResultMessage += " - Add product SKU\n";
        driver.findElement(productSkuFieldLocator).sendKeys(sku);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add product Name\n";
        driver.findElement(productNameFieldLocator).sendKeys(name);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add product weight\n";
        driver.findElement(productWeightFieldLocator).sendKeys(weight);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add product Short Description\n";
        driver.findElement(productShortDescriptionLocator).sendKeys(shortDescription);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addProductPrices(String retailPrice) {
        totalResultMessage += "Adding product prices:\n";
        totalResultMessage += " - Select 'Pricing' tab\n";
        driver.findElement(productPricingTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add product Retail Price\n";
        driver.switchTo().frame("pricingIframe");

        driver.findElement(productRetailPriceLocator).sendKeys(retailPrice);
        ProgressBar.addProgressValue(progressVariable);

        driver.switchTo().defaultContent();
    }

    public void addProductSalesChannel(String channelName) {
        totalResultMessage += "Adding Sales Channel:\n";
        totalResultMessage += " - Select 'Sales Channels' tab\n";
        driver.findElement(productSalesChannelsTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Type 'Call Center' name to the field\n";
        driver.findElement(salesChannelNameFieldLocator).sendKeys(channelName);
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Call Center' tooltip was not found");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(salesChannelTooltipLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'Call Center' tooltip is not displayed");

        totalResultMessage += " - Click 'Call Center' tooltip\n";
        driver.findElement(salesChannelTooltipLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Click 'Plus' icon\n";
        driver.findElement(salesChannelAddButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addProductSupplier(String unitCost) throws InterruptedException {
        totalResultMessage += "Adding Supplier:\n";
        totalResultMessage += " - Select 'Suppliers' tab\n";
        driver.findElement(productSuppliersTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Add Supplier tab was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addSupplierButtonLocator));

        Thread.sleep(1000);

        totalResultMessage += " - Click 'Add Supplier' button\n";
        driver.findElement(addSupplierButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        Thread.sleep(2000);

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Supplier' popup was not found");
        WebElement element2 = wait2.until(ExpectedConditions.elementToBeClickable(selectSupplierCheckboxLocator));
        Assert.assertEquals(element2.isDisplayed(), true, "Popup for the 'Add Supplier' form is not displayed");

        totalResultMessage += " - Select Supplier from the list\n";
        driver.findElement(selectSupplierCheckboxLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        totalResultMessage += " - Add selected Supplier\n";
        driver.findElement(addSelectedSupplierButtonLocator).click();
        driver.findElement(selectAddedSupplierLocator).click();
        ProgressBar.addProgressValue(progressVariable);
        totalResultMessage += " - Open Supplier in edit mode\n";
        driver.findElement(openAddedSupplierToEditButtonLocator).click();
        driver.findElement(warehouseTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Supplier Unit Cost\n";
        Thread.sleep(500);
        driver.findElement(unitCostFieldLocator).sendKeys(unitCost);
        driver.findElement(unitCostAddButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Save Supplier changes\n";
        Thread.sleep(500);
        driver.findElement(supplierSaveOkButton).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void saveProduct() {
        totalResultMessage += "Saving Product:\n";
        totalResultMessage += " - Click 'Save and Close' button\n";
        driver.findElement(saveAndCloseProductButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productMessageBoxLocator));

        totalResultMessage += " - Confirm success popup\n";
        driver.findElement(popupOkBtnLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openInventoryPage() throws InterruptedException {
        totalResultMessage += "Open 'Product Inventory' page\n";
        Thread.sleep(2000);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Product grid was not opened");
        wait.until(ExpectedConditions.elementToBeClickable(siteLogoIconLocator));
        driver.findElement(inventoryMenuItemLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }
}
