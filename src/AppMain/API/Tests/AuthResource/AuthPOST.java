package AppMain.API.Tests.AuthResource;

import AppMain.API.Settings.EnvSettings;
import AppMain.API.Settings.JsonReader;
import AppMain.API.Settings.RequestsBuilder;
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
