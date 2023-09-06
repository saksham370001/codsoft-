import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cod extends JFrame implements ActionListener {
    // GUI Components
    private JTextField[] subjectFields;
    private JTextField resultField;
    private JButton calculateButton;

    public cod() {
        // Create the frame
        setTitle("Student Grade Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panel for components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Initialize subjectFields array
        subjectFields = new JTextField[5];

        // Add labels and input fields for 5 subjects
        String[] subjectNames = {"Maths", "Physics", "English", "Computer Science", "Chemistry"};
        for (int i = 0; i < 5; i++) {
            panel.add(new JLabel(subjectNames[i] + " Score:"));
            subjectFields[i] = new JTextField();
            panel.add(subjectFields[i]);
        }

        // Create calculate button
        calculateButton = new JButton("Calculate Average and Percentage");
        calculateButton.addActionListener(this);
        panel.add(calculateButton);

        // Create result field
        resultField = new JTextField();
        resultField.setEditable(false);
        panel.add(resultField);

        // Add panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            // Calculate the average and percentage of subject scores
            try {
                double totalScore = 0.0;
                int maxScore = 100; // Assuming the maximum score for each subject is 100

                for (int i = 0; i < 5; i++) {
                    double subjectScore = Double.parseDouble(subjectFields[i].getText());
                    totalScore += subjectScore;
                }

                // Calculate the average
                double average = totalScore / 5;

                // Calculate the percentage
                double percentage = (totalScore / (5 * maxScore)) * 100;

                // Display the result
                resultField.setText("Average Score: " + average + "  Percentage: " + percentage + "%");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new cod());
    }
}
