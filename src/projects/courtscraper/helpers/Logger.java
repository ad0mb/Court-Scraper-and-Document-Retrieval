package courtscraper.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static courtscraper.FlowStart.driver;
import static courtscraper.helpers.FolderPaths.CRASH_LOGS_FOLDER_PATH;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.*;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.search;

public class Logger {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String startTime;
    private String endTime;
    private String runStatus;

    public Logger() {

    }

    public void logError(Exception e) throws IOException {
        //specific folder name format (new format because folders cant accept other form
        String folderName = new SimpleDateFormat("dd-MM-yyyy HH.MM.SS").format(new Date());

        //create crash folder
        File crashFolder = new File(CRASH_LOGS_FOLDER_PATH + "\\" + folderName);
        crashFolder.mkdir();

        //create info text file
        File crashInfoFile = new File(CRASH_LOGS_FOLDER_PATH + "\\" + folderName + "\\info.txt");
        crashInfoFile.createNewFile();

        //create html file
        File crashHtmlFile = new File(CRASH_LOGS_FOLDER_PATH + "\\" + folderName + "\\crashpage.html");
        crashHtmlFile.createNewFile();

        //write error to info txt
        FileWriter infoWriter = new FileWriter(crashInfoFile, true);
        infoWriter.write("Start Time: " + startTime + "\nEnd Time: " + endTime + "\nSearch: " + search.getText() + "\nSelected State: " + selectedStateMain + "\nSelected County: " + selectedCountyMain + "\nSelected Flow Type: " + selectedFlowType + "\n " + "\n");
        e.printStackTrace(new PrintWriter(infoWriter));
        infoWriter.flush();
        infoWriter.close();

        //write html source to html file
        FileWriter crashWriter = new FileWriter(crashHtmlFile);
        crashWriter.write(driver.getPageSource());
        crashWriter.flush();
        crashWriter.close();

    }

    public String updateInfo(String type, String info) {
        switch (type) {
            case "startTime":
                startTime = info;
                break;
            case "endTime":
                endTime = info;
                break;
            case "runStatus":
                runStatus = info;
                break;
        }
        return info;
    }


}
