package Projects;

import Projects.Flows.Courtlink.CourtlinkMain;
import Projects.Setups.Browser.Firefox;

import java.io.IOException;

import static Projects.Flows.States.StateParser.stateRetrievalFlow;
import static Projects.Helpers.TabManagement.closeAllTabs;

public class FlowStart {

    public static void StartMainFlowButton() throws IOException, InterruptedException {

        new Firefox().FirefoxLaunch();
        new CourtlinkMain().CourtlinkFlow();

        //closes all open tabs
        closeAllTabs();

        stateRetrievalFlow();

    }
}
