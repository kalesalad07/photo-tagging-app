import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FiveColumnGUI extends JFrame {
    private JTable table;
    private String[] headings = {"Photo ID", "Roll Number", "Frame Number", "Clicker ID", "Detailer ID", "Camera ID", "Time", "Club/Dept", "File Address"};
    private String[][] data;
    private JTextArea studentInfoTextArea;
    private JTextField inputTextField;

    public FiveColumnGUI() {
        setTitle("Photo Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create text area to display student info
        // studentInfoTextArea = new JTextArea(10, 20);
        // studentInfoTextArea.setEditable(false);
        // JScrollPane scrollPane = new JScrollPane(studentInfoTextArea);

        // Create panel to hold text area
        // JPanel textAreaPanel = new JPanel(new BorderLayout());
        // textAreaPanel.add(new JLabel("Student Info"), BorderLayout.NORTH);
        // textAreaPanel.add(scrollPane, BorderLayout.CENTER);

        // Create panel to hold input text field
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputTextField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
			
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "select photos.PHOTO_ID, photos.ROLL_NUM, photos.DRIVE_LINK, photos.FRAME_NUM, photos.DATE_TIME, "
					+ "club_dept.name, control_desk.clicker_id, control_desk.detailer_id, control_desk.camera_id "
					+ "from control_desk inner join photos on control_desk.photo_id = photos.photo_id "
					+ "inner join club_dept on club_dept.id = photos.club_dept;";
			ResultSet rs = stmt.executeQuery(query);
			int rowcount = 0;
			
			if(rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}	
			data = new String[rowcount][9];
    		int i = 0;
    		while(rs.next()) {
    			data[i][0] = String.valueOf(rs.getInt("PHOTO_ID"));
    			data[i][1] = String.valueOf(rs.getInt("ROLL_NUM"));
    			data[i][2] = String.valueOf(rs.getInt("FRAME_NUM"));
    			data[i][3] = String.valueOf(rs.getInt("clicker_id"));
    			data[i][4] = String.valueOf(rs.getInt("detailer_id"));
    			data[i][5] = String.valueOf(rs.getInt("camera_id"));
    			data[i][6] = rs.getString("DATE_TIME");
    			data[i][7] = rs.getString("name");
    			data[i][8] = rs.getString("DRIVE_LINK");
    			i++;
    		}
    		con.close();
				
    	} catch(Exception e1) {System.out.println(e1);}
        
        DefaultTableModel model = new DefaultTableModel(data, headings);
        table = new JTable(model);

        JScrollPane scrollPane2 = new JScrollPane(table);
        
        add(scrollPane2, BorderLayout.CENTER);
//        inputPanel.add(new JLabel("Enter Text:"));
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
//        inputPanel.add(inputTextField);
//
//        // Add panels to the frame
//        add(textAreaPanel, BorderLayout.CENTER);
//        add(inputPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FiveColumnGUI();
            }
        });
    }
}

