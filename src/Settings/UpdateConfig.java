package Settings;

import FXUI.Controller;
import FXUI.GeneratePopupBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ihor on 8/14/2016.
 */
public class UpdateConfig {

    public static void updateTimeout() throws IOException {
        FileInputStream in = new FileInputStream("C:\\appFiles\\timeout.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("C:\\appFiles\\timeout.properties");

        props.setProperty("timeoutVariable", GeneratePopupBox.currentTimeout);

        props.store(out, null);
        out.close();

        System.out.println("Config Property Successfully Updated..");
        System.out.println("New timeout: " + props.getProperty("timeoutVariable"));
    }

    public static void updateCredentials() throws IOException {
        FileInputStream in = new FileInputStream("C:\\appFiles\\credentials.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("C:\\appFiles\\credentials.properties");
        props.setProperty("lastEmail", Controller.login);
        props.setProperty("lastPassword", Controller.password);

        props.store(out, null);
        out.close();

        System.out.println("Config Property Successfully Updated..");
        System.out.println("New email: " + props.getProperty("lastEmail"));
        System.out.println("New pass: " + props.getProperty("lastPassword"));
    }

    public static void updateUser() throws IOException {
        FileInputStream in = new FileInputStream("C:\\appFiles\\user.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("C:\\appFiles\\user.properties");

        props.setProperty("user", GeneratePopupBox.currentUser);

        props.store(out, null);
        out.close();

        System.out.println("Config Property Successfully Updated..");
        System.out.println("New user: " + props.getProperty("user"));
    }
}
