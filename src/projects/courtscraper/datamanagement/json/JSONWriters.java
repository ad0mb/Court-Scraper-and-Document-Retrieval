/**
 * @author Adam Bouloudene
 * @summary This contains all the methods used to change .json file elements.
 *
 */

package courtscraper.datamanagement.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static courtscraper.helpers.FolderPaths.*;

public class JSONWriters {

    //this class if for writing data to configs

    private static Gson gson = new Gson();

    public static void loginWriter(String loginType, String username, String password) throws IOException {
        // Opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(LOGINS_JSON_PATH));
        JsonObject logins = gson.fromJson(reader, JsonObject.class);

        for (JsonElement element : logins.get("credentials").getAsJsonArray()) {
            // Grabs key if inputed parameter is the same as something on the api key
            if (element.getAsJsonObject().get("ID").getAsString().equals(loginType)) {
                element.getAsJsonObject().add("username", new JsonPrimitive(username));
                element.getAsJsonObject().add("password", new JsonPrimitive(password));
            }
        }

        FileWriter fileWriter = new FileWriter(LOGINS_JSON_PATH);
        gson.toJson(logins, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    public static void apiWriter(String apiID, String Key) throws IOException {
        // Opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(API_KEYS_JSON_PATH));
        JsonObject apiKeys = gson.fromJson(reader, JsonObject.class);

        for (JsonElement element : apiKeys.get("API Keys").getAsJsonArray()) {
            // Grabs key if inputed parameter is the same as something on the api key
            if (element.getAsJsonObject().get("ID").getAsString().equals(apiID)) {
                element.getAsJsonObject().add("Key", new JsonPrimitive(Key));
            }
        }

        FileWriter fileWriter = new FileWriter(API_KEYS_JSON_PATH);
        gson.toJson(apiKeys, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    public static void configWriter(String config, String input) throws IOException {
        // Opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader(CONFIGS_JSON_PATH));
        JsonObject settings = gson.fromJson(reader, JsonObject.class);

        // Targets the jsonobject containing the configs and executes the necessary changes
        JsonObject target = settings.getAsJsonObject("settings");
        target.add(config, new JsonPrimitive(input));

        // Adds it to the json file
        FileWriter fileWriter = new FileWriter(CONFIGS_JSON_PATH);
        gson.toJson(settings, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
