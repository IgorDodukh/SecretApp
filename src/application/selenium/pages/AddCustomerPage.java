package application.selenium.pages;

import application.fxui.ProgressBar;
import application.selenium.settings.BrowserSettings;
import application.selenium.settings.Locators;
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

    private Locators locators = new Locators();

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addCustomerInfo(String customerFirstName, String customerLastName, String customerEmail, String customerPhone) {
        setTotalResultMessage(getTotalResultMessage() + "Adding customer info:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Add First Name\n");
        driver.findElement(locators.customerFirstNameFieldLocator).sendKeys(customerFirstName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Last Name\n");
        driver.findElement(locators.customerLastNameFieldLocator).sendKeys(customerLastName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add email\n");
        driver.findElement(locators.customerEmailFieldLocator).sendKeys(customerEmail);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add phone\n");
        driver.findElement(locators.customerPhoneFieldLocator).sendKeys(customerPhone);
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addBillingAddress(String billingFirstName, String billingLastName, String billingAddressLine1, String billingZip) {
        setTotalResultMessage(getTotalResultMessage() + "Adding Billing Address:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select Billing Address tab\n");
        driver.findElement(locators.customerBillingAddressTabLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Billing Address' button\n");
        driver.findElement(locators.getBillingAddressBtnLocator).click();
        Assert.assertEquals(driver.findElement(locators.newBillingAddressSectionLocator).isDisplayed(), true, "Add new billing address form does not appear");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address First Name\n");
        driver.findElement(locators.billingAddressFirstNameLocator).sendKeys(billingFirstName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address Last Name\n");
        driver.findElement(locators.billingAddressLastNameLocator).sendKeys(billingLastName);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address Address line 1\n");
        driver.findElement(locators.billingAddressAddr1Locator).sendKeys(billingAddressLine1);
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Billing Address Zip\n");
        driver.findElement(locators.billingAddressZipLocator).sendKeys(billingZip);
        driver.findElement(locators.billingAddressAddr1Locator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addShippingAddress() {
        setTotalResultMessage(getTotalResultMessage() + "Adding Shipping Address:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select Shipping Address tab\n");
        driver.findElement(locators.shippingAddressTabLocator).click();
        String currentShippingAddressTitle = driver.findElement(locators.shippingAddressTitleLocator).getText();
        Assert.assertEquals(currentShippingAddressTitle, shippingAddressTitle, "Unexpected Shipping Address tab title");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add Shipping Address' button\n");
        driver.findElement(locators.getShippingAddressBtnLocator).click();
        Assert.assertEquals(driver.findElement(locators.newShippingAddressSectionLocator).isDisplayed(), true, "Add new Shipping address form does not appear");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Add Shipping info. Same as Billing\n");
        driver.findElement(locators.sameAsBillingButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);
    }

    public void addCreditCard(String testCardNumber) {
        setTotalResultMessage(getTotalResultMessage() + "Adding Credit Card:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Select Payment Methods tab\n");
        driver.findElement(locators.paymentDetailsTabLocator).click();
        String currentPaymentMethodsTitle = driver.findElement(locators.paymentDetailsTabTitleLocator).getText();
        Assert.assertEquals(currentPaymentMethodsTitle, paymentMethodsTitle, "Unexpected Payment Methods tab title");
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Add New Card' button\n");
        driver.findElement(locators.addNewCardBtnLocator).click();
        Assert.assertEquals(driver.findElement(locators.cardStackLocator).isDisplayed(), true, "New Card stack does not appear");

        setTotalResultMessage(getTotalResultMessage() + " - Add Card info\n");
        WebElement cardNumberField = driver.findElement(locators.cardNumberFieldLocator);
        cardNumberField.clear();
        cardNumberField.sendKeys(testCardNumber);
        ProgressBar.addProgressValue(progressVariable);

        driver.findElement(locators.cardExpiredYearLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save Card' button\n");
        driver.findElement(locators.saveCardLinkLocator).click();
        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Waiting popup is not hidden for a long time");
        wait.until(ExpectedConditions.elementToBeClickable(locators.editCardLinkLocator));
        ProgressBar.addProgressValue(progressVariable);
        Assert.assertEquals(driver.findElement(locators.editCardLinkLocator).isDisplayed(), true, "Credit card is not saved");
    }

    public void saveNewCustomer() {
        setTotalResultMessage(getTotalResultMessage() + "Saving new Customer:\n");
        setTotalResultMessage(getTotalResultMessage() + " - Click 'Save and Close' button\n");
        driver.findElement(locators.saveAndCloseContextualButtonLocator).click();
        ProgressBar.addProgressValue(progressVariable);

        final Wait<WebDriver> wait = new WebDriverWait(driver, timeoutVariable).withMessage("Confirmation popup was not found");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locators.popupOkBtnLocator));

        setTotalResultMessage(getTotalResultMessage() + " - Confirm success popup\n");
//        String currentPopupMessage = driver.findElement(popupBoxMessageLocator).getText();
//        Assert.assertEquals(currentPopupMessage, addCustomerPopupMessage, "Unexpected popup message");
        driver.findElement(locators.popupOkBtnLocator).click();
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
