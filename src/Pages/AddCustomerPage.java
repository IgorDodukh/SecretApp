package Pages;

import FXUI.ProgressBar;
import Settings.BrowserSettings;
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
public class AddCustomerPage extends BrowserSettings {
    private final WebDriver driver;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstNameFieldLocator = By.xpath("//input[@id='customer_first_name']");
    private final By lastNameFieldLocator = By.xpath("//input[@id='customer_last_name']");
    private final By emailFieldLocator = By.xpath("//input[@id='customer_email']");
    private final By phoneFieldLocator = By.xpath("//input[@id='customer_phone']");

    private final By billingAddressTabLocator = By.xpath("//*[@id='tabBillingAddresses']");
    private final By getBillingAddressBtnLocator = By.xpath("//a[@id='addNewBillingAddressBtn']");
    private final By newBillingAddressSectionLocator = By.xpath("//div[@id='new_billing_address_section_basic']");
    private final By billingAddressFirstNameLocator = By.xpath("//input[@id='first_name_basic']");
    private final By billingAddressLastNameLocator = By.xpath("//input[@id='last_name_basic']");
    private final By billingAddressAddr1Locator = By.xpath("//input[@id='address_basic']");
    private final By billingAddressZipLocator = By.xpath("//input[@id='zip_basic']");

    private final By sameAsBillingButtonLocator = By.xpath("(//input[@class='sameAddBtn primary-button sameBillingAddBtn'])[3]");

    private final By shippingAddressTabLocator = By.xpath("//*[@id='tabShippingAddresses']");
    private final By shippingAddressTitleLocator = By.xpath("(//*[@class='columns colx_h2'])[2]");
    private final By getShippingAddressBtnLocator = By.xpath("//a[@id='addNewshippingAddressBtn']");
    private final By newShippingAddressSectionLocator = By.xpath("(//div[@id='new_billing_address_section_basic'])[3]");

    private final By paymentDetailsTabLocator = By.xpath("//li[@id='tabPaymentMethods']");
    private final By paymentDetailsTabTitleLocator = By.xpath("//*[@tooltipid='Customer_PaymentMethods']");
    private final By addNewCardBtnLocator = By.xpath("//*[@id='linkAddNewCard']");
    private final By cardStackLocator = By.xpath("(//*[@class='card stack'])[2]");
    private final By cardNumberFieldLocator = By.xpath("(//*[@name='card_no'])[2]");
    private final By cardExpiredYearLocator = By.xpath("(//*[@id='card_expYear'])[2]/option[7]");
    private final By saveCardLinkLocator = By.xpath("(//*[@id='linkSaveCard'])[2]");
    private final By editCardLinkLocator = By.xpath("(//*[@id='linkEditCard'])[2]");
    private final By saveAndCloseContextualButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private final By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

    public void addCustomerInfo(String customerFirstName, String customerLastName, String customerEmail, String customerPhone) {
        setTotalResultMessage(getTotalResultMessage() + "Adding customer info:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add First Name\n");
        driver.findElement(firstNameFieldLocator).sendKeys(customerFirstName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Last Name\n");
        driver.findElement(lastNameFieldLocator).sendKeys(customerLastName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add email\n");
        driver.findElement(emailFieldLocator).sendKeys(customerEmail);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add phone\n");
        driver.findElement(phoneFieldLocator).sendKeys(customerPhone);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addBillingAddress(String billingFirstName, String billingLastName, String billingAddressLine1, String billingZip) {
        setTotalResultMessage(getTotalResultMessage() + "Adding Billing Address:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select Billing Address tab\n");
        driver.findElement(billingAddressTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Billing Address' button\n");
        driver.findElement(getBillingAddressBtnLocator).click();
        Assert.assertEquals(driver.findElement(newBillingAddressSectionLocator).isDisplayed(), true, "Add new billing address form does not appear");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address First Name\n");
        driver.findElement(billingAddressFirstNameLocator).sendKeys(billingFirstName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address Last Name\n");
        driver.findElement(billingAddressLastNameLocator).sendKeys(billingLastName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address Address line 1\n");
        driver.findElement(billingAddressAddr1Locator).sendKeys(billingAddressLine1);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address Zip\n");
        driver.findElement(billingAddressZipLocator).sendKeys(billingZip);
        driver.findElement(billingAddressAddr1Locator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addShippingAddress() {
        setTotalResultMessage(getTotalResultMessage() + "Adding Shipping Address:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select Shipping Address tab\n");
        driver.findElement(shippingAddressTabLocator).click();
        String currentShippingAddressTitle = driver.findElement(shippingAddressTitleLocator).getText();
        Assert.assertEquals(currentShippingAddressTitle, shippingAddressTitle, "Unexpected Shipping Address tab title");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Shipping Address' button\n");
        driver.findElement(getShippingAddressBtnLocator).click();
        Assert.assertEquals(driver.findElement(newShippingAddressSectionLocator).isDisplayed(), true, "Add new Shipping address form does not appear");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Shipping info. Same as Billing\n");
        driver.findElement(sameAsBillingButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addCreditCard(String testCardNumber) {
        setTotalResultMessage(getTotalResultMessage() + "Adding Credit Card:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select Payment Methods tab\n");
        driver.findElement(paymentDetailsTabLocator).click();
        String currentPaymentMethodsTitle = driver.findElement(paymentDetailsTabTitleLocator).getText();
        Assert.assertEquals(currentPaymentMethodsTitle, paymentMethodsTitle, "Unexpected Payment Methods tab title");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add New Card' button\n");
        driver.findElement(addNewCardBtnLocator).click();
        Assert.assertEquals(driver.findElement(cardStackLocator).isDisplayed(), true, "New Card stack does not appear");

        setTotalResultMessage(getTotalResultMessage() + " - Add Card info\n");
        WebElement cardNumberField = driver.findElement(cardNumberFieldLocator);
        cardNumberField.clear();
        cardNumberField.sendKeys(testCardNumber);
        ProgressBar.addProgressValue(progressVariable);

        driver.findElement(cardExpiredYearLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save Card' button\n");
        driver.findElement(saveCardLinkLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Waiting popup is not hidden for a long time");
        wait.until(ExpectedConditions.elementToBeClickable(editCardLinkLocator));
        ProgressBar.addProgressValue(progressVariable);
        Assert.assertEquals(driver.findElement(editCardLinkLocator).isDisplayed(), true, "Credit card is not saved");
    }

    public void saveNewCustomer() {
        setTotalResultMessage(getTotalResultMessage() + "Saving new Customer:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save and Close' button\n");
        driver.findElement(saveAndCloseContextualButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupOkBtnLocator));

        setTotalResultMessage(getTotalResultMessage() + " - Confirm success popup\n");
//        String currentPopupMessage = driver.findElement(popupBoxMessageLocator).getText();
//        Assert.assertEquals(currentPopupMessage, addCustomerPopupMessage, "Unexpected popup message");
        driver.findElement(popupOkBtnLocator).click();
        ProgressBar.addProgressValue(progressVariable);
//        final Wait<WebDriver> wait2 = new WebDriverWait(driver, timeoutVariable).withMessage("Progress bar was not hidden");
//        wait2.until(ExpectedConditions.elementToBeClickable(filterCustomersFieldLocator));
//        ProgressBar.addProgressValue(progressVariable);
    }

//    public void searchNewCustomerInTheGrid (String customerFirstName) {
//        log("Search new Customer in the grid");
//        WebElement searchField = driver.findElement(filterCustomersFieldLocator);
//        searchField.clear();
//        searchField.click();
//        searchField.sendKeys(customerFirstName);
//        searchField.sendKeys(Keys.ENTER);
//
//        int numberOfElements = driver.findElements(numberOfCustomersLocator).size();
//
//        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Waiting popup was not found");
//        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(numberOfCustomersLocator, numberOfElements));
//
//
//
//        log("Compare Customer's data from the grid");
//        Assert.assertEquals(driver.findElement(customerNameInTheGridLocator).getText(), firstName + " " + lastName, "Unexpected Customer name");
//        Assert.assertEquals(driver.findElement(customerEmailInTheGridLocator).getText(), email, "Unexpected Customer Email");
//        Assert.assertEquals(driver.findElement(customerAddressInTheGridLocator).getText(), addressLine1, "Unexpected Customer address");
//        Assert.assertEquals(driver.findElement(customerZipInTheGridLocator).getText(), addressZip, "Unexpected Customer Zip");
//    }
}
