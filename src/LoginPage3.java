import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage3 extends JFrame {
    public LoginPage3() {
        setTitle("Login Page 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);

        JLabel headingLabel = new JLabel("Admin Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        GridBagConstraints gbcHeading = new GridBagConstraints();
        gbcHeading.gridx = 0;
        gbcHeading.gridy = 0;
        gbcHeading.gridwidth = 2;
        gbcHeading.insets = new Insets(10, 10, 20, 10); // Adding margin
        gbcHeading.anchor = GridBagConstraints.CENTER;
        panel.add(headingLabel, gbcHeading);

        JLabel usernameLabel = new JLabel("Username:");
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        gbcUsernameLabel.gridx = 0;
        gbcUsernameLabel.gridy = 1;
        gbcUsernameLabel.insets = new Insets(0, 10, 5, 5);
        gbcUsernameLabel.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbcUsernameLabel);

        JTextField usernameField = new JTextField(20); // Increased width of text field
        GridBagConstraints gbcUsernameField = new GridBagConstraints();
        gbcUsernameField.gridx = 1;
        gbcUsernameField.gridy = 1;
        gbcUsernameField.insets = new Insets(0, 0, 5, 10);
        gbcUsernameField.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbcUsernameField);

        JLabel passwordLabel = new JLabel("Password:");
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 2;
        gbcPasswordLabel.insets = new Insets(0, 10, 10, 5);
        gbcPasswordLabel.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbcPasswordLabel);

        JPasswordField passwordField = new JPasswordField(20); // Increased width of password field
        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        gbcPasswordField.insets = new Insets(0, 0, 10, 10);
        gbcPasswordField.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbcPasswordField);

        JButton loginButton = new JButton("Login");
        GridBagConstraints gbcLoginButton = new GridBagConstraints();
        gbcLoginButton.gridx = 0;
        gbcLoginButton.gridy = 3;
        gbcLoginButton.gridwidth = 2;
        gbcLoginButton.insets = new Insets(10, 10, 10, 10);
        gbcLoginButton.anchor = GridBagConstraints.CENTER;
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query =  "select admin_id from admin where admin_id = '"+username + "' and password = '" + password +"';";
        			ResultSet rs = stmt.executeQuery(query);
        			
        			int rowcount = 0;
        			if(rs.last()) {
        				rowcount = rs.getRow();
        				rs.beforeFirst();
        			}
        			
        			if(rowcount == 0) {
        				
        				con.close();
                        JOptionPane.showMessageDialog(LoginPage3.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);

//        				
        			}
        			else {
            			con.close();
            			new AdminGUI();
        			}
        			
        			
        		} catch(Exception e1) {System.out.println(e1);}
            }
        });
        panel.add(loginButton, gbcLoginButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
}

