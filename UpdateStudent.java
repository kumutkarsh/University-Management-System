package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateStudent extends JFrame implements ActionListener {
    JTextField textAddress, textPhone, textEmail, textAadhar, textCourse, textBranch;
    JLabel empText, textName, textFather, dobLabel, textM10, textM12;
    JButton submit, cancel;
    Choice cRollNo;

    UpdateStudent() {
        getContentPane().setBackground(new Color(230, 210, 251));
        setLayout(null);

        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 35));
        add(heading);

        JLabel rollNoLabel = new JLabel("Select Roll Number");
        rollNoLabel.setBounds(50, 100, 200, 20);
        rollNoLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(rollNoLabel);

        cRollNo = new Choice();
        cRollNo.setBounds(250, 100, 200, 20);
        add(cRollNo);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                cRollNo.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 100, 30);
        name.setFont(new Font("serif", Font.BOLD, 20));
        add(name);

        textName = new JLabel();
        textName.setBounds(200, 150, 150, 30);
        add(textName);

        JLabel fatherName = new JLabel("Father Name");
        fatherName.setBounds(400, 150, 200, 30);
        fatherName.setFont(new Font("serif", Font.BOLD, 20));
        add(fatherName);

        textFather = new JLabel();
        textFather.setBounds(600, 150, 150, 30);
        add(textFather);

        JLabel rollNo = new JLabel("Roll Number");
        rollNo.setBounds(50, 200, 200, 30);
        rollNo.setFont(new Font("serif", Font.BOLD, 20));
        add(rollNo);

        empText = new JLabel();
        empText.setBounds(200, 200, 150, 30);
        empText.setFont(new Font("serif", Font.BOLD, 20));
        add(empText);

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(400, 200, 200, 30);
        dob.setFont(new Font("serif", Font.BOLD, 20));
        add(dob);

        dobLabel = new JLabel();
        dobLabel.setBounds(600, 200, 150, 30);
        add(dobLabel);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 200, 30);
        address.setFont(new Font("serif", Font.BOLD, 20));
        add(address);

        textAddress = new JTextField();
        textAddress.setBounds(200, 250, 150, 30);
        add(textAddress);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(400, 250, 200, 30);
        phone.setFont(new Font("serif", Font.BOLD, 20));
        add(phone);

        textPhone = new JTextField();
        textPhone.setBounds(600, 250, 150, 30);
        add(textPhone);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 300, 200, 30);
        email.setFont(new Font("serif", Font.BOLD, 20));
        add(email);

        textEmail = new JTextField();
        textEmail.setBounds(200, 300, 150, 30);
        add(textEmail);

        JLabel m10 = new JLabel("Class X (%)");
        m10.setBounds(400, 300, 200, 30);
        m10.setFont(new Font("serif", Font.BOLD, 20));
        add(m10);

        textM10 = new JLabel();
        textM10.setBounds(600, 300, 150, 30);
        add(textM10);

        JLabel m12 = new JLabel("Class XII (%)");
        m12.setBounds(50, 350, 200, 30);
        m12.setFont(new Font("serif", Font.BOLD, 20));
        add(m12);

        textM12 = new JLabel();
        textM12.setBounds(200, 350, 150, 30);
        add(textM12);

        JLabel aadharNo = new JLabel("Aadhar Number");
        aadharNo.setBounds(400, 350, 200, 30);
        aadharNo.setFont(new Font("serif", Font.BOLD, 20));
        add(aadharNo);

        textAadhar = new JTextField();
        textAadhar.setBounds(600, 350, 150, 30);
        add(textAadhar);

        JLabel qualification = new JLabel("Course");
        qualification.setBounds(50, 400, 200, 30);
        qualification.setFont(new Font("serif", Font.BOLD, 20));
        add(qualification);

        textCourse = new JTextField();
        textCourse.setBounds(200, 400, 150, 30);
        add(textCourse);

        JLabel department = new JLabel("Branch");
        department.setBounds(400, 400, 200, 30);
        department.setFont(new Font("serif", Font.BOLD, 20));
        add(department);

        textBranch = new JTextField();
        textBranch.setBounds(600, 400, 150, 30);
        add(textBranch);

        cRollNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Conn c = new Conn();
                    String query = "SELECT * FROM student WHERE rollno = '" + cRollNo.getSelectedItem() + "'";
                    ResultSet rs = c.statement.executeQuery(query);
                    while (rs.next()) {
                        textName.setText(rs.getString("name"));
                        textFather.setText(rs.getString("fname"));
                        dobLabel.setText(rs.getString("dob"));
                        textAddress.setText(rs.getString("address"));
                        textPhone.setText(rs.getString("phone"));
                        textEmail.setText(rs.getString("email"));
                        textM10.setText(rs.getString("class_x"));
                        textM12.setText(rs.getString("class_xii"));
                        textAadhar.setText(rs.getString("aadhar"));
                        empText.setText(rs.getString("rollno"));
                        textCourse.setText(rs.getString("course"));
                        textBranch.setText(rs.getString("branch"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        submit = new JButton("Update");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        setLocation(350, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String rollNo = empText.getText();
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textEmail.getText();
            String course = textCourse.getText();
            String branch = textBranch.getText();

            try {
                String query = "UPDATE student SET address = '" + address + "', phone = '" + phone + "', email = '" + email + "', course = '" + course + "', branch = '" + branch + "' WHERE rollno = '" + rollNo + "'";
                Conn c = new Conn();
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Details Updated");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateStudent();
    }
}


