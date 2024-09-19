package CourtScraper.Flows.States;


import CourtScraper.Flows.States.Texas.Dallas.DallasCounty;

import java.io.FileNotFoundException;

import static CourtScraper.Flows.States.Florida.MiamiDade.MiamiDadeCounty.miamiDadeMain;
import static CourtScraper.Flows.States.Texas.Harris.HarrisCounty.harrisMain;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCountyMain;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.selectedStateMain;

public class StateSelect extends StateParser {

    //This file contains the filter that decides which county under which state to search

    public static void stateFilter(String caseNumber) throws FileNotFoundException, InterruptedException {
        Thread.sleep(500);
        switch (selectedStateMain) {
            case "California":
                if (selectedCountyMain.equals("Whole State")) {
                }
                break;
            case "Florida":
                if (selectedCountyMain.equals("Miami Dade")) {
                    miamiDadeMain(caseNumber);
                }
                break;
            case "New York":
                if (selectedCountyMain.equals("New York BKQ")) {
                }
                break;
            case "Texas":
                if (selectedCountyMain.equals("Harris")) {
                    harrisMain(caseNumber);
                } else if (selectedCountyMain.equals("Dallas")) {
                    new DallasCounty().dallasMain();
                }
                break;

        }
    }

}
