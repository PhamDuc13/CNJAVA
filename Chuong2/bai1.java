import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MyFirstSwingApp {
    public static void main(String[] args) {
        // Chạy giao diện trên Event Dispatch Thread (đảm bảo an toàn luồng trong Swing)
        SwingUtilities.invokeLater(() -> {
            // 1. Tạo JFrame với tiêu đề "My First Swing App"
            JFrame frame = new JFrame("My First Swing App");

            // 2. Thiết lập kích thước 400x300
            frame.setSize(400, 300);

            // Cấu hình thoát chương trình khi nhấn nút đóng (X)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Căn giữa màn hình
            frame.setLocationRelativeTo(null);

            // 3. Tạo JLabel hiển thị "Hello World" và căn giữa nội dung label
            JLabel label = new JLabel("Hello World", SwingConstants.CENTER);

            // Thêm label vào JFrame
            frame.add(label);

            // Hiển thị cửa sổ
            frame.setVisible(true);
        });
    }
}
