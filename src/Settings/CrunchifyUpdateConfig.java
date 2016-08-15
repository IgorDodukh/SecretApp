package Settings;

import FXUI.Controller;
import FXUI.GeneratePopupBox;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.Objects;

/**
 * Created by Ihor on 8/14/2016.
 */
public class CrunchifyUpdateConfig {
    public static void main(String[] args) {

        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration("C:\\appFiles\\config.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        if(!Objects.equals(GeneratePopupBox.currentTimeout, null)) {
            config.setProperty("timeoutVariable", GeneratePopupBox.currentTimeout);
        }
        if(!Objects.equals(Controller.login, null)&&!Objects.equals(Controller.password, null)){
            config.setProperty("lastEmail", Controller.login);
            config.setProperty("lastPassword", Controller.password);
        }
        if(!Objects.equals(GeneratePopupBox.currentUser, null)) {
            config.setProperty("user", GeneratePopupBox.currentUser);
        }

        try {
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("Config Property Successfully Updated..");
        System.out.println("New timeout: " + config.getProperty("timeoutVariable").toString());
        System.out.println("New email: " + config.getProperty("lastEmail").toString());
        System.out.println("New pass: " + config.getProperty("lastPassword").toString());
        System.out.println("New user: " + config.getProperty("user").toString());
    }
}
