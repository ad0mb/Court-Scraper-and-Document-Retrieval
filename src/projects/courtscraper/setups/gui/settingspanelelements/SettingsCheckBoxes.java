package courtscraper.setups.gui.settingspanelelements;

import courtscraper.setups.gui.Panels;

import javax.swing.*;
import java.awt.*;

public class SettingsCheckBoxes extends Panels {

    private static JCheckBox headless;

    public static void settingsPanelCheckBoxes() {
        setHeadless();
    }

    public static void setHeadless() {
        headless = new JCheckBox("Headless");

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 2;
        gbcSettings.insets = new Insets(10,25,0,0);
        settingsPanel.add(headless, gbcSettings);

    }


}
