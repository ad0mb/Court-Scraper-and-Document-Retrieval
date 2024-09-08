package CourtScraper;

import CourtScraper.Flows.Courtlink.CourtlinkMain;
import CourtScraper.Setups.Browser.Firefox;

import java.io.IOException;

import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedFlowType;


public class FlowStart {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    public static void StartMainFlowButton() throws IOException, InterruptedException {
        new Firefox().FirefoxLaunch();

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


    }
}
