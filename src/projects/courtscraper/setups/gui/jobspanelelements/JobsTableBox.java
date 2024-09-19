package courtscraper.setups.gui.jobspanelelements;

import courtscraper.setups.gui.Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

import static courtscraper.helpers.PastJobsManager.getJobs;

public class JobsTableBox extends Panels {

    //This file contains table for the jobs panel

    public static void jobsTables() throws IOException {
        jobsTable();
    }

    public static JTable recentJobs;
    private static JScrollPane jobsScrollPane;
    private static String[] columnData = {"Keywords", "Date Range", "State", "Counties", "Status", "Job Type", "Start Time", "End Time"};

    public static void jobsTable() {
        //invoke later allows for editing function later on throughout the interfaces instantiation
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

        //the override make sure that table boxes cannot be edited. It overrides a library function
    public static DefaultTableModel jobsModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public static void updateJobsTable() {
        SwingUtilities.invokeLater(() -> {
            try {
            jobsModel.setColumnIdentifiers(columnData);

            //this line is for resetting rows on "Update" button click
            jobsModel.setRowCount(0);

            for (String[] subArray : getJobs()) {
                jobsModel.addRow(subArray);
            }


        } catch (IOException ignored) {}
        });
    }
}
