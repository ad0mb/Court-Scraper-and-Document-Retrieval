package CourtScraper;

import CourtScraper.Flows.Courtlink.CourtlinkMain;
import CourtScraper.Setups.Browser.Firefox;

import java.io.IOException;



public class FlowStart {

    //this is the flow start for the entire process, it can be divided up into specific flow configuration based on users input

    public static void StartMainFlowButton() throws IOException, InterruptedException {

        new Firefox().FirefoxLaunch();
        new CourtlinkMain().CourtlinkFlow();

        //closes all open tabs
        CourtScraper.Helpers.TabManagement.closeAllTabs();

        CourtScraper.Flows.States.StateParser.stateRetrievalFlow();

    }
}
