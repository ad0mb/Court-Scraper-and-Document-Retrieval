package CourtScraper.Setups.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static CourtScraper.Setups.GUI.MainPanelElements.MainButtons.mainPanelButtons;
import static CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.mainPanelComboBoxes;
import static CourtScraper.Setups.GUI.MainPanelElements.MainInputBoxes.mainPanelBoxes;
import static CourtScraper.Setups.GUI.JobsPanelElements.JobsTableBox.jobsTables;
import static CourtScraper.Setups.GUI.TerminalPanelElements.TerminalTextArea.terminalTextAreas;

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
}
