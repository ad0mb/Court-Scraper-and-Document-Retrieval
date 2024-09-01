package Projects.DataManagement.CSV;

import java.io.FileWriter;
import java.io.IOException;

import static Projects.Helpers.CheckIfRetrieved.downloadedFilePath;

public class CSVDownloadedAppend {

    public static void appendToDownloaded(String row) throws IOException {
        FileWriter csvFile = new FileWriter(downloadedFilePath, true);

        csvFile.append("\n");
        csvFile.append(row);
    }
}
