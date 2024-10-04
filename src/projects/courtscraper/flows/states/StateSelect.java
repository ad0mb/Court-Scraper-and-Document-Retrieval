package courtscraper.flows.states;


import courtscraper.exceptions.BlockedDocumentException;
import courtscraper.flows.states.texas.dallas.DallasCounty;

import java.io.FileNotFoundException;

import static courtscraper.flows.states.florida.miamidade.MiamiDadeCounty.miamiDadeMain;
import static courtscraper.flows.states.texas.harris.HarrisCounty.harrisMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;

public class StateSelect extends StateParser {

    //this file contains the filter that decides which county under which state to search

    public static void stateFilter(String caseNumber) throws FileNotFoundException, InterruptedException, BlockedDocumentException {
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
