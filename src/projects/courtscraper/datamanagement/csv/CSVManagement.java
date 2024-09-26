/**
 * @author Adam Bouloudene
 * @summary This contains various methods that serve specific functionalities when dealing with .csv files.
 *
 * @todo Add exclusion of empty lines in the loops if statement on deleteLine().
 */

package courtscraper.datamanagement.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVManagement {

    public static void deleteLine(String tempFilePath, String tempFolderPath, String line) throws IOException {
        File newTempFile = new File(tempFolderPath + "\\temp2temp.csv"); // Defines new file
        newTempFile.createNewFile(); // Creates new file
        File oldTempFile = new File(tempFilePath);

        FileWriter writer = new FileWriter(tempFolderPath + "\\temp2temp.csv");

        List<String> rows = Files.readAllLines(Path.of(tempFilePath));

        // Loops through all the files and if it runs into any lines equal to the desired line to delete it skips adding that line
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
