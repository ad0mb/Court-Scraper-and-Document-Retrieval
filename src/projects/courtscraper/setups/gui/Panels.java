package courtscraper.setups.gui;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static courtscraper.setups.gui.mainpanelelements.MainButtons.mainPanelButtons;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.mainPanelComboBoxes;
import static courtscraper.setups.gui.mainpanelelements.MainInputBoxes.mainPanelBoxes;
import static courtscraper.setups.gui.jobspanelelements.JobsTableBox.jobsTables;
import static courtscraper.setups.gui.settingspanelelements.SettingsButtons.settingsPanelButtons;
import static courtscraper.setups.gui.settingspanelelements.SettingsCheckBoxes.settingsPanelCheckBoxes;
import static courtscraper.setups.gui.settingspanelelements.SettingsComboBoxes.settingsPanelComboBoxes;
import static courtscraper.setups.gui.settingspanelelements.SettingsInputBoxes.settingsPanelBoxes;
import static courtscraper.setups.gui.terminalpanelelements.TerminalTextArea.terminalTextAreas;

public class Panels extends Interface {

    //this file contains all the panels

    public static JPanel mainPanel = new JPanel();
    public static GridBagConstraints gbcMain = new GridBagConstraints();

    public static void mainPanel() throws InterruptedException {
        //borders around edges
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        //borders around edges

        mainPanel.setLayout(new GridBagLayout());

        gbcMain.fill = GridBagConstraints.BOTH;
        //gbcMain.insets = new Insets(5, 5, 5, 5);



        mainPanelBoxes();
        mainPanelButtons();
        mainPanelComboBoxes();
    }

    public static JPanel jobsPanel = new JPanel();
    public static GridBagConstraints gbcJobs = new GridBagConstraints();

    public static void jobsPanel() throws IOException {
        //borders around edges
        jobsPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20 ,10));
        //borders around edges

        jobsPanel.setLayout(new GridBagLayout());


        gbcJobs.fill = GridBagConstraints.BOTH;

        jobsTables();
        //jobsButtons();
    }

    public static JPanel terminalPanel = new JPanel();
    public static GridBagConstraints gbcTerminal = new GridBagConstraints();

    public static void terminalPanel() {
        //borders around edges
        terminalPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20 ,10));
        //borders around edges

        terminalPanel.setLayout(new GridBagLayout());

        gbcTerminal.fill = GridBagConstraints.BOTH;

        terminalTextAreas();

    }

    public static JPanel settingsPanel = new JPanel();
    public static GridBagConstraints gbcSettings = new GridBagConstraints();

    public static void settingsPanel() throws FileNotFoundException {
        //borders around edges
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 10 ,20, 10));
        //borders around edges

        settingsPanel.setLayout(new GridBagLayout());

        gbcSettings.fill = GridBagConstraints.BOTH;

        settingsPanelComboBoxes();
        settingsPanelBoxes();
        settingsPanelButtons();
        settingsPanelCheckBoxes();
    }
}
