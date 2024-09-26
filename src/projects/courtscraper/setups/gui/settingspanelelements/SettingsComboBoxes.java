package courtscraper.setups.gui.settingspanelelements;

import courtscraper.datamanagement.json.JSONGrabbers;
import courtscraper.setups.gui.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static courtscraper.datamanagement.json.JSONWriters.configWriter;
import static courtscraper.setups.gui.settingspanelelements.SettingsInputBoxes.apiKeyInputRenameChecker;

public class SettingsComboBoxes extends Panels {

    //this file contains the combobox elements for the settings panel

    public static void settingsPanelComboBoxes() throws FileNotFoundException {

        setStateSettings();
        setCountySettings();
        setCaptchaSolution();

    }

//Settings Panel login comboboxes
    private static final String[] stateElements = {"Courtlink"/*, "California"*/, "Florida" /*, "New York"*/, "Texas"};
    public static JComboBox<String> state = new JComboBox<>(stateElements);
    public static String selectedStateSettings = "Courtlink";

    public static void setStateSettings() {
        JLabel instruction = new JLabel("Select State");

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 0;
        gbcSettings.insets = new Insets(0, 0, 0, 0);
        settingsPanel.add(instruction, gbcSettings);

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 1;
        gbcSettings.insets = new Insets(0, 0, 0, 0);
        settingsPanel.add(state, gbcSettings);

        //Listener for County Combo Box selection
        state.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedStateSettings = (String) state.getSelectedItem();

                    //decides whether county box is open or not
                    boolean isCountyOpen = false;

                    switch(selectedStateSettings) {
                        case "Courtlink":
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

    private static String[] countyElements = {"Select State First"};
    public static JComboBox<String> counties = new JComboBox<>(countyElements);
    public static String selectedCountySettings = "";

    public static void setCountySettings() {
        JLabel instruction = new JLabel("Select County");

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 2;
        gbcSettings.insets = new Insets(0, 0, 0, 0);
        settingsPanel.add(instruction, gbcSettings);

        counties.setEnabled(false);

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 3;
        gbcSettings.insets = new Insets(0, 0, 0, 0);
        settingsPanel.add(counties, gbcSettings);

        //Listening to grab input for county

        counties.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedCountySettings = (String) counties.getSelectedItem();
                }
            }
        });
    }

    private static String[] captchaOptions = {"None", "2captcha"/*, "Death By Captcha"*/};
    public static JComboBox<String> captchaSolution = new JComboBox<>(captchaOptions);
    public static String selectedCaptchaSolution = "";

    public static void setCaptchaSolution() throws FileNotFoundException {
        JLabel instruction = new JLabel("Captcha Solver");

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 0;
        gbcSettings.insets = new Insets(0,25,0,0);
        settingsPanel.add(instruction, gbcSettings);

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 1;
        gbcSettings.insets = new Insets(0,25,0,0);
        settingsPanel.add(captchaSolution, gbcSettings);

        captchaSolution.setSelectedItem(new JSONGrabbers().configGrabber("captcha"));
        selectedCaptchaSolution = (String) captchaSolution.getSelectedItem();

        captchaSolution.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedCaptchaSolution = (String) captchaSolution.getSelectedItem();

                    try {
                        configWriter("captcha", selectedCaptchaSolution);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    apiKeyInputRenameChecker();
                }
            }
        });
    }
}
