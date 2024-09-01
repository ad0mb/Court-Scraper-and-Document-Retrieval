package Projects.Setups.GUI;

import Projects.Setups.GUI.MainPanelElements.MainButtons;

import javax.swing.*;
import java.awt.*;

import static Projects.Setups.GUI.Panels.mainPanel;


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
        Panels.mainPanel();
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
