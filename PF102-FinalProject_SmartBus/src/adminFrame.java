import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import static java.sql.DriverManager.getConnection;

public class adminFrame extends JFrame {

    private JTable dataTable;
    private static DefaultTableModel tableModel;
    private JButton exitButton; // New exit button

    public adminFrame() {
        setTitle("Admin Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);



        // Initialize data table
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Passengers");
        tableModel.addColumn("Destination");
        tableModel.addColumn("Date");
        tableModel.addColumn("Amount");

        add(new JScrollPane(dataTable), BorderLayout.CENTER);

        // Add exit button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitButton = new JButton("Exit");
        bottomPanel.add(exitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });
}

    public static void addTicketToTable(String name, String passengers, String destination, String date, int amount) {

        Connection databaseLink = new DatabaseConnection().getConnection();
        PreparedStatement pst;


        // Format date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);

        // Add ticket to table
        Object[] rowData = {name, passengers, destination, formattedDate, "$" + amount};
        tableModel.addRow(rowData);



    }
    private void exitAction() {
        dispose();
    }



    public static void main(String[] args) {
    }
}
