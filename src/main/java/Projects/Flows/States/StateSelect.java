package Projects.Flows.States;

import Projects.Flows.States.Texas.Dallas.DallasCounty;
import Projects.Flows.States.Texas.Harris.HarrisCounty;

import java.io.FileNotFoundException;

import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;

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
                    new HarrisCounty().harrisMain(caseNumber);
                } else if (selectedCounty.equals("Dallas")) {
                    new DallasCounty().dallasMain();
                }

        }
    }

}
