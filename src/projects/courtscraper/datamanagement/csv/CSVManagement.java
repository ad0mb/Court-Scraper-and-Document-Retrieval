package courtscraper.datamanagement.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVManagement {

    //this will contain the methods to manage and modify csv files

    public static void deleteLine(String tempFilePath, String tempFolderPath, String line) throws IOException {
        //defines and creates temporary file to rewrite to
        File newTempFile = new File(tempFolderPath + "\\temp2temp.csv");
        newTempFile.createNewFile();

        File oldTempFile = new File(tempFilePath);
        FileWriter writer = new FileWriter(tempFolderPath + "\\temp2temp.csv");

        List<String> rows = Files.readAllLines(Path.of(tempFilePath));

        //loops through the rows skipping ones that match the line paramater
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

    public static void appendToCSV(String row, String filePath) throws IOException {
        FileWriter csvFile = new FileWriter(filePath, true);

        csvFile.append("\n");
        csvFile.append(row);

        csvFile.flush();
        csvFile.close();
    }
}
