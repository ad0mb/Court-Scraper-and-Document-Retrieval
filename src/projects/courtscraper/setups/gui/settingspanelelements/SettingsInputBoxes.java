package courtscraper.setups.gui.settingspanelelements;

import courtscraper.setups.gui.Panels;

import javax.swing.*;
import java.awt.*;

public class SettingsInputBoxes extends Panels {

    public static JTextField username;
    public static JTextField password;

    public static void settingsPanelBoxes() {
        usernameBox();
        passwordBox();
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

}
