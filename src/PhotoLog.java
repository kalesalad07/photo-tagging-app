import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhotoLog extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] headings = {"Photo ID", "Roll Number", "Frame Number", "Clicker ID", "Detailer ID", "Camera ID", "Time", "File Address"};
    private String[][] data;
    private JTextArea studentInfoTextArea;
    private JTextField inputTextField;
    

    public PhotoLog() {
        // Frame setup
        setTitle("Photo Log");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout());

        // Table setup
//        tableModel = new DefaultTableModel();
//        tableModel.addColumn("Data");
//        table = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(table);
//        add(scrollPane, BorderLayout.CENTER);

        // Panel for text field and buttons
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputTextField = new JTextField(20);
        
        
        
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
			
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "select photos.PHOTO_ID, photos.ROLL_NUM, photos.DRIVE_LINK, photos.FRAME_NUM, photos.DATE_TIME, "
					+ " control_desk.clicker_id, control_desk.detailer_id, control_desk.camera_id "
					+ "from control_desk inner join photos on control_desk.photo_id = photos.photo_id "
					+ ";";
			ResultSet rs = stmt.executeQuery(query);
			int rowcount = 0;
			
			if(rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}	
			data = new String[rowcount][8];
    		int i = 0;
    		while(rs.next()) {
    			data[i][0] = String.valueOf(rs.getInt("PHOTO_ID"));
    			data[i][1] = String.valueOf(rs.getInt("ROLL_NUM"));
    			data[i][2] = String.valueOf(rs.getInt("FRAME_NUM"));
    			data[i][3] = String.valueOf(rs.getInt("clicker_id"));
    			data[i][4] = String.valueOf(rs.getInt("detailer_id"));
    			data[i][5] = String.valueOf(rs.getInt("camera_id"));
    			data[i][6] = rs.getString("DATE_TIME");
    			data[i][7] = rs.getString("DRIVE_LINK");
    			i++;
    		}
    		con.close();
				
    	} catch(Exception e1) {System.out.println(e1);}
        
        DefaultTableModel model = new DefaultTableModel(data, headings);
        table = new JTable(model);

        JScrollPane scrollPane2 = new JScrollPane(table);
        
        add(scrollPane2, BorderLayout.CENTER);
        
        

        

      

        

        add(inputPanel, BorderLayout.SOUTH);

        // Make the frame visible
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PhotoLog();
            }
        });
    }
}
