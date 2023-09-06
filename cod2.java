import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class cod2 extends JFrame implements ActionListener {
    // GUI Components
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    
    // Game Variables
    private int randomNumber;
    private int attemptsLeft = 10;

    public cod2() {
        // Create the frame
        setTitle("Number Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        // Create panel for components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Add labels and input fields
        panel.add(new JLabel("Guess the number (1-100):"));
        guessField = new JTextField();
        panel.add(guessField);

        // Create guess button
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        panel.add(guessButton);

        // Create result label
        resultLabel = new JLabel("Attempts left: " + attemptsLeft);
        panel.add(resultLabel);

        // Add panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                
                if (userGuess < 1 || userGuess > 100) {
                    JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 100.");
                } else {
                    attemptsLeft--;
                    if (userGuess == randomNumber) {
                        resultLabel.setText("Congratulations! You guessed the correct number.");
                        guessButton.setEnabled(false);
                    } else {
                        if (userGuess < randomNumber) {
                            resultLabel.setText("Try a higher number. Attempts left: " + attemptsLeft);
                        } else {
                            resultLabel.setText("Try a lower number. Attempts left: " + attemptsLeft);
                        }
                        
                        if (attemptsLeft == 0) {
                            resultLabel.setText("Out of attempts. The correct number was " + randomNumber + ".");
                            guessButton.setEnabled(false);
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
            }
            
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new cod2());
    }
}
