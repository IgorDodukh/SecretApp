package Tests;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.OrderCreatingPage;
import Pages.ViewOrderPage;
import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by Ihor on 7/7/2016.
 */
public class MakeReorder extends BrowserSettings {

    @Test
    public void makeReorder(String email, String merchantPassword, WebDriver driver) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMerchant(email, merchantPassword);

        MainPage mainPage = new MainPage(driver);
        mainPage.openOrdersGrid();

        ViewOrderPage viewOrderPage = new ViewOrderPage(driver);
        viewOrderPage.openViewOrderPage();
//        viewOrderPage.getCustomerInfo();
        viewOrderPage.getOrderItemsInfo();
        viewOrderPage.backToOrdersGrid();
        viewOrderPage.openOrderCreatingForm();

        OrderCreatingPage orderCreatingPage = new OrderCreatingPage(driver);
        orderCreatingPage.addOrderItems();
        orderCreatingPage.addCustomer();
        orderCreatingPage.selectShippingMethod();
        orderCreatingPage.placeOrder();
    }
}
