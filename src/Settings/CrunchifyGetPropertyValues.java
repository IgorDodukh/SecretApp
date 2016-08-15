package Settings;

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

    public String getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "\\config\\config.properties";
//            File f = new File("C:/appFiles/config.properties");

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            timeoutProperty = prop.getProperty("timeoutVariable");
            String user = prop.getProperty("user");
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

