import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CameraLogGUI extends JFrame {
    private JTable table;
    private int[] clickerIDs;
    private int[] detailerIDs;
    private int[] cameraIDs;
    private int[] rollNums;
    private String[] dateTime;

    public CameraLogGUI() {
        setTitle("Camera Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model with three columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Camera ID");
        model.addColumn("Clicker ID");
        model.addColumn("Detailer ID");
        model.addColumn("Roll Number");
        model.addColumn("Time");
        
        // Fetch data w/ query
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
			
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "select control_desk.clicker_id, control_desk.detailer_id, photos.roll_num, control_desk.camera_id, photos.date_time "
					+ "from control_desk inner join photos on control_desk.photo_id = photos.photo_id "
					+ "where photos.date_time in ("
					+ "select max(photos.date_time) "
					+ "from control_desk inner join photos on control_desk.photo_id = photos.photo_id "
					+ "group by camera_id)"
					+ "order by date_time desc;";
			ResultSet rs = stmt.executeQuery(query);
			
			int rowcount = 0;
			if(rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}
			clickerIDs = new int[rowcount];
			detailerIDs = new int[rowcount];
			cameraIDs = new int[rowcount];
			rollNums = new int[rowcount];
			dateTime = new String[rowcount];
//    			
    		int i = 0;
    		while(rs.next()) {
    			clickerIDs[i] = rs.getInt("CLICKER_ID");
    			detailerIDs[i] = rs.getInt("DETAILER_ID");
    			cameraIDs[i] = rs.getInt("CAMERA_ID");
    			rollNums[i] = rs.getInt("ROLL_NUM");
    			dateTime[i] = rs.getString("DATE_TIME");
    			i++;
    		}
    		con.close();
				
    	} catch(Exception e1) {System.out.println(e1);}
        
        // Add data to the table model
        for (int i = 0; i < cameraIDs.length; i++) {
            model.addRow(new Object[]{cameraIDs[i], detailerIDs[i], cameraIDs[i], rollNums[i], dateTime[i]});
        }

        // Create table with the model
        table = new JTable(model);

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        // Create scroll pane and add table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Add scroll pane to frame
        add(scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CameraLogGUI();
            }
        });
    }
}

