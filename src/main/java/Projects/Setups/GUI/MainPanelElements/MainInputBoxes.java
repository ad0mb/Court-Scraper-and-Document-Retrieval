package Projects.Setups.GUI.MainPanelElements;

import Projects.Setups.GUI.Panels;

import javax.swing.*;

public class MainInputBoxes extends Panels {

//mainPanel variables
    //declarations are public so they can be grabbed
    public static JTextField search;
    public static JTextField attorney;
    public static JTextField date;

//mainPanel Boxes
    public static void mainPanelBoxes() {
        searchBox();
        attorneyBox();
        dateBox();
    }

    public static void searchBox() {
        JLabel instruction = new JLabel("Search Terms");
        search = new JTextField(15);

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        mainPanel.add(instruction, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 3;
        gbcMain.gridwidth = 2;
        mainPanel.add(search, gbcMain);
    }

    public static void attorneyBox() {
        JLabel instruction = new JLabel("Attorney Search Terms");
        attorney = new JTextField();

        gbcMain.gridx = 0;
        gbcMain.gridy = 6;
        mainPanel.add(instruction, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 7;
        gbcMain.gridwidth = 2;
        mainPanel.add(attorney, gbcMain);

    }

    public static void dateBox() {
        date = new JTextField();

        gbcMain.gridx = 1;
        gbcMain.gridy = 9;
        gbcMain.gridwidth = 1;
        mainPanel.add(date, gbcMain);

        date.setEditable(false);
    }


//mainPanel Boxes
}
