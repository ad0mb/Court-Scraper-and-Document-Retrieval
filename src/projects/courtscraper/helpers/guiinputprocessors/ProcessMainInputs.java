package courtscraper.helpers.guiinputprocessors;

import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.*;

public class ProcessMainInputs {

    //This Helper class is for grabbing the inputs from the search box and combo boxes on the interface to then be processed into usable variables throughout the code

    private String startDate = "";
    private String endDate = "";

    public String[] grabMainInputs() {


        getDateBox(); //runs getDateBox() to get the dates inputed into the fixed variables above



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
