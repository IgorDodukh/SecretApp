package AppMain.Selenium.Pages;

import AppMain.Selenium.Settings.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Ihor on 8/18/2016. All rights reserved!
 */
public class CorporateMailBox extends BrowserSettings {
    private final WebDriver driver;

    public CorporateMailBox(WebDriver driver) {
        this.driver = driver;
    }

    private final By mailboxLoginFieldLocator = By.xpath("//input[@id='Email']");
    private final By mailboxPasswordFieldLocator = By.xpath("//input[@id='Passwd']");
    private final By searchFieldLocator = By.xpath("//input[@id='gbqfq']");

    private final By firstFoundEmailLocator = By.xpath("//div[@role='main']//tbody/tr[1]/td[5]");
    private final By linkToFSFromEmailLocator = By.xpath("//div[@class='Bk']//div//a[2]");

    private final By newMerchantPasswordLocator = By.xpath("input[id=password]");
    private final By newMerchantConfirmPasswordLocator = By.xpath("input[id=confirmPWD]");
    private final By activateMerchantButtonLocator = By.xpath("//a[@id='setMerchantBtn']/div[@class='text']");

    public void openMailBox() {
        setTotalResultMessage(getTotalResultMessage() + " Open mail box\n");
        setTotalResultMessage(getTotalResultMessage() + "  - Navigate to mail box\n");
        driver.get(mailboxUrl);
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait.until(ExpectedConditions.elementToBeClickable(mailboxLoginFieldLocator));



        setTotalResultMessage(getTotalResultMessage() + "  - Fill mail box credentials\n");
        driver.findElement(mailboxLoginFieldLocator).sendKeys(mailboxLogin);
        driver.findElement(mailboxLoginFieldLocator).sendKeys(Keys.ENTER);

        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait2.until(ExpectedConditions.elementToBeClickable(mailboxPasswordFieldLocator));

        driver.findElement(mailboxPasswordFieldLocator).sendKeys(mailboxPassword);
        driver.findElement(mailboxPasswordFieldLocator).sendKeys(Keys.ENTER);

        final Wait<WebDriver> wait3 = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait3.until(ExpectedConditions.elementToBeClickable(searchFieldLocator));
    }

    public void openInvitationEmail() throws InterruptedException {
        setTotalResultMessage(getTotalResultMessage() + "  - Search invitation email\n");

        driver.findElement(searchFieldLocator).sendKeys("/web/Merchant/SetMerchantPassword");
        driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        setTotalResultMessage(getTotalResultMessage() + "  - Open invitation email\n");
        driver.findElement(firstFoundEmailLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("");
        wait.until(ExpectedConditions.elementToBeClickable(linkToFSFromEmailLocator));

        setTotalResultMessage(getTotalResultMessage() + "  - Navigate by confirmation link\n");
        driver.findElement(linkToFSFromEmailLocator).click();
    }

    public void setNewMerchantPassword() {

        List<WebElement> allFormChildElements = driver.findElements(By.xpath("//form[@id='updatePasswordForm']"));
        for(WebElement item : allFormChildElements ) {
            if (item.getTagName().equals("input")) {
                switch (item.getAttribute("name")) {
                    case "password":
                        item.sendKeys("78qa22!#");
                        break;
                }
            } else System.out.println("first field not found");

            if (item.getTagName().equals("input")) {
                switch (item.getAttribute("name")) {
                    case "confirmPWD":
                        item.sendKeys("78qa22!#");
                        break;
                }
            } else System.out.println("second field not found");
        }
        List<WebElement> allFormChildElements2 = driver.findElements(By.xpath("//form[@id='bottomActionBar']"));
        for(WebElement item : allFormChildElements2 ) {

            if (item.getTagName().equals("div")) {
                switch (item.getAttribute("class")) {
                    case "text":
                        item.click();
                        break;
                }
            } else System.out.println("button not found");

        }
//        driver.findElement(newMerchantPasswordLocator).sendKeys("78qa22!#");
//        driver.findElement(newMerchantConfirmPasswordLocator).sendKeys("78qa22!#");
//        driver.findElement(activateMerchantButtonLocator).click();

    }
}
