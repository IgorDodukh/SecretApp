package Settings;

import FXUI.ProgressBar;
import Tests.AddNewCustomer;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by igor on 17.04.16. All rights reserved!
 */

public class BrowserSettings {
    protected WebDriver driver;

    private static final GenerateRandomData generateRandomData = new GenerateRandomData();

    //    List of Environments
    private static final ArrayList<String> fsEnvironment = new ArrayList<>(
            Arrays.asList("https://qa01.freestylecommerce.info/web",
                    "https://qa03.freestylecommerce.info/web",
                    "https://qa05.freestylecommerce.info/web",
                    "https://my.freestylecommerce.com/web"));

    protected static final ArrayList<String> magentoEnvironment = new ArrayList<>(
            Arrays.asList("https://linux.mailordercentral.com/qatestlab01/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab02/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab03/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab04/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab05/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab06/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab07/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab08/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab09/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/qatestlab10/index.php/admin/system_config/edit/section/freestyle_advancedexport/",
                    "https://linux.mailordercentral.com/hercules/index.php/admin/system_config/edit/section/freestyle_advancedexport/"));

    //    Customer data
    public static int randomValueLength = 4;
    protected static final String customerEmail = AddNewCustomer.createdFirstName + "@dydacomp.biz";
    protected static final String phone = generateRandomData.generateRandomNumber(10);

//    Billing Address data
    protected static final String addressFirstName = "Billing" + AddNewCustomer.createdFirstName;
    protected static final String addressLastName = "Billing" + AddNewCustomer.createdLastName;
    protected static final String addressLine1 = "Billing Address Line 11";
    protected static final String addressZip = "10113";

//    Shipping Address data
    protected static final String shippingAddressTitle = "Shipping Address";
    protected static final String paymentMethodsTitle = "Payment Method";

//    CreditCard info
    public static final String visaTestCardNumber = "4005550000000019";
    public static final String masterCardTestCardNumber = "5424180279791765";
    public static final String discoverTestCardNumber = "6011000993010978";
    public static final String americanExpressTestCardNumber = "372700997251009";
    protected static final String saveSettingsPopupMessage = "Configuration has been saved successfully.";

    //    Warehouse data
    public static String warehouseNameValue = "Test Warehouse";
    public static final String warehouseName = warehouseNameValue + "_" + generateRandomData.generateRandomNumber(randomValueLength);
    protected static final String warehouseContactName = "Test Warehouse Contact Name";
    protected static final String startPickupTime = "08:00";
    protected static final String endPickupTime = "21:00";

//    Bin data
    public static String newBinName = "Test Warehouse Bin";
    protected static final String saveWarehousePopupMessage = "Save warehouse successfully";

//    Product data
    public static String productSKUStart = "ProductSKU";
    public static String productNameStart = "ProductName";
    private static final String prodNum = generateRandomData.generateRandomNumber(randomValueLength);
    public static final String productSku = productSKUStart + " " + prodNum;
    protected static final String productName = productNameStart + " " + prodNum;
    protected static final String productWeight = generateRandomData.generateRandomNumber(1);
    protected static final String productDescription = productSku + " Description";
    protected static final String productRetailPrice = generateRandomData.generateRandomNumber(3);
    protected static final String productSalesChannel = "Call Center";

//    Supplier data
    protected static String supplierAccountNumber = new GenerateRandomData().generateRandomNumber(randomValueLength);
    protected static String supplierURL = generateRandomData.generateRandomNumber(5) + ".site.blabla";
    protected static String supplierAddress = "Lucky Street " + generateRandomData.generateRandomNumber(3);


//    Bin Data
    protected static final String binPriority = generateRandomData.generateRandomNumber(3);

//    Inventory Data
    protected static final String inventoryLotNumber = generateRandomData.generateRandomNumber(5);
    protected static final String inventoryUnitCost = generateRandomData.generateRandomNumber(2);
    public static final String inventoryQty = generateRandomData.generateRandomNumberAllovedDigits(6, "");
    protected static final String inventoryNotes = "My Notes " + generateRandomData.generateRandomString(10);

//    UPS credentials
    protected static final String upsUserName = "Dev.api@dydacomp";
    protected static final String upsPassword = "7xy7mZBcXYEKw358gCKrDaqqeX";
    protected static final String upsLicenseNumber = "0C8701ECC4023070";
    protected static final String upsShipperNumber = "08611E";

//    USPS credentials
    protected static final String uspsAccountId = "2502974";
    protected static final String uspsPassPhrase = "EliManningHOF!1?";

//    create UPS Ground shipping method
    protected static final String upsGroundMethodName = "UPS Ground-" + generateRandomData.generateRandomNumber(2);
    protected static final String shippingMethodPrice = generateRandomData.generateRandomNumber(1);

// For reorder
    protected static String orderedItems = "";
    protected static String shippingMethod = "";
    protected static String orderedCustomerName = "";
    public static String orderNumber = "";

// Magento config
protected final String magentoLogin = "FSAWS_Admin";
    protected final String magentoPassword = "#Dydacomp1";
    protected static String magentoChannelID;
    protected static String magentoFSLink;

    public static int timeoutVariable = 30;
    public static int progressVariable;

    public static String totalResultMessage = "";

    //Corporate mail box access
    protected final String mailboxUrl = "gmail.com";
    public String mailboxLogin = "wildcard@dydacomp.biz";
    public String mailboxPassword = "Dydacomp2014!";

    @BeforeTest
    public void setUp(int envIndex, int browserIndex, WebDriver driver) {
        System.out.println("Run WebDriver");
        totalResultMessage += "Run WebDriver\n";
        ProgressBar.addProgressValue(progressVariable);
        driver.manage().window().setSize(new Dimension(1366, 900));
        magentoFSLink = fsEnvironment.get(envIndex);
        driver.get(fsEnvironment.get(envIndex));
        driver.manage().timeouts().implicitlyWait(timeoutVariable, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(WebDriver driver) {
        System.out.println("Close WebDriver");
        totalResultMessage += "Close WebDriver";
        driver.quit();
    }

}
