package API.Settings;

import FXUI.AppStyles;
import FXUI.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ihor on 2/11/2017.
 */
public class EnvSettings {

    protected static List<String> resourcesPathList = new ArrayList<>();
    List<String> environmentApiUrlList = new ArrayList<>();

    protected static String environmentUrl = null;

    private static String token;

    public String getAuthJsonPath() {
        return authJsonPath;
    }

    private final String authJsonPath = AppStyles.jsonPath + "auth" + File.separator + "qa01Auth.json";

    static String getToken() {
        return token;
    }

    static void setToken(String token) {
        EnvSettings.token = token;
    }

//    @BeforeClass
    public void setupVariables() throws IOException {

        environmentApiUrlList.add("https://apiqa01.freestylecommerce.info/V2/");
        environmentApiUrlList.add("https://apiqa03.freestylecommerce.info/V2/");
        environmentApiUrlList.add("https://apiqa05.freestylecommerce.info/V2/");
        environmentApiUrlList.add("https://api.freestylecommerce.com/V2/");

        resourcesPathList.add("auth");
        resourcesPathList.add("products");
        resourcesPathList.add("customers");

        environmentUrl = environmentApiUrlList.get(Controller.environmentComboBoxIndex);
        System.out.println("ENV url :" + environmentUrl);

        if(environmentUrl == null){
            environmentUrl = environmentApiUrlList.get(0);
        }

        System.out.println("ENV url :" + environmentUrl);

        System.out.println("--filepath is: " + authJsonPath);
        System.out.println("--api url: " + environmentApiUrlList.get(0) +
                "\n" + environmentApiUrlList.get(1) +
                "\n" + environmentApiUrlList.get(2));

        JsonReader.readJsonFile(authJsonPath);
    }

}