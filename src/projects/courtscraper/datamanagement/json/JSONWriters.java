package courtscraper.datamanagement.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriters {

    private static Gson gson = new Gson();

    public static void configWriter(String config, String input) throws IOException {
        //opens file as JsonArray
        JsonReader reader = new JsonReader(new FileReader("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs\\Configs.json"));
        JsonObject settings = gson.fromJson(reader, JsonObject.class);

        //targets the jsonobject containing the configs and excecutes the necessary changes
        JsonObject target = settings.getAsJsonObject("settings");
        target.add(config, new JsonPrimitive(input));

        //adds it to the json file
        FileWriter fileWriter = new FileWriter("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs\\Configs.json");
        gson.toJson(settings, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
