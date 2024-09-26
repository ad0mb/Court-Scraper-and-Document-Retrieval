/**
 * @author Adam Bouloudene
 * @summary This class contains all the methods used to grab data from different .json files. Currently, it contains capabilities to from logins.json, configs.json, and API Keys.json. These data in these files serve various purposes throughout the code.
 *
 * @todo Remove the extra return on loginGrabber.
 */

package courtscraper.datamanagement.json;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static courtscraper.helpers.FolderPaths.*;

public class JSONGrabbers {

    private static Gson gson = new Gson();

    public String[] loginGrabber(String website) throws FileNotFoundException {

        String[] loginCreds; // Defining Array with login credentials

        //Opening File and reading the file as Json
        JsonReader reader = new JsonReader(new FileReader(LOGINS_JSON_PATH));
        JsonObject logins = gson.fromJson(reader, JsonObject.class);

        for (JsonElement element : logins.get("credentials").getAsJsonArray()) {
            // Finds credentials matching input
            if (element.getAsJsonObject().get("ID").getAsString().equals(website)) {

                // Grabs username and passwords and puts them in an array
                String username = element.getAsJsonObject().get("username").getAsString();
                String password = element.getAsJsonObject().get("password").getAsString();
                loginCreds = new String[]{username, password};
                return loginCreds;
            }

        }
        return loginCreds = new String[]{null};
    }

    public String apiGrabber(String apiID) throws FileNotFoundException {
        // Opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(API_KEYS_JSON_PATH));
        JsonObject apiKeys = gson.fromJson(reader, JsonObject.class);

        for (JsonElement element : apiKeys.get("API Keys").getAsJsonArray()) {
            // Grabs key if inputed parameter is the same as something on the api key
            if (element.getAsJsonObject().get("ID").getAsString().equals(apiID)) {
                return element.getAsJsonObject().get("Key").getAsString();
            }
        }
        return null;
    }

    public String configGrabber(String config) throws FileNotFoundException {
        // Opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(CONFIGS_JSON_PATH));
        JsonObject settings = gson.fromJson(reader, JsonObject.class);


        return settings.getAsJsonObject("settings").get(config).getAsString(); // Returns the targetted config

    }
}
