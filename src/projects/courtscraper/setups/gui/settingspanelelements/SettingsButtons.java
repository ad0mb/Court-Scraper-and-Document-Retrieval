package courtscraper.setups.gui.settingspanelelements;

import courtscraper.setups.gui.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static courtscraper.datamanagement.json.JSONWriters.apiWriter;
import static courtscraper.datamanagement.json.JSONWriters.loginWriter;
import static courtscraper.setups.gui.settingspanelelements.SettingsComboBoxes.*;
import static courtscraper.setups.gui.settingspanelelements.SettingsInputBoxes.*;

public class SettingsButtons extends Panels {

    public static void settingsPanelButtons() {
        applyButton();
    }

    public static JButton apply;

    public static void applyButton() {
        apply = new JButton("Apply");

        gbcSettings.gridx = 50;
        gbcSettings.gridy = 50;
        gbcSettings.insets = new Insets(0,0,0,0);
        settingsPanel.add(apply, gbcSettings);

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                        loginWriter(selectedStateSettings + selectedCountySettings, username.getText(), password.getText());
                    } if (!apiKey.getText().isEmpty()) {
                        apiWriter(selectedCaptchaSolution, apiKey.getText());
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
