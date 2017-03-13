package API.Settings;

import FXUI.AppStyles;
import FXUI.Controller;
import FXUI.GeneratePopupBox;
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
    static Controller controller;

    public void jerseyPOSTRequest(String targetUrl, String jsonEntity){
        Runnable runnable = () -> {
            String tokenPath = AppStyles.jsonPath + "token.json";
            ClientConfig config = new DefaultClientConfig();

            Client client = Client.create(config);

            WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());

            if (getToken() != null) {
                webResource.header("x-freestyle-api-auth", getToken());
            }

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonEntity);
            try {
                Assert.assertEquals("Unexpected response status.", 200, response.getStatus());
            } catch (AssertionError error) {
                GeneratePopupBox.failedPopupBox("Unexpected response status: " +
                        String.valueOf(response.getStatus()) + " " +
                        response.getStatusInfo());
            }

            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(response.getEntity(String.class));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = (JSONObject) obj;
            Assert.assertNotEquals("Response body is null.", jsonObject, null);

            if (targetUrl.contains(resourcesPathList.get(0))) {
                setToken((String) jsonObject.get("token"));
                writeJsonFile(tokenPath, jsonObject);
            }

            System.out.println("POST Status: " + response.getStatus() + " " + response.getStatusInfo());
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void getRequest(String targetUrl) throws ParseException {

        ClientConfig config = new DefaultClientConfig();

        Client client = Client.create(config);

        WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
        webResource.header("x-freestyle-api-auth", getToken());

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).header("x-freestyle-api-auth", getToken()).get(ClientResponse.class);

        controller.requestResult(response.getStatus() + " " + response.getStatusInfo(), response.getEntity(String.class));
        System.out.println("GET Status: " + response.getStatus() + " " + response.getStatusInfo());

        System.out.println("GET response body: " + response.getEntity(String.class));
    }
}