package AppMain.Selenium.Pages;

import AppMain.FXUI.Controller;
import AppMain.Selenium.Settings.BrowserSettings;
import AppMain.FXUI.ProgressBar;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ihor on 7/20/2016. All rights reserved!
 */
public class SyncPage extends BrowserSettings{
    private final WebDriver driver;

    public SyncPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By syncFilterByFieldLocator = By.xpath("//div[@class='dataTables_filter']//input");
    private final By syncChannelInGridLocator = By.xpath("//table/tbody//tr[1]/td[2]");
    private final By syncViewChannelButtonLocator = By.xpath("//table/tbody//tr[1]//input[@name='view_salesChannel']");
    private final By channelOverviewTabLocator = By.xpath("//aside[@id='leftNav']//ul[1]/li/a");
    private final By channelIDLocator = By.xpath("//input[@id='txtChannelId']");

    public void openChannel() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "Search needed Channel\n");

        String channelName = Controller.magentoIndexName.replace("qatestlab", "");
        driver.findElement(syncFilterByFieldLocator).sendKeys(channelName);
        driver.findElement(syncFilterByFieldLocator).sendKeys(Keys.ENTER);
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("'Sync' page was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(syncFilterByFieldLocator));

        setTotalResultMessage(getTotalResultMessage() + "Open found Channel\n");
        Thread.sleep(1000);
        driver.findElement(syncChannelInGridLocator).click();
        driver.findElement(syncViewChannelButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("'View Channel' page was not loaded");
        wait3.until(ExpectedConditions.elementToBeClickable(channelOverviewTabLocator));
    }

    public void getChannelID() {
        setTotalResultMessage(getTotalResultMessage() + "Remember Channel ID\n");
        magentoChannelID = driver.findElement(channelIDLocator).getAttribute("value");
        ProgressBar.addProgressValue(progressVariable);
    }
}
