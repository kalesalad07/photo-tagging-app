import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClubStudentList extends JFrame {
    private JPanel buttonPanel;

    public ClubStudentList(String[] pictureNames) {
        setTitle("Clubs/Department");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading Label
        JLabel headingLabel = new JLabel("View Members:");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headingLabel, BorderLayout.NORTH);

        // Panel to hold buttons
        buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

        // Create buttons for each picture name
        createButtons(pictureNames);

        // Set up window
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void createButtons(String[] pictureNames) {
        for (String name : pictureNames) {
            JButton button = new JButton(name);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Add action for button click if needed
                    // JOptionPane.showMessageDialog(PictureButtonGUI.this, "Button clicked: " + name);
//                	String[] filepaths = {
//                            "/Users/deepan_roy/Desktop/eval/bball_img.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/beach.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/crowd_stock.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/crowd_vid_ss.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/diwali.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/gate.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/mandir_1.jpeg",
//                            "/Users/deepan_roy/Desktop/eval/mandir.jpeg"
//                    };
                	
                	try {
            			Class.forName("com.mysql.cj.jdbc.Driver");
            			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
            			
            			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            			String query = "select name, id from student inner join membership on student.id = membership.s_id where membership.c_id = (select id from club_dept where name = '"+ name + "');";
            			ResultSet rs = stmt.executeQuery(query);
            			
            			int rowcount = 0;
            			if(rs.last()) {
            				rowcount = rs.getRow();
            				rs.beforeFirst();
            			}
            			
            			
            			String[] names = new String[rowcount];
            			String[] ids = new String[rowcount];
//                			
                		int i = 0;
                		while(rs.next()) {
                			names[i] = rs.getString("name");
                			ids[i] = rs.getString("id");
                			i++;
                		}
                		con.close();
                		new StudentListGUI(names, ids, name);
            				
                	} catch(Exception e1) {System.out.println(e1);}
                	
                	
                }
            });
            buttonPanel.add(button);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] pictureNames = {"Picture 1", "Picture 2", "Picture 3", "Picture 4", "Picture 5"};
                new ClubStudentList(pictureNames);
            }
        });
    }
}




//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class PictureButtonGUI extends JFrame {
//    public PictureButtonGUI(String[] images) {
//        setTitle("Picture Button GUI");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        for (String image : images) {
//            JButton button = new JButton(image);
//            button.setPreferredSize(new Dimension(100, 100)); // Decrease button size
//            buttonPanel.add(button);
//        }
//
//        JScrollPane scrollPane = new JScrollPane(buttonPanel);
//        add(scrollPane, BorderLayout.CENTER);
//
//        pack();
//        setSize(600, 450); // Increase frame size
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                String[] images = { "image1", "image2", "image3", "image4" }; // Example images
//                new PictureButtonGUI(images);
//            }
//        });
//    }
//}

