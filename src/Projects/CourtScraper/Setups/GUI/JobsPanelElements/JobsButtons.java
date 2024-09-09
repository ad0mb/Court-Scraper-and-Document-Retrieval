package CourtScraper.Setups.GUI.JobsPanelElements;

import javax.swing.*;

import java.awt.*;

import static CourtScraper.Setups.GUI.JobsPanelElements.JobsTableBox.updateJobsTable;
import static CourtScraper.Setups.GUI.Panels.gbcJobs;
import static CourtScraper.Setups.GUI.Panels.jobsPanel;

public class JobsButtons {

    //This file contains the buttons for the jobs panel

    public static JButton updateTableButton;

    public static void jobsButtons() {
        jobsUpdateButton();
    }

    public static void jobsUpdateButton() {
        updateTableButton = new JButton("Update");

        gbcJobs.gridx  = 4;
        gbcJobs.gridy = 0;
        gbcJobs.insets = new Insets(0, 300, 5, 5);
        jobsPanel.add(updateTableButton, gbcJobs);

        updateTableButton.addActionListener(e -> {
            updateJobsTable();
        });
    }
}
