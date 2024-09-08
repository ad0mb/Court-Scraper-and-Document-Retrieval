package CourtScraper.Setups.GUI.MainPanelElements;

//panel imports
import static CourtScraper.FlowStart.StartMainFlowButton;
import CourtScraper.Setups.GUI.Panels;


//java swing imports
import javax.swing.*;

public class MainButtons extends Panels {

    //This file contains the main panel buttons

    public static void mainPanelButtons() throws InterruptedException {
        startButton();
    }


    //startButton declarations
    private static JButton start;

    //start button for mainPanel to start flow
    public static void startButton() throws InterruptedException {
        //start button intialization

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


                    //MAIN FLOW START vvvv
                    StartMainFlowButton();
                    System.out.println("Ended");
                    //MAIN FLOW START ^^^^


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
    }
}