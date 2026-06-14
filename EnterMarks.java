package university.management.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class EnterMarks extends JFrame implements ActionListener {

    Choice choicerollno;
    JComboBox<String> comboBox;
    JTextField sub1, sub2, sub3, sub4, sub5;
    JTextField mrk1, mrk2, mrk3, mrk4, mrk5;
    JButton submit, cancel;

    public EnterMarks() {

        // Main Frame Settings
        setTitle("Student Marks Entry");
        setSize(650, 520);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(20, 108, 148));
        header.setPreferredSize(new Dimension(100, 70));

        JLabel heading = new JLabel("Enter Student Marks");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("SansSerif", Font.BOLD, 26));
        header.add(heading);

        add(header, BorderLayout.NORTH);

        // Main Content Panel
        JPanel main = new JPanel();
        main.setBackground(new Color(236, 242, 255));
        main.setLayout(null);

        // Roll Number Label + Choice
        JLabel rollno = new JLabel("Select Roll Number:");
        rollno.setBounds(50, 20, 200, 25);
        rollno.setFont(new Font("SansSerif", Font.PLAIN, 14));
        main.add(rollno);

        choicerollno = new Choice();
        choicerollno.setBounds(220, 22, 200, 20);
        main.add(choicerollno);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from student");
            while (rs.next()) {
                choicerollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Semester selection
        JLabel sem = new JLabel("Select Semester:");
        sem.setBounds(50, 60, 200, 25);
        sem.setFont(new Font("SansSerif", Font.PLAIN, 14));
        main.add(sem);

        String[] semester = {
                "1st Semester", "2nd Semester", "3rd Semester", "4th Semester",
                "5th Semester", "6th Semester", "7th Semester", "8th Semester"
        };

        comboBox = new JComboBox<>(semester);
        comboBox.setBounds(220, 62, 200, 22);
        comboBox.setBackground(Color.WHITE);
        main.add(comboBox);

        // Subject & Marks Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 8, 10));
        formPanel.setBorder(new TitledBorder(
                new LineBorder(Color.GRAY, 1, true),
                "Enter Subjects & Marks",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)
        ));
        formPanel.setBounds(50, 110, 520, 250);
        formPanel.setBackground(Color.WHITE);

        sub1 = new JTextField();  mrk1 = new JTextField();
        sub2 = new JTextField();  mrk2 = new JTextField();
        sub3 = new JTextField();  mrk3 = new JTextField();
        sub4 = new JTextField();  mrk4 = new JTextField();
        sub5 = new JTextField();  mrk5 = new JTextField();

        formPanel.add(new JLabel("Subject 1:"));  formPanel.add(sub1);
        formPanel.add(new JLabel("Marks 1:"));    formPanel.add(mrk1);

        formPanel.add(new JLabel("Subject 2:"));  formPanel.add(sub2);
        formPanel.add(new JLabel("Marks 2:"));    formPanel.add(mrk2);

        formPanel.add(new JLabel("Subject 3:"));  formPanel.add(sub3);
        formPanel.add(new JLabel("Marks 3:"));    formPanel.add(mrk3);

        formPanel.add(new JLabel("Subject 4:"));  formPanel.add(sub4);
        formPanel.add(new JLabel("Marks 4:"));    formPanel.add(mrk4);

        formPanel.add(new JLabel("Subject 5:"));  formPanel.add(sub5);
        formPanel.add(new JLabel("Marks 5:"));    formPanel.add(mrk5);

        main.add(formPanel);

        // Buttons
        submit = new JButton("Submit");
        submit.setBounds(180, 380, 120, 35);
        submit.setBackground(new Color(34, 139, 34));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("SansSerif", Font.BOLD, 14));
        submit.setFocusPainted(false);
        submit.addActionListener(this);
        main.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(340, 380, 120, 35);
        cancel.setBackground(new Color(178, 34, 34));
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancel.setFocusPainted(false);
        cancel.addActionListener(this);
        main.add(cancel);

        add(main, BorderLayout.CENTER);

        // 🔹 NEW: When rollno or semester changes, auto-load subjects from DB
        choicerollno.addItemListener(e -> loadSubjectsFromDatabase());
        comboBox.addActionListener(e -> loadSubjectsFromDatabase());

        // Optional: Load subjects for initially selected roll & sem
        loadSubjectsFromDatabase();

        setVisible(true);
    }

    // 🔹 NEW: Load subject names from DB into text fields
    private void loadSubjectsFromDatabase() {
        String roll = choicerollno.getSelectedItem();
        String sem = (String) comboBox.getSelectedItem();

        if (roll == null || sem == null) return;

        try {
            Conn c = new Conn();
            // Assuming subject table structure: rollno, semester, sub1, sub2, sub3, sub4, sub5
            String query = "select * from subject where rollno = '" + roll + "' and semester = '" + sem + "'";
            ResultSet rs = c.statement.executeQuery(query);

            if (rs.next()) {
                // Using column index: 3–7 for subject names
                sub1.setText(rs.getString(3));
                sub2.setText(rs.getString(4));
                sub3.setText(rs.getString(5));
                sub4.setText(rs.getString(6));
                sub5.setText(rs.getString(7));
            } else {
                // No record found → clear subject fields
                sub1.setText("");
                sub2.setText("");
                sub3.setText("");
                sub4.setText("");
                sub5.setText("");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            // You can also show a dialog if you want:
            // JOptionPane.showMessageDialog(this, "Error loading subjects: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            // 1. Get and trim all values
            String s1 = sub1.getText().trim();
            String s2 = sub2.getText().trim();
            String s3 = sub3.getText().trim();
            String s4 = sub4.getText().trim();
            String s5 = sub5.getText().trim();

            String m1 = mrk1.getText().trim();
            String m2 = mrk2.getText().trim();
            String m3 = mrk3.getText().trim();
            String m4 = mrk4.getText().trim();
            String m5 = mrk5.getText().trim();

            // 2. Check empty fields
            if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() ||
                    m1.isEmpty() || m2.isEmpty() || m3.isEmpty() || m4.isEmpty() || m5.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all subject names and marks before submitting.",
                        "Missing Data",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            try {
                // 3. Parse marks as integers
                int mk1 = Integer.parseInt(m1);
                int mk2 = Integer.parseInt(m2);
                int mk3 = Integer.parseInt(m3);
                int mk4 = Integer.parseInt(m4);
                int mk5 = Integer.parseInt(m5);

                // 4. Validate marks range (0–100)
                if (!isValidMark(mk1) || !isValidMark(mk2) || !isValidMark(mk3) ||
                        !isValidMark(mk4) || !isValidMark(mk5)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Marks must be between 0 and 100.",
                            "Invalid Marks",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // 5. Insert into DB only if all checks pass
                try {
                    Conn c = new Conn();

                    String Q1 = "insert into subject values('" +
                            choicerollno.getSelectedItem() + "','" +
                            comboBox.getSelectedItem() + "','" +
                            s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "')";

                    String Q2 = "insert into marks values('" +
                            choicerollno.getSelectedItem() + "','" +
                            comboBox.getSelectedItem() + "','" +
                            mk1 + "','" + mk2 + "','" + mk3 + "','" + mk4 + "','" + mk5 + "')";

                    c.statement.executeUpdate(Q1);
                    c.statement.executeUpdate(Q2);

                    JOptionPane.showMessageDialog(this, "Marks Inserted Successfully");
                    setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            this,
                            "Error while inserting data: " + ex.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter only numeric values for marks.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == cancel) {
            setVisible(false);
        }
    }

    // Helper method to validate marks range
    private boolean isValidMark(int m) {
        return m >= 0 && m <= 100;
    }

    public static void main(String[] args) {
        new EnterMarks();
    }
}
