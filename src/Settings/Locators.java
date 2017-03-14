package Settings;

import org.openqa.selenium.By;

/**
 * Created by Ihor on 3/14/2017.
 */
public class Locators {

//   Customers page locators
    public final By customerFirstNameFieldLocator = By.xpath("//input[@id='customer_first_name']");
    public final By customerLastNameFieldLocator = By.xpath("//input[@id='customer_last_name']");
    public final By customerEmailFieldLocator = By.xpath("//input[@id='customer_email']");
    public final By customerPhoneFieldLocator = By.xpath("//input[@id='customer_phone']");

    public final By customerBillingAddressTabLocator = By.xpath("//*[@id='tabBillingAddresses']");
    public final By getBillingAddressBtnLocator = By.xpath("//a[@id='addNewBillingAddressBtn']");
    public final By newBillingAddressSectionLocator = By.xpath("//div[@id='new_billing_address_section_basic']");
    public final By billingAddressFirstNameLocator = By.xpath("//input[@id='first_name_basic']");
    public final By billingAddressLastNameLocator = By.xpath("//input[@id='last_name_basic']");
    public final By billingAddressAddr1Locator = By.xpath("//input[@id='address_basic']");
    public final By billingAddressZipLocator = By.xpath("//input[@id='zip_basic']");

    public final By sameAsBillingButtonLocator = By.xpath("(//input[@class='sameAddBtn primary-button sameBillingAddBtn'])[3]");

    public final By shippingAddressTabLocator = By.xpath("//*[@id='tabShippingAddresses']");
    public final By shippingAddressTitleLocator = By.xpath("(//*[@class='columns colx_h2'])[2]");
    public final By getShippingAddressBtnLocator = By.xpath("//a[@id='addNewshippingAddressBtn']");
    public final By newShippingAddressSectionLocator = By.xpath("(//div[@id='new_billing_address_section_basic'])[3]");

    public final By paymentDetailsTabLocator = By.xpath("//li[@id='tabPaymentMethods']");
    public final By paymentDetailsTabTitleLocator = By.xpath("//*[@tooltipid='Customer_PaymentMethods']");
    public final By addNewCardBtnLocator = By.xpath("//*[@id='linkAddNewCard']");
    public final By cardStackLocator = By.xpath("(//*[@class='card stack'])[2]");
    public final By cardNumberFieldLocator = By.xpath("(//*[@name='card_no'])[2]");
    public final By cardExpiredYearLocator = By.xpath("(//*[@id='card_expYear'])[2]/option[7]");
    public final By saveCardLinkLocator = By.xpath("(//*[@id='linkSaveCard'])[2]");
    public final By editCardLinkLocator = By.xpath("(//*[@id='linkEditCard'])[2]");
    public final By saveAndCloseContextualButtonLocator = By.xpath("//a[@id='btnSaveAndClose']");
    public final By popupOkBtnLocator = By.xpath("//button[@class='primary-button']");

//   Products page locators
}
