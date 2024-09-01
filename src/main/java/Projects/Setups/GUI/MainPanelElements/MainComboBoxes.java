package Projects.Setups.GUI.MainPanelElements;

import Projects.Setups.GUI.Panels;

import javax.swing.*;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static Projects.Setups.GUI.MainPanelElements.MainInputBoxes.date;


public class MainComboBoxes extends Panels {

//Main Panel Combo Boxes
    public static void mainPanelComboBoxes() {
        setDateType();
        setState();
        setCounty();

    }

    //Date Boxes
    //Date type declarations
    private static final String[] dateTypeElements = {"All available dates", "Date is", "Date is before", "Date is after", "Date is between"};
    public static JComboBox<String> dateType = new JComboBox<>(dateTypeElements);
    public static String selectedDateType = "All available dates";
    private static JLabel dateLabel;

    public static void setDateType() {
        gbcMain.gridx = 0;
        gbcMain.gridy = 8;
        dateLabel = new JLabel("MM/DD/YYYY");
        mainPanel.add(dateLabel, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 9;
        mainPanel.add(dateType, gbcMain);

        //Listener for Date instruction label
        dateType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    selectedDateType = (String) dateType.getSelectedItem();
                    if (dateType.getSelectedItem() == "All available dates") {
                        date.setEditable(false);
                    } else if (dateType.getSelectedItem() == "Date is between") {
                        date.setEditable(true);
                        dateLabel.setText("MM/DD/YYYY - MM/DD/YYYY");
                    } else {
                        date.setEditable(true);
                        dateLabel.setText("MM/DD/YYYY");
                    }
                }
            }
        });

    }

    //State Boxes
    //State type declarations
    private static final String[] stateElements = {"Select State", "California", "Florida", "New York", "Texas"};
    public static JComboBox<String> state = new JComboBox<>(stateElements);
    public static String selectedState = "";

    public static void setState() {
        JLabel instruction = new JLabel("Select State");

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        mainPanel.add(instruction, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        mainPanel.add(state, gbcMain);

        //Listener for County Combo Box selection
        state.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedState = (String) state.getSelectedItem();

                    switch(selectedState) {
                        case "California":
                            counties.removeAllItems();
                            counties.addItem("Whole State");
                            break;
                        case "Florida":
                            counties.removeAllItems();
                            counties.addItem("Miami-Dade");
                            break;
                        case "New York":
                            counties.removeAllItems();
                            counties.addItem("New York BKQ");
                            break;
                        case "Texas":
                            counties.removeAllItems();
                            counties.addItem("Harris");
                            counties.addItem("Dallas");
                    }
                    counties.setEnabled(true);

                }
            }
        });


    }

    //County Box
    //County type declarations
    private static String[] countyElements = {"Select State First"};
    public static JComboBox<String> counties = new JComboBox<>(countyElements);
    public static String selectedCounty = "";

    public static void setCounty() {
        JLabel instruction = new JLabel("Select County");

        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        mainPanel.add(instruction, gbcMain);

        counties.setEnabled(false);

        gbcMain.gridx = 1;
        gbcMain.gridy = 1;
        mainPanel.add(counties, gbcMain);

        //Listening to grab input for county
        counties.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedCounty = (String) counties.getSelectedItem();
                }
            }
        });
    }

//Main Panel Combo Boxes


}
