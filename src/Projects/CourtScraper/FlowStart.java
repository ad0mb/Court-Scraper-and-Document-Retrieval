package CourtScraper;

import CourtScraper.Flows.Courtlink.CourtlinkMain;
import CourtScraper.Setups.Browser.Firefox;

import java.io.IOException;

import static CourtScraper.Helpers.PastJobsManager.addJob;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedFlowType;


public class FlowStart {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    private static String runStatus = "";

    public static void StartMainFlowButton() throws IOException, InterruptedException {
        try {
            new Firefox().FirefoxLaunch();

            runStatus = "Finished";

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
            runStatus = "Failed";
        }
        addJob(runStatus);


    }
}
