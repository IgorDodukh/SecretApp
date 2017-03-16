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
import java.util.Arrays;
import java.util.List;

import static API.Settings.EnvSettings.*;
import static API.Settings.JsonReader.writeJsonFile;

/**
 * Created by Ihor on 2/11/2017.
 */
public class RequestsBuilder {

    public static int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        RequestsBuilder.responseStatus = responseStatus;
    }

    private static int responseStatus;

    List<String> productKeysList = new ArrayList<>(Arrays.asList("ProductName", "ProductSku"));

    List<String> customerKeysList = new ArrayList<>(Arrays.asList("FirstName", "LastName", "CustomerNumber"));

    List<String> ordersKeysList = new ArrayList<>(Arrays.asList("TotalAmount", "OrderNumber"));

    List<String> suppliersKeysList = new ArrayList<>(Arrays.asList("Name"));

    List<String> warehousesKeysList = new ArrayList<>(Arrays.asList("WarehouseName"));

    List<String> binsKeysList = new ArrayList<>(Arrays.asList("BinName"));

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
            setResponseStatus(response.getStatus());
            try {
                Assert.assertEquals("Unexpected response status.", 200, response.getStatus());
            } catch (AssertionError error) {
                GeneratePopupBox.failedPopupBox("Unexpected response status: " +
                        String.valueOf(response.getStatus()) + " " +
                        response.getStatusInfo() +
                        "\nPlease check your credentials and retry to enable API mode");
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

            if (targetUrl.contains(resourcesPathList.get(0)) || getToken() != null) {
                setToken((String) jsonObject.get("token"));
                writeJsonFile(tokenPath, jsonObject);
                Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
            } else if (getToken() == null){
                GeneratePopupBox.failedPopupBox("Unexpected response status: " +
                        String.valueOf(response.getStatus()) + " " +
                        response.getStatusInfo() +
                        "\nToken was not received successfully");
                Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
            }

//            System.out.println("POST Status: " + response.getStatus() + " " + response.getStatusInfo());
//
//            Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());
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

            System.out.println("Target URL: " + targetUrl);
            WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
            webResource.header("x-freestyle-api-auth", getToken());

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).header("x-freestyle-api-auth", getToken()).get(ClientResponse.class);

            /**
             * Parse JSON response
             * */
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
        };
        Thread thread = new Thread(runnable);
        thread.start();

//        enableSpinner(false);
    }

    private void getJsonParameters(List<String> keysList, JSONArray obj) {
        ArrayList<String> responseList = new ArrayList<>();
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
        GeneratePopupBox.listBox(responseList);
    }
}