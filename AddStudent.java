package university.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddStudent extends JFrame implements ActionListener {

    JTextField textName, textFather, textAddress, textPhone, textEmail, textM10, textM12, textAadhar;
    JLabel rollLabel;
    JDateChooser dobChooser;
    JComboBox<String> courseBox, branchBox;
    JButton submit, cancel;

    Color bgColor = new Color(245, 247, 255);
    Color cardColor = Color.WHITE;
    Color primary = new Color(88, 101, 242);
    Color textColor = new Color(55, 65, 81);

    Random ran = new Random();
    long rollNo = Math.abs((ran.nextLong() % 9000L) + 1000L);

    public AddStudent() {

        setTitle("Add New Student");
        setSize(950, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(bgColor);

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primary);
        header.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("New Student Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Enter complete student information");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 220, 255));

        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.add(title);
        titleBox.add(Box.createVerticalStrut(5));
        titleBox.add(subtitle);

        header.add(titleBox, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        /* ================= CARD ================= */
        JPanel card = new JPanel(new GridLayout(6, 4, 20, 18));
        card.setBackground(cardColor);
        card.setBorder(new EmptyBorder(30, 40, 30, 40));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(bgColor);
        wrapper.setBorder(new EmptyBorder(30, 60, 40, 60));
        wrapper.add(card);

        add(wrapper, BorderLayout.CENTER);

        /* ================= FORM FIELDS ================= */
        card.add(label("Name"));
        card.add(textName = field());

        card.add(label("Father Name"));
        card.add(textFather = field());

        card.add(label("Roll Number"));
        rollLabel = new JLabel(String.valueOf(rollNo));
        rollLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        card.add(rollLabel);

        card.add(label("Date of Birth"));
        dobChooser = new JDateChooser();
        dobChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        card.add(dobChooser);

        card.add(label("Address"));
        card.add(textAddress = field());

        card.add(label("Phone"));
        card.add(textPhone = field());

        card.add(label("Email"));
        card.add(textEmail = field());

        card.add(label("Class X (%)"));
        card.add(textM10 = field());

        card.add(label("Class XII (%)"));
        card.add(textM12 = field());

        card.add(label("Aadhar Number"));
        card.add(textAadhar = field());

        card.add(label("Course"));
        courseBox = new JComboBox<>(new String[]{
                "B.Tech", "BBA", "BCA", "BSc", "MSc", "MBA", "MCA", "M.Com", "MA", "BA"
        });
        card.add(courseBox);

        card.add(label("Branch"));
        branchBox = new JComboBox<>(new String[]{
                "Computer Science", "IT", "Electronics", "Mechanical", "Civil"
        });
        card.add(branchBox);

        /* ================= BUTTONS ================= */
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(cardColor);

        submit = button("Submit", primary);
        cancel = button("Cancel", Color.DARK_GRAY);

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        wrapper.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /* ================= UI HELPERS ================= */
    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l.setForeground(textColor);
        return l;
    }

    private JTextField field() {
        JTextField t = new JTextField();
        t.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return t;
    }

    private JButton button(String text, Color bg) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addActionListener(this);
        return b;
    }

    /* ================= ACTIONS ================= */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {

            String name = textName.getText();
            String father = textFather.getText();
            String roll = rollLabel.getText();
            String dob = ((JTextField) dobChooser.getDateEditor().getUiComponent()).getText();
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textEmail.getText();
            String x = textM10.getText();
            String xii = textM12.getText();
            String aadhar = textAadhar.getText();
            String course = (String) courseBox.getSelectedItem();
            String branch = (String) branchBox.getSelectedItem();

            try {
                String q = "insert into student values('" + name + "','" + father + "','" +
                        roll + "','" + dob + "','" + address + "','" + phone + "','" +
                        email + "','" + x + "','" + xii + "','" + aadhar + "','" +
                        course + "','" + branch + "')";

                Conn c = new Conn();
                c.statement.executeUpdate(q);

                JOptionPane.showMessageDialog(this, "Student Added Successfully");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
