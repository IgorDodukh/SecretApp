package application.selenium.settings;

import application.fxui.AppStyles;
import application.fxui.Controller;
import application.fxui.DialogBoxGenerator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ihor on 8/14/2016. All rights reserved!
 */
public class UpdateConfig {

    public static void updateSystemVariables() throws IOException {
        try {
            FileInputStream in = new FileInputStream(AppStyles.propertiesResourcePath + "systemVar.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(AppStyles.propertiesResourcePath + "systemVar.properties");

            props.setProperty("timeoutVariable", DialogBoxGenerator.currentTimeout);
            props.setProperty("randomValue", DialogBoxGenerator.currentRandomLength);
            props.setProperty("defaultPath", DialogBoxGenerator.currentMainPath);

            props.store(out, null);
            out.close();
        } catch (Exception e) {
            DialogBoxGenerator.warningPopupBox(e.getMessage());
        }
    }

    public static void updateCredentials() throws IOException {
        try {
            FileInputStream in = new FileInputStream(AppStyles.propertiesResourcePath + "credentials.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(AppStyles.propertiesResourcePath + "credentials.properties");
            props.setProperty("lastEmail", Controller.login);
            props.setProperty("lastPassword", Controller.password);

            props.store(out, null);
            out.close();
        } catch (Exception e) {
                DialogBoxGenerator.warningPopupBox(e.getMessage());
            }
    }

    public static void updateUser() throws IOException {
        try {
            FileInputStream in = new FileInputStream(AppStyles.propertiesResourcePath + "user.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(AppStyles.propertiesResourcePath + "user.properties");

            props.setProperty("user", DialogBoxGenerator.currentUser);

            props.store(out, null);
            out.close();
        } catch (Exception e) {
        DialogBoxGenerator.warningPopupBox(e.getMessage());
    }
    }

    public static void updateNames() throws IOException {
        try {
            FileInputStream in = new FileInputStream(AppStyles.propertiesResourcePath + "names.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(AppStyles.propertiesResourcePath + "names.properties");

            props.setProperty("customerFirstName", DialogBoxGenerator.currentCustomerFirstName);
            props.setProperty("customerLastName", DialogBoxGenerator.currentCustomerLastName);
            props.setProperty("productSKU", DialogBoxGenerator.currentProductSKU);
            props.setProperty("productName", DialogBoxGenerator.currentProductName);
            props.setProperty("supplierName", DialogBoxGenerator.currentSupplierName);
            props.setProperty("warehouseName", DialogBoxGenerator.currentWarehouseName);
            props.setProperty("binName", DialogBoxGenerator.currentBinName);

            props.store(out, null);
            out.close();

            System.out.println("Names Property Successfully Updated..");
        } catch (Exception e) {
        DialogBoxGenerator.warningPopupBox(e.getMessage());
        System.out.println("Exception: " + e);
    }
    }
}
