package Settings;

import FXUI.Controller;
import FXUI.GeneratePopupBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by Ihor on 8/14/2016.
 */
public class CrunchifyUpdateConfig {
    public static void main() throws IOException {

        FileOutputStream out = new FileOutputStream("C:\\appFiles\\config.properties");
        FileInputStream in = new FileInputStream("C:\\appFiles\\config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        if(!Objects.equals(GeneratePopupBox.currentTimeout, null)) {
            props.setProperty("timeoutVariable", GeneratePopupBox.currentTimeout);
        }
        if(!Objects.equals(Controller.login, null)&&!Objects.equals(Controller.password, null)){
            props.setProperty("lastEmail", Controller.login);
            props.setProperty("lastPassword", Controller.password);
        }
        if(!Objects.equals(GeneratePopupBox.currentUser, null)) {
            props.setProperty("user", GeneratePopupBox.currentUser);
        }
        props.store(out, null);
        out.close();

        System.out.println("Config Property Successfully Updated..");
        System.out.println("New timeout: " + props.getProperty("timeoutVariable"));
        System.out.println("New email: " + props.getProperty("lastEmail"));
        System.out.println("New pass: " + props.getProperty("lastPassword"));
        System.out.println("New user: " + props.getProperty("user"));
    }
}
