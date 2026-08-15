import javax.swing.*;
import java.awt.*;

public class Bai6_Login extends JFrame {
    private JTextField txtUser = new JTextField(15);
    private JPasswordField txtPass = new JPasswordField(15);
    private JComboBox<String> cbRole = new JComboBox<>(new String[]{"Admin", "User", "Guest"});
    private JCheckBox chkRemember = new JCheckBox("Ghi nhớ đăng nhập");

    public Bai6_Login() {
        setTitle("Đăng nhập hệ thống");
        setSize(380, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 8, 8));

        add(new JLabel(" Tên đăng nhập:"));
        add(txtUser);
        add(new JLabel(" Mật khẩu:"));
        add(txtPass);
        add(new JLabel(" Quyền hạn:"));
        add(cbRole);
        add(new JLabel(""));
        add(chkRemember);

        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnReset = new JButton("Làm mới");
        add(btnLogin);
        add(btnReset);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());
            String role = (String) cbRole.getSelectedItem();

            // Giả lập kiểm tra tài khoản: admin / 123456
            if (user.equals("admin") && pass.equals("123456")) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công với vai trò: " + role);
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnReset.addActionListener(e -> {
            txtUser.setText("");
            txtPass.setText("");
            cbRole.setSelectedIndex(0);
            chkRemember.setSelected(false);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai6_Login().setVisible(true));
    }
}
