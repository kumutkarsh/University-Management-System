package university.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class main_class extends JFrame implements ActionListener {

    Color bgColor = new Color(243, 245, 252);
    Color cardColor = Color.WHITE;
    Color textColor = new Color(55, 65, 81);

    public main_class() {

        setTitle("University Management System");
        setSize(1540, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /* ================= HEADER ================= */
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(
                        0, 0, new Color(88, 101, 242),
                        getWidth(), getHeight(), new Color(139, 92, 246)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setBorder(new EmptyBorder(30, 60, 30, 60));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("University Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Smart · Secure · Efficient");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setForeground(new Color(220, 220, 255));

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);
        add(header, BorderLayout.NORTH);

        /* ================= DASHBOARD ================= */
        JPanel dashboard = new JPanel(new GridLayout(3, 6, 45, 45));
        dashboard.setBackground(bgColor);
        dashboard.setBorder(new EmptyBorder(50, 80, 70, 80));

        dashboard.add(createCard("New Student", "New Student Information"));
        dashboard.add(createCard("New Faculty", "New Faculty Information"));
        dashboard.add(createCard("Student Details", "View Student Details"));
        dashboard.add(createCard("Faculty Details", "View Faculty Details"));
        dashboard.add(createCard("Student Leave", "Student Leave"));
        dashboard.add(createCard("Faculty Leave", "Faculty Leave"));

        dashboard.add(createCard("Student Leave Details", "Student Leave Details"));
        dashboard.add(createCard("Faculty Leave Details", "Faculty Leave Details"));
        dashboard.add(createCard("Examination Result", "Examination Result"));
        dashboard.add(createCard("Enter Marks", "Enter Marks"));
        dashboard.add(createCard("Update Student", "Update Student Details"));
        dashboard.add(createCard("Update Faculty", "Update Faculty Details"));

        dashboard.add(createCard("Fee Structure", "Fee Structure"));
        dashboard.add(createCard("Student Fee Form", "Student Fee Form"));
        dashboard.add(createCard("Calculator", "Calculator"));
        dashboard.add(createCard("Notepad", "Notepad"));
        dashboard.add(createCard("About", "About"));
        dashboard.add(createCard("Exit", "Exit"));

        add(dashboard, BorderLayout.CENTER);

        setVisible(true);
    }

    /* ================= CARD ================= */
    private JPanel createCard(String text, String command) {

        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(textColor);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(new EmptyBorder(20, 20, 20, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setActionCommand(command);
        btn.addActionListener(this);

        JPanel card = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(6, 6, getWidth() - 12, getHeight() - 12, 20, 20);

                g2.setColor(cardColor);
                g2.fillRoundRect(0, 0, getWidth() - 12, getHeight() - 12, 20, 20);
            }
        };

        card.setOpaque(false);
        card.add(btn, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                btn.doClick();
            }
        });

        return card;
    }

    /* ================= ACTION HANDLER ================= */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "New Student Information": new AddStudent(); break;
            case "New Faculty Information": new AddFaculty(); break;

            case "View Student Details": new StudentDetails(); break;
            case "View Faculty Details": new TeacherDetails(); break;

            case "Student Leave": new StudentLeave(); break;
            case "Faculty Leave": new TeacherLeave(); break;

            case "Student Leave Details": new StudentLeaveDetails(); break;
            case "Faculty Leave Details": new TeacherLeaveDetails(); break;

            case "Update Student Details": new UpdateStudent(); break;
            case "Update Faculty Details": new UpdateTeacher(); break;

            case "Enter Marks": new EnterMarks(); break;
            case "Examination Result": new ExaminationDetails(); break;

            case "Fee Structure": new FeeStructure(); break;

            case "Student Fee Form":
                JOptionPane.showMessageDialog(this,
                        "Student Fee Form module coming soon!");
                break;

            case "Calculator":
                try { Runtime.getRuntime().exec("calc.exe"); } catch (Exception ignored) {}
                break;

            case "Notepad":
                try { Runtime.getRuntime().exec("notepad.exe"); } catch (Exception ignored) {}
                break;

            case "About":
                JOptionPane.showMessageDialog(this,
                        "University Management System\nDeveloped by Utkarsh Kumar",
                        "About", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Exit":
                int confirm = JOptionPane.showConfirmDialog(
                        this, "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        new main_class();
    }
}
