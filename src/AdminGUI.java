import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    public AdminGUI() {
        setTitle("Two Button GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a heading label
        JLabel headingLabel = new JLabel("Admin Screen");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headingLabel, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Create buttons
        JButton button1 = new JButton("Open Control Desk");
        JButton button2 = new JButton("Tagging Screen");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ControlDesk();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "select DRIVE_LINK from photos";
        			ResultSet rs = stmt.executeQuery(query);
        			
        			int rowcount = 0;
        			if(rs.last()) {
        				rowcount = rs.getRow();
        				rs.beforeFirst();
        			}
        			
        			
        			String arr[] = new String[rowcount];
//            			
            		int i = 0;
            		while(rs.next()) {
            			arr[i] = rs.getString("DRIVE_LINK");
            			
            			i++;
            		}
            		con.close();
            		new PhotoGalleryGUI(arr);
            		
        				
            	} catch(Exception e1) {System.out.println(e1);}
                
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
                new AdminGUI();
            }
        });
    }
}

