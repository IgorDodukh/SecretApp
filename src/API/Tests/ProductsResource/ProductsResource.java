package API.Tests.ProductsResource;

import API.Settings.EnvSettings;
import API.Settings.JsonReader;
import API.Settings.RequestsSender;
import org.json.simple.parser.ParseException;

public class ProductsResource extends EnvSettings {

    private RequestsSender requestsSender = new RequestsSender();
    private JsonReader jsonReader = new JsonReader();

//    @Test
    public void productsGet() throws ParseException {
        requestsSender.jerseyPOSTRequest(environmentUrl + resourcesPathList.get(0), jsonReader.getReceivedJsonString());
        RequestsSender.getRequest(environmentUrl + resourcesPathList.get(1));
    }
}
