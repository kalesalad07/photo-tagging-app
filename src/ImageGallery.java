import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageGallery extends JFrame {
    private JLabel imageLabel;
    private int currentIndex = 0;
    private String[] filepaths;

    public ImageGallery(String[] filepaths) {
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

        add(controlPanel, BorderLayout.SOUTH);
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] filepaths = {
                        "/Users/deepan_roy/Desktop/eval/bball_img.jpeg",
                        "/Users/deepan_roy/Desktop/eval/beach.jpeg",
                        "/Users/deepan_roy/Desktop/eval/crowd_stock.jpeg",
                        "/Users/deepan_roy/Desktop/eval/crowd_vid_ss.jpeg",
                        "/Users/deepan_roy/Desktop/eval/diwali.jpeg",
                        "/Users/deepan_roy/Desktop/eval/gate.jpeg",
                        "/Users/deepan_roy/Desktop/eval/mandir_1.jpeg",
                        "/Users/deepan_roy/Desktop/eval/mandir.jpeg"
                };
                new ImageGallery(filepaths);
            }
        });
    }
}

