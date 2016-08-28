package Tests;

import Pages.*;
import Settings.BrowserSettings;
import Settings.GenerateRandomData;
import Settings.GetPropertyValues;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by igor on 21.04.16. All rights reserved!
 */
public class AddProductWithInventory extends BrowserSettings{
    public static String createdProductSKU = "";
    public static String createdProductName = "";
    @Test
    public void addProductWithInventory(String email, String merchantPassword, WebDriver driver) throws InterruptedException{

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openAddProductPage();

        AddProductPage addProductPage = new AddProductPage(driver);
        addProductPage.addProductInfo(
                createdProductSKU = GetPropertyValues.productSKU + new GenerateRandomData().generateRandomNumber(randomValueLength),
                createdProductName = GetPropertyValues.productName + new GenerateRandomData().generateRandomNumber(randomValueLength),
                productWeight, productDescription);
        addProductPage.addProductPrices(productRetailPrice);
        addProductPage.addProductSalesChannel(productSalesChannel);
        addProductPage.addProductSupplier(productRetailPrice);
        addProductPage.saveProduct();
        addProductPage.openInventoryPage();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openAddInventoryForm(createdProductSKU);
        inventoryPage.addInventoryInfo();
        inventoryPage.saveInventory();
    }

}
