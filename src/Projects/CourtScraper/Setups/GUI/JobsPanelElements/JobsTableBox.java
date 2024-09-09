package CourtScraper.Setups.GUI.JobsPanelElements;

import CourtScraper.Setups.GUI.Panels;
import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static CourtScraper.Helpers.PastJobsManager.getJobs;

public class JobsTableBox extends Panels {

    public static void jobsTables() throws IOException {
        jobsTable();
    }

    public static JTable recentJobs;
    private static JScrollPane jobsScrollPane;
    private static String[] columnData = {"Keywords", "Date Range", "State", "Counties", "Status", "Job Type", "Start Time", "End Time"};

    public static void jobsTable() throws IOException {
        recentJobs = new JTable(getJobs(), columnData);
        jobsScrollPane = new JScrollPane(recentJobs);
        JLabel instructions = new JLabel("Previous Jobs");

        gbcJobs.gridx = 0;
        gbcJobs.gridy = 0;
        jobsPanel.add(instructions, gbcJobs);

        gbcJobs.gridx = 0;
        gbcJobs.gridy = 1;
        gbcJobs.weightx = 1.0;
        gbcJobs.weighty = 1.0;
        jobsPanel.add(jobsScrollPane, gbcJobs);
    }

    public static void updateJobsTable() throws IOException {
    }
}
