package courtscraper.datamanagement.json;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static courtscraper.helpers.FolderPaths.*;

public class JSONGrabbers {

    //this class is for grabbing any data in configs

    private static Gson gson = new Gson();

    public String[] loginGrabber(String website) throws FileNotFoundException {
        String[] loginCreds; //defining Array with login credentials

        //opening File and reading the file as Json
        JsonReader reader = new JsonReader(new FileReader(LOGINS_JSON_PATH));
        JsonObject logins = gson.fromJson(reader, JsonObject.class);

        for (JsonElement element : logins.get("credentials").getAsJsonArray()) {
            //finds credentials matching input
            if (element.getAsJsonObject().get("ID").getAsString().equals(website)) {

                //grabs username and passwords and puts them in an array
                String username = element.getAsJsonObject().get("username").getAsString();
                String password = element.getAsJsonObject().get("password").getAsString();
                loginCreds = new String[]{username, password};
                return loginCreds;
            }

        }
        return loginCreds = new String[]{null};
    }

    public String apiGrabber(String apiID) throws FileNotFoundException {
        //opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(API_KEYS_JSON_PATH));
        JsonObject apiKeys = gson.fromJson(reader, JsonObject.class);

        for (JsonElement element : apiKeys.get("API Keys").getAsJsonArray()) {
            //grabs key if inputed parameter is the same as something on the api key
            if (element.getAsJsonObject().get("ID").getAsString().equals(apiID)) {
                return element.getAsJsonObject().get("Key").getAsString();
            }
        }
        return null;
    }

    public String configGrabber(String config) throws FileNotFoundException {
        //opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(CONFIGS_JSON_PATH));
        JsonObject settings = gson.fromJson(reader, JsonObject.class);

        return settings.getAsJsonObject("settings").get(config).getAsString(); //returns the targetted config

    }
}
