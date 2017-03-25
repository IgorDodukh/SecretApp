package AppMain.Selenium.Pages;

import AppMain.Selenium.Settings.BrowserSettings;
import AppMain.FXUI.ProgressBar;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

/**
 * Created by Ihor on 7/16/2016. All rights reserved!
 */
public class OrderCreatingPage extends BrowserSettings {
    private final WebDriver driver;

    public OrderCreatingPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By addItemButtonLocator = By.xpath("//div[@class='input-group-btn']/button[@class='btn btn-default']");
    private final By quickAddFieldLocator = By.xpath("//div[@id='Add Products']//input");
    private final By addedItemSectionLocator = By.xpath("//tbody[@class='order-item row-with-input ng-scope']");
    private final By customerNameFieldLocator = By.xpath("//section[@id='Customer']//input[@id='firstName']");
    private final By customerLastNameFieldLocator = By.xpath("//input[@id='lastName']");
    private final By isExistingCustomerLocator = By.xpath("//div[@class='panel panel-default panel-success']");
    private final By selectCustomerButtonLocator = By.xpath("//div[@class='panel panel-default panel-success']//tr[1]/td[3]/a");
    private final By shippingMethodDropdownLocator = By.xpath("//div[@id='Shipping Address']//button//span[@class='caret']");
    private final By orderTotalValueLocator = By.xpath("//section[@id='Tallys and Buttons']/div/div[2]/strong");
    private final By placeOrderButtonLocator = By.xpath("//button[@ng-click='placeOrder()']");
    private final By orderSummaryTabLocator = By.xpath("//aside[@id='leftNav']//li[1]/a");
    private final By orderNumberLocator = By.xpath("//section[@id='titleSection']//h1");


    public void addOrderItems() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Add Order items\n");
        Thread.sleep(2000);
        driver.findElement(quickAddFieldLocator).sendKeys(orderedItems);
        driver.findElement(addItemButtonLocator).click();

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Item was not added");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addedItemSectionLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addCustomer() {
        setTotalResultMessage(getTotalResultMessage() + "Add Customer to the Order\n");
        driver.findElement(customerNameFieldLocator).sendKeys(orderedCustomerName);
        driver.findElement(customerNameFieldLocator).sendKeys(Keys.ENTER);
        driver.findElement(customerLastNameFieldLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Is Existing Customer' popup not appear");
        wait.until(ExpectedConditions.visibilityOfElementLocated(isExistingCustomerLocator));

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("Existing Customer 'Select' link is not clickable");
        wait2.until(ExpectedConditions.elementToBeClickable(selectCustomerButtonLocator));

        driver.findElement(selectCustomerButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void selectShippingMethod() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Select Shipping Method\n");
        Thread.sleep(1000);
        driver.findElement(shippingMethodDropdownLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        for (int i = 1; i <= 100; i++){
            By shippingMethodMoreButtonLocator = By.xpath(
                    "//div[@id='Shipping Address']//ul[@class='uib-dropdown-menu dropdown-menu']/li[" + i + "]/a");
            String dropdownValue = driver.findElement(shippingMethodMoreButtonLocator).getText();
            if(Objects.equals("", shippingMethod)){
                driver.findElement(shippingMethodMoreButtonLocator).click();
                break;
            }else if (Objects.equals(dropdownValue, shippingMethod)) {
                driver.findElement(shippingMethodMoreButtonLocator).click();
                break;
            }
        }
        ProgressBar.addProgressValue(progressVariable);
    }

    public void placeOrder() {
        setTotalResultMessage(getTotalResultMessage() + "Wait Order Total value\n");
        while (true){
            String totalValue = driver.findElement(orderTotalValueLocator).getText();
            if(!Objects.equals(totalValue, "--")){
                driver.findElement(placeOrderButtonLocator).click();
                break;
            }
        }
        ProgressBar.addProgressValue(progressVariable);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Edit Order' page is not appear");
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSummaryTabLocator));

        setTotalResultMessage(getTotalResultMessage() + "Click 'Place Order' button\n");
        orderNumber = driver.findElement(orderNumberLocator).getText();
        ProgressBar.addProgressValue(progressVariable);

        int numChar = orderNumber.indexOf("#");
        StringBuffer buffer = new StringBuffer(orderNumber);
        buffer.replace(0, numChar + 1, "");
        orderNumber = Objects.toString(buffer);
        ProgressBar.addProgressValue(progressVariable);
    }
}
