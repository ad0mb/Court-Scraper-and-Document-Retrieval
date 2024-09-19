package courtscraper.setups.gui.settingspanelelements;

import courtscraper.setups.gui.Panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsButtons extends Panels {

    public static void settingsPanelButtons() {
        applyButton();
    }

    public static JButton apply;

    public static void applyButton() {
        apply = new JButton("Apply");

        gbcSettings.gridx = 100;
        gbcSettings.gridy = 100;
        settingsPanel.add(apply, gbcSettings);

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
