package CourtScraper.Setups.GUI.JobsPanelElements;

import CourtScraper.Setups.GUI.Panels;
import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static CourtScraper.Helpers.PastJobsManager.getJobs;

public class JobsTableBox extends Panels {

    //This file contains table for the jobs panel

    public static void jobsTables() throws IOException {
        jobsTable();
    }

    public static JTable recentJobs;
    public static DefaultTableModel jobsModel = new DefaultTableModel();
    private static JScrollPane jobsScrollPane;
    private static String[] columnData = {"Keywords", "Date Range", "State", "Counties", "Status", "Job Type", "Start Time", "End Time"};

    public static void jobsTable() {
        SwingUtilities.invokeLater(() -> {
            updateJobsTable();

            recentJobs = new JTable(jobsModel);
            jobsScrollPane = new JScrollPane(recentJobs);
            JLabel instructions = new JLabel("Previous Jobs");

            gbcJobs.gridx = 0;
            gbcJobs.gridy = 0;
            gbcJobs.insets = new Insets(0, 0, 0,0);
            jobsPanel.add(instructions, gbcJobs);

            gbcJobs.gridx = 0;
            gbcJobs.gridy = 1;
            gbcJobs.gridwidth = 5;
            gbcJobs.weightx = 1.0;
            gbcJobs.weighty = 1.0;
            gbcJobs.insets = new Insets(0,0,0,0);
            jobsPanel.add(jobsScrollPane, gbcJobs);
        });
        }

    public static void updateJobsTable() {
        SwingUtilities.invokeLater(() -> {
            try {
            jobsModel.setColumnIdentifiers(columnData);

            jobsModel.setRowCount(0);

            for (String[] subArray : getJobs()) {
                jobsModel.addRow(subArray);
            }
        } catch (IOException ignored) {}
        });
    }
}
