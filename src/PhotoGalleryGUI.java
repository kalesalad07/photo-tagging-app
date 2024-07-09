import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhotoGalleryGUI extends JFrame {
    private JLabel photoLabel;
    private JButton prevButton;
    private JButton nextButton;
    private JTextArea nameTextArea;
    private JTextField textBox;
    private JButton addButton;
    private JButton removeButton;
    private String[] filepaths;
    private ArrayList<String> names;
    private int currentPhotoIndex = 0;
    private int noOfPhotos;

    public PhotoGalleryGUI(String filepaths[]) {
    	this.filepaths = filepaths;
    	noOfPhotos = filepaths.length;
        setTitle("Photo Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Photo display panel
        JPanel photoPanel = new JPanel(new BorderLayout());
        photoLabel = new JLabel();
        updatePhoto();
        photoPanel.add(photoLabel, BorderLayout.CENTER);

        // Navigation buttons
        prevButton = new JButton("Previous");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPreviousPhoto();
                updateNames();
            }
        });
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNextPhoto();
                updateNames();
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        photoPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Names display panel
        JPanel namesPanel = new JPanel(new BorderLayout());
        nameTextArea = new JTextArea();
        nameTextArea.setEditable(false);
        updateNames();
        JScrollPane scrollPane = new JScrollPane(nameTextArea);
        namesPanel.add(new JLabel("Names:"), BorderLayout.NORTH);
        namesPanel.add(scrollPane, BorderLayout.CENTER);

        // Text box and buttons panel
        JPanel textBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textBox = new JTextField(20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String id = textBox.getText();
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			
        			String query = "insert into tagged_in (s_id, photo_id) values ('"+ id + "', (select photo_id from photos where DRIVE_LINK = '" + filepaths[currentPhotoIndex] + "'));";
        			int rs = stmt.executeUpdate(query);
        			
        			
        			
            		con.close();
        				
            	} catch(Exception e1) {System.out.println(e1);}
            	
                textBox.setText("");
                updateNames();
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String id = textBox.getText();
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			
        			String query = "delete from tagged_in where s_id = '" + id + "' and photo_id in (select PHOTO_ID from photos where DRIVE_LINK = '" + filepaths[currentPhotoIndex] + "') ;";
        			int rs = stmt.executeUpdate(query);
        			
        			
        			
            		con.close();
        				
            	} catch(Exception e1) {System.out.println(e1);}
            	
                textBox.setText("");
                updateNames();
            
            }
        });
        textBoxPanel.add(textBox);
        textBoxPanel.add(addButton);
        textBoxPanel.add(removeButton);

        // Add components to the frame
        add(photoPanel, BorderLayout.WEST);
        add(namesPanel, BorderLayout.CENTER);
        add(textBoxPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updatePhoto() {
        ImageIcon icon = new ImageIcon(filepaths[currentPhotoIndex]);
        Image image = icon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(image));
    }

    private void updateNames() {
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
			
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			String query = "select s_id from tagged_in where photo_id in (select PHOTO_ID from photos where DRIVE_LINK ='" + filepaths[currentPhotoIndex] + "');";
			ResultSet rs = stmt.executeQuery(query);
			int rowcount = 0;
			
			if(rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}	
			names = new ArrayList<String>();
    		int i = 0;
    		while(rs.next()) {
    			names.add(rs.getString("s_id"));
    			i++;
    		}
    		con.close();
				
    	} catch(Exception e1) {System.out.println(e1);}
    	
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            builder.append("• ").append(name).append("\n");
        }
        nameTextArea.setText(builder.toString());
    }

    private void showPreviousPhoto() {
        currentPhotoIndex--;
        if(currentPhotoIndex == -1) currentPhotoIndex = noOfPhotos - 1;
        updatePhoto();
    }

    private void showNextPhoto() {
        currentPhotoIndex++;
        if(currentPhotoIndex == noOfPhotos)currentPhotoIndex = 0;
        updatePhoto();
    }

    
    
}