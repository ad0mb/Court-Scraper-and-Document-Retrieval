package Projects.Helpers;

import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedCounty;
import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.selectedState;
import static Projects.Setups.GUI.MainPanelElements.MainInputBoxes.*;

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

    public static String getSearchBox() {
        return search.getText();
    }

    public static String getAttorneyBox() {
        return attorney.getText();
    }

    public static void getDateBox() {
        String[] reformattedDates = date.getText().replaceAll("[^0-9/-]", "").split("-");
        if (reformattedDates.length > 1) {
            startDate = reformattedDates[0];
            endDate = reformattedDates[1];
        } else {
            startDate = reformattedDates[0];
        }
    }

    public static String getState() {
        return selectedState;
    }

    public static String getCounty() {
        return selectedCounty;
    }
}
