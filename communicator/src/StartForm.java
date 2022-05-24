import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartForm {
    private static JFrame mainFrame;
    public static StartForm mForm;
    private ClientInterface clientInterface;
    private JPanel mainPanel;

    private JTextField inputNameTextField;
    private JLabel inputNameLabel;

    public StartForm() {
        inputNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(inputNameTextField.getText(), "")) {
                    clientInterface = new ClientInterface(inputNameTextField.getText());
                    mainFrame.dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        mainFrame = new JFrame("Log in");
        mForm = new StartForm();
        mainFrame.setContentPane(mForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
