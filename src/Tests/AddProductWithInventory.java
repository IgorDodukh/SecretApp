package Tests;

import Pages.*;
import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by igor on 21.04.16.
 */
public class AddProductWithInventory extends BrowserSettings{

    @Test
    public void jira3015(String email, String merchantPassword, WebDriver driver) throws InterruptedException{

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openAddProductPage();

        AddProductPage addProductPage = new AddProductPage(driver);
        addProductPage.addProductInfo(productSku, productName, productWeight, productDescription);
        addProductPage.addProductPrices(productRetailPrice);
        addProductPage.addProductSalesChannel(productSalesChannel);
        addProductPage.addProductSupplier(productRetailPrice);
        addProductPage.saveProduct();
        addProductPage.openInventoryPage();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openAddInventoryForm();
        inventoryPage.addInventoryInfo();
        inventoryPage.saveInventory();
    }

}
