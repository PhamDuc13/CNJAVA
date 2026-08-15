import javax.swing.*;
import java.awt.*;

public class Bai7_Calculator extends JFrame {
    private JTextField txtNum1 = new JTextField(8);
    private JTextField txtNum2 = new JTextField(8);
    private JTextField txtResult = new JTextField(8);
    private JTextArea txtHistory = new JTextArea(6, 25);

    public Bai7_Calculator() {
        setTitle("Máy tính mini");
        setSize(420, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel(" Số thứ nhất:"));
        inputPanel.add(txtNum1);
        inputPanel.add(new JLabel(" Số thứ hai:"));
        inputPanel.add(txtNum2);
        inputPanel.add(new JLabel(" Kết quả:"));
        txtResult.setEditable(false);
        inputPanel.add(txtResult);
        add(inputPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");
        JButton btnMul = new JButton("*");
        JButton btnDiv = new JButton("/");
        JButton btnClear = new JButton("Clear");

        btnPanel.add(btnAdd);
        btnPanel.add(btnSub);
        btnPanel.add(btnMul);
        btnPanel.add(btnDiv);
        btnPanel.add(btnClear);
        add(btnPanel, BorderLayout.CENTER);

        txtHistory.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistory);
        scroll.setBorder(BorderFactory.createTitledBorder("Lịch sử tính toán"));
        add(scroll, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> calculate('+'));
        btnSub.addActionListener(e -> calculate('-'));
        btnMul.addActionListener(e -> calculate('*'));
        btnDiv.addActionListener(e -> calculate('/'));
        btnClear.addActionListener(e -> {
            txtNum1.setText("");
            txtNum2.setText("");
            txtResult.setText("");
        });
    }

    private void calculate(char op) {
        try {
            double n1 = Double.parseDouble(txtNum1.getText().trim());
            double n2 = Double.parseDouble(txtNum2.getText().trim());
            double res = 0;

            if (op == '/' && n2 == 0) {
                JOptionPane.showMessageDialog(this, "Không thể chia cho 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (op) {
                case '+' -> res = n1 + n2;
                case '-' -> res = n1 - n2;
                case '*' -> res = n1 * n2;
                case '/' -> res = n1 / n2;
            }

            txtResult.setText(String.valueOf(res));
            txtHistory.append(String.format("%.2f %c %.2f = %.2f\n", n1, op, n2, res));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập hai số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai7_Calculator().setVisible(true));
    }
}
