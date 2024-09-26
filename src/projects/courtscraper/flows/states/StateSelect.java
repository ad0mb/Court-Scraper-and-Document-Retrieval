/**
 * @author Adam Bouloudene
 * @summary This class is used in StateParser to specify which state it wants to search the selected case number in.
 *
 */

package courtscraper.flows.states;


import courtscraper.flows.states.texas.dallas.DallasCounty;

import java.io.FileNotFoundException;

import static courtscraper.flows.states.florida.miamidade.MiamiDadeCounty.miamiDadeMain;
import static courtscraper.flows.states.texas.harris.HarrisCounty.harrisMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;

public class StateSelect extends StateParser {

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
