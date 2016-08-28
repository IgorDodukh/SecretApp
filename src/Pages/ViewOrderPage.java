package Pages;

import Settings.BrowserSettings;
import FXUI.ProgressBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

/**
 * Created by Ihor on 7/11/2016. All rights reserved!
 */
public class ViewOrderPage extends BrowserSettings {
    private final WebDriver driver;

    public ViewOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstOrderFromTheListLocator = By.xpath("//tbody//tr[2]/td/a");
    private final By orderSummaryTabLocator = By.xpath("//aside[@id='leftNav']//li[1]/a");
    private final By customerNameLocator = By.xpath("//tbody//tr[2]/td[4]");
    private final By shippingDetailsTabLocator = By.xpath("//aside[@id='leftNav']//li[4]/a");

    private final By linkToOrdersGridLocator = By.xpath("//section[@id='titleSection']//li[1]");
    private final By addOrderButtonLocator = By.xpath("//a[@href='/web/Order/OrderCreating']");
    private final By addItemButtonLocator = By.xpath("//div[@class='input-group-btn']/button[@class='btn btn-default']");

    private final By orderedSkuListLocator = By.xpath("//div[@id='ship_items_list']/div//span[1]");

    private final By shippingMethodLocator = By.xpath("//section[@class='columns shipping_details shipment ng-scope']//div/p[2]/span[1]");

    public void openViewOrderPage() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open 'View Order' page\n");
        Thread.sleep(2000);
        orderedCustomerName = driver.findElement(customerNameLocator).getText();

        int nameLength = orderedCustomerName.length();
        int nameSpace = orderedCustomerName.indexOf(" ");
        StringBuffer buffer = new StringBuffer(orderedCustomerName);
        buffer.replace(nameSpace, nameLength, "");
        orderedCustomerName = Objects.toString(buffer);
        System.out.println(orderedCustomerName);
        ProgressBar.addProgressValue(progressVariable);

        driver.findElement(firstOrderFromTheListLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'View Order' page was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(orderSummaryTabLocator));

        shippingMethod = driver.findElement(shippingMethodLocator).getText();
        ProgressBar.addProgressValue(progressVariable);

        System.out.println("Shipping Method " + shippingMethod);
    }

    public void getOrderItemsInfo() {
        setTotalResultMessage(getTotalResultMessage() + "Get Ordered Items Info\n");
        driver.findElement(shippingDetailsTabLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Shipping Details' tab is not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(orderSummaryTabLocator));
        orderedItems = driver.findElement(orderedSkuListLocator).getText();

        List<WebElement> totalLinks = driver.findElements(orderedSkuListLocator);
        int totalLinkSize = totalLinks.size();
        ProgressBar.addProgressValue(progressVariable);
        System.out.println("Total Links size : " + totalLinkSize);
        System.out.println("Total Links values : " + orderedItems);
    }

    public void backToOrdersGrid() {
        setTotalResultMessage(getTotalResultMessage() + "Back To Orders Grid\n");
        driver.findElement(linkToOrdersGridLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Orders grid is not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addOrderButtonLocator));
        ProgressBar.addProgressValue(progressVariable);
    }

    public void openOrderCreatingForm () throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Open Order creating form\n");
        Thread.sleep(2000);
        driver.findElement(addOrderButtonLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Order creating form is not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(addItemButtonLocator));
        ProgressBar.addProgressValue(progressVariable);
    }
}
