package courtscraper.setups.filesetup.verifylogs;

import courtscraper.setups.filesetup.VerifyFilesMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VerifyLogsMain extends VerifyFilesMain {

    public static void verifyLogs() throws IOException {

        String[] loggingFiles = {"\\Crash Logs", "\\Past Jobs"};

        for (String file : loggingFiles) {
            File chosenDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Logs" + file);

            if (!chosenDirectory.exists()) {
                chosenDirectory.mkdir();
            }
        }

        String[] pastJobsFiles = {"\\AllJobs.csv", "\\PastJobs.csv"};

        for (String file : pastJobsFiles) {
            File chosenPastJobsDirectory = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Logs\\Past Jobs" + file);

            if (!chosenPastJobsDirectory.exists()) {
                chosenPastJobsDirectory.createNewFile();

                FileWriter fileWriter = new FileWriter(chosenPastJobsDirectory);
                fileWriter.write("Keywords,Date Range,State,Counties,Status,Job Type,Start Time,End Time");
                fileWriter.flush();
                fileWriter.close();
            }
        }
    }
}
