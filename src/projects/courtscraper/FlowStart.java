package courtscraper;

import courtscraper.flows.courtlink.CourtlinkMain;
import courtscraper.helpers.Logger;
import courtscraper.setups.browser.Firefox;

import java.io.IOException;

import static courtscraper.flows.states.StateParser.stateRetrievalFlow;
import static courtscraper.helpers.TabManagement.closeAllTabs;
import static courtscraper.setups.gui.jobspanelelements.JobsTableBox.updateJobsTable;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedFlowType;


public class FlowStart {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    private static Logger runLogger = new Logger();

    public static void StartMainFlowButton() throws IOException, InterruptedException {

        try {
            new Firefox().FirefoxLaunch();

            switch (selectedFlowType) {
                case "Scrape and Retrieve":
                    new CourtlinkMain().CourtlinkFlow();
                    closeAllTabs();
                    stateRetrievalFlow();
                    break;
                case "Scrape Only":
                    new CourtlinkMain().CourtlinkFlow();
                    break;
                case "Retrieve Only":
                    stateRetrievalFlow();
                    break;
            }
        } catch(Exception e) {
            runLogger.logError(e);
            e.printStackTrace();
        }

        runLogger.logJob();
        updateJobsTable();


    }
}
