package view;

import calculate.Calculate;
import exceptions.InvalidInputException;
import validations.Validations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorForm {
    private static CalculatorForm mForm;

    private JPanel mainPanel;
    private JTextField textField;
    private JButton undoButton;
    private JButton clearButton;
    private JButton openParenthesisButton;
    private JButton closeParenthesisButton;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton divisionButton;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton multiplicationButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton subtractionButton;
    private JButton button0;
    private JButton commaButton;
    private JButton equalButton;
    private JButton additionButton;
    private JPanel ButtonsPanel;
    private JPanel ExitPanel;
    private JButton exitButton;

    private String mExpression;
    private int mParenthesisCounter;

    private void setTextField() {
        if (!Validations.ifIgnoreSign(mExpression))
            textField.setText(mExpression);
        else
            mExpression = mExpression.substring(0, mExpression.length() - 1);
    }

    public CalculatorForm() {
        mExpression = "";
        mParenthesisCounter = 0;

        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "0";
                setTextField();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "1";
                setTextField();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "2";
                setTextField();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "3";
                setTextField();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "4";
                setTextField();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "5";
                setTextField();
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "6";
                setTextField();
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "7";
                setTextField();
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "8";
                setTextField();
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "9";
                setTextField();
            }
        });

        commaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += ".";
                setTextField();
            }
        });

        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "+";
                setTextField();
            }
        });

        subtractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "-";
                setTextField();
            }
        });

        multiplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "x";
                setTextField();
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression += "/";
                setTextField();
            }
        });

        openParenthesisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mParenthesisCounter++;
                mExpression += "(";
                setTextField();
            }
        });

        closeParenthesisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mExpression.length() != 0)
                    if (Validations.balancedParenthesizes(mExpression.charAt(mExpression.length() - 1))) {
                        mParenthesisCounter--;
                        mExpression += ")";
                        setTextField();
                    }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExpression = "";
                setTextField();
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mExpression.length() > 0)
                    mExpression = mExpression.substring(0, mExpression.length() - 1);

                setTextField();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mParenthesisCounter == 0) {
                    try {
                        mExpression = String.valueOf(Calculate.solve(mExpression));
                    } catch (InvalidInputException err) {
                        textField.setText(err.getMessage());
                    }
                    setTextField();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Calculator");
        mForm = new CalculatorForm();
        mainFrame.setContentPane(mForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
