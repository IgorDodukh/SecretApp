package API.Settings;

import FXUI.AppStyles;
import Settings.GenerateRandomData;
import Settings.GetPropertyValues;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ihor on 2/11/2017.
 */
public class JsonReader extends EnvSettings {
    public static String getReceivedJsonString() {
        return receivedJsonString;
    }

    public static String receivedJsonString;

    public String getCreatedItemFullName() {
        return createdItemFullName;
    }

    private static String createdItemFullName = "";

    private RequestsBuilder requestsBuilder = new RequestsBuilder();

    public static void readJsonFile(String jsonPath) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(jsonPath));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.toJSONString());
            receivedJsonString = jsonObject.toJSONString();
            System.out.println("Received JSON string: " +  receivedJsonString);
        } catch (IOException | ParseException e) {
            e.printStackTrace();        
        }
    }

    static void writeJsonFile(String jsonPath, JSONObject jsonObject) {
        try (FileWriter file = new FileWriter(jsonPath)) {
            file.write(jsonObject.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeJsonFields(String jsonPath, List<String> keys, List<String> values) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(jsonPath));

            JSONObject jsonObject = (JSONObject) obj;
            for (String value : keys) {
                jsonObject.put(value, values.get(keys.indexOf(value)));
                System.out.println("Fields from array: " + value);
                System.out.println("Parameters: " + values.get(keys.indexOf(value)));
            }

            writeJsonFile(jsonPath, jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateJsonForPOST(String selectedResourceValue) {
        int randomIntLength = Integer.parseInt(GetPropertyValues.randomValueProperty);
        String randomString = new GenerateRandomData().generateRandomNumber(randomIntLength);

        String createdProductSKU = GetPropertyValues.productSKU + "-" + randomString;
        String createdProductName = GetPropertyValues.productName + "-" + randomString;

        String createdCustomerFirstName = GetPropertyValues.customerFirstName + "-" + randomString;
        String createdCustomerLastName = GetPropertyValues.customerLastName + "-" + randomString;

        String createdSupplierName = GetPropertyValues.supplierName + "-" + randomString;
        String createdBinName = GetPropertyValues.binName + "-" + randomString;
        String createdWarehouseName = GetPropertyValues.warehouseName + "-" + randomString;

        List<String> productValues = new ArrayList<>();
        productValues.add(createdProductName);
        productValues.add(createdProductSKU);

        List<String> customerValues = new ArrayList<>();
        customerValues.add(createdCustomerFirstName);
        customerValues.add(createdCustomerLastName);
        customerValues.add(null);

        List<String> supplierValues = new ArrayList<>();
        supplierValues.add(createdSupplierName);

        List<String> binValues = new ArrayList<>();
        supplierValues.add(createdBinName);

        List<String> warehouseValues = new ArrayList<>();
        warehouseValues.add(createdWarehouseName);

        String jsonForPOST = AppStyles.jsonPath + selectedResourceValue + ".json";
        System.out.println("ENV url: " + EnvSettings.getEnvironmentUrl());

        if(selectedResourceValue.contains("Product")){
            createdItemNameCreator(productValues);
            System.out.println("***" + jsonForPOST + "\n***" + requestsBuilder.productKeysList + "\n***" + productValues);
            JsonReader.changeJsonFields(jsonForPOST, requestsBuilder.productKeysList, productValues);
        } else if (selectedResourceValue.contains("Customer")) {
            createdItemNameCreator(customerValues);
            System.out.println("***" + jsonForPOST + "\n***" + requestsBuilder.customerKeysList + "\n***" + customerValues);
            JsonReader.changeJsonFields(jsonForPOST, requestsBuilder.customerKeysList.subList(0, requestsBuilder.customerKeysList.size() - 1), customerValues);
        } /*else if (selectedResourceValue.contains("Order")){
            createdItemNameCreator(productValues);
        } */else if (selectedResourceValue.contains("Suppliers")){
            createdItemNameCreator(supplierValues);
            System.out.println("***" + jsonForPOST + "\n***" + requestsBuilder.suppliersKeysList + "\n***" + supplierValues);
            JsonReader.changeJsonFields(jsonForPOST, requestsBuilder.suppliersKeysList, supplierValues);
        } else if (selectedResourceValue.contains("Bin")){
            createdItemNameCreator(binValues);
            System.out.println("***" + jsonForPOST + "\n***" + requestsBuilder.binsKeysList + "\n***" + binValues);
            JsonReader.changeJsonFields(jsonForPOST, requestsBuilder.binsKeysList, binValues);
        } else if (selectedResourceValue.contains("Warehouse")){
            createdItemNameCreator(warehouseValues);
            System.out.println("***" + jsonForPOST + "\n***" + requestsBuilder.warehousesKeysList + "\n***" + warehouseValues);
            JsonReader.changeJsonFields(jsonForPOST, requestsBuilder.warehousesKeysList, warehouseValues);
        }
        JsonReader.readJsonFile(jsonForPOST);
    }

    private void createdItemNameCreator(List<String> itemValues) {
        createdItemFullName = "";
        for (String value : itemValues) {
            if(value != null){
                createdItemFullName += value;
                if(itemValues.size()-1 > itemValues.indexOf(value))
                    createdItemFullName += " ";
            }
        }
    }
}
