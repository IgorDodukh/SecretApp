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
 * Created by igor on 17.04.16.
 */
public class AddCustomerPage extends BrowserSettings {
    private WebDriver driver;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameFieldLocator = By.xpath("//input[@id='customer_first_name']");
    private By lastNameFieldLocator = By.xpath("//input[@id='customer_last_name']");
    private By emailFieldLocator = By.xpath("//input[@id='customer_email']");
    private By phoneFieldLocator = By.xpath("//input[@id='customer_phone']");

    private By billingAddressTabLocator = By.xpath("//*[@id='tabBillingAddresses']");
    private By billingAddressTitleLocator = By.xpath("(//*[@class='columns colx_h2'])[1]");
    private By getBillingAddressBtnLocator = By.xpath("//a[@id='addNewBillingAddressBtn']");
    private By newBillingAddressSectionLocator = By.xpath("//div[@id='new_billing_address_section_basic']");
    private By billingAddressFirstNameLocator = By.xpath("//input[@id='first_name_basic']");
    private By billingAddressLastNameLocator = By.xpath("//input[@id='last_name_basic']");
    private By billingAddressAddr1Locator = By.xpath("//input[@id='address_basic']");
    private By billingAddressZipLocator = By.xpath("//input[@id='zip_basic']");

    private By sameAsBillingButtonLocator = By.xpath("(//input[@class='sameAddBtn primary-button sameBillingAddBtn'])[3]");

    private By shippingAddressTabLocator = By.xpath("//*[@id='tabShippingAddresses']");
    private By shippingAddressTitleLocator = By.xpath("(//*[@class='columns colx_h2'])[2]");
    private By getShippingAddressBtnLocator = By.xpath("//a[@id='addNewshippingAddressBtn']");
    private By newShippingAddressSectionLocator = By.xpath("(//div[@id='new_billing_address_section_basic'])[3]");

    private By paymentDetailsTabLocator = By.xpath("//li[@id='tabPaymentMethods']");
    private By paymentDetailsTabTitleLocator = By.xpath("//*[@tooltipid='Customer_PaymentMethods']");
    private By addNewCardBtnLocator = By.xpath("//*[@id='linkAddNewCard']");
    private By cardStackLocator = By.xpath("(//*[@class='card stack'])[2]");
    private By cardNumberFieldLocator = By.xpath("(//*[@name='card_no'])[2]");
    private By cardExpiredYearLocator = By.xpath("(//*[@id='card_expYear'])[2]/option[7]");
    private By saveCardLinkLocator = By.xpath("(//*[@id='linkSaveCard'])[2]");
    private By editCardLinkLocator = By.xpath("(//*[@id='linkEditCard'])[2]");
//    private By saveContextualButton = By.xpath("//a[@id='btnSave']");
    private By saveAndCloseContextualButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    private By popupBoxMessageLocator = By.xpath("(//div[@id='customerMessageBox']//*)[1]");
    private By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");


    private By filterCustomersFieldLocator = By.xpath("//*[@aria-controls='searchCustomerResult']");
    private By customerNameInTheGridLocator = By.xpath("((//*[@id='searchCustomerResult'])//tbody/tr/*)[4]");
    private By customerEmailInTheGridLocator = By.xpath("((//*[@id='searchCustomerResult'])//tbody/tr/*)[5]");
    private By customerAddressInTheGridLocator = By.xpath("((//*[@id='searchCustomerResult'])//tbody/tr/*)[7]");
    private By customerCityInTheGridLocator = By.xpath("((//*[@id='searchCustomerResult'])//tbody/tr/*)[8]");
//    private By customerStateInTheGridLocator = By.xpath("((//*[@id='searchCustomerResult'])//tbody/tr/*)[9]");
    private By customerZipInTheGridLocator = By.xpath("((//*[@id='searchCustomerResult'])//tbody/tr/*)[10]/div[1]");

    private By waitingPopupLocator = By.xpath("//*[@id='waitingPopup']");
    private By waitingPopupTextLocator = By.xpath("//*[@id='waitingPopup']//*[@id='waiting-popup-text']");
    private By numberOfCustomersLocator = By.xpath("//*[@id='searchCustomerResult']//tbody/tr");

    public void addCustomerInfo(String customerFirstName, String customerLastName, String customerEmail, String customerPhone) {
        totalResultMessage += "Adding customer info:\n";
        totalResultMessage += " - Add First Name\n";
        driver.findElement(firstNameFieldLocator).sendKeys(customerFirstName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Last Name\n";
        driver.findElement(lastNameFieldLocator).sendKeys(customerLastName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add email\n";
        driver.findElement(emailFieldLocator).sendKeys(customerEmail);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add phone\n";
        driver.findElement(phoneFieldLocator).sendKeys(customerPhone);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addBillingAddress(String billingFirstName, String billingLastName, String billingAddressLine1, String billingZip) {
        totalResultMessage += "Adding Billing Address:\n";
        totalResultMessage += " - Select Billing Address tab\n";
        driver.findElement(billingAddressTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Click 'Add Billing Address' button\n";
        driver.findElement(getBillingAddressBtnLocator).click();
        Assert.assertEquals(driver.findElement(newBillingAddressSectionLocator).isDisplayed(), true, "Add new billing address form does not appear");
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Billing Address First Name\n";
        driver.findElement(billingAddressFirstNameLocator).sendKeys(billingFirstName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Billing Address Last Name\n";
        driver.findElement(billingAddressLastNameLocator).sendKeys(billingLastName);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Billing Address Address line 1\n";
        driver.findElement(billingAddressAddr1Locator).sendKeys(billingAddressLine1);
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Billing Address Zip\n";
        driver.findElement(billingAddressZipLocator).sendKeys(billingZip);
        driver.findElement(billingAddressAddr1Locator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addShippingAddress() {
        totalResultMessage += "Adding Shipping Address:\n";
        totalResultMessage += " - Select Shipping Address tab\n";
        driver.findElement(shippingAddressTabLocator).click();
        String currentShippingAddressTitle = driver.findElement(shippingAddressTitleLocator).getText();
        Assert.assertEquals(currentShippingAddressTitle, shippingAddressTitle, "Unexpected Shipping Address tab title");
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Click 'Add Shipping Address' button\n";
        driver.findElement(getShippingAddressBtnLocator).click();
        Assert.assertEquals(driver.findElement(newShippingAddressSectionLocator).isDisplayed(), true, "Add new Shipping address form does not appear");
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Add Shipping info. Same as Billing\n";
        driver.findElement(sameAsBillingButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addCreditCard(String testCardNumber) {
        totalResultMessage += "Adding Credit Card:\n";
        totalResultMessage += " - Select Payment Methods tab\n";
        driver.findElement(paymentDetailsTabLocator).click();
        String currentPaymentMethodsTitle = driver.findElement(paymentDetailsTabTitleLocator).getText();
        Assert.assertEquals(currentPaymentMethodsTitle, paymentMethodsTitle, "Unexpected Payment Methods tab title");
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Click 'Add New Card' button\n";
        driver.findElement(addNewCardBtnLocator).click();
        Assert.assertEquals(driver.findElement(cardStackLocator).isDisplayed(), true, "New Card stack does not appear");

        totalResultMessage += " - Add Card info\n";
        WebElement cardNumberField = driver.findElement(cardNumberFieldLocator);
        cardNumberField.clear();
        cardNumberField.sendKeys(testCardNumber);
        ProgressBar.addProgressValue(progressVariable);

        driver.findElement(cardExpiredYearLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        totalResultMessage += " - Click 'Save Card' button\n";
        driver.findElement(saveCardLinkLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Waiting popup is not hidden for a long time");
        wait.until(ExpectedConditions.elementToBeClickable(editCardLinkLocator));
        ProgressBar.addProgressValue(progressVariable);
        Assert.assertEquals(driver.findElement(editCardLinkLocator).isDisplayed(), true, "Credit card is not saved");
    }

    public void saveNewCustomer() {
        totalResultMessage += "Saving new Customer:\n";
        totalResultMessage += " - Click 'Save and Close' button\n";
        driver.findElement(saveAndCloseContextualButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupOkBtnLocator));

        totalResultMessage += " - Confirm success popup\n";
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
