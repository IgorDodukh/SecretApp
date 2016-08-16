package Settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Ihor on 8/14/2016.
 */
public class GetPropertyValues {
    String result = "";
    InputStream timeoutInputStream;
    InputStream credentialsInputStream;
    InputStream userInputStream;
    public static String timeoutProperty;
    public static String loginProperty;
    public static String passProperty;
    public static String user;

    public String getPropValues() throws IOException {

//        http://stackoverflow.com/questions/15337409/updating-property-value-in-properties-file-without-deleting-other-values

        try {
            Properties timeoutProp = new Properties();
            Properties credentialsProp = new Properties();
            Properties userProp = new Properties();
            InputStream timeoutInput = new FileInputStream("C:\\appFiles\\timeout.properties");
            InputStream credentialsInput = new FileInputStream("C:\\appFiles\\credentials.properties");
            InputStream userInput = new FileInputStream("C:\\appFiles\\user.properties");

            timeoutInputStream = timeoutInput;
            credentialsInputStream = credentialsInput;
            userInputStream = userInput;

            if (timeoutInputStream != null) {
                timeoutProp.load(timeoutInputStream);
            } else {
                throw new FileNotFoundException("property file '" + timeoutInput.toString() + "' not found in the classpath");
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

            // get the property value and print it out
            timeoutProperty = timeoutProp.getProperty("timeoutVariable");
            user = userProp.getProperty("user");
            loginProperty = credentialsProp.getProperty("lastEmail");
            passProperty = credentialsProp.getProperty("lastPassword");

            result = "Last credentials: " + loginProperty + ", " + passProperty ;
            System.out.println(result + "\nTimeout is set to " + timeoutProperty + " by user=" + user);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert timeoutInputStream != null;
            timeoutInputStream.close();

            assert credentialsInputStream != null;
            credentialsInputStream.close();

            assert userInputStream != null;
            userInputStream.close();
        }
        return result;
    }
}

