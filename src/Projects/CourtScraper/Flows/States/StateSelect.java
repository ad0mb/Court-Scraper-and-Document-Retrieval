package CourtScraper.Flows.States;


import CourtScraper.Flows.States.Texas.Dallas.DallasCounty;

import java.io.FileNotFoundException;

import static CourtScraper.Flows.States.Florida.MiamiDade.MiamiDadeCounty.miamiDadeMain;
import static CourtScraper.Flows.States.Texas.Harris.HarrisCounty.harrisMain;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

public class StateSelect extends StateParser {

    //This file contains the filter that decides which county under which state to search

    public static void stateFilter(String caseNumber) throws FileNotFoundException, InterruptedException {
        Thread.sleep(500);
        switch (selectedState) {
            case "California":
                if (selectedCounty.equals("Whole State")) {
                }
                break;
            case "Florida":
                if (selectedCounty.equals("Miami-Dade")) {
                    miamiDadeMain(caseNumber);
                }
                break;
            case "New York":
                if (selectedCounty.equals("New York BKQ")) {
                }
                break;
            case "Texas":
                if (selectedCounty.equals("Harris")) {
                    harrisMain(caseNumber);
                } else if (selectedCounty.equals("Dallas")) {
                    new DallasCounty().dallasMain();
                }
                break;

        }
    }

}
