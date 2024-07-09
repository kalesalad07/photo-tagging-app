import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PORClubScreen extends JFrame {
    private JPanel buttonPanel;

    public PORClubScreen(String[] pictureNames) {
        setTitle("Pictures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading Label
        JLabel headingLabel = new JLabel("Pictures");
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
                    String[] names = { "John", "Alice", "Bob", "Emma", "Michael" };
                    String[] rollNos = { "101", "102", "103", "104", "105" };
//                    new StudentListGUI(names, rollNos);
                }
            });
            buttonPanel.add(button);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] pictureNames = {"Picture 1", "Picture 2", "Picture 3", "Picture 4", "Picture 5"};
                new PORClubScreen(pictureNames);
            }
        });
    }
}




//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class PORClubScreen extends JFrame {
//    public PORClubScreen(String[] images) {
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
//                new PORClubScreen(images);
//            }
//        });
//    }
//}
