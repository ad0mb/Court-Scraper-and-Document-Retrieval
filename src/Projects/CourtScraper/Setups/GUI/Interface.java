package CourtScraper.Setups.GUI;

import CourtScraper.Setups.GUI.MainPanelElements.MainButtons;
import CourtScraper.StartGUI;

import javax.swing.*;
import java.awt.*;

import static CourtScraper.Setups.GUI.Panels.mainPanel;
import static CourtScraper.Setups.GUI.Panels.jobsPanel;


public class Interface extends StartGUI {

    //This file contains the interface box and all of its elements

    private static JFrame frame = new JFrame();
    private static JTabbedPane topTabs = new JTabbedPane();

    public static void mainGUI() throws InterruptedException {

        panelSetups();

        //put panel elements above here

        topTabsSetup();
        //put top tab above here

        frameSetup();
        //put frame elements above here but below panel

        MainButtons.isClicked();
        //put all waiting or conditional elements above here but below frames

    }

    public static void topTabsSetup() {

        topTabs.setPreferredSize(new Dimension(400, 285));
        topTabs.add(mainPanel, "Start Configuration");
        topTabs.add(jobsPanel, "Jobs");

    }

    public static void panelSetups() throws InterruptedException {

        //panel setup
        mainPanel();
        jobsPanel();
        //panel setup
    }

    public static void frameSetup() {
        //frame setup
        //frame.add(Panels.mainPanel, BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.add(topTabs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Scraper");
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
        //frame setup
    }



}
