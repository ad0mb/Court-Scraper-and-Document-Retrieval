package CourtScraper.Setups.GUI;

import CourtScraper.Setups.GUI.MainPanelElements.MainButtons;

import javax.swing.*;
import java.awt.*;

import static CourtScraper.Setups.GUI.MainPanelElements.MainInputBoxes.mainPanelBoxes;

public class Panels {

    public static JPanel mainPanel = new JPanel();
    public static GridBagConstraints gbcMain = new GridBagConstraints();

    public static void mainPanel() throws InterruptedException {
        //borders around edges
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //borders around edges


        mainPanel.setLayout(new GridBagLayout());

        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(5, 5, 5, 5);



        mainPanelBoxes();
        MainButtons.mainPanelButtons();
        CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes.mainPanelComboBoxes();
    }
}
