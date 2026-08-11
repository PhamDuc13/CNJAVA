import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageViewerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Thay "path/to/your/image.jpg" bằng đường dẫn thực tế của tệp ảnh
            ImageIcon imageIcon = new ImageIcon("image.jpg");
            JLabel imageLabel = new JLabel(imageIcon);

            frame.add(imageLabel);

            // Co giãn kích thước JFrame vừa vặn với dung lượng ảnh/component bên trong
            frame.pack();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
