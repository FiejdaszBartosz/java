import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

public class MainInterfaceForm {
    private Client mClient;

    private JPanel mainPanel;
    private JPanel messagePanel;
    private JTextField messageTextField;
    private JPanel inputMessagePanel;
    private JLabel messageLabel;
    private JLabel chatLabel;
    private JTextArea chatTextArea;

    public MainInterfaceForm(String username, Color clientColour) {
        mainPanel.setBackground(clientColour);
        messagePanel.setForeground(clientColour);

        messageTextField.setForeground(clientColour);
        chatTextArea.setForeground(clientColour);

        JFrame mainFrame = new JFrame("Chat " + username);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.pack();
        mainFrame.setVisible(true);


        try {
            Socket socket = new Socket("localhost", 6666);
            mClient = new Client(socket, username);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        readMessage();

        messageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = "";
                temp = messageTextField.getText();
                if (!Objects.equals(temp, "")) {
                    mClient.sendMessage(temp);
                    messageTextField.setText("");
                }
            }
        });
    }

    private void readMessage() {
        new Thread(new Runnable() {
            String message;

            @Override
            public void run() {
                while ((message = mClient.waitForMessage()) != null)
                    chatTextArea.setText(chatTextArea.getText() + message + "\n");
            }
        }).start();
    }

    public static Color randomColors() {
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(5);

        return switch (randomNum) {
            case 0 -> new Color(67, 99, 114);
            case 1 -> new Color(43, 78, 96);
            case 2 -> new Color(106, 146, 168);
            case 3 -> new Color(75, 133, 125);
            default -> new Color(90, 164, 187);
        };
    }
}
