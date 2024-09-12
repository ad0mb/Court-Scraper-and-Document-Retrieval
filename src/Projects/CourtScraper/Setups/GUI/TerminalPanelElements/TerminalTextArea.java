package CourtScraper.Setups.GUI.TerminalPanelElements;

import CourtScraper.Setups.GUI.Panels;

import javax.swing.*;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

import static CourtScraper.Setups.GUI.Panels.gbcTerminal;
import static CourtScraper.Setups.GUI.Panels.terminalPanel;

public class TerminalTextArea {

    public static JTextArea terminalBox;
    private static Font terminalFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    public static void terminalTextAreas() {
        terminalTextArea();
    }

    public static void terminalTextArea() {
        terminalBox = new JTextArea();
        JScrollPane terminalScrollPane = new JScrollPane(terminalBox);

        terminalBox.setEditable(false);
        terminalBox.setForeground(Color.WHITE);
        terminalBox.setBackground(Color.DARK_GRAY);
        terminalBox.setFont(terminalFont);

        gbcTerminal.gridx = 0;
        gbcTerminal.gridy = 0;
        gbcTerminal.weightx = 1.0;
        gbcTerminal.weighty = 1.0;
        gbcTerminal.insets = new Insets(0,0,0,0);
        terminalPanel.add(terminalScrollPane, gbcTerminal);
    }

    public static void terminalToTextArea(Exception e) {
        StringWriter writer  = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.flush();

        terminalBox.append(writer.toString());
    }
}
