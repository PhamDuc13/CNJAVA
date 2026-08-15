import javax.swing.*;
import java.awt.*;

public class Bai5_Fibonacci extends JFrame {
    private JTextField txtN = new JTextField(10);
    private JTextArea txtArea = new JTextArea(8, 25);

    public Bai5_Fibonacci() {
        setTitle("Dãy Fibonacci");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nhập n:"));
        topPanel.add(txtN);
        JButton btnGenerate = new JButton("Hiển thị");
        topPanel.add(btnGenerate);
        add(topPanel, BorderLayout.NORTH);

        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtArea);
        add(scrollPane, BorderLayout.CENTER);

        btnGenerate.addActionListener(e -> generateFibonacci());
    }

    private void generateFibonacci() {
        try {
            int n = Integer.parseInt(txtN.getText().trim());
            if (n <= 0) {
                JOptionPane.showMessageDialog(this, "n phải là số nguyên dương!");
                return;
            }

            StringBuilder sb = new StringBuilder();
            long f0 = 0, f1 = 1;
            for (int i = 1; i <= n; i++) {
                if (i == 1) {
                    sb.append(f0);
                } else if (i == 2) {
                    sb.append(", ").append(f1);
                } else {
                    long fn = f0 + f1;
                    sb.append(", ").append(fn);
                    f0 = f1;
                    f1 = fn;
                }
                if (i % 10 == 0) sb.append("\n");
            }
            txtArea.setText(sb.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai5_Fibonacci().setVisible(true));
    }
}
