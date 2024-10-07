package courtscraper;

import courtscraper.helpers.JobsManager;
import courtscraper.helpers.TabManager;
import courtscraper.setups.browser.Firefox;
import courtscraper.setups.gui.mainpanelelements.MainButtons;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static courtscraper.flows.courtlink.CourtlinkMain.courtLinkFlow;
import static courtscraper.flows.states.StateParser.stateRetrievalFlow;
import static courtscraper.setups.gui.jobspanelelements.JobsTableBox.updateJobsTable;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedFlowType;


public class FlowStart extends MainButtons {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    public static WebDriver driver;
    public static TabManager tabManager = new TabManager();
    private static JobsManager jobsManager;
    private static String runStatus;

    public static void StartMainFlowButton() throws IOException, InterruptedException {
        jobsManager = new JobsManager();
        runStatus = runLogger.updateInfo("runStatus","Finished");

        try {
            new Firefox().FirefoxLaunch();

            switch (selectedFlowType) {
                case "Scrape and Retrieve":
                    courtLinkFlow();
                    tabManager.closeAllTabs();
                    stateRetrievalFlow();
                    break;
                case "Scrape Only":
                    courtLinkFlow();
                    break;
                case "Retrieve Only":
                    stateRetrievalFlow();
                    break;
            }
        } catch(Exception e) {
            runStatus = runLogger.updateInfo("runStatus", "Failed");
            runLogger.logError(e);
            e.printStackTrace();
        }

        jobsManager.addJob(runStatus);
        updateJobsTable();

        driver.quit(); //to ensure geckodriver is closed in turn saving computer resources
    }
}
