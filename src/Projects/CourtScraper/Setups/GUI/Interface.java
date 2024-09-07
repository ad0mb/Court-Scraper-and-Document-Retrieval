package CourtScraper.Setups.GUI;

import CourtScraper.Setups.GUI.MainPanelElements.MainButtons;

import javax.swing.*;
import java.awt.*;


public class Interface {

    private static JFrame frame = new JFrame();

    public static void mainGUI() throws InterruptedException {

        panelSetups();

        //put panel elements above here

        frameSetup();
        //put frame elements above here but below panel

        MainButtons.isClicked();
        //put all waiting or conditional elements above here but below frames

    }

    public static void panelSetups() throws InterruptedException {

        //panel setup
        CourtScraper.Setups.GUI.Panels.mainPanel();
        //panel setup
    }

    public static void frameSetup() {
        //frame setup
        frame.add(Panels.mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Scraper");
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
        //frame setup
    }



}
