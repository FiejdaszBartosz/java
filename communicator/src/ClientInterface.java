import client.Client;
import message.Message;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class ClientInterface {
    private static ClientInterface mForm;

    private Client mClient;

    private JPanel mainPanel;
    private JPanel messagePanel;
    private JTextField messageTextField;
    private JPanel inputMessagePanel;
    private JComboBox comboBox1;
    private JLabel toWhoLabel;
    private JLabel messageLabel;
    private JLabel chatLabel;
    private JTextArea chatTextArea;

    private void readMessage() {
        new Thread(new Runnable() {
            Message message;
            String content = "";

            @Override
            public void run() {
                while ((message = mClient.waitForMessage()) != null) {
                    content = chatTextArea.getText() + message.presentMessage() + "\n";
                    chatTextArea.setText(content);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Chat");
        mForm = new ClientInterface();
        mainFrame.setContentPane(mForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);


        try {
            Socket socket = new Socket("localhost", 6666);
            mForm.mClient = new Client(socket, "Fedson1");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        mForm.readMessage();



    }
}
