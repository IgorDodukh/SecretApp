package Pages;

import Settings.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ihor on 8/18/2016. All rights reserved!
 */
public class CorporateMailBox extends BrowserSettings {
    private final WebDriver driver;

    public CorporateMailBox(WebDriver driver) {
        this.driver = driver;
    }

    private final By mailboxLoginFieldLocator = By.xpath("//input[@id='Email']");
    private final By mailboxLoginButtonLocator = By.xpath("//input[@id='Next']");
    private final By mailboxPasswordFieldLocator = By.xpath("//input[@id='Passwd']");
    private final By signInButtonLocator = By.xpath("//input[@id='SignIn']");
    private final By searchFieldLocator = By.xpath("//input[@id='gbqfq']");

    private final By firstFoundEmailLocator = By.xpath("//div[@class='UI']//tbody/tr[1]");
    private final By linkToFSFromEmailLocator = By.xpath("//div[@class='Bk']//div//a[2]");

    public void openMailBox() {
        driver.get(mailboxUrl);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait.until(ExpectedConditions.elementToBeClickable(mailboxLoginFieldLocator));

        driver.findElement(mailboxLoginFieldLocator).sendKeys(mailboxLogin);
        driver.findElement(mailboxLoginButtonLocator).click();

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait2.until(ExpectedConditions.elementToBeClickable(mailboxPasswordFieldLocator));

        driver.findElement(mailboxPasswordFieldLocator).sendKeys(mailboxPassword);
        driver.findElement(signInButtonLocator).click();

        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait3.until(ExpectedConditions.elementToBeClickable(searchFieldLocator));
    }

    public void openInvitationEmail() throws InterruptedException {
        driver.findElement(searchFieldLocator).sendKeys("/web/Merchant/SetMerchantPassword");
        driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        driver.findElement(firstFoundEmailLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait.until(ExpectedConditions.elementToBeClickable(linkToFSFromEmailLocator));

        driver.findElement(linkToFSFromEmailLocator).click();
    }


}
