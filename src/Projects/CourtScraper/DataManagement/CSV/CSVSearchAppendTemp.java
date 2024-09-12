package CourtScraper.DataManagement.CSV;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static CourtScraper.Flows.Courtlink.CourtlinkSearchConfig.CourtlinkSearchConfigMain.processedInputs;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;


public class CSVSearchAppendTemp {

    //this will contain the search flows specific appending variables

    public static String tempFilePath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + selectedState + "\\" + selectedCounty + "\\temp.csv";

    //declarations of current date and time
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date date = new Date();

    public void courtLinkAppendToTemp(List<String> toBeAppended) throws IOException {

        String appendableString = "";

        FileWriter csvFile = new FileWriter(tempFilePath, true);

        //adds case number and date of case filed from the courtlink site
        for (int i = toBeAppended.size()-1; i>=0; i--) {
            appendableString += toBeAppended.get(i).replace("\"", "") + ",";
        }

        //adds keywords used in search
        appendableString += processedInputs[0] + ",";
        //adds attorney terms used in search
        appendableString += processedInputs[1] + ",";
        //adds current date and time from which the document was grabbed from
        appendableString += dateFormat.format(date) + ",";
        //adds selected state
        appendableString += selectedState + ",";
        //added selected county
        appendableString += selectedCounty + ",";
        //adds documents selected
        appendableString += "" + ",";


        csvFile.append("\n");
        csvFile.append(appendableString.substring(0, appendableString.length() - 1));

        csvFile.flush();
        csvFile.close();
    }
}
