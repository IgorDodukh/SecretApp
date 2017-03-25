package AppMain.Selenium.Pages;

import AppMain.FXUI.ProgressBar;
import AppMain.Selenium.Settings.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by igor on 17.04.16. All rights reserved!
 */
public class MainPage extends BrowserSettings {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By customersMenuButtonLocator = By.xpath(".//*[@id='HUD']/nav[2]/div/ul/li[1]/a");
    private final By addCustomerMenuButtonLocator = By.xpath("//li/a[@href='/web/Customer/CreateCustomerView']");

    private final By productsMenuButtonLocator = By.xpath("//*[@class='nav navbar-nav']/li[2]");
    private final By addProductMenuButtonLocator = By.xpath("//li/a[@href='/web/Product/ProductCreate']");

    private final By suppliersMenuButtonLocator = By.xpath("//li/a[@href='/web/Product/SearchSupplier']");
    private final By addSupplierButtonLocator = By.xpath("//button[@id='add_supplier']");
    private final By supplierAccountNumberFieldLocator = By.xpath("//input[@id='acct_no']");

    private final By customerInfoTabLocator = By.xpath("//h2[@tooltipid='Customer_CustomerInfo']");

    private final By headerMenuLocator = By.xpath(".//*[@id='HUD']/nav[2]/div/ul/li[4]/a");
    private final By searchWarehouseButtonLocator = By.xpath("//a[@href='/web/Inventory/SearchWarehouse']");
    private final By addWarehouseButtonLocator = By.xpath("//button[@tooltipid='addWarehouse']");
    private final By addPageBreadcrumpLocator = By.xpath("//*[@id='breadCrumb']/ul/li[2]/h1");

    private final By addCustomerPageTitleLocator = By.xpath("//*[@class='tabContainer columns']//h2/strong");

    private final By setupButtonLocator = By.xpath("//li[@id='Help'][2]");
    private final By settingsButtonLocator = By.xpath("//a[@href='/web/Configuration/ConfigurationManagement']");
    private final By shippingMethodsButtonLocator = By.xpath("//a[@href='/web/Configuration/ShippingMethods']");
    private final By basicSettingTitleLocator = By.xpath("//section[@id='basicSettingTabpage']/div[1]");
    private final By siteLogoIconLocator = By.xpath("//img[@src='/web/Content/Images/nextgen_logo-white.png']");

    private final By addShippingMethodButtonLocator = By.xpath("//button[@id='add_shippingMethod']");

    private final By orderProcessingTabLocator = By.xpath("//aside[@id='leftNav']//li[4]");

    private final By ordersMenuButtonLocator = By.xpath("//*[@class='nav navbar-nav']/li[3]");
    private final By viewAllOrdersMenuButtonLocator = By.xpath("//li/a[@href='/web/Order/SearchOrderAdvance']");
    private final By allOrdersTabButtonLocator = By.xpath("//section[@id='tabsSection']/button[6]");

    private final By syncButtonLocator = By.xpath("//*[@class='nav navbar-nav']/li[7]");

    private final By manageMerchantButtonLocator = By.xpath("//a[@href='/web/Merchant/SearchMerchant']");
    private final By addMerchantButtonLocator = By.xpath("//button[@id='createMerchant']");

    private final By manageUsersButtonLocator = By.xpath("//a[@href='/web/UserPermissions/Search']");


    public void openAddCustomerPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Add Customer' page\n");
        driver.findElement(customersMenuButtonLocator).click();
        driver.findElement(addCustomerMenuButtonLocator).click();
        Assert.assertEquals(driver.findElement(customerInfoTabLocator).isDisplayed(), true, "Customer creating page is not loaded");
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openAddWarehousePage () {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Add Warehouse' page\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Inventory' menu item\n");
        driver.findElement(headerMenuLocator).click();
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Warehouse' menu item\n");
        driver.findElement(searchWarehouseButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Warehouse' button is not clickable");
        wait.until(ExpectedConditions.elementToBeClickable(addWarehouseButtonLocator));
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Warehouse' button\n");
        driver.findElement(addWarehouseButtonLocator).click();
        Assert.assertEquals(driver.findElement(addPageBreadcrumpLocator).getText(), "Add", "Warehouse creating page is not loaded");
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openAddProductPage() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Add Product' page\n");
        driver.findElement(productsMenuButtonLocator).click();
        driver.findElement(addProductMenuButtonLocator).click();
        Assert.assertEquals(driver.findElement(addPageBreadcrumpLocator).getText(), "Add", "Customer creating page is not loaded");
        Assert.assertEquals(driver.findElement(addCustomerPageTitleLocator).getText(), "Product Details", "Unexpected page title");
        ProgressBar.addProgressValue(progressVariable);
        Thread.sleep(5000);
    }

    public void openSetUpPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'AppMain.Selenium.Settings' page\n");
        driver.findElement(setupButtonLocator).click();
        driver.findElement(settingsButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Basic AppMain.Selenium.Settings' page popup was not found");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(basicSettingTitleLocator));
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(orderProcessingTabLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'Basic AppMain.Selenium.Settings' page title was not found");
        Assert.assertEquals(element2.isDisplayed(), true, "'Basic AppMain.Selenium.Settings' page was not loaded");
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openMainPage() {
        setTotalResultMessage(getTotalResultMessage() + "Navigate to Main Page\n");
        driver.findElement(siteLogoIconLocator).click();
        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("Main Page is loaded for a long time");
        wait2.until(ExpectedConditions.elementToBeClickable(siteLogoIconLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openShippingMethodsPage() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Shipping Methods' page\n");
        driver.findElement(setupButtonLocator).click();
        driver.findElement(shippingMethodsButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        Thread.sleep(2000);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Shipping Methods' page popup was not loaded");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addShippingMethodButtonLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'Shipping Methods' page title was not found");
    }

    public void openSuppliersPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Suppliers' page\n");
        driver.findElement(productsMenuButtonLocator).click();
        driver.findElement(suppliersMenuButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openAddSupplierPage() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Add Supplier' page\n");
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Suppliers grid was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addSupplierButtonLocator));
        Thread.sleep(1000);
        ProgressBar.addProgressValue(progressVariable);
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Supplier' button\n");
        driver.findElement(addSupplierButtonLocator).click();
        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'Add Supplier' page popup was not loaded");
        WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(supplierAccountNumberFieldLocator));
        Assert.assertEquals(element.isDisplayed(), true, "'Shipping Methods' page title was not found");
        ProgressBar.addProgressValue(progressVariable);
    }

// --Commented out by Inspection START (7/14/2016 10:10 PM):
//    public void openBinsPage() {
//        totalResultMessage += "Open 'Bins' page\n";
//        driver.findElement(inventoryMenuButtonLocator).click();
//        driver.findElement(binsButtonLocator).click();
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Bins' page popup was not loaded");
//        wait.until(ExpectedConditions.elementToBeClickable(addBinButtonLocator));
//    }
// --Commented out by Inspection STOP (7/14/2016 10:10 PM)

// --Commented out by Inspection START (7/14/2016 10:10 PM):
//    public void openInventoryPage() {
//        totalResultMessage += "Open 'Product Inventory' page\n";
//        driver.findElement(inventoryMenuButtonLocator).click();
//        driver.findElement(productInventoryButtonLocator).click();
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Product Inventory' page popup was not loaded");
//        wait.until(ExpectedConditions.elementToBeClickable(productInventoryFilterByFieldLocator));
//    }
// --Commented out by Inspection STOP (7/14/2016 10:10 PM)

    public void openOrdersGrid() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Orders' grid\n");
        driver.findElement(ordersMenuButtonLocator).click();
        driver.findElement(viewAllOrdersMenuButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Orders' grid was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(siteLogoIconLocator));
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + "Select 'All' tab\n");
        Thread.sleep(2000);
        driver.findElement(allOrdersTabButtonLocator).click();
        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("'All Orders' grid was not loaded");
        wait2.until(ExpectedConditions.elementToBeClickable(allOrdersTabButtonLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openSyncPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Sync' page\n");
        driver.findElement(syncButtonLocator).click();

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Sync' page was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(siteLogoIconLocator));
    }

    public void openMerchantsPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Manage Merchant' page\n");
        driver.findElement(setupButtonLocator).click();
        driver.findElement(manageMerchantButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Manage Merchant' page was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addMerchantButtonLocator));
    }

    public void openUsersPermissionsPage() {
        setTotalResultMessage(getTotalResultMessage() + "Open 'Manage Users' page\n");
        driver.findElement(setupButtonLocator).click();
        driver.findElement(manageUsersButtonLocator).click();
    }
}
