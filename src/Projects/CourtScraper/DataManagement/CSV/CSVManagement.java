package CourtScraper.DataManagement.CSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVManagement {

    //this will contain the methods to manage and modify csv files

    public static void deleteLine(String tempFilePath, String tempFolderPath, String line) throws IOException {
        File newTempFile = new File(tempFolderPath + "\\temp2temp.csv");
        newTempFile.createNewFile();
        File oldTempFile = new File(tempFilePath);

        FileWriter writer = new FileWriter(tempFolderPath + "\\temp2temp.csv");

        List<String> rows = Files.readAllLines(Path.of(tempFilePath));

        for (String row : rows) {
            if (row.equals(line)) {
                continue;
            }
            writer.write(row + "\n");
        }

        writer.flush();
        writer.close();

        oldTempFile.delete();
        newTempFile.renameTo(oldTempFile);

    }
}
