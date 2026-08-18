import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciApp extends JFrame {
    private JTextField txtN;
    private JButton btnFind;
    private JLabel lblResult;
    private JProgressBar progressBar;

    public FibonacciApp() {
        // Thiết lập tiêu đề và kích thước cửa sổ
        setTitle("Tìm số Fibonacci thứ N");
        setSize(460, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel nhập liệu (Top)
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelInput.add(new JLabel("Nhập N:"));
        txtN = new JTextField(10);
        btnFind = new JButton("Tìm");
        panelInput.add(txtN);
        panelInput.add(btnFind);
        add(panelInput, BorderLayout.NORTH);

        // 2. Panel hiển thị thanh tiến trình & kết quả (Center)
        JPanel panelCenter = new JPanel(new GridLayout(2, 1, 8, 8));

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Chờ thực hiện...");

        lblResult = new JLabel("Kết quả sẽ hiển thị tại đây", SwingConstants.CENTER);
        lblResult.setFont(new Font("Segoe UI", Font.BOLD, 13));

        panelCenter.add(progressBar);
        panelCenter.add(lblResult);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(panelCenter, BorderLayout.CENTER);

        // Gán sự kiện khi click nút "Tìm" hoặc nhấn Enter trong ô nhập
        ActionListener actionListener = e -> findFibonacci();
        btnFind.addActionListener(actionListener);
        txtN.addActionListener(actionListener);
    }

    // Hàm đệ quy có nhớ (Memoization) kết hợp BigInteger chống tràn số
    private BigInteger fibonacci(int n, Map<Integer, BigInteger> memo) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        BigInteger value = fibonacci(n - 1, memo).add(fibonacci(n - 2, memo));
        memo.put(n, value);
        return value;
    }

    // Xử lý logic nền với SwingWorker
    private void findFibonacci() {
        int n;
        try {
            n = Integer.parseInt(txtN.getText().trim());
            if (n < 0) {
                JOptionPane.showMessageDialog(this, "N phải >= 0", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Khóa nút và bật hiệu ứng thanh tiến trình chạy vô định (Indeterminate)
        btnFind.setEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setString("Đang tính toán...");
        lblResult.setText("Đang tính Fibonacci...");

        // Luồng nền SwingWorker
        SwingWorker<BigInteger, Void> worker = new SwingWorker<>() {
            @Override
            protected BigInteger doInBackground() {
                Map<Integer, BigInteger> memo = new HashMap<>();
                return fibonacci(n, memo);
            }

            @Override
            protected void done() {
                try {
                    BigInteger result = get();
                    // Hiển thị kết quả rút gọn nếu chuỗi số quá dài
                    String strResult = result.toString();
                    if (strResult.length() > 30) {
                        lblResult.setText("F(" + n + ") có " + strResult.length() + " chữ số (xem log/tooltip)");
                        lblResult.setToolTipText("F(" + n + ") = " + strResult);
                    } else {
                        lblResult.setText("F(" + n + ") = " + strResult);
                    }
                } catch (Exception ex) {
                    lblResult.setText("Có lỗi xảy ra khi tính toán!");
                } finally {
                    // Trả lại trạng thái UI bình thường
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(100);
                    progressBar.setString("Hoàn thành");
                    btnFind.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FibonacciApp().setVisible(true);
        });
    }
}
