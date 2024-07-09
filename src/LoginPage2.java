import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage2 extends JFrame {
    public LoginPage2() {
//        setTitle("Login Page 2");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new GridLayout(4, 2));
//
//        JLabel headingLabel = new JLabel("Welcome to Login Page 2");
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
        setTitle("Login Page 2");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);

        JLabel headingLabel = new JLabel("Student Login");
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
        			String query =  "select id from student where id = '"+username + "' and password = '" + password +"';";
        			ResultSet rs = stmt.executeQuery(query);
        			
        			int rowcount = 0;
        			if(rs.last()) {
        				rowcount = rs.getRow();
        				rs.beforeFirst();
        			}
        			
        			if(rowcount == 0) {
        				
        				con.close();
                        JOptionPane.showMessageDialog(LoginPage2.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);

//        				
        			}
        			else {
        				
            			con.close();
            			openNewGUI(username);
        			}
        			
        			
        		} catch(Exception e1) {System.out.println(e1);}
                
                
                
                
            }
        });
        panel.add(loginButton, gbcLoginButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openNewGUI(String id) {
        JFrame frame = new JFrame();
        frame.setTitle("Welcome Student");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel headingLabel = new JLabel("Welcome to DoPy!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton button1 = new JButton("View My Pictures");
        JButton button2 = new JButton("View My Clubs/Dept");
        JButton button3 = new JButton("View My Bill");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open file path upon clicking the button
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                int option = fileChooser.showOpenDialog(frame);
//                if (option == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    JOptionPane.showMessageDialog(frame, "Opening file path: " + selectedFile.getAbsolutePath());
//                }
//                String filePath = "/Users/deepan_roy/Desktop/eval"; // Replace this with your file path
//                try {
//                    Desktop.getDesktop().open(new File(filePath));
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
            	
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "select drive_link from photos inner join tagged_in on photos.photo_id = tagged_in.photo_id where s_id ='" + id + "';" ;
        			ResultSet rs = stmt.executeQuery(query);
        			
        			int rowcount = 0;
        			if(rs.last()) {
        				rowcount = rs.getRow();
        				rs.beforeFirst();
        			}
        			
        			
        			String[] arr = new String[rowcount];
//            			
            		int i = 0;
            		while(rs.next()) {
            			arr[i] = rs.getString("DRIVE_LINK");
            			i++;
            		}
            		con.close();
            		new ImageGallery(arr);
        				
            	} catch(Exception e1) {System.out.println(e1);}
            	
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String[] pictureNames = {"Club 1", "Club 2"};
                // Add code to open Price Calculator GUI
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "select name from club_dept inner join membership on membership.c_id = club_dept.id where membership.s_id = '"+id+"';";
        			ResultSet rs = stmt.executeQuery(query);
        			
        			int rowcount = 0;
        			if(rs.last()) {
        				rowcount = rs.getRow();
        				rs.beforeFirst();
        			}
        			
        			
        			String[] arr = new String[rowcount];
//            			
            		int i = 0;
            		while(rs.next()) {
            			arr[i] = rs.getString("name");
            			i++;
            		}
            		con.close();
            		new PictureButtonGUI(arr);
        				
            	} catch(Exception e1) {System.out.println(e1);}
            	
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to open Price Calculator GUI
                new PriceCalculator(id);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.setLayout(new BorderLayout());
        frame.add(headingLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

