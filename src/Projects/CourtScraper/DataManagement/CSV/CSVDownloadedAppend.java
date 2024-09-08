package CourtScraper.DataManagement.CSV;

import java.io.FileWriter;
import java.io.IOException;


public class CSVDownloadedAppend {

    //this will append data to the downloaded folder to prevent duplicate downloads

    public static void appendToDownloaded(String row) throws IOException {
        FileWriter csvFile = new FileWriter(CourtScraper.Helpers.CheckIfRetrieved.downloadedFilePath, true);

        csvFile.append("\n");
        csvFile.append(row);

        csvFile.flush();
        csvFile.close();
    }
}
