import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResizableGUI extends JFrame {
    private JLabel headingLabel;
    private JButton button1, button2, button3;

    public ResizableGUI() {
        setTitle("Resizable GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Using GridBagLayout for better control over component positioning
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

        // Heading Label
        headingLabel = new JLabel("Resizable GUI");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(headingLabel, gbc);

        // Button 1
        button1 = new JButton("1");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset grid width
        add(button1, gbc);

        // Button 2
        button2 = new JButton("2");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(button2, gbc);

        // Button 3
        button3 = new JButton("3");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(button3, gbc);

        // Set up window
        pack(); // Pack the components to set the window size according to preferred sizes
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ResizableGUI();
            }
        });
    }
}
