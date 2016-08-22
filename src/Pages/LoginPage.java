package Pages;

import Settings.BrowserSettings;
import FXUI.ProgressBar;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by igor on 17.04.16. All rights reserved!
 */
public class LoginPage extends BrowserSettings{
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailInputLocator = By.xpath("//input[@name='UserName']");
    private final By passwordInputLocator = By.xpath("//input[@name='UserPassword']");
    private final By loginButtonLocator = By.xpath("//input[@value='Login']");
    private final By msgBox = By.xpath("//div[@id='dydacomp_messagebox']");
    private final By msgBoxOkButton = By.xpath("//button[@autotest-id='btnOK']");
    private final By siteLogoIconLocator = By.xpath("//img[@id='logoIcon']");

    public void loginMerchant(String email, String pass) throws InterruptedException {
        totalResultMessage += "Login user to FS:\n";
        totalResultMessage += " - Enter Login\n";
        WebElement login = driver.findElement(emailInputLocator);
        login.clear();
        login.sendKeys(email);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Enter Password\n";
        WebElement password = driver.findElement(passwordInputLocator);
        password.clear();
        password.sendKeys(pass);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Click 'Login' button\n";
        driver.findElement(loginButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        Thread.sleep(1000);
            try {
                driver.findElement(msgBox);
                totalResultMessage += "'User is already logged' popup appears\n";
                driver.findElement(msgBoxOkButton).click();
                totalResultMessage += "'User is already logged' popup confirmed\n";
            } catch (NoSuchElementException e) {
                totalResultMessage += "User is logging now\n";
            }
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("User was not loaded");
        wait.until(ExpectedConditions.elementToBeClickable(siteLogoIconLocator));
        ProgressBar.addProgressValue(progressVariable);
    }
}
