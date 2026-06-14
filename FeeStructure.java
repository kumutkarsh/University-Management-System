package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils; // Ensure this library is in your classpath.

public class FeeStructure extends JFrame implements ActionListener {

    FeeStructure() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(400, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30)); // Fixed incorrect 'font.BOLD'
        add(heading);

        JTable table = new JTable(); // Corrected variable name to match usage.

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM fee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet)); // Corrected 'resultSetToTabModel'
        } catch (Exception e) { // Corrected 'Expection' to 'Exception'
            e.printStackTrace();
        }

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 60, 1000, 600); // Adjusted dimensions to fit within frame.
        add(js);

        setSize(1000, 700);
        setLocation(250, 50);
        setVisible(true); // Fixed 'setVisisble' to 'setVisible'
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add action handling logic if needed.
    }

    public static void main(String[] args) { // Fixed 'string' to 'String'
        new FeeStructure();
    }
}



