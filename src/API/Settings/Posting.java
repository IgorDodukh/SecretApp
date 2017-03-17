package API.Settings;

import FXUI.AppStyles;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Igor Dodukh on 3/17/2017.
 */
public class Posting {

    static String tokenPath = AppStyles.jsonPath + "Products.json";
    static String token = "bWFnZW50b0NsaW50QGR5ZGFjb21wLmJpejtjZGM1NWM1OC1hN2Y2LTRkZmQtODAwMC1hNmQ2NmU1MmYwNTA7MTI3LjAuMC4xOzYzNjI1MzQyNjkzOTE1MjE3NTtFYXRqUHJ3UGVmZlJQWDJackwxb1IxR0NldUk2clJ5Mk1hOTVIcm1YRzMwPQ==";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        JsonReader.readJsonFile(tokenPath);
        sendPost();
    }

    public static void sendPost() throws IOException {
        String postUrl = "https://apiqa05.freestylecommerce.info/V2/Products";// put in your url

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        post.addHeader("x-freestyle-api-auth", token);

        System.out.println("Json: " + JsonReader.getReceivedJsonString());

        StringEntity postingString = new StringEntity(JsonReader.getReceivedJsonString());
        post.setEntity(postingString);
        System.out.println("postingString: " + postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        System.out.println("Response: " + response.getStatusLine());

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println("HttpResponseBody: " + responseString);
    }

}
