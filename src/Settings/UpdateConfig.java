package Settings;

import FXUI.AppStyles;
import FXUI.Controller;
import FXUI.GeneratePopupBox;

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

            props.setProperty("timeoutVariable", GeneratePopupBox.currentTimeout);
            props.setProperty("randomValue", GeneratePopupBox.currentRandomLength);
            props.setProperty("defaultPath", GeneratePopupBox.currentMainPath);

            props.store(out, null);
            out.close();
        } catch (Exception e) {
            GeneratePopupBox.warningPopupBox(e.getMessage());
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
                GeneratePopupBox.warningPopupBox(e.getMessage());
            }
    }

    public static void updateUser() throws IOException {
        try {
            FileInputStream in = new FileInputStream(AppStyles.propertiesResourcePath + "user.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(AppStyles.propertiesResourcePath + "user.properties");

            props.setProperty("user", GeneratePopupBox.currentUser);

            props.store(out, null);
            out.close();
        } catch (Exception e) {
        GeneratePopupBox.warningPopupBox(e.getMessage());
    }
    }

    public static void updateNames() throws IOException {
        try {
            FileInputStream in = new FileInputStream(AppStyles.propertiesResourcePath + "names.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(AppStyles.propertiesResourcePath + "names.properties");

            props.setProperty("customerFirstName", GeneratePopupBox.currentCustomerFirstName);
            props.setProperty("customerLastName", GeneratePopupBox.currentCustomerLastName);
            props.setProperty("productSKU", GeneratePopupBox.currentProductSKU);
            props.setProperty("productName", GeneratePopupBox.currentProductName);
            props.setProperty("supplierName", GeneratePopupBox.currentSupplierName);
            props.setProperty("warehouseName", GeneratePopupBox.currentWarehouseName);
            props.setProperty("binName", GeneratePopupBox.currentBinName);

            props.store(out, null);
            out.close();

            System.out.println("Names Property Successfully Updated..");
        } catch (Exception e) {
        GeneratePopupBox.warningPopupBox(e.getMessage());
        System.out.println("Exception: " + e);
    }
    }
}
