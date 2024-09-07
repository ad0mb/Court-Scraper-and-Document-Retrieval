package CourtScraper.Setups.GUI.MainPanelElements;

import CourtScraper.Setups.GUI.Panels;

import javax.swing.*;

import static CourtScraper.FlowStart.StartMainFlowButton;
import static CourtScraper.Setups.GUI.Panels.gbcMain;
import static CourtScraper.Setups.GUI.Panels.mainPanel;

public class MainButtons extends Panels {

//Main Panel Buttons

    public static void mainPanelButtons() throws InterruptedException {
        startButton();
    }


    //startButton declarations
    private static JButton start;

    //start button for mainPanel to start flow
    public static void startButton() throws InterruptedException {
        //button intialization

        start = new JButton("Start");

        gbcMain.gridx = 0;
        gbcMain.gridy = 100;
        mainPanel.add(start, gbcMain);

        //clicked action reader AND STARTS FLOW
        start.addActionListener(e -> {
            clicked = true;
            SwingWorker<Void, Void> startLooper = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    start.setEnabled(false);
                    start.setText("Started");


                    //FLOW START vvvv
                    StartMainFlowButton();
                    System.out.println("Ended");
                    //FLOW START ^^^^


                    start.setEnabled(true);
                    start.setText("Start");

                    return null;
                }

        };
            startLooper.execute();
    });

}

    //isClicked declarations
    public static boolean clicked = false;

    public static void isClicked() throws InterruptedException {
        while (!clicked) {
            Thread.sleep(1);
        }
        //start.setEnabled(false);
        //start.setText("Running");
    }


//Main Panel Buttons
}
