package university.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class StudentDetails extends JFrame implements ActionListener {

    Choice choice;
    JTable table;
    JButton search, print, update, add, close;

    Color bgColor = new Color(245, 247, 255);
    Color cardColor = Color.WHITE;
    Color primary = new Color(88, 101, 242);
    Color danger = new Color(239, 68, 68);
    Color textColor = new Color(55, 65, 81);

    public StudentDetails() {

        setTitle("Student Details");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(bgColor);

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primary);
        header.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Student Records");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("View, search and manage student information");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 220, 255));

        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);
        titleBox.add(title);
        titleBox.add(Box.createVerticalStrut(4));
        titleBox.add(subtitle);

        header.add(titleBox, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        /* ================= CARD ================= */
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(cardColor);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel cardWrapper = new JPanel(new BorderLayout());
        cardWrapper.setBackground(bgColor);
        cardWrapper.setBorder(new EmptyBorder(20, 40, 40, 40));
        cardWrapper.add(card);

        add(cardWrapper, BorderLayout.CENTER);

        /* ================= CONTROLS ================= */
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Roll Number:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchLabel.setForeground(textColor);

        choice = new Choice();
        choice.setPreferredSize(new Dimension(160, 26));

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select rollno from student");
            while (rs.next()) {
                choice.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        search = createPrimaryButton("Search");
        print = createPrimaryButton("Print");
        add = createPrimaryButton("Add Student");
        update = createPrimaryButton("Update");
        close = createDangerButton("Close");

        controlPanel.add(searchLabel);
        controlPanel.add(choice);
        controlPanel.add(search);
        controlPanel.add(print);
        controlPanel.add(add);
        controlPanel.add(update);
        controlPanel.add(close);

        card.add(controlPanel, BorderLayout.NORTH);

        /* ================= TABLE ================= */
        table = new JTable();
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(new Color(230, 230, 250));
        table.setSelectionBackground(new Color(224, 231, 255));
        table.setSelectionForeground(textColor);

        JTableHeader th = table.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));
        th.setBackground(primary);
        th.setForeground(Color.WHITE);
        th.setReorderingAllowed(false);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new LineBorder(new Color(230, 230, 250)));
        scroll.getViewport().setBackground(Color.WHITE);

        card.add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    /* ================= BUTTONS ================= */
    private JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(primary);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    private JButton createDangerButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(danger);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    /* ================= ACTIONS ================= */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == search) {
            String q = "select * from student where rollno = '" + choice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == add) {
            new AddStudent();

        } else if (e.getSource() == update) {
            JOptionPane.showMessageDialog(this,
                    "Connect Update Student form here");

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
