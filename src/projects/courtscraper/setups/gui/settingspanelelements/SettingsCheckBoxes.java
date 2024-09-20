package courtscraper.setups.gui.settingspanelelements;

import courtscraper.datamanagement.json.JSONGrabbers;
import courtscraper.setups.gui.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static courtscraper.datamanagement.json.JSONWriters.configWriter;

public class SettingsCheckBoxes extends Panels {

    private static JCheckBox headless;
    private static JCheckBox manualSearch;

    public static void settingsPanelCheckBoxes() throws FileNotFoundException {
        setHeadless();
        //setManualSearch();
    }

    public static void setHeadless() throws FileNotFoundException {
        headless = new JCheckBox("Headless");

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 4;
        gbcSettings.insets = new Insets(10,25,0,0);
        settingsPanel.add(headless, gbcSettings);

        headless.setSelected(Boolean.parseBoolean(new JSONGrabbers().configGrabber("headless")));

        headless.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configWriter("headless", String.valueOf(headless.isSelected()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /*
    public static void setManualSearch() throws FileNotFoundException {
        manualSearch = new JCheckBox("Manual Search Input");

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 3;
        gbcSettings.insets = new Insets(0,25,0,0);
        settingsPanel.add(manualSearch, gbcSettings);

        manualSearch.setSelected(Boolean.parseBoolean(new JSONGrabbers().configGrabber("headless")));

        manualSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configWriter("manual search input", String.valueOf(manualSearch.isSelected()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
     */


}
