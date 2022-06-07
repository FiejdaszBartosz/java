package view;

import event.Event;
import eventHandler.EventHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ResultForm {
    private JPanel mainPanel;
    private JLabel typeLabel;
    private JTable mainTable;
    private JScrollPane mainScrollPanel;
    private JFrame mainFrame;
    private EventHandler mEventHandler;
    private String mType;

    ResultForm(EventHandler eventHandler, String type) {
        this.mEventHandler = eventHandler;
        this.mType = type;

        createUIComponents();
        typeLabel.setText(type);
        mainFrame = new JFrame(type);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Creates table
     */
    private void createUIComponents() {
        mainTable = new JTable();
        String[] headers = new String[]{"Amount", "Description", "Category", "Date"};

        DefaultTableModel dm = new DefaultTableModel(0, 0);
        dm.setColumnIdentifiers(headers);
        mainTable.setModel(dm);

        for (Event i : mEventHandler.getEventsArray()) {
            if (mType.equals(String.valueOf(i.getEventType()))) {
                Vector<Object> temp = new Vector<Object>();
                temp.add(i.getAmount());
                temp.add(i.getDescription());
                temp.add(i.getCategory());
                temp.add(i.getDate());
                dm.addRow(temp);
            }
        }
    }
}
