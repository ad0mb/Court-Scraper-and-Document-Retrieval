package CourtScraper.Setups.GUI.MainPanelElements;


import CourtScraper.Setups.GUI.Panels;


import javax.swing.*;

public class MainInputBoxes extends Panels {

    //This file contains input boxes (text boxes) for the interface

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

        Panels.gbcMain.gridx = 0;
        Panels.gbcMain.gridy = 2;
        Panels.mainPanel.add(instruction, Panels.gbcMain);

        Panels.gbcMain.gridx = 0;
        Panels.gbcMain.gridy = 3;
        Panels.gbcMain.gridwidth = 2;
        Panels.mainPanel.add(search, Panels.gbcMain);
    }

    public static void attorneyBox() {
        JLabel instruction = new JLabel("Attorney Search Terms");
        attorney = new JTextField();

        Panels.gbcMain.gridx = 0;
        Panels.gbcMain.gridy = 6;
        Panels.mainPanel.add(instruction, Panels.gbcMain);

        Panels.gbcMain.gridx = 0;
        Panels.gbcMain.gridy = 7;
        Panels.gbcMain.gridwidth = 2;
        Panels.mainPanel.add(attorney, Panels.gbcMain);

    }

    public static void dateBox() {
        date = new JTextField();

        Panels.gbcMain.gridx = 1;
        Panels.gbcMain.gridy = 9;
        Panels.gbcMain.gridwidth = 1;
        Panels.mainPanel.add(date, Panels.gbcMain);

        date.setEditable(false);
    }
}