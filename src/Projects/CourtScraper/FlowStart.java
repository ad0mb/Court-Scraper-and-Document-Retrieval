package CourtScraper;

import CourtScraper.Flows.Courtlink.CourtlinkMain;
import CourtScraper.Setups.Browser.Firefox;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static CourtScraper.Helpers.PastJobsManager.addJob;
import static CourtScraper.Setups.GUI.Interface.frame;
import static CourtScraper.Setups.GUI.JobsPanelElements.JobsTableBox.updateJobsTable;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedFlowType;


public class FlowStart {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static String runStatus = "";
    public static String startTime = "";
    public static String endTime = "";

    public static void StartMainFlowButton() throws IOException, InterruptedException {
        try {
            new Firefox().FirefoxLaunch();

            runStatus = "Finished";
            //adds start time for job
            startTime = dateFormat.format(new Date());

            switch (selectedFlowType) {
                case "Scrape and Retrieve":
                    new CourtlinkMain().CourtlinkFlow();
                    CourtScraper.Helpers.TabManagement.closeAllTabs();
                    CourtScraper.Flows.States.StateParser.stateRetrievalFlow();
                    break;
                case "Scrape Only":
                    new CourtlinkMain().CourtlinkFlow();
                    break;
                case "Retrieve Only":
                    CourtScraper.Flows.States.StateParser.stateRetrievalFlow();
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
            //sets status for recent jobs to failed
            runStatus = "Failed";
        }
        //adds end time for job
        endTime = dateFormat.format(new Date());

        addJob(runStatus);


    }
}
