package API.Tests.AuthResource;

import API.Settings.EnvSettings;
import API.Settings.JsonReader;
import API.Settings.RequestsBuilder;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class AuthPOST extends EnvSettings {

    private RequestsBuilder requestsBuilder = new RequestsBuilder();
    private JsonReader jsonReader = new JsonReader();

//    @Test
    public void authorisationPOST() throws ParseException, IOException {
        requestsBuilder.jerseyPOST(environmentUrl + resourcesPathList.get(0), jsonReader.getReceivedJsonString());
    }
}
