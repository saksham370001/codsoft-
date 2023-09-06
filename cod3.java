import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class cod3 extends JFrame implements ActionListener, ChangeListener {
    private JTabbedPane tabbedPane;
    private JPanel setPinPanel;
    private JPanel mainPanel;
    private JPanel balancePanel;
    private JPanel depositPanel;
    private JPanel withdrawPanel;
    
    private JTextField setPinField;
    private JPasswordField pinField; // Changed to JPasswordField for PIN entry
    private JTextField balanceField;
    private JTextField depositField;
    private JTextField withdrawField;
    
    private JButton setPinButton;
    private JButton pinEnterButton;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    
    private String userPin = null;
    private double accountBalance = 0.0;
    private boolean pinVerified = false;

    public cod3() {
        // Create the frame
        setTitle("ATM Interface");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(this);

        // Create panels for each tab
        setPinPanel = new JPanel();
        mainPanel = new JPanel();
        balancePanel = new JPanel();
        depositPanel = new JPanel();
        withdrawPanel = new JPanel();

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Set PIN", setPinPanel);
        tabbedPane.addTab("Main Menu", mainPanel);
        tabbedPane.addTab("Check Balance", balancePanel);
        tabbedPane.addTab("Deposit", depositPanel);
        tabbedPane.addTab("Withdraw", withdrawPanel);

        // Set up the "Set PIN" tab
        setPinPanel.setLayout(new FlowLayout());
        setPinField = new JTextField(10);
        setPinButton = new JButton("Set PIN");
        setPinButton.addActionListener(this);
        setPinPanel.add(new JLabel("Set your PIN: "));
        setPinPanel.add(setPinField);
        setPinPanel.add(setPinButton);

        // Set up the "Main Menu" tab
        mainPanel.setLayout(new GridLayout(4, 1));
        pinField = new JPasswordField(10); // Changed to JPasswordField
        pinEnterButton = new JButton("Enter PIN");
        pinEnterButton.addActionListener(this);
        mainPanel.add(new JLabel("Enter your PIN: "));
        mainPanel.add(pinField);
        mainPanel.add(pinEnterButton);

        // Set up the "Check Balance" tab
        balancePanel.setLayout(new FlowLayout());
        balanceField = new JTextField(10);
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        balanceField.setEditable(false);
        balancePanel.add(new JLabel("Your Balance: "));
        balancePanel.add(balanceField);
        balancePanel.add(checkBalanceButton);

        // Set up the "Deposit" tab
        depositPanel.setLayout(new FlowLayout());
        depositField = new JTextField(10);
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        depositPanel.add(new JLabel("Enter deposit amount: "));
        depositPanel.add(depositField);
        depositPanel.add(depositButton);

        // Set up the "Withdraw" tab
        withdrawPanel.setLayout(new FlowLayout());
        withdrawField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        withdrawPanel.add(new JLabel("Enter withdrawal amount: "));
        withdrawPanel.add(withdrawField);
        withdrawPanel.add(withdrawButton);

        // Add tabbed pane to the frame
        add(tabbedPane);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setPinButton) {
            userPin = setPinField.getText();
            setPinField.setText("");
            JOptionPane.showMessageDialog(this, "PIN set successfully!");
        } else if (e.getSource() == pinEnterButton) {
            char[] enteredPin = pinField.getPassword(); // Get the entered PIN as a char array
            String enteredPinStr = new String(enteredPin); // Convert to String
            if (enteredPinStr.equals(userPin)) {
                pinVerified = true;
                tabbedPane.setEnabledAt(1, true); // Enable the "Main Menu" tab
                tabbedPane.setSelectedIndex(1); // Switch to the "Main Menu" tab
                pinField.setText(""); // Clear the PIN field
            } else {
                JOptionPane.showMessageDialog(this, "Invalid PIN. Please try again.");
                pinField.setText("");
            }
        } else if (e.getSource() == checkBalanceButton) {
            if (pinVerified) {
                balanceField.setText("$" + accountBalance);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid PIN.");
            }
        } else if (e.getSource() == depositButton) {
            if (pinVerified) {
                try {
                    double depositAmount = Double.parseDouble(depositField.getText());
                    accountBalance += depositAmount;
                    JOptionPane.showMessageDialog(this, "Deposit successful!");
                    depositField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid PIN.");
            }
        } else if (e.getSource() == withdrawButton) {
            if (pinVerified) {
                try {
                    double withdrawAmount = Double.parseDouble(withdrawField.getText());
                    if (withdrawAmount <= accountBalance) {
                        accountBalance -= withdrawAmount;
                        JOptionPane.showMessageDialog(this, "Withdrawal successful!");
                        withdrawField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid PIN.");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (tabbedPane.getSelectedIndex() == 1) {
            balanceField.setText("");
            depositField.setText("");
            withdrawField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new cod3());
    }
}
