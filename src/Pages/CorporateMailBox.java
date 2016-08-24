package Pages;

import Settings.BrowserSettings;
import org.openqa.selenium.By;
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

    private final By mailboxLoginLocator = By.xpath("//input[@id='Email']");

    public void openMailBox() {
        driver.get(mailboxUrl);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailboxLoginLocator));
    }
}