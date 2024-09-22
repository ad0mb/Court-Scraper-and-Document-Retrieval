package courtscraper.setups.gui.mainpanelelements;

import courtscraper.setups.gui.Panels;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static courtscraper.setups.gui.mainpanelelements.MainButtons.start;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.*;


public class MainComboBoxes extends Panels {

    //This file contains the main panels combo boxes (dropdowns)


    public static void mainPanelComboBoxes() {
        setDateType();
        setStateMain();
        setCountyMain();
        setFlowType();

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
        gbcMain.insets = new Insets(0, 0, 0, 0);
        dateLabel = new JLabel("MM/DD/YYYY");
        mainPanel.add(dateLabel, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 9;
        gbcMain.insets = new Insets(0, 0, 10, 10);
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
    public static final String[] stateElements = {"Select State", "California", "Florida", "New York", "Texas"};
    public static JComboBox<String> state = new JComboBox<>(stateElements);
    public static String selectedStateMain = "";

    public static void setStateMain() {
        JLabel instruction = new JLabel("Select State");

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(instruction, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.insets = new Insets(0, 0, 20, 10);
        mainPanel.add(state, gbcMain);

        //Listener for County Combo Box selection
        state.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedStateMain = (String) state.getSelectedItem();

                    //decides whether county box is open or not
                    boolean isCountyOpen = false;

                    switch(selectedStateMain) {
                        case "Select State":
                            counties.removeAllItems();
                            counties.addItem("Select State First");
                            isCountyOpen = false;
                            break;
                        case "California":
                            counties.removeAllItems();
                            counties.addItem("Whole State");
                            isCountyOpen = true;
                            break;
                        case "Florida":
                            counties.removeAllItems();
                            counties.addItem("Miami Dade");
                            isCountyOpen = true;
                            break;
                        case "New York":
                            counties.removeAllItems();
                            counties.addItem("New York BKQ");
                            isCountyOpen = true;
                            break;
                        case "Texas":
                            counties.removeAllItems();
                            counties.addItem("Harris");
                            isCountyOpen = true;
                            break;
                    }
                    counties.setEnabled(isCountyOpen);
                }
            }
        });
    }

//County Box
    //County type declarations
    private static String[] countyElements = {"Select State First"};
    public static JComboBox<String> counties = new JComboBox<>(countyElements);
    public static String selectedCountyMain = "";

    public static void setCountyMain() {
        JLabel instruction = new JLabel("Select County");

        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        gbcMain.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(instruction, gbcMain);

        counties.setEnabled(false);

        gbcMain.gridx = 1;
        gbcMain.gridy = 1;
        gbcMain.insets = new Insets(0, 0, 20, 0);
        counties.setPreferredSize(new Dimension(100, 0));
        mainPanel.add(counties, gbcMain);

        //Listening to grab input for county
        counties.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedCountyMain = (String) counties.getSelectedItem();
                }
            }
        });
    }

//Flow type box
    //Flow type declarations
    private static String[] flowTypeElements = {"Select", "Scrape and Retrieve", "Scrape Only", "Retrieve Only"};
    public static JComboBox<String> flowType = new JComboBox<>(flowTypeElements);
    public static String selectedFlowType = "";

    public static void setFlowType() {
        gbcMain.gridx = 0;
        gbcMain.gridy = 100;
        gbcMain.insets = new Insets(0, 0, 10, 10);
        mainPanel.add(flowType, gbcMain);

        flowType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                selectedFlowType = (String) flowType.getSelectedItem();

                //boolean that disables and enables search elements
                boolean searchElements = true;

                switch (selectedFlowType) {
                    case "Select":
                        searchElements = true;

                        start.setEnabled(false);
                        break;
                    case "Scrape and Retrieve":
                    case "Scrape Only":
                        searchElements = true;

                        start.setEnabled(true);
                        break;
                    case "Retrieve Only":
                        searchElements = false;

                        start.setEnabled(true);
                        break;
                }

                //disable input boxes
                search.setEditable(searchElements);
                attorney.setEditable(searchElements);
                date.setEditable(searchElements);

                //disable combo boxes
                dateType.setEnabled(searchElements);
            }
        });
    }
}