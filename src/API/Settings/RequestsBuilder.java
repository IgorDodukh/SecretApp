package API.Settings;

import FXUI.AppStyles;
import FXUI.Controller;
import FXUI.GeneratePopupBox;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static API.Settings.EnvSettings.*;
import static API.Settings.JsonReader.writeJsonFile;

/**
 * Created by Ihor on 2/11/2017.
 */
public class RequestsBuilder {

    public static int getResponseStatusCode() {
        return responseStatusCode;
    }

    public static void setResponseStatusCode(int responseStatusCode) {
        RequestsBuilder.responseStatusCode = responseStatusCode;
    }

    private static int responseStatusCode;

    ArrayList<String> responseList;

    private List<String> productKeysList = new ArrayList<>(Arrays.asList("ProductName", "ProductSku"));
    private List<String> customerKeysList = new ArrayList<>(Arrays.asList("FirstName", "LastName", "CustomerNumber"));
    private List<String> ordersKeysList = new ArrayList<>(Arrays.asList("TotalAmount", "OrderNumber"));
    private List<String> suppliersKeysList = new ArrayList<>(Arrays.asList("Name"));
    private List<String> warehousesKeysList = new ArrayList<>(Arrays.asList("WarehouseName"));
    private List<String> binsKeysList = new ArrayList<>(Arrays.asList("BinName"));

    public void jerseyPOST(String targetUrl, String jsonEntity){
        Runnable runnable = () -> {
            String tokenPath = AppStyles.jsonPath + "token.json";
            ClientConfig config = new DefaultClientConfig();

            Client client = Client.create(config);

            WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
            if (getToken() != null) {
                webResource.header("x-freestyle-api-auth", getToken());
            }

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonEntity);
            setResponseStatusCode(response.getStatus());
            try {
                Assert.assertEquals("Unexpected response status.", 200, response.getStatus());
            } catch (AssertionError error) {
                GeneratePopupBox.failedPopupBox("Unexpected response status: " +
                        String.valueOf(response.getStatus()) + " " +
                        response.getStatusInfo() +
                        "\n\nAPI token was not received." +
                        "\nPlease check your credentials and enable 'API mode' button once more.");
            }

            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(response.getEntity(String.class));
            } catch (ParseException e) {
                e.printStackTrace();
                Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
            }
            JSONObject jsonObject = (JSONObject) obj;
            Assert.assertNotEquals("Response body is null.", jsonObject, null);

            if (targetUrl.contains(resourcesPathList.get(0))) {
                Controller.setResponseStatus("API mode ON");
                setToken((String) jsonObject.get("token"));
                writeJsonFile(tokenPath, jsonObject);
            } else {
                Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
                GeneratePopupBox.successPopupBox(response.getStatus() + " " + response.getStatusInfo() +
                "\nNew item has been created");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void sendPost(String targetUrl, String jsonEntity) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(targetUrl);
        post.addHeader("x-freestyle-api-auth", getToken());

        System.out.println("Json: " + JsonReader.getReceivedJsonString());

        StringEntity postingString = new StringEntity(jsonEntity);
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println("HttpResponseBody: " + responseString);

        setResponseStatusCode(response.getStatusLine().getStatusCode());

        Controller.setResponseStatus(getResponseStatusCode() + " " +
                response.getStatusLine().getReasonPhrase());

        if(getResponseStatusCode() == 200 || getResponseStatusCode() == 201){
            GeneratePopupBox.successPopupBox(response.getStatusLine().getStatusCode() + " " +
                    response.getStatusLine().getReasonPhrase() +
                    "\nNew " + Controller.getSelectedResourceValue() + " has been created successfully.");
        } else {
            GeneratePopupBox.failedPopupBox(response.getStatusLine().getStatusCode() + " " +
                    response.getStatusLine().getReasonPhrase() +
                    "\nResponse body: " + responseString);
        }
    }

    public void jerseyGET(String targetUrl) throws ParseException {
        Runnable runnable = () -> {
            ClientConfig config = new DefaultClientConfig();

            Client client = Client.create(config);

            System.out.println("Target URL: " + targetUrl);
            WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
            webResource.header("x-freestyle-api-auth", getToken());

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).header("x-freestyle-api-auth", getToken()).get(ClientResponse.class);

//  Parse JSON response
            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(response.getEntity(String.class));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(targetUrl.contains("Product")){
                getJsonParameters(productKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Customer")) {
                getJsonParameters(customerKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Order")){
                getJsonParameters(ordersKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Suppliers")){
                getJsonParameters(suppliersKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Bin")){
                getJsonParameters(binsKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Warehouse")){
                getJsonParameters(warehousesKeysList, (JSONArray) obj);
            }
            Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
            GeneratePopupBox.listBox(responseList);
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private ArrayList<String> getJsonParameters(List<String> keysList, JSONArray obj) {
//        ArrayList<String> responseList = new ArrayList<>();
        responseList = new ArrayList<>();
        JSONArray json = obj;

        for (Object o : json) {
            String value = "";
            JSONObject jsonLineItem = (JSONObject) o;
            for (int i = keysList.size()-1; i >= 0; i--){
                value += jsonLineItem.get(keysList.get(i)).toString();
                if(i > 0)
                    value += ", ";
            }
            responseList.add(value);
        }

        return responseList;
//        GeneratePopupBox.listBox(responseList);
    }
}