package courtscraper;

import courtscraper.helpers.Logger;
import courtscraper.setups.browser.Firefox;
import courtscraper.setups.gui.mainpanelelements.MainButtons;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static courtscraper.flows.courtlink.CourtlinkMain.CourtlinkFlow;
import static courtscraper.flows.states.StateParser.stateRetrievalFlow;
import static courtscraper.helpers.TabManagement.closeAllTabs;
import static courtscraper.setups.gui.jobspanelelements.JobsTableBox.updateJobsTable;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedFlowType;


public class FlowStart extends MainButtons {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    public static Logger runLogger = Logger.getInstance();
    public static WebDriver driver;

    public static void StartMainFlowButton() throws IOException, InterruptedException {

        try {
            new Firefox().FirefoxLaunch();

            switch (selectedFlowType) {
                case "Scrape and Retrieve":
                    CourtlinkFlow();
                    closeAllTabs();
                    stateRetrievalFlow();
                    break;
                case "Scrape Only":
                    CourtlinkFlow();
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
