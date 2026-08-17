import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DownloadProgressDemo extends JFrame {
    private JButton btnDownload;
    private JProgressBar progressBar;
    private JLabel lblStatus;

    public DownloadProgressDemo() {
        setTitle("Mô phỏng tải dữ liệu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Khởi tạo các thành phần giao diện
        btnDownload = new JButton("Tải dữ liệu");
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // Hiển thị phần trăm số trên thanh tiến trình

        lblStatus = new JLabel("Trạng thái: Sẵn sàng", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 13));

        // Bố cục giao diện
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        centerPanel.add(progressBar);
        centerPanel.add(lblStatus);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnDownload);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện click nút tải dữ liệu
        btnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDownloadTask();
            }
        });
    }

    private void startDownloadTask() {
        // Vô hiệu hóa nút trong quá trình tải để tránh bấm nhiều lần
        btnDownload.setEnabled(false);
        progressBar.setValue(0);

        // Khởi tạo SwingWorker: <Kiểu kết quả trả về, Kiểu dữ liệu cập nhật trung gian>
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Mô phỏng tải dữ liệu trong 10 giây (100 bước x 100ms = 10,000ms)
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(100); 
                    publish(i); // Đẩy giá trị tiến độ sang luồng UI
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                // Nhận giá trị mới nhất và cập nhật UI trên Event Dispatch Thread (EDT)
                int latestProgress = chunks.get(chunks.size() - 1);
                progressBar.setValue(latestProgress);
                lblStatus.setText("Đang tải dữ liệu: " + latestProgress + "%");
            }

            @Override
            protected void done() {
                // Xử lý sau khi luồng nền kết thúc
                lblStatus.setText("Trạng thái: Tải dữ liệu hoàn tất!");
                btnDownload.setEnabled(true);
            }
        };

        // Kích hoạt tiến trình nền
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DownloadProgressDemo().setVisible(true);
        });
    }
}
