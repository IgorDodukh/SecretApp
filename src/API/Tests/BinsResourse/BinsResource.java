package API.Tests.BinsResourse;

import API.Settings.EnvSettings;
import API.Settings.RequestsBuilder;
import org.json.simple.parser.ParseException;

public class BinsResource extends EnvSettings {

    private RequestsBuilder requestsBuilder = new RequestsBuilder();

//    @Test
    public void binsGet() throws ParseException {
        requestsBuilder.getRequest(environmentUrl + resourcesPathList.get(1));
    }
}
