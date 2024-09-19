package CourtScraper.Helpers;


import CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes;
import CourtScraper.Setups.GUI.MainPanelElements.MainInputBoxes;

public class ProcessInputs {

    //This Helper class is for grabbing the inputs from the search box and combo boxes on the interface to then be processed into usable variables throughout the code


    private static String startDate = "";
    private static String endDate = "";

    public String[] grabInputs() {

        //Runs getDateBox() to get the dates inputed into the fixed variables above
        getDateBox();



        String[] inputs = {getSearchBox(), getAttorneyBox(), startDate, endDate, getState(), getCounty()};
        return inputs;
    }

    private static String getSearchBox() {
        return MainInputBoxes.search.getText();
    }

    private static String getAttorneyBox() {
        return MainInputBoxes.attorney.getText();
    }

    private static void getDateBox() {
        String[] reformattedDates = MainInputBoxes.date.getText().replaceAll("[^0-9/-]", "").split("-");
        if (reformattedDates.length > 1) {
            startDate = reformattedDates[0];
            endDate = reformattedDates[1];
        } else {
            startDate = reformattedDates[0];
        }
    }

    private static String getState() {
        return MainComboBoxes.selectedStateMain;
    }

    private static String getCounty() {
        return MainComboBoxes.selectedCountyMain;
    }
}
