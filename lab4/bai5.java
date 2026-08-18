import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class LargeFileLineCounterApp extends JFrame {
    private JButton btnChoose;
    private JButton btnCount;
    private JLabel lblFile;
    private JLabel lblResult;
    private JProgressBar progressBar;
    private File selectedFile;

    public LargeFileLineCounterApp() {
        // Cấu hình giao diện cửa sổ
        setTitle("Đọc file lớn và đếm số dòng");
        setSize(520, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel điều khiển trên cùng (Chọn file & Đếm dòng)
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnChoose = new JButton("Chọn file");
        btnCount = new JButton("Đếm dòng");
        btnCount.setEnabled(false); // Chỉ bật khi đã chọn file

        panelTop.add(btnChoose);
        panelTop.add(btnCount);
        add(panelTop, BorderLayout.NORTH);

        // 2. Panel nội dung ở giữa (Đường dẫn, Progress Bar, Kết quả)
        JPanel panelCenter = new JPanel(new GridLayout(3, 1, 8, 8));

        lblFile = new JLabel("Chưa chọn file nào", SwingConstants.CENTER);
        lblFile.setForeground(Color.DARK_GRAY);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        lblResult = new JLabel("Kết quả số dòng sẽ hiển thị tại đây", SwingConstants.CENTER);
        lblResult.setFont(new Font("Segoe UI", Font.BOLD, 13));

        panelCenter.add(lblFile);
        panelCenter.add(progressBar);
        panelCenter.add(lblResult);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(5, 20, 15, 20));
        add(panelCenter, BorderLayout.CENTER);

        // 3. Gán sự kiện cho các nút
        btnChoose.addActionListener(e -> chooseFile());
        btnCount.addActionListener(e -> countLines());
    }

    // Xử lý mở JFileChooser để chọn file
    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            lblFile.setText("File: " + selectedFile.getAbsolutePath());
            lblResult.setText("Sẵn sàng đếm dòng.");
            progressBar.setValue(0);
            btnCount.setEnabled(true);
        }
    }

    // Xử lý đếm dòng chạy nền với SwingWorker
    private void countLines() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn file trước", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Khóa các nút điều khiển để tránh kích hoạt trùng lặp
        btnChoose.setEnabled(false);
        btnCount.setEnabled(false);
        progressBar.setValue(0);
        lblResult.setText("Đang đọc file...");

        SwingWorker<Long, Void> worker = new SwingWorker<>() {
            @Override
            protected Long doInBackground() throws Exception {
                long totalBytes = Files.size(selectedFile.toPath());
                long readBytes = 0;
                long lines = 0;

                try (BufferedReader reader = Files.newBufferedReader(selectedFile.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines++;
                        // Ước tính kích thước byte đọc được (+ 1 byte tương ứng với ký tự ngắt dòng '\n')
                        readBytes += line.getBytes(StandardCharsets.UTF_8).length + 1;

                        int progress = (totalBytes == 0)
                                ? 100
                                : (int) Math.min(100, (readBytes * 100 / totalBytes));
                        
                        setProgress(progress);
                    }
                }
                return lines;
            }

            @Override
            protected void done() {
                try {
                    long lineCount = get();
                    lblResult.setText("Tổng số dòng: " + String.format("%,d", lineCount));
                } catch (Exception ex) {
                    lblResult.setText("Lỗi khi đọc file!");
                } finally {
                    progressBar.setValue(100);
                    btnChoose.setEnabled(true);
                    btnCount.setEnabled(true);
                }
            }
        };

        // Lắng nghe sự kiện thay đổi tiến độ để cập nhật ProgressBar trên EDT
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LargeFileLineCounterApp().setVisible(true);
        });
    }
}
