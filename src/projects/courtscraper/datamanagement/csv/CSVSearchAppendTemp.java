package courtscraper.datamanagement.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static courtscraper.flows.courtlink.courtlinksearchconfig.CourtlinkSearchConfigMain.processedInputs;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;


public class CSVSearchAppendTemp {

    //this will contain the search flows specific appending variables

    public static String tempFilePath = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\States\\" + selectedStateMain + "\\" + selectedCountyMain + "\\temp.csv";

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
        appendableString += selectedStateMain + ",";
        //added selected county
        appendableString += selectedCountyMain + ",";
        //adds documents selected
        appendableString += "" + ",";


        csvFile.append("\n");
        csvFile.append(appendableString.substring(0, appendableString.length() - 1));

        csvFile.flush();
        csvFile.close();
    }
}
