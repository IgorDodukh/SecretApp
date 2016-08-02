package Settings;

import FXUI.ProgressBar;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by igor on 17.04.16.
 */

public class BrowserSettings {
    public WebDriver driver;

    private static GenerateRandomData generateRandomData = new GenerateRandomData();

    //    List of Environments
    private static ArrayList<String> fsEnvironment = new ArrayList<String>(
            Arrays.asList("https://qa01.freestylecommerce.info/web",
                    "https://qa03.freestylecommerce.info/web",
                    "https://qa05.freestylecommerce.info/web",
                    "https://my.freestylecommerce.com/web"));

    public static ArrayList<String> magentoEnvironment = new ArrayList<String>(
            Arrays.asList("https://linux.mailordercentral.com/qatestlab01/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab02/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab03/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab04/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab05/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab06/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab07/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab08/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab09/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/qatestlab10/index.php/admin/dashboard/",
                    "https://linux.mailordercentral.com/hercules/index.php/admin/dashboard/"));

//    protected String userAlreadyLoggedMsg = "This user is already logged in. Do you want to log off the active session?";

//    Customer data
    public static String firstName = "FirstName_" + generateRandomData.generateRandomNumber(4);
    public static String lastName = "LastName_" + generateRandomData.generateRandomNumber(4);
    protected static String customerEmail = firstName + "@dydacomp.biz";
    protected static String phone = generateRandomData.generateRandomNumber(10);

//    Billing Address data
    public static String billingAddressTitle = "Billing Address";
    protected static String addressFirstName = "TesterBillingFirstName";
    protected static String addressLastName = "TesterBillingLastName";
    protected static String addressLine1 = "Tester Billing Address Line 11";
    protected static String addressZip = "10113";

//    Shipping Address data
    protected static String shippingAddressTitle = "Shipping Address";
    protected static String paymentMethodsTitle = "Payment Method";

//    CreditCard info
    public static String visaTestCardNumber = "4005550000000019";
    public static String masterCardTestCardNumber = "5424180279791765";
    public static String discoverTestCardNumber = "6011000993010978";
    public static String americanExpressTestCardNumber = "372700997251009";
    protected static String addCustomerPopupMessage = "The customer has been successfully created.";
    public static String saveSupplierPopupMessage = "Supplier was created successfully.\n";
    protected static String saveSettingsPopupMessage = "Configuration has been saved successfully.";

//    Warehouse data
    public static String warehouseName = "Test Warehouse_" + generateRandomData.generateRandomNumber(5);
    protected static String warehouseContactName = "Test Warehouse Contact Name";
    protected static String startPickupTime = "08:00";
    protected static String endPickupTime = "21:00";

//    Bin data
    public static String newBinName = "Test Warehouse Bin";
    protected static String saveWarehousePopupMessage = "Save warehouse successfully";

//    Product data
    private static String prodNum = generateRandomData.generateRandomNumber(4);
    public static String productSku = "ProductSKU " + prodNum;
    protected static String productName = "ProductName " + prodNum;
    protected static String productWeight = generateRandomData.generateRandomNumber(1);
    protected static String productDescription = productSku + " Description";
    protected static String productRetailPrice = generateRandomData.generateRandomNumber(3);
    protected static String productSalesChannel = "Call Center";

//    Supplier data
    protected static String supplierAccountNumber = generateRandomData.generateRandomNumber(5);
    public static String supplierName = "MySupplier_" + supplierAccountNumber;
    protected static String supplierURL = generateRandomData.generateRandomNumber(5) + ".site.blabla";
    protected static String supplierAddress = "Lucky Street " + generateRandomData.generateRandomNumber(3);
    protected static String supplierEmail = supplierName + "@dydacomp.biz";

//    Bin Data
    public static String binName = productName + " Bin " + generateRandomData.generateRandomNumber(2);
    protected static String binPriority = generateRandomData.generateRandomNumber(3);

//    Inventory Data
    protected static String inventoryLotNumber = generateRandomData.generateRandomNumber(5);
    protected static String inventoryUnitCost = generateRandomData.generateRandomNumber(2);
    public static String inventoryQty = generateRandomData.generateRandomNumberAllovedDigits(6, "");
    protected static String inventoryNotes = "My Notes " + generateRandomData.generateRandomString(10);

//    Authorize credentials
    public static String authApiLoginId = "";
    public static String authTransactionKey = "";

//    UPS credentials
    protected static String upsUserName = "Dev.api@dydacomp";
    protected static String upsPassword = "7xy7mZBcXYEKw358gCKrDaqqeX";
    protected static String upsLicenseNumber = "0C8701ECC4023070";
    protected static String upsShipperNumber = "08611E";

//    USPS credentials
    protected static String uspsAccountId = "2502974";
    protected static String uspsPassPhrase = "EliManningHOF!1?";

//    create UPS Ground shipping method
    protected static String upsGroundMethodName = "UPS Ground-" + generateRandomData.generateRandomNumber(2);
    protected static String shippingMethodPrice = generateRandomData.generateRandomNumber(1);

// For reorder
    protected static String orderedItems = "";
    protected static String shippingMethod = "";
    protected static String orderedCustomerName = "";
    public static String orderNumber = "";

// Magento config
    public String magentoLogin = "FSAWS_Admin";
    public String magentoPassword = "#Dydacomp1";
    public static String magentoChannelID;
    public static String magentoFSLink;
    protected static int timeoutVariable = 10;
    public static int progressVariable;

    public static String totalResultMessage = "";

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
