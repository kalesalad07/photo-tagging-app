import javax.swing.*;
import java.sql.*;

import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    public MainGUI() {
        setTitle("Main GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        JLabel headingLabel = new JLabel("Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headingLabel);

        JButton button1 = new JButton("Club/Dept Head");
        JButton button2 = new JButton("Student");
        JButton button3 = new JButton("Admin");

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);

        Dimension buttonSize = new Dimension(200, 40);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);

        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage1();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage2();
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage3();
            }
        });

        mainPanel.add(Box.createVerticalStrut(20)); // Add some vertical spacing
        mainPanel.add(button1);
        mainPanel.add(Box.createVerticalStrut(10)); // Add some vertical spacing
        mainPanel.add(button2);
        mainPanel.add(Box.createVerticalStrut(10)); // Add some vertical spacing
        mainPanel.add(button3);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}

