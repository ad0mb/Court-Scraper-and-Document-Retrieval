package CourtScraper.Flows.States;


import CourtScraper.Flows.States.Texas.Dallas.DallasCounty;

import java.io.FileNotFoundException;

import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class StateSelect extends StateParser {

    public static void stateFilter(String caseNumber) throws FileNotFoundException, InterruptedException {
        Thread.sleep(500);
        switch (selectedState) {
            case "California":
                if (selectedCounty.equals("Whole State")) {
                }
            case "Florida":
                if (selectedCounty.equals("Miami-Dade")) {
                }
            case "New York":
                if (selectedCounty.equals("New York BKQ")) {
                }
            case "Texas":
                if (selectedCounty.equals("Harris")) {
                    CourtScraper.Flows.States.Texas.Harris.HarrisCounty.harrisMain(caseNumber);
                } else if (selectedCounty.equals("Dallas")) {
                    new DallasCounty().dallasMain();
                }

        }
    }

}
