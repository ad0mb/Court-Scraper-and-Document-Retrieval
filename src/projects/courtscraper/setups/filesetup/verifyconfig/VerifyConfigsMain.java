package courtscraper.setups.filesetup.verifyconfig;

import courtscraper.setups.filesetup.VerifyFilesMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class VerifyConfigsMain extends VerifyFilesMain {

    private static Map<String, String> stringToWrite = Map.of(
            "\\API Keys.json", "{\"API Keys\":[{\"ID\":\"2captcha\",\"Key\":\"\"}]}",
            "\\Configs.json", "{\"settings\":{\"captcha\":\"2captcha\",\"headless\":\"false\",\"manual search input\":\"false\"}}",
            "\\logins.json", "{\"credentials\":[{\"ID\":\"Courtlink\",\"username\":\"\",\"password\":\"\"},{\"ID\":\"TexasHarris\",\"username\":\"\",\"password\":\"\"},{\"ID\":\"FloridaMiami Dade\",\"username\":\"\",\"password\":\"\"}]}"
    );

    public static void verifyConfigs() throws IOException {
        String[] configFiles = {"\\Captchas", "\\API Keys.json", "\\Configs.json", "\\logins.json"};

        for (String file : configFiles) {
            File chosenDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs" + file);

            //if chosen directory is captchas then create a directory or else create a file
            if (!chosenDirectory.exists() && file.equals("\\Captchas")) {
                chosenDirectory.mkdir();
            } else if (!chosenDirectory.exists() && !file.equals("\\Captchas")) {
                FileWriter fileWriter = new FileWriter(chosenDirectory);
                fileWriter.write(stringToWrite.get(file));
                fileWriter.flush();
                fileWriter.close();
            }
        }
    }
}
