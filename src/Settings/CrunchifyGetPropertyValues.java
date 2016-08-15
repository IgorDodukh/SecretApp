package Settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Ihor on 8/14/2016.
 */
public class CrunchifyGetPropertyValues {
    String result = "";
    InputStream inputStream;
    public static String timeoutProperty;
    public static String loginProperty;
    public static String passProperty;
    public static String user;

    public String getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("C:\\appFiles\\config.properties");

            inputStream = input;

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + input.toString() + "' not found in the classpath");
            }

            // get the property value and print it out
            timeoutProperty = prop.getProperty("timeoutVariable");
            user = prop.getProperty("user");
            loginProperty = prop.getProperty("lastEmail");
            passProperty = prop.getProperty("lastPassword");

            result = "Last credentials: " + loginProperty + ", " + passProperty ;
            System.out.println(result + "\nTimeout is set to " + timeoutProperty + " by user=" + user);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return result;
    }
}

