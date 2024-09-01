package Projects.Setups.GUI;

import javax.swing.*;
import java.awt.*;

import static Projects.Setups.GUI.MainPanelElements.MainButtons.mainPanelButtons;
import static Projects.Setups.GUI.MainPanelElements.MainComboBoxes.mainPanelComboBoxes;
import static Projects.Setups.GUI.MainPanelElements.MainInputBoxes.mainPanelBoxes;

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
        mainPanelButtons();
        mainPanelComboBoxes();
    }
}
