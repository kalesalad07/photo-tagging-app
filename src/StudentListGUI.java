//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class StudentListGUI extends JFrame {
//    private JTextArea studentInfoTextArea;
//    private JTextField inputTextField;
//
//    public StudentListGUI(String[] names, String[] rollNos) {
//        setTitle("Student List");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Create text area to display student info
//        studentInfoTextArea = new JTextArea(10, 20);
//        studentInfoTextArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(studentInfoTextArea);
//
//        // Create panel to hold text area
//        JPanel textAreaPanel = new JPanel(new BorderLayout());
//        textAreaPanel.add(new JLabel("Student Info"), BorderLayout.NORTH);
//        textAreaPanel.add(scrollPane, BorderLayout.CENTER);
//
//        // Create panel to hold input text field
//        JPanel inputPanel = new JPanel(new BorderLayout());
//        inputTextField = new JTextField();
//        inputTextField.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                clearInputTextField();
//            }
//        });
//        inputPanel.add(new JLabel("Enter Text:"), BorderLayout.WEST);
//        inputPanel.add(inputTextField, BorderLayout.CENTER);
//
//        // Add panels to the frame
//        add(textAreaPanel, BorderLayout.CENTER);
//        add(inputPanel, BorderLayout.SOUTH);
//
//        // Populate student info
//        displayStudentInfo(names, rollNos);
//
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private void displayStudentInfo(String[] names, String[] rollNos) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < Math.min(names.length, rollNos.length); i++) {
//            sb.append(names[i]).append("\t").append(rollNos[i]).append("\n");
//        }
//        studentInfoTextArea.setText(sb.toString());
//    }
//
//    private void clearInputTextField() {
//        inputTextField.setText("");
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                String[] names = { "John", "Alice", "Bob", "Emma", "Michael" };
//                String[] rollNos = { "101", "102", "103", "104", "105" };
//                new StudentListGUI(names, rollNos);
//            }
//        });
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class StudentListGUI extends JFrame {
    private JTextArea studentInfoTextArea;
    private JTextField inputTextField;
    ArrayList<String> names;
    ArrayList<String> rollNos;
    int noOfMembers;
    String clubName;

    public StudentListGUI(String[] names, String[] rollNos, String clubName) {
    	this.names = new ArrayList<String>();
    	this.rollNos = new ArrayList<String>();
    	this.clubName = clubName;
    	this.noOfMembers = names.length;
    	Collections.addAll(this.names, names);
    	Collections.addAll(this.rollNos, rollNos);
    	
        setTitle("Student List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create text area to display student info
        studentInfoTextArea = new JTextArea(10, 20);
        studentInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentInfoTextArea);

        // Create panel to hold text area
        JPanel textAreaPanel = new JPanel(new BorderLayout());
        textAreaPanel.add(new JLabel("Student Info"), BorderLayout.NORTH);
        textAreaPanel.add(scrollPane, BorderLayout.CENTER);

        // Create panel to hold input text field
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputTextField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
//        inputTextField.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                clearInputTextField();
//            }
//        });
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for button click if needed
            	String toAdd = inputTextField.getText();
            	
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "insert into membership (c_id, s_id) values ((select id from club_dept where name = '" + clubName+"'), '"+toAdd+"');";
        			int result = stmt.executeUpdate(query);
        			noOfMembers += result;
            		con.close();
            		
        				
            	} catch(Exception e1) {System.out.println(e1);}
            	
            	refresh();
                repaint();
                revalidate();
                inputTextField.setText("");
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for button click if needed
            	
            	String toRemove = inputTextField.getText();
            	
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "delete from membership where c_id = (select id from club_dept where name = '"+clubName+"') and s_id = '"+toRemove+"';";
        			int result = stmt.executeUpdate(query);
        			noOfMembers -= result;
            		con.close();
            		
        				
            	} catch(Exception e1) {System.out.println(e1);}
            	
            	
            	
            	refresh();
                repaint();
                revalidate();
                inputTextField.setText("");
            }
        });
        
        inputPanel.add(new JLabel("Enter Text:"));
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(inputTextField);

        // Add panels to the frame
        add(textAreaPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Populate student info
        displayStudentInfo(this.names, this.rollNos);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void refresh() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
			
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "select name, id from student inner join membership on student.id = membership.s_id where membership.c_id = (select id from club_dept where name = '"+ clubName + "');";
			ResultSet rs = stmt.executeQuery(query);
			
			int rowcount = 0;
			if(rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}
			
			
			names = new ArrayList<String>();
			rollNos = new ArrayList<String>();
//    			
    		int i = 0;
    		while(rs.next()) {
    			names.add(rs.getString("name"));
    			rollNos.add(rs.getString("id"));
    			
    			i++;
    		}
    		con.close();
    		
				
    	} catch(Exception e1) {System.out.println(e1);}
    	displayStudentInfo(this.names, this.rollNos);
    }

    private void displayStudentInfo(ArrayList<String> names, ArrayList<String> rollNos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noOfMembers; i++) {
            sb.append(names.get(i)).append("\t").append(rollNos.get(i)).append("\n");
        }
        studentInfoTextArea.setText(sb.toString());
    }

    private void clearInputTextField() {
        inputTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] names = { "John", "Alice", "Bob", "Emma", "Michael" };
                String[] rollNos = { "101", "102", "103", "104", "105" };
                new StudentListGUI(names, rollNos, "Dance Club");
            }
        });
    }
}

