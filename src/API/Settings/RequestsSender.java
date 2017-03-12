package API.Settings;

import FXUI.AppStyles;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import static API.Settings.JsonReader.writeJsonFile;

/**
 * Created by Ihor on 2/11/2017.
 */
public class RequestsSender extends EnvSettings {

    public void jerseyPOSTRequest(String targetUrl, String jsonEntity) throws ParseException {
        String tokenPath = AppStyles.jsonPath + "token.json";
        ClientConfig config = new DefaultClientConfig();

        Client client = Client.create(config);

        WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());

        if (getToken() != null) {
            webResource.header("x-freestyle-api-auth", getToken());
        }

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonEntity);

        Assert.assertEquals("Unexpected response status.", 200, response.getStatus());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response.getEntity(String.class));
        JSONObject jsonObject = (JSONObject) obj;
        Assert.assertNotEquals("Response body is null.", jsonObject, null);

        if (targetUrl.contains(resourcesPathList.get(0))) {
            setToken((String) jsonObject.get("token"));
            writeJsonFile(tokenPath, jsonObject);
        }

        System.out.println("POST Status: " + response.getStatus() + " " + response.getStatusInfo());
    }

    public static void getRequest(String targetUrl) throws ParseException {

        ClientConfig config = new DefaultClientConfig();

        Client client = Client.create(config);

        WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
        webResource.header("x-freestyle-api-auth", getToken());

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).header("x-freestyle-api-auth", getToken()).get(ClientResponse.class);

        System.out.println("GET Status: " + response.getStatus() + " " + response.getStatusInfo());

        System.out.println("GET response body: " + response.getEntity(String.class));
    }
}