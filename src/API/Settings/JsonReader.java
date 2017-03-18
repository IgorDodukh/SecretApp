package API.Settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ihor on 2/11/2017.
 */
public class JsonReader extends EnvSettings {
    public static String getReceivedJsonString() {
        return receivedJsonString;
    }

    public static String receivedJsonString;

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


}
