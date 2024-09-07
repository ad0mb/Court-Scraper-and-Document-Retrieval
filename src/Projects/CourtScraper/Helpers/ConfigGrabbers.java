package CourtScraper.Helpers;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigGrabbers {

    //This helper class is for grabbing any data in configs

    private static Gson gson = new Gson();

    public String[] loginGrabber(String website) throws FileNotFoundException {

        //Defining Array with login credentials
        String[] loginCreds;

        //Opening File and reading the file as Json
        JsonReader reader = new JsonReader(new FileReader("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs\\logins.json"));
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
}
