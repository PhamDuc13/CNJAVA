import javax.swing.*;
import java.awt.*;

public class Bai4_TamGiac extends JFrame {
    private JTextField txtA = new JTextField(10);
    private JTextField txtB = new JTextField(10);
    private JTextField txtC = new JTextField(10);
    private JLabel lblResult = new JLabel("Kết quả: ");

    public Bai4_TamGiac() {
        setTitle("Kiểm tra tam giác");
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel(" Cạnh a:"));
        add(txtA);
        add(new JLabel(" Cạnh b:"));
        add(txtB);
        add(new JLabel(" Cạnh c:"));
        add(txtC);

        JButton btnCheck = new JButton("Kiểm tra");
        btnCheck.addActionListener(e -> checkTriangle());
        add(btnCheck);
        add(lblResult);
    }

    private void checkTriangle() {
        try {
            double a = Double.parseDouble(txtA.getText().trim());
            double b = Double.parseDouble(txtB.getText().trim());
            double c = Double.parseDouble(txtC.getText().trim());

            if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
                lblResult.setText("Kết quả: Không phải tam giác");
                return;
            }

            boolean isVuong = (Math.abs(a * a + b * b - c * c) < 1e-5) ||
                              (Math.abs(a * a + c * c - b * b) < 1e-5) ||
                              (Math.abs(b * b + c * c - a * a) < 1e-5);
            boolean isDeu = (a == b && b == c);
            boolean isCan = (a == b || b == c || a == c);

            if (isDeu) {
                lblResult.setText("Kết quả: Tam giác đều");
            } else if (isVuong && isCan) {
                lblResult.setText("Kết quả: Tam giác vuông cân");
            } else if (isVuong) {
                lblResult.setText("Kết quả: Tam giác vuông");
            } else if (isCan) {
                lblResult.setText("Kết quả: Tam giác cân");
            } else {
                lblResult.setText("Kết quả: Tam giác thường");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập độ dài hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai4_TamGiac().setVisible(true));
    }
}
