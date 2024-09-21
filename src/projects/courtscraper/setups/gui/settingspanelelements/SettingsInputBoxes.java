package courtscraper.setups.gui.settingspanelelements;

import courtscraper.datamanagement.json.JSONGrabbers;
import courtscraper.setups.gui.Panels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static courtscraper.datamanagement.json.JSONWriters.apiWriter;

public class SettingsInputBoxes extends Panels {

    public static JTextField username;
    public static JTextField password;
    public static JTextField apiKey;

    public static void settingsPanelBoxes() {
        usernameBox();
        passwordBox();
        apiKeyBox();
    }

    public static void usernameBox() {
        JLabel instruction = new JLabel("Username");
        username = new JTextField(13);

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 4;
        gbcSettings.insets = new Insets(0,0,0,0);
        settingsPanel.add(instruction, gbcSettings);

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 5;
        gbcSettings.insets = new Insets(0,0,0,0);
        settingsPanel.add(username, gbcSettings);
    }

    public static void passwordBox() {
        JLabel instruction = new JLabel("Password");
        password = new JTextField(13);

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 6;
        gbcSettings.insets = new Insets(0,0,0,0);
        settingsPanel.add(instruction, gbcSettings);

        gbcSettings.gridx = 0;
        gbcSettings.gridy = 7;
        gbcSettings.insets = new Insets(0,0,0,0);
        settingsPanel.add(password, gbcSettings);
    }

    public static void apiKeyBox() {
        JLabel instruction = new JLabel("API Key");
        apiKey = new JTextField(10);

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 2;
        gbcSettings.insets = new Insets(5, 25, 0, 0);
        settingsPanel.add(instruction, gbcSettings);

        gbcSettings.gridx = 1;
        gbcSettings.gridy = 3;
        gbcSettings.insets = new Insets(0, 25, 0, 0);
        settingsPanel.add(apiKey, gbcSettings);

        apiKeyInputRenameChecker();
    }

    public static void apiKeyInputRenameChecker() {
        try {
            switch (new JSONGrabbers().configGrabber("captcha")) {
                case "None":
                    apiKey.setEditable(false);
                    break;
                case "2captcha":
                    apiKey.setEditable(true);
                    break;
            }
        } catch (IOException er) {};
    }

}
