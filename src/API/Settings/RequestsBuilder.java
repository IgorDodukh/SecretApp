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
import java.util.List;

import static API.Settings.EnvSettings.*;
import static API.Settings.JsonReader.writeJsonFile;

/**
 * Created by Ihor on 2/11/2017.
 */
public class RequestsBuilder {

    public static String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        RequestsBuilder.responseBody = responseBody;
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

            System.out.println("Target URL: " + targetUrl);
            WebResource webResource = client.resource(UriBuilder.fromUri(targetUrl).build());
            webResource.header("x-freestyle-api-auth", getToken());

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).header("x-freestyle-api-auth", getToken()).get(ClientResponse.class);

            List<String> productKeysList = new ArrayList<>();
            productKeysList.add("ProductName");
            productKeysList.add("ProductSku");

            List<String> customerKeysList = new ArrayList<>();
            customerKeysList.add("FirstName");
            customerKeysList.add("LastName");
            customerKeysList.add("CustomerNumber");

            List<String> ordersKeysList = new ArrayList<>();
            ordersKeysList.add("TotalAmount");
            ordersKeysList.add("OrderNumber");

            List<String> suppliersKeysList = new ArrayList<>();
            suppliersKeysList.add("Name");

            List<String> warehousesKeysList = new ArrayList<>();
            warehousesKeysList.add("WarehouseName");

            List<String> binsKeysList = new ArrayList<>();
            binsKeysList.add("BinName");

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
                jsonParametersParser(productKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Customer")) {
                jsonParametersParser(customerKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Order")){
                jsonParametersParser(ordersKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Suppliers")){
                jsonParametersParser(suppliersKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Bin")){
                jsonParametersParser(binsKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Warehouse")){
                jsonParametersParser(warehousesKeysList, (JSONArray) obj);
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();

//        enableSpinner(false);
    }

    private void jsonParametersParser(List<String> keysList, JSONArray obj) {
        ArrayList<String> responseList = new ArrayList<>();
        JSONArray json = obj;
        System.out.println("Json: " + json);

        for (Object o : json) {
            String value = "";
            JSONObject jsonLineItem = (JSONObject) o;
            for (int i = keysList.size()-1; i >= 0; i--){
                value += jsonLineItem.get(keysList.get(i)).toString();
                value += ", ";
            }
            responseList.add(value);
        }
        GeneratePopupBox.listBox(responseList);
    }
}