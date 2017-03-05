package Settings;

import FXUI.GeneratePopupBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static FXUI.AppStyles.getPropertiesResourcePath;
import static FXUI.Controller.getLogin;
import static FXUI.Controller.getPassword;
import static FXUI.GeneratePopupBox.*;

/**
 * Created by Ihor on 8/14/2016. All rights reserved!
 */
public class UpdateConfig {

    public static void updateSystemVariables() throws IOException {
        try {
            FileInputStream in = new FileInputStream(getPropertiesResourcePath() + "systemVar.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(getPropertiesResourcePath() + "systemVar.properties");

            props.setProperty("timeoutVariable", getCurrentTimeout());
            props.setProperty("randomValue", getCurrentRandomLength());
            props.setProperty("defaultPath", getCurrentMainPath());

            props.store(out, null);
            out.close();
        } catch (Exception e) {
            GeneratePopupBox.warningPopupBox(e.getMessage());
        }
    }

    public static void updateCredentials() throws IOException {
        try {
            FileInputStream in = new FileInputStream(getPropertiesResourcePath() + "credentials.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(getPropertiesResourcePath() + "credentials.properties");
            props.setProperty("lastEmail", getLogin());
            props.setProperty("lastPassword", getPassword());

            props.store(out, null);
            out.close();
        } catch (Exception e) {
                GeneratePopupBox.warningPopupBox(e.getMessage());
            }
    }

    public static void updateUser() throws IOException {
        try {
            FileInputStream in = new FileInputStream(getPropertiesResourcePath() + "user.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(getPropertiesResourcePath() + "user.properties");

            props.setProperty("user", getCurrentUser());

            props.store(out, null);
            out.close();
        } catch (Exception e) {
        GeneratePopupBox.warningPopupBox(e.getMessage());
    }
    }

    public static void updateNames() throws IOException {
        try {
            FileInputStream in = new FileInputStream(getPropertiesResourcePath() + "names.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(getPropertiesResourcePath() + "names.properties");

            props.setProperty("customerFirstName", getCurrentCustomerFirstName());
            props.setProperty("customerLastName", getCurrentCustomerLastName());
            props.setProperty("productSKU", getCurrentProductSKU());
            props.setProperty("productName", getCurrentProductName());
            props.setProperty("supplierName", getCurrentSupplierName());
            props.setProperty("warehouseName", getCurrentWarehouseName());
            props.setProperty("binName", getCurrentBinName());

            props.store(out, null);
            out.close();

            System.out.println("Names Property Successfully Updated..");
        } catch (Exception e) {
        GeneratePopupBox.warningPopupBox(e.getMessage());
        System.out.println("Exception: " + e);
    }
    }
}
