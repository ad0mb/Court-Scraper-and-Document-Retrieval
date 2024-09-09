package CourtScraper.Setups.GUI;

import CourtScraper.Setups.GUI.MainPanelElements.MainButtons;

import javax.swing.*;
import java.awt.*;

import static CourtScraper.Setups.GUI.MainPanelElements.MainInputBoxes.mainPanelBoxes;

public class Panels extends Interface{

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
        MainButtons.mainPanelButtons();
        CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.mainPanelComboBoxes();
    }
}
