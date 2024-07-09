//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class LoginPage1 extends JFrame {
//    public LoginPage1() {
//        setTitle("Login Page 1");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new GridLayout(4, 2));
//
//        JLabel headingLabel = new JLabel("Welcome to Login Page 1");
//        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        add(headingLabel);
//
//        JLabel usernameLabel = new JLabel("Username:");
//        JTextField usernameField = new JTextField();
//        add(usernameLabel);
//        add(usernameField);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        JPasswordField passwordField = new JPasswordField();
//        add(passwordLabel);
//        add(passwordField);
//
//        JButton loginButton = new JButton("Login");
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//                if (username.equals("user1") && password.equals("password1")) {
//                    JOptionPane.showMessageDialog(LoginPage1.this, "Login successful");
//                } else {
//                    JOptionPane.showMessageDialog(LoginPage1.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//        add(loginButton);
//
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//}
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage1 extends JFrame {
    public LoginPage1() {
        setTitle("Login Page 1");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);

        JLabel headingLabel = new JLabel("POR Login");
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
        
//        JButton loginButton = new JButton("Login");
//        GridBagConstraints gbcLoginButton = new GridBagConstraints();
//        gbcLoginButton.gridx = 0;
//        gbcLoginButton.gridy = 3;
//        gbcLoginButton.gridwidth = 2;
//        gbcLoginButton.insets = new Insets(10, 10, 10, 10);
//        gbcLoginButton.anchor = GridBagConstraints.CENTER;


        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // String[] clubNames = {"club 1", "dept 1"};
                
                boolean flag = true;
                try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "select name from por inner join club_dept on por.c_id = club_dept.id where s_id = '" + username + "' and password = '" + password + "'";
        			ResultSet rs = stmt.executeQuery(query);
        			
        			int rowcount = 0;
        			if(rs.last()) {
        				rowcount = rs.getRow();
        				rs.beforeFirst();
        			}
        			
        			if(rowcount == 0) {
        				flag = false;
        				con.close();
        				JOptionPane.showMessageDialog(LoginPage1.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        			}
        			else {
        				String[] arr = new String[rowcount];
//            			
            			int i = 0;
            			while(rs.next()) {
            				arr[i] = rs.getString("name");
            				i++;
            			}
            			con.close();
            			new ClubStudentList(arr);
        			}
        			
        			
        		} catch(Exception e1) {System.out.println(e1);}
                
                
                
                if (flag) {
                    
                } else {
                    
                }
            }
        });

        panel.add(loginButton, gbcLoginButton);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
}
