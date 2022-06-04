package view;

import event.Event;
import eventHandler.EventHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerForm {
    private JPanel mainPanel;
    private JButton addEventButton;
    private JPanel resultPanel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel incomeAmountLabel;
    private JLabel expenseAmountLabel;
    private JLabel balanceLabel;
    private JLabel amountBalance;
    private JButton showIncomeButton;
    private JButton showExpenseButton;
    private JPanel showPanel;
    private static JFrame mMainFrame;
    private EventForm mEventForm;
    private ResultForm mResultForm;
    private EventHandler mEventHandler;
    private static ManagerForm managerForm;


    public ManagerForm() {
        addEventButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mEventForm = new EventForm(managerForm);
            }
        });
        this.mEventHandler = new EventHandler();

        mMainFrame = new JFrame("Manager");
        mMainFrame.setContentPane(mainPanel);
        mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mMainFrame.pack();
        mMainFrame.setVisible(true);
        showIncomeButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mResultForm = new ResultForm(mEventHandler, "INCOME");
            }
        });
        showExpenseButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mResultForm = new ResultForm(mEventHandler, "EXPENSE");
            }
        });
    }

    public void addEvent(Event event) {
        this.mEventHandler.addEvent(event);
        this.expenseAmountLabel.setText(String.valueOf(mEventHandler.calculateExpense()));
        this.incomeAmountLabel.setText(String.valueOf(mEventHandler.calculateIncome()));
        this.amountBalance.setText(String.valueOf(mEventHandler.getAccountBalance()));
    }


    public static void main(String[] args) {
        managerForm = new ManagerForm();
    }
}
