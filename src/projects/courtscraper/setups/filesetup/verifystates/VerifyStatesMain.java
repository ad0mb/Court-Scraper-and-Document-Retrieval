package courtscraper.setups.filesetup.verifystates;

import courtscraper.setups.filesetup.VerifyFilesMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.stateElements;

public class VerifyStatesMain extends VerifyFilesMain {

    private static final Map<String, String[]> countyFileMap = Map.of(
            "California", new String[]{"San Diego SO"},
            "Florida", new String[]{"Whole State", "Miami Dade"},
            "Texas", new String[]{"Whole State", "Harris"},
            "Minnesota", new String[]{"Whole State"},
            "New York", new String[]{"Kings"}
    );

    public static void verifyStatesAndCounties() throws IOException {

        //loop to check for state folder and its documents
        for (int i = 1; i<stateElements.length; i++) {
            File chosenDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + stateElements[i]);

            if (!chosenDirectory.exists()) {
                chosenDirectory.mkdir();
            }

            //loop to check for county folder and its documents
            for (int p = 0; p< countyFileMap.get(stateElements[i]).length; p++) {
                File countyDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + stateElements[i] + "\\" + countyFileMap.get(stateElements[i])[p]);

                if (!countyDirectory.exists()) {
                    countyDirectory.mkdir();
                }

                String[] countyFiles = {"\\Downloaded", "\\downloadedtest.csv", "\\temp.csv"};

                //loop to check for files that are supposed to be in county
                for (String file : countyFiles) {
                    File chosenCountyDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + stateElements[i] + "\\" + countyFileMap.get(stateElements[i])[p] + file);

                    //if chosen directory is downloaded then create a directory or else create a file
                    if (!chosenCountyDirectory.exists() && file.equals("\\Downloaded")) {
                        chosenCountyDirectory.mkdir();
                    } else if (!chosenCountyDirectory.exists() && !file.equals("\\Downloaded")) {
                        chosenCountyDirectory.createNewFile();

                        FileWriter fileWriter = new FileWriter(chosenCountyDirectory);
                        fileWriter.write("Case Number,Date Filed,Keywords Used,Attorney Terms,Date Grabbed,State,County,Documents,Errors");
                        fileWriter.flush();
                        fileWriter.close();
                    }
                }
            }


        }
    }
}
