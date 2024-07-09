//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class LoginFrame extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JButton loginButton;
//
//    public LoginFrame() {
//        setTitle("Login Page");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Using GridBagLayout for better control over component positioning
//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
//
//        JLabel usernameLabel = new JLabel("Username:");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        add(usernameLabel, gbc);
//
//        usernameField = new JTextField(20); // Set preferred width
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        add(usernameField, gbc);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        add(passwordLabel, gbc);
//
//        passwordField = new JPasswordField(20); // Set preferred width
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        add(passwordField, gbc);
//
//        loginButton = new JButton("Login");
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        gbc.anchor = GridBagConstraints.CENTER; // Center button horizontally
//        add(loginButton, gbc);
//
//        // Action listener for the login button
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//                // Here you can implement your login logic
//                // For simplicity, let's just display the entered username and password
//                JOptionPane.showMessageDialog(LoginFrame.this, "Username: " + username + "\nPassword: " + password);
//            }
//        });
//
//        pack(); // Pack the components to set the window size according to preferred sizes
//        setLocationRelativeTo(null); // Center the window on the screen
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new LoginFrame();
//            }
//        });
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login Page");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Check if username and password are correct
                if (username.equals("admin") && password.equals("admin")) {
                    openNewGUI();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for layout purposes
        add(loginButton);

        setVisible(true);
    }

    private void openNewGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("New GUI");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel headingLabel = new JLabel("Welcome to the New GUI!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.setLayout(new BorderLayout());
        frame.add(headingLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}


