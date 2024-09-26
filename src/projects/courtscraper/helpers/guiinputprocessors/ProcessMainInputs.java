/**
 * @author Adam Bouloudene
 * @summary This class processes the interface inputs to be used for logging, storing data, and configuring search terms.
 *
 */

package courtscraper.helpers.guiinputprocessors;

import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.*;

public class ProcessMainInputs {

    private String startDate = "";
    private String endDate = "";

    public String[] grabMainInputs() {
        getDateBox(); // Runs getDateBox() to get the dates inputed into the fixed variables above

        String[] inputs = {getSearchBox(), getAttorneyBox(), startDate, endDate, getState(), getCounty()};
        return inputs;
    }

    private String getSearchBox() {
        return search.getText();
    }

    private String getAttorneyBox() {
        return attorney.getText();
    }

    private void getDateBox() {
        String[] reformattedDates = date.getText().replaceAll("[^0-9/-]", "").split("-");
        if (reformattedDates.length > 1) {
            startDate = reformattedDates[0];
            endDate = reformattedDates[1];
        } else {
            startDate = reformattedDates[0];
        }
    }

    private String getState() {
        return selectedStateMain;
    }

    private String getCounty() {
        return selectedCountyMain;
    }
}
