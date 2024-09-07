package CourtScraper;

import CourtScraper.Flows.Courtlink.CourtlinkMain;
import CourtScraper.Setups.Browser.Firefox;

import java.io.IOException;



public class FlowStart {

    public static void StartMainFlowButton() throws IOException, InterruptedException {

        new Firefox().FirefoxLaunch();
        new CourtlinkMain().CourtlinkFlow();

        //closes all open tabs
        CourtScraper.Helpers.TabManagement.closeAllTabs();

        CourtScraper.Flows.States.StateParser.stateRetrievalFlow();

    }
}
