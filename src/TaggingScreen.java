import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaggingScreen extends JFrame {
    private JLabel imageLabel;
    private JTextArea inputList;
    private int currentIndex = 0;
    private String[] filepaths;
    private List<String> userInputList = new ArrayList<>();

    public TaggingScreen(String[] filepaths) {
        this.filepaths = filepaths;

        setTitle("Image Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        controlPanel.add(prevButton);
        controlPanel.add(nextButton);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        updateImage();

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateImage();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < filepaths.length - 1) {
                    currentIndex++;
                    updateImage();
                }
            }
        });

        inputList = new JTextArea(10, 20);
        inputList.setEditable(false);
        JScrollPane inputScrollPane = new JScrollPane(inputList);

        JTextField inputField = new JTextField(20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim();
                if (!userInput.isEmpty()) {
                    userInputList.add(userInput);
                    updateInputList();
                    inputField.setText("");
                }
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Input List"), BorderLayout.NORTH);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);
        inputPanel.add(inputField, BorderLayout.WEST);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        mainPanel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.EAST);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateImage() {
        try {
            Image image = ImageIO.read(new File(filepaths[currentIndex]));
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            // Resize image if it's too large to fit in the window
            int maxWidth = 600;
            int maxHeight = 400;
            if (width > maxWidth || height > maxHeight) {
                double scale = Math.min((double) maxWidth / width, (double) maxHeight / height);
                width *= scale;
                height *= scale;
            }

            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateInputList() {
        inputList.setText("");
        for (int i = 0; i < userInputList.size(); i++) {
            inputList.append((i + 1) + ". " + userInputList.get(i) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] filepaths = {
                        "C:\\Users\\ashis\\OneDrive\\Pictures\\133535345680047817.jpg",
                        "C:\\Users\\ashis\\OneDrive\\Pictures\\133542792140226558.jpg",// Add more file paths as needed
                };
                new TaggingScreen(filepaths);
            }
        });
    }
}

