import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ExitButtonApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exit Window");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Tạo nút Exit
            JButton exitButton = new JButton("Exit");

            // Bắt sự kiện click để thoát ứng dụng
            exitButton.addActionListener(e -> System.exit(0));

            // Thêm nút vào giữa cửa sổ
            frame.add(exitButton);

            frame.setVisible(true);
        });
    }
}
