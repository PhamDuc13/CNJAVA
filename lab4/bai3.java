import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimeSumApp extends JFrame {
    private JTextField txtN;
    private JButton btnCalculate;
    private JLabel lblResult;
    private JProgressBar progressBar;

    public PrimeSumApp() {
        // Thiết lập tiêu đề và kích thước cửa sổ
        setTitle("Tính tổng số nguyên tố < N");
        setSize(420, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel nhập liệu (Top)
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelInput.add(new JLabel("Nhập N:"));
        txtN = new JTextField(12);
        btnCalculate = new JButton("Tính");
        panelInput.add(txtN);
        panelInput.add(btnCalculate);
        add(panelInput, BorderLayout.NORTH);

        // 2. Panel hiển thị tiến trình & kết quả (Center)
        JPanel panelCenter = new JPanel(new GridLayout(2, 1, 5, 5));
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Hiển thị số % trên thanh tiến trình
        
        lblResult = new JLabel("Kết quả sẽ hiển thị tại đây", SwingConstants.CENTER);
        lblResult.setFont(new Font("Segoe UI", Font.BOLD, 13));

        panelCenter.add(progressBar);
        panelCenter.add(lblResult);
        
        // Thêm khoảng đệm viền cho phần giữa
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(panelCenter, BorderLayout.CENTER);

        // Gán sự kiện cho nút Tính
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePrimeSum();
            }
        });
    }

    // Hàm kiểm tra số nguyên tố
    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Xử lý logic đa luồng với SwingWorker
    private void calculatePrimeSum() {
        int n;
        try {
            n = Integer.parseInt(txtN.getText().trim());
            if (n <= 2) {
                JOptionPane.showMessageDialog(this, "N phải lớn hơn 2", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vô hiệu hóa nút và thiết lập trạng thái ban đầu
        btnCalculate.setEnabled(false);
        progressBar.setValue(0);
        lblResult.setText("Đang tính toán...");

        // Khởi tạo SwingWorker: <Kiểu kết quả trả về, Kiểu dữ liệu trung gian>
        SwingWorker<Long, Void> worker = new SwingWorker<Long, Void>() {
            @Override
            protected Long doInBackground() {
                long sum = 0;
                for (int i = 2; i < n; i++) {
                    if (isPrime(i)) {
                        sum += i;
                    }
                    // Cập nhật tiến độ hoàn thành từ 0 đến 100%
                    int progress = (int) (((double) i / n) * 100);
                    setProgress(progress);
                }
                return sum;
            }

            @Override
            protected void done() {
                try {
                    long result = get();
                    lblResult.setText("Tổng các SNT nhỏ hơn " + n + " = " + result);
                } catch (Exception ex) {
                    lblResult.setText("Có lỗi xảy ra trong quá trình tính toán.");
                } finally {
                    // Kích hoạt lại nút bấm và đưa thanh tiến trình về 100%
                    btnCalculate.setEnabled(true);
                    progressBar.setValue(100);
                }
            }
        };

        // Lắng nghe sự thay đổi của thuộc tính "progress" để cập nhật JProgressBar
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        // Bắt đầu thực thi trên luồng nền (background thread)
        worker.execute();
    }

    public static void main(String[] args) {
        // Chạy ứng dụng trên Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new PrimeSumApp().setVisible(true);
        });
    }
}
