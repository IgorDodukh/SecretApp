package Settings;

import java.io.IOException;

/**
 * Created by Ihor on 8/14/2016.
 */
public class ReadConfigMain {
    public static void main() throws IOException {
        GetPropertyValues properties = new GetPropertyValues();
        properties.getPropValues();
    }
}