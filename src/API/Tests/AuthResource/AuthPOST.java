package API.Tests.AuthResource;

import API.Settings.EnvSettings;
import API.Settings.JsonReader;
import API.Settings.RequestsSender;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class AuthPOST extends EnvSettings {

    private RequestsSender requestsSender = new RequestsSender();
    private JsonReader jsonReader = new JsonReader();

//    @Test
    public void authorisationPOST() throws ParseException, IOException {
        requestsSender.jerseyPOSTRequest(environmentUrl + resourcesPathList.get(0), jsonReader.getReceivedJsonString());
    }
}
