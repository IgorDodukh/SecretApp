package application.api.settings;

import application.fxui.AppStyles;
import application.fxui.Controller;
import application.fxui.DialogBoxGenerator;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static application.api.settings.EnvSettings.*;
import static application.api.settings.JsonReader.writeJsonFile;

/**
 * Created by Ihor on 2/11/2017.
 */
public class RequestsBuilder {

    private static int getResponseStatusCode() {
        return responseStatusCode;
    }

    private static void setResponseStatusCode(int responseStatusCode) {
        RequestsBuilder.responseStatusCode = responseStatusCode;
    }

    private static JsonReader jsonReader = new JsonReader();
    public static int responseStatusCode;

    private ArrayList<String> responseList;
    public static ArrayList<String> guidList;

    final List<String> productKeysList = new ArrayList<>(
            Arrays.asList("ProductName", "ProductSku", "AvailableQuantity"));
    final List<String> customerKeysList = new ArrayList<>(
            Arrays.asList("LastName", "FirstName", "CustomerNumber", "Postcode"));
    final List<String> ordersKeysList = new ArrayList<>(
            Arrays.asList("OrderNumber"));
    final List<String> suppliersKeysList = new ArrayList<>(
            Arrays.asList("Name"));
    final List<String> warehousesKeysList = new ArrayList<>(
            Arrays.asList("WarehouseName", "ZipCode"));
    final List<String> binsKeysList = new ArrayList<>(
            Arrays.asList("BinName"));
    final List<String> salesChannelsKeysList = new ArrayList<>(
            Arrays.asList("UniqueName", "ChannelType"));
    final List<String> shippingMethodsKeysList = new ArrayList<>(
            Arrays.asList("Name", "ServiceDescription"));

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
                DialogBoxGenerator.failedPopupBox("Unexpected response status: " +
                        String.valueOf(response.getStatus()) + " " +
                        response.getStatusInfo() +
                        "\n\napplication.api token was not received." +
                        "\nPlease check your credentials and enable 'application.api mode' button once more.");
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
                DialogBoxGenerator.successPopupBox(response.getStatus() + " " + response.getStatusInfo() +
                        "\nNew item has been created");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendPost(String targetUrl, String jsonEntity) throws IOException {
        Runnable runnable = () -> {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(targetUrl);
            post.addHeader("x-freestyle-api-auth", getToken());

            StringEntity postingString = null;
            try {
                postingString = new StringEntity(jsonEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse response = null;
            try {
                response = httpClient.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity entity = response.getEntity();
            String responseString = null;
            try {
                responseString = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONParser parser = new JSONParser();
            JSONObject json = null;
            try {
                json = (JSONObject) parser.parse(responseString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("HttpResponseBody: " + responseString);

            getJsonGuidParameter(json);
            String responseStatusReason = response.getStatusLine().getReasonPhrase();

            responseStatusCode = response.getStatusLine().getStatusCode();

            Controller.setResponseStatus(responseStatusCode + " " + responseStatusReason);

            if(responseStatusCode == 200 || responseStatusCode == 201){
                String resourceName = Controller.getSelectedResourceValue();
                DialogBoxGenerator.successPopupBox(responseStatusCode + " " + responseStatusReason +
                        "\nNew " + resourceName.substring(0,resourceName.length()-1) +
                        " has been created successfully.\n" + jsonReader.getCreatedItemFullName());
            } else {
                DialogBoxGenerator.failedPopupBox(responseStatusCode + " " + responseStatusReason +
                        "\nResponse body: " + responseString);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
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
                getJsonArrayParameters(productKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Customer")) {
                getJsonArrayParameters(customerKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Order")){
                getJsonArrayParameters(ordersKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Suppliers")){
                getJsonArrayParameters(suppliersKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Bin")){
                getJsonArrayParameters(binsKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Warehouse")){
                getJsonArrayParameters(warehousesKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Channels")){
                getJsonArrayParameters(salesChannelsKeysList, (JSONArray) obj);
            } else if (targetUrl.contains("Methods")){
                getJsonArrayParameters(shippingMethodsKeysList, (JSONArray) obj);
            }
            Controller.setResponseStatus(response.getStatus() + " " + response.getStatusInfo());

            getJsonGuidParameters((JSONArray) obj);

            DialogBoxGenerator.resultsListBox(responseList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private ArrayList<String> getJsonArrayParameters(List<String> keysList, JSONArray obj) {
        responseList = new ArrayList<>();
        JSONArray json = obj;

        for (Object o : json) {
            String value = "";
            JSONObject jsonLineItem = (JSONObject) o;
            for (int i = keysList.size()-1; i >= 0; i--){
                /**
                 * Avoid catching null pointer when parameter was not found in the response
                 */
                try {
                    value += jsonLineItem.get(keysList.get(i)).toString();
                    if (i > 0)
                        value += ", ";
                } catch (NullPointerException e) {
                    DialogBoxGenerator.failedPopupBox(String.valueOf(e.getCause()));
                }

            }
            responseList.add(value);
        }
        return responseList;
    }

    private ArrayList<String> getJsonGuidParameter(JSONObject object) {
        guidList = new ArrayList<>();
        String value = "";
        if (object.get("Id") != null) {
            value = object.get("Id").toString();
        } else if (object.get("SupplierId") != null) {
            value = object.get("SupplierId").toString();
        } else if (object.get("ChannelId") != null) {
            value = object.get("ChannelId").toString();
        } else if (object.get("OrderNumber") != null) {
            value = object.get("OrderNumber").toString();
        } else {
            value = object.get("id").toString();
        }

        guidList.add(value);
        return guidList;
    }
    private ArrayList<String> getJsonGuidParameters(JSONArray obj) {
        guidList = new ArrayList<>();
        JSONArray json = obj;


        for (Object o : json) {
            String value = "";
            JSONObject jsonLineItem = (JSONObject) o;
            if (jsonLineItem.get("Id") != null) {
                value += jsonLineItem.get("Id").toString();
            } else if (jsonLineItem.get("SupplierId") != null) {
                value += jsonLineItem.get("SupplierId").toString();
            } else if (jsonLineItem.get("ChannelId") != null) {
                value += jsonLineItem.get("ChannelId").toString();
            } else if (jsonLineItem.get("OrderNumber") != null) {
                value += jsonLineItem.get("OrderNumber").toString();
            } else value = jsonLineItem.get("id").toString();

            guidList.add(value);
        }
        return guidList;
    }
}