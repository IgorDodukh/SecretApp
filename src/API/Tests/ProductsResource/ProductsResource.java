package API.Tests.ProductsResource;

import API.Settings.EnvSettings;
import API.Settings.RequestsBuilder;
import FXUI.Controller;
import org.json.simple.parser.ParseException;

public class ProductsResource extends EnvSettings {

    private RequestsBuilder requestsBuilder = new RequestsBuilder();

//    @Test
    public void sendGet() throws ParseException {
        requestsBuilder.jerseyGET(environmentUrl + Controller.getSelectedResourceValue());
    }
}
