package courtscraper.setups.gui;

import courtscraper.StartGUI;
import courtscraper.setups.gui.mainpanelelements.MainButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import static courtscraper.FlowStart.driver;
import static courtscraper.setups.gui.Panels.*;


public class Interface extends StartGUI {

    //This file contains the interface box and all of its elements

    public static JFrame frame = new JFrame();
    public static JTabbedPane topTabs = new JTabbedPane();

    public static void mainGUI() throws InterruptedException, IOException {

        panelSetups();

        //put panel elements above here

        topTabsSetup();
        //put top tab above here

        frameSetup();
        //put frame elements above here but below panel

        MainButtons.isClicked();
        //put all waiting or conditional elements above here but below frames
        //the isClicked above pauses the interface as it waits to be clicked
    }

    public static void topTabsSetup() {

        topTabs.setPreferredSize(new Dimension(500, 285));
        topTabs.add(mainPanel, "Start Configuration");
        topTabs.add(jobsPanel, "Jobs");
        //topTabs.add(terminalPanel, "Terminal");
        topTabs.add(settingsPanel, "Settings");

    }

    public static void panelSetups() throws InterruptedException, IOException {

        //panel setup
        mainPanel();
        jobsPanel();
        terminalPanel();
        settingsPanel();
        //panel setup
    }

    public static void frameSetup() {
        //frame setup
        //frame.add(Panels.mainPanel, BorderLayout.CENTER);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.add(topTabs, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Scraper");
        frame.pack();
        frame.setVisible(true);
        //frame setup

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (driver != null) {driver.quit();}
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }



}
