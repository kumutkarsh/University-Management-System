package university.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/* ================= Rounded Button ================= */
class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
    }
}

/* ================= Gradient Background ================= */
class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(58, 123, 213),
                0, getHeight(), new Color(0, 210, 255)
        );
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}

/* ================= Login Class ================= */
public class Login extends JFrame implements ActionListener {

    JTextField textFieldName;
    JPasswordField passwordField;
    JButton login, back;

    public Login() {

        setTitle("University Management System - Login");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GradientPanel background = new GradientPanel();
        background.setLayout(null);
        setContentPane(background);

        /* ---------- Right Side Image ---------- */
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/second.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(460, 80, 230, 230);
        background.add(img);

        /* ---------- Login Card ---------- */
        JPanel card = new JPanel();
        card.setBounds(90, 60, 340, 270);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        background.add(card);

        JLabel title = new JLabel("Welcome Back");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(20, 15, 200, 30);
        card.add(title);

        JLabel sub = new JLabel("Login to continue");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        sub.setForeground(Color.GRAY);
        sub.setBounds(20, 45, 200, 20);
        card.add(sub);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(20, 75, 100, 20);
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card.add(lblUser);

        textFieldName = new JTextField();
        textFieldName.setBounds(20, 95, 290, 30);
        textFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textFieldName.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        card.add(textFieldName);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(20, 130, 100, 20);
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card.add(lblPass);

        passwordField = new JPasswordField();
        passwordField.setBounds(20, 150, 290, 30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passwordField.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        card.add(passwordField);

        /* ---------- Buttons ---------- */
        login = new RoundedButton("LOGIN");
        login.setBounds(20, 200, 130, 35);
        login.setBackground(new Color(46, 204, 113));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Segoe UI", Font.BOLD, 14));
        login.addActionListener(this);
        card.add(login);

        back = new RoundedButton("BACK");
        back.setBounds(180, 200, 130, 35);
        back.setBackground(new Color(231, 76, 60));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.addActionListener(this);
        card.add(back);

        setVisible(true);
    }

    /* ================= Button Actions ================= */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == login) {

            String username = textFieldName.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter both username and password",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "select * from login where username='" + username + "' and password='" + password + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.statement.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new main_class();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid Username or Password",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Database Error",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
