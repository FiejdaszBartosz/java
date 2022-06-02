package view;

import event.Event;
import event.IEvent;
import exceptions.AmountTypeException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventForm {
    private JLabel createEventLabel;
    private JPanel inputPanel;
    private JLabel typeLabel;
    private JComboBox typeComboBox;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JPanel typePanel;
    private JPanel amountPanel;
    private JPanel descriptionPanel;
    private JLabel descriptionLabel;
    private JTextField descriptionTextField;
    private JPanel categoryPanel;
    private JLabel categoryLabel;
    private JTextField categoryTextField;
    private JPanel datePanel;
    private JLabel dateLabel;
    private JTextField dateTextField;
    private JPanel mainPanel;
    private JButton addButton;

    private Event mTempEvent;

    private boolean mCheckInputs;

    public EventForm() {
        this.typeComboBox.setModel(new DefaultComboBoxModel<>(IEvent.EventType.values()));
        this.mCheckInputs = true;

        addButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parseEvent();
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Add Event");
        EventForm mForm = new EventForm();
        mainFrame.setContentPane(mForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Parse event
     * @return event
     */
    public Event parseEvent() {
        Event event = null;
        try {
            event = new Event(
                    (IEvent.EventType) typeComboBox.getSelectedItem(),
                    parseAmount(),
                    parseDescription(),
                    parseCategory(),
                    parseDate());
        } catch (AmountTypeException e) {
            this.mCheckInputs = false;
        }

        return event;
    }

    /**
     * Gets input from amountTextField. Marks if input is empty String or incorrect type.
     *
     * @return amount from amountTextField
     */
    private double parseAmount() {
        String input = amountTextField.getText();
        double output = 0;
        boolean checkInput = !input.equals("");

        if (checkInput) {
            try {
                output = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                this.mCheckInputs = false;
            }
        } else
            this.mCheckInputs = false;

        return output;
    }

    /**
     * Gets input from descriptionTextField. Marks if input is empty String.
     *
     * @return description from descriptionTextField
     */
    private String parseDescription() {
        String input = descriptionTextField.getText();
        boolean checkInput = input.equals("");

        if (checkInput)
            this.mCheckInputs = false;

        return input;
    }

    /**
     * Gets input from categoryTextField. Marks if input is empty String.
     *
     * @return description from categoryTextField
     */
    private String parseCategory() {
        String input = categoryTextField.getText();
        boolean checkInput = input.equals("");

        if (checkInput)
            this.mCheckInputs = false;

        return input;
    }

    /**
     * Gets input from categoryTextField. Marks if input is empty String.
     *
     * @return description from categoryTextField
     */
    private String parseDate() {
        String input = dateTextField.getText();
        boolean checkInput = input.equals("");

        if (checkInput || !input.matches("\\d{4}-\\d{2}-\\d{2}"))
            this.mCheckInputs = false;

        return input;
    }
}
