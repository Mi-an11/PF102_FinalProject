import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;


class adminFrame extends JFrame {


    private JTable dataTable;

    private static DefaultTableModel tableModel;
    private JButton exitButton; // New exit button

    private JButton deleteButton;

    private JTextField Tid;
    private JLabel idLabel;




    Connection databaseLink = new DatabaseConnection().getConnection();
    PreparedStatement pst;

    public adminFrame() {



        setTitle("Admin Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        // Initialize data table
                tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Passengers");
        tableModel.addColumn("From");
        tableModel.addColumn("Destination");
        tableModel.addColumn("Time");
        tableModel.addColumn("Date");
        tableModel.addColumn("Amount");

        add(new JScrollPane(dataTable), BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deleteButton = new JButton("Delete");
        exitButton = new JButton("Exit");
        Tid = new JTextField(10);
        idLabel = new JLabel("Enter ID");
        bottomPanel.add(idLabel);
        bottomPanel.add(Tid);

        bottomPanel.add(deleteButton);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);



        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAction();
            }

        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });

        setVisible(true);
        DatafromDatabase();


}
    private void deleteAction() {
        if (!ensureConnection()) {
            return;
        }

        String idText = Tid.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format.");
            return;
        }

        int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION) {
            try {
                pst = databaseLink.prepareStatement("DELETE FROM ticketinfo WHERE id_TicketInfo = ?");
                pst.setInt(1, id);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record Deleted");
                    DatafromDatabase();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete record.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting record.");
            } finally {
                try {
                    if (pst != null) {
                        pst.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    private void DatafromDatabase() {
        try {
            pst = databaseLink.prepareStatement("SELECT id_TicketInfo, Pname, Npassengers, departureFrom, destination, departureTime, departureDate, fareAmount FROM ticketinfo");
            ResultSet resultSet = pst.executeQuery();
            dataTable.setModel(DbUtils.resultSetToTableModel(resultSet));

            tableModel.setRowCount(0);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_TicketInfo");
                String name = resultSet.getString("Pname");
                String passengers = resultSet.getString("Npassengers");
                String from = resultSet.getString("departureFrom");
                String destination = resultSet.getString("destination");
                String time = resultSet.getString("departureTime");
                String date = resultSet.getString("departureDate");
                String amount = resultSet.getString("fareAmount");

                tableModel.addRow(new Object[]{id, name, passengers, from, destination, time, date, amount});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (databaseLink != null) {
                    databaseLink.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean ensureConnection() {
        try {
            if (databaseLink == null || databaseLink.isClosed()) {
                databaseLink = new DatabaseConnection().getConnection();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error.");
            return false;
        }
    }




    private void exitAction() {
        dispose();
    }



    public static void main(String[] args) {
    }
}
