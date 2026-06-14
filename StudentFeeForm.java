package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class StudentFeeForm extends JFrame implements ActionListener {
    Choice cRollNumber;
    JComboBox<String> courseBox, departmentBox, semesterBox;
    JLabel textName, textfName, totalAmount;
    JButton pay, update, back;

    StudentFeeForm() {
        getContentPane().setBackground(new Color(210, 252, 251));
        setLayout(null);

        JLabel rollNumber = new JLabel("Select Roll Number");
        rollNumber.setBounds(40, 60, 150, 20);
        rollNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(rollNumber);

        cRollNumber = new Choice();
        cRollNumber.setBounds(200, 60, 150, 20);
        add(cRollNumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                cRollNumber.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(40, 100, 150, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(name);

        textName = new JLabel();
        textName.setBounds(200, 100, 150, 20);
        add(textName);

        JLabel fname = new JLabel("Father Name");
        fname.setBounds(40, 140, 150, 20);
        fname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(fname);

        textfName = new JLabel();
        textfName.setBounds(200, 140, 150, 20);
        add(textfName);

        cRollNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Conn c = new Conn();
                    String query = "SELECT * FROM student WHERE rollno = '" + cRollNumber.getSelectedItem() + "'";
                    ResultSet resultSet = c.statement.executeQuery(query);
                    while (resultSet.next()) {
                        textName.setText(resultSet.getString("name"));
                        textfName.setText(resultSet.getString("fname"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel qualification = new JLabel("Course");
        qualification.setBounds(40, 180, 150, 20);
        add(qualification);

        String[] courses = {"BTech", "BBA", "BCA", "BSC", "MSC", "MBA", "MCA", "M.Com", "MA", "BA"};
        courseBox = new JComboBox<>(courses);
        courseBox.setBounds(200, 180, 150, 20);
        courseBox.setBackground(Color.WHITE);
        add(courseBox);

        JLabel department = new JLabel("Branch");
        department.setBounds(40, 220, 150, 20);
        add(department);

        String[] departments = {"Computer Science", "Electronic", "Mechanical", "Civil", "IT"};
        departmentBox = new JComboBox<>(departments);
        departmentBox.setBounds(200, 220, 150, 20);
        departmentBox.setBackground(Color.WHITE);
        add(departmentBox);

        JLabel textSemester = new JLabel("Semester");
        textSemester.setBounds(40, 260, 150, 20);
        add(textSemester);

        String[] semesters = {"semester1", "semester2", "semester3", "semester4", "semester5", "semester6", "semester7", "semester8"};
        semesterBox = new JComboBox<>(semesters);
        semesterBox.setBounds(200, 260, 150, 20);
        add(semesterBox);

        JLabel total = new JLabel("Total Payable");
        total.setBounds(40, 300, 150, 20);
        add(total);

        totalAmount = new JLabel();
        totalAmount.setBounds(200, 300, 150, 20);
        add(totalAmount);

        update = new JButton("Update");
        update.setBounds(30, 380, 100, 25);
        update.addActionListener(this);
        add(update);

        pay = new JButton("Pay");
        pay.setBounds(150, 380, 100, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(270, 380, 100, 25);
        back.addActionListener(this);
        add(back);

        setSize(900, 500);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM fee WHERE course = '" + course + "'");
                while (resultSet.next()) {
                    totalAmount.setText(resultSet.getString(semester));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == pay) {
            String rollno = cRollNumber.getSelectedItem();
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            String branch = (String) departmentBox.getSelectedItem();
            String total = totalAmount.getText();

            try {
                Conn c = new Conn();
                String query = "INSERT INTO feecollege VALUES('" + rollno + "', '" + course + "', '" + branch + "', '" + semester + "', '" + total + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Fee Submitted Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
