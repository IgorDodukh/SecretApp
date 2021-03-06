package Settings;

import FXUI.AppStyles;
import FXUI.GeneratePopupBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static FXUI.AppStyles.getPropertiesResourcePath;

/**
 * Created by Ihor on 8/14/2016. All rights reserved!
 */
public class GetPropertyValues {
    private InputStream systemVariablesInputStream;
    private InputStream credentialsInputStream;
    private InputStream userInputStream;
    private InputStream namesInputStream;

    public static String timeoutProperty;
    public static String randomValueProperty;
    public static String defaultPathProperty;
    public static String loginProperty;
    public static String passProperty;
    public static String user;
    public static String customerFirstName;
    public static String customerLastName;
    public static String productSKU;
    public static String productName;
    public static String warehouseName;
    public static String supplierName;
    public static String binName;

    public void getPropValues() throws IOException {

        try {
            Properties systemVarProp = new Properties();
            Properties credentialsProp = new Properties();
            Properties userProp = new Properties();
            Properties namesProp = new Properties();

            InputStream systemVariablesInput = new FileInputStream(getPropertiesResourcePath() + "systemVar.properties");
            InputStream credentialsInput = new FileInputStream(getPropertiesResourcePath() + "credentials.properties");
            InputStream userInput = new FileInputStream(getPropertiesResourcePath() + "user.properties");
            InputStream namesInput = new FileInputStream(getPropertiesResourcePath() + "names.properties");

            systemVariablesInputStream = systemVariablesInput;
            credentialsInputStream = credentialsInput;
            userInputStream = userInput;
            namesInputStream = namesInput;

            if (systemVariablesInputStream != null) {
                systemVarProp.load(systemVariablesInputStream);
            } else {
                throw new FileNotFoundException("property file '" + systemVariablesInput.toString() + "' not found in the classpath");
            }
            if (credentialsInputStream != null) {
                credentialsProp.load(credentialsInputStream);
            } else {
                throw new FileNotFoundException("property file '" + credentialsInput.toString() + "' not found in the classpath");
            }
            if (userInputStream != null) {
                userProp.load(userInputStream);
            } else {
                throw new FileNotFoundException("property file '" + userInput.toString() + "' not found in the classpath");
            }

            if (namesInputStream != null) {
                namesProp.load(namesInputStream);
            } else {
                throw new FileNotFoundException("property file '" + namesInput.toString() + "' not found in the classpath");
            }

            // get the property value and print it out
            timeoutProperty = systemVarProp.getProperty("timeoutVariable");
            randomValueProperty = systemVarProp.getProperty("randomValue");
            defaultPathProperty = systemVarProp.getProperty("defaultPath");

            user = userProp.getProperty("user");

            loginProperty = credentialsProp.getProperty("lastEmail");
            passProperty = credentialsProp.getProperty("lastPassword");

            customerFirstName = namesProp.getProperty("customerFirstName");
            customerLastName = namesProp.getProperty("customerLastName");
            productSKU = namesProp.getProperty("productSKU");
            productName = namesProp.getProperty("productName");
            warehouseName = namesProp.getProperty("warehouseName");
            supplierName = namesProp.getProperty("supplierName");
            binName = namesProp.getProperty("binName");

        } catch (Exception e) {
            GeneratePopupBox.warningPopupBox(e.getMessage());
        } finally {
            assert systemVariablesInputStream != null;
            systemVariablesInputStream.close();

            assert credentialsInputStream != null;
            credentialsInputStream.close();

            assert userInputStream != null;
            userInputStream.close();

            assert namesInputStream != null;
            namesInputStream.close();
        }
    }
}

