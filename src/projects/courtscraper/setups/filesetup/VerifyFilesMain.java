package courtscraper.setups.filesetup;

import courtscraper.StartGUI;

import java.io.File;
import java.io.IOException;

import static courtscraper.setups.filesetup.verifyconfig.VerifyConfigsMain.verifyConfigs;
import static courtscraper.setups.filesetup.verifylogs.VerifyLogsMain.verifyLogs;
import static courtscraper.setups.filesetup.verifystates.VerifyStatesMain.verifyStatesAndCounties;

public class VerifyFilesMain extends StartGUI {

    public static void  verifyMain() throws IOException {
        verifyPrimaryFolders();

        verifyConfigs();

        verifyStatesAndCounties();

        verifyLogs();


    }


    private static void verifyPrimaryFolders() {
        //array containing all main files names to check (all subclasses follow same format)
        String[] primaryDirectories = {"", "\\Configs", "\\States", "\\Temp", "\\Logs"};

        for (String file : primaryDirectories) {
            File chosenDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper" + file);

            //if filename does not exist make the directory for it (all subclasses follow same format)
            if (!chosenDirectory.exists()) {
                chosenDirectory.mkdir();
            }
        }
    }
}


