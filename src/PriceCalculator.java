//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class PriceCalculator extends JFrame {
//    private JLabel imagesLabel, priceLabel, totalLabel, totalImages, pricePerImage, totalPrice;
//
//    public PriceCalculator() {
//        setTitle("Image Price Calculator");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new GridLayout(3, 2));
//
//        JLabel headingLabel = new JLabel("Price Calculator");
//        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        add(headingLabel);
//
//        imagesLabel = new JLabel("Number of Images Allotted:");
//        totalImages = new JLabel("10");
//        add(imagesLabel);
//        add(totalImages);
//
//        priceLabel = new JLabel("Price per Picture:");
//        pricePerImage = new JLabel("$5");
//        add(priceLabel);
//        add(pricePerImage);
//
//        totalLabel = new JLabel("Total Price:");
//        totalPrice = new JLabel("$50");
//        add(totalLabel);
//        add(totalPrice);
//
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PriceCalculator extends JFrame {
    public PriceCalculator(String id) {
        setTitle("Price Calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel, BorderLayout.CENTER);

        JLabel headingLabel = new JLabel("Price Calculator");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        GridBagConstraints gbcHeading = new GridBagConstraints();
        gbcHeading.gridx = 0;
        gbcHeading.gridy = 0;
        gbcHeading.gridwidth = 2;
        gbcHeading.insets = new Insets(10, 10, 20, 10); // Adding margin
        gbcHeading.anchor = GridBagConstraints.CENTER;
        panel.add(headingLabel, gbcHeading);

        JLabel imagesLabel = new JLabel("Number of Images:");
        GridBagConstraints gbcImagesLabel = new GridBagConstraints();
        gbcImagesLabel.gridx = 0;
        gbcImagesLabel.gridy = 1;
        gbcImagesLabel.insets = new Insets(0, 10, 5, 5);
        gbcImagesLabel.anchor = GridBagConstraints.EAST;
        panel.add(imagesLabel, gbcImagesLabel);

//        JTextField imagesField = new JTextField(10);
//        GridBagConstraints gbcImagesField = new GridBagConstraints();
//        gbcImagesField.gridx = 1;
//        gbcImagesField.gridy = 1;
//        gbcImagesField.insets = new Insets(0, 0, 5, 10);
//        gbcImagesField.anchor = GridBagConstraints.WEST;
//        panel.add(imagesField, gbcImagesField);

        JLabel priceLabel = new JLabel("Price per Picture: ₹15.00");
        GridBagConstraints gbcPriceLabel = new GridBagConstraints();
        gbcPriceLabel.gridx = 0;
        gbcPriceLabel.gridy = 2;
        gbcPriceLabel.insets = new Insets(0, 10, 5, 5);
        gbcPriceLabel.anchor = GridBagConstraints.EAST;
        panel.add(priceLabel, gbcPriceLabel);

//        JTextField priceField = new JTextField(10);
//        GridBagConstraints gbcPriceField = new GridBagConstraints();
//        gbcPriceField.gridx = 1;
//        gbcPriceField.gridy = 2;
//        gbcPriceField.insets = new Insets(0, 0, 5, 10);
//        gbcPriceField.anchor = GridBagConstraints.WEST;
//        panel.add(priceField, gbcPriceField);

        JLabel totalLabel = new JLabel("Total:");
        GridBagConstraints gbcTotalLabel = new GridBagConstraints();
        gbcTotalLabel.gridx = 0;
        gbcTotalLabel.gridy = 3;
        gbcTotalLabel.insets = new Insets(0, 10, 10, 5);
        gbcTotalLabel.anchor = GridBagConstraints.EAST;
        panel.add(totalLabel, gbcTotalLabel);

        JLabel resultLabel = new JLabel("-"); // Default value
        resultLabel.setFont(new Font(resultLabel.getFont().getName(), Font.BOLD, 16)); // Bigger font
        GridBagConstraints gbcResultLabel = new GridBagConstraints();
        gbcResultLabel.gridx = 1;
        gbcResultLabel.gridy = 3;
        gbcResultLabel.insets = new Insets(0, 0, 10, 10);
        gbcResultLabel.anchor = GridBagConstraints.WEST;
        panel.add(resultLabel, gbcResultLabel);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int numImages = 0;
            	try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopy","root","pword");
        			
        			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			String query = "select count(photo_id) as num from tagged_in where tagged_in.s_id = '"+id+"';";
        			ResultSet rs = stmt.executeQuery(query);
        			
        			rs.next();
        			numImages = rs.getInt("num");
        			
            		con.close();
//            		new ImageGallery(arr);
        				
            	} catch(Exception e1) {System.out.println(e1);}
                try {
//                    int numImages = Integer.parseInt(imagesField.getText());
//                    double pricePerPicture = Double.parseDouble(priceField.getText());
                	double total = numImages * 15;
                    resultLabel.setText(String.format("%.2f", total)); // Format to two decimal places
                    imagesLabel.setText(String.format("Number of Images: %d", numImages));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PriceCalculator.this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        GridBagConstraints gbcCalculateButton = new GridBagConstraints();
        gbcCalculateButton.gridx = 0;
        gbcCalculateButton.gridy = 4;
        gbcCalculateButton.gridwidth = 2;
        gbcCalculateButton.insets = new Insets(10, 10, 10, 10);
        gbcCalculateButton.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, gbcCalculateButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
}
