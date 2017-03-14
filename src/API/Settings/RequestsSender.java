package API.Settings;

import FXUI.AppStyles;
import FXUI.Controller;
import FXUI.GeneratePopupBox;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;

import static API.Settings.EnvSettings.*;
import static API.Settings.JsonReader.writeJsonFile;

/**
 * Created by Ihor on 2/11/2017.
 */
public class RequestsSender {

    public static String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        RequestsSender.responseBody = responseBody;
    }

    private static String responseBody;

    public void jerseyPOSTRequest(String targetUrl, String jsonEntity){
//        enableSpinner(true);
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

            Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
        };
        Thread thread = new Thread(runnable);
        thread.start();
//        enableSpinner(false);
    }

    public void getRequest(String targetUrl) throws ParseException {
//        enableSpinner(true);
        Runnable runnable = () -> {
            ClientConfig config = new DefaultClientConfig();

            Client client = Client.create(config);

            WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
            webResource.header("x-freestyle-api-auth", getToken());

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).header("x-freestyle-api-auth", getToken()).get(ClientResponse.class);

// Parse JSON response

            String parsingKey = "ProductSku";

            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(response.getEntity(String.class));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONArray json = (JSONArray) obj;
            System.out.println("Json: " + json);

//            json.toJSONString();
//            JSONArray jsonarray = new JSONArray(jsonStr);
//            for (int i = 0; i < jsonarray.length(); i++) {
//                JSONObject jsonobject = jsonarray.getJSONObject(i);
//                String name = jsonobject.getString("name");
//                String url = jsonobject.getString("url");
//            }

            ArrayList<String> responseList = new ArrayList<>();


            for(int i=0;i<json.size();i++){
                responseList.add(i, json.get(i).toString());
                System.out.println("array is " + json.get(i));

            }

//            for(int i=0;i<jsonArray.length();i++){
//                System.out.println("array is " + jsonArray.get(i));
//
//            }
//            for(Object o: data){
//                responseList.add(o.toString());
//                System.out.println(o);
//            }

            JSONArray array = new JSONArray();
            array.add(obj);

            System.out.println("Array: " + array.toString());
//            JSONObject jsonObject = (JSONObject) obj;
            Assert.assertNotEquals("Response body is null.", array, null);

            System.out.println("GET Status: " + response.getStatus() + " " + response.getStatusInfo());
            responseBody = array.toString();
            ArrayList<String> responseArray = new ArrayList<>();

            for(Object object: array){
                responseArray.add(object.toString());
            }
//            System.out.println("GET response body: " + response.getEntity(String.class));
//            GeneratePopupBox.successPopupBox(responseBody);
            GeneratePopupBox.listBox(responseList);
        };
        Thread thread = new Thread(runnable);
        thread.start();

//        enableSpinner(false);
    }
}