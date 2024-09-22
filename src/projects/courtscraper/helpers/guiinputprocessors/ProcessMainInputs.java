package courtscraper.helpers.guiinputprocessors;


import courtscraper.setups.gui.mainpanelelements.MainComboBoxes;
import courtscraper.setups.gui.mainpanelelements.MainInputBoxes;

public class ProcessMainInputs {

    //This Helper class is for grabbing the inputs from the search box and combo boxes on the interface to then be processed into usable variables throughout the code


    private String startDate = "";
    private String endDate = "";

    public String[] grabMainInputs() {

        //Runs getDateBox() to get the dates inputed into the fixed variables above
        getDateBox();



        String[] inputs = {getSearchBox(), getAttorneyBox(), startDate, endDate, getState(), getCounty()};
        return inputs;
    }

    private String getSearchBox() {
        return MainInputBoxes.search.getText();
    }

    private String getAttorneyBox() {
        return MainInputBoxes.attorney.getText();
    }

    private void getDateBox() {
        String[] reformattedDates = MainInputBoxes.date.getText().replaceAll("[^0-9/-]", "").split("-");
        if (reformattedDates.length > 1) {
            startDate = reformattedDates[0];
            endDate = reformattedDates[1];
        } else {
            startDate = reformattedDates[0];
        }
    }

    private String getState() {
        return MainComboBoxes.selectedStateMain;
    }

    private String getCounty() {
        return MainComboBoxes.selectedCountyMain;
    }
}
