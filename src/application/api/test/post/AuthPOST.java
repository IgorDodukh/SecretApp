package application.api.test.post;

import application.api.settings.EnvSettings;
import application.api.settings.JsonReader;
import application.api.settings.RequestsBuilder;
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
