import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlDesk extends JFrame {
    public ControlDesk() {
        setTitle("Two Button GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a heading label
        JLabel headingLabel = new JLabel("Control Desk");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headingLabel, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Create buttons
        JButton button1 = new JButton("Camera Log");
        JButton button2 = new JButton("Photo Log");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CameraLogGUI();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PhotoLog();
            }
        });

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ControlDesk();
            }
        });
    }
}

