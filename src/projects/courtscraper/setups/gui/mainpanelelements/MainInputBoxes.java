package courtscraper.setups.gui.mainpanelelements;


import courtscraper.setups.gui.Panels;


import javax.swing.*;
import java.awt.*;

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

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        gbcMain.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(instruction, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 3;
        gbcMain.gridwidth = 2;
        gbcMain.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(search, gbcMain);
    }

    public static void attorneyBox() {
        JLabel instruction = new JLabel("Attorney Search Terms");
        attorney = new JTextField();

        gbcMain.gridx = 0;
        gbcMain.gridy = 6;
        gbcMain.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(instruction, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 7;
        gbcMain.gridwidth = 2;
        gbcMain.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(attorney, gbcMain);

    }

    public static void dateBox() {
        date = new JTextField();

        gbcMain.gridx = 1;
        gbcMain.gridy = 9;
        gbcMain.insets = new Insets(0, 0, 10, 0);
        gbcMain.gridwidth = 1;
        mainPanel.add(date, gbcMain);

        date.setEditable(false);
    }
}