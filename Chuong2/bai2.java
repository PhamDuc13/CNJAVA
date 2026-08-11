import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class WelcomeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Welcome");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            // Hiển thị cửa sổ trước (nếu muốn thông báo đè lên cửa sổ)
            frame.setVisible(true);

            // Hiển thị hộp thoại thông báo
            JOptionPane.showMessageDialog(frame, "Welcome to Java Swing", "Welcome", JOptionPane.INFORMATION_MESSAGE);

            // Đóng ứng dụng ngay sau khi người dùng nhấn OK
            System.exit(0);
        });
    }
}
