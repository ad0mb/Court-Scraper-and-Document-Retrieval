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
        //verifyFilesButton();
    }

    public static JButton apply;

    public static void applyButton() {
        apply = new JButton("Apply");
        apply.setPreferredSize(new Dimension(15, 10));

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 7;
        gbcSettings.insets = new Insets(0,55,0,0);
        settingsPanel.add(apply, gbcSettings);

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    //if block for entering username and password
                    if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                        passwordErrorField.setText("");
                        loginWriter(selectedStateSettings + selectedCountySettings, username.getText(), password.getText());
                    } else if (!username.getText().isEmpty() && password.getText().isEmpty()){
                        passwordErrorField.setText("Please enter password");
                    } else if (username.getText().isEmpty() && !password.getText().isEmpty()) {
                        passwordErrorField.setText("Please enter username");
                    }//dafgasdgasdgadsdfasdgaga@gmail.com

                    if (!apiKey.getText().isEmpty()) {
                        apiWriter(selectedCaptchaSolution, apiKey.getText());
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
/*
    public static JButton verifyFiles;

    public static void verifyFilesButton() {
        verifyFiles = new JButton("Verify");

        verifyFiles.setPreferredSize(new Dimension(15, 20));

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 7;
        gbcSettings.insets = new Insets(0,25,0,45);
        settingsPanel.add(verifyFiles, gbcSettings);
    }*/
}
