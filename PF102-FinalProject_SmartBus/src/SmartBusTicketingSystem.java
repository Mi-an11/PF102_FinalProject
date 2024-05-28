import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class SmartBusTicketingSystem extends JFrame {

    private JTextField nameField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> destinationComboBox;
    private JTextField numberOfPassengersField;
    private JSpinner timeSpinner;

    private JCalendar calendar;
    private JTextArea ticketSummaryArea;
    private JTextField payableAmountField;
    private JButton submitButton;
    private JButton resetButton;
    private JButton printButton;
    private JButton exitButton; // New exit button

    Connection databaseLink = new DatabaseConnection().getConnection();
    PreparedStatement pst;

    private adminFrame adminFrame;

    public SmartBusTicketingSystem() {
        initComponents();
        layoutComponents();
        addEventHandlers();
        setTitle("SmartBus Ticketing System");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    private void initComponents() {



        nameField = new JTextField(20);
        fromComboBox = new JComboBox<>(new String[]{"San Jose"});
        destinationComboBox = new JComboBox<>(new String[]{"Libertad", "Pandan", "Sebaste", "Culasi", "Tibiao", "Barbaza", "Laua-an", "Bugasong", "Bugasong", "Valderama", "Patnongon", "San Remigio", "Belison", "Sibalom", "Hamtic", "Tobias Fornier", "Anini-y"});
        numberOfPassengersField = new JTextField(5);

        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new java.util.Date());

        calendar = new JCalendar();
        ticketSummaryArea = new JTextArea(15, 30);
        payableAmountField = new JTextField(10);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        printButton = new JButton("Print");
        exitButton = new JButton("Exit");

    }

    private void layoutComponents() {
        JPanel panel = new JPanel();

        final JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to SmartBus Ticketing System", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBounds(70, 30, 500, 30);
        panel1.add(titleLabel);

        JLabel nameLabel = new JLabel("Name of Passenger");
        nameLabel.setBounds(30, 90, 150, 25);
        panel1.add(nameLabel);
        nameField.setBounds(160, 90, 150, 25);
        panel1.add(nameField);

        JLabel numberOfPassenegrsLabel = new JLabel("No. of Passengers");
        numberOfPassenegrsLabel.setBounds(350, 90, 150, 25);
        panel1.add(numberOfPassenegrsLabel);
        numberOfPassengersField.setBounds(470, 90, 50, 25);
        panel1.add(numberOfPassengersField);

        JLabel fromLabel = new JLabel("From");
        fromLabel.setBounds(30, 125, 150, 25);
        panel1.add(fromLabel);
        fromComboBox.setBounds(160, 125, 150, 25);
        panel1.add(fromComboBox);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(350, 125, 150, 25);
        panel1.add(destinationLabel);
        destinationComboBox.setBounds(470, 125, 150, 25);
        panel1.add(destinationComboBox);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setBounds(30, 160, 150, 25);
        panel1.add(timeLabel);
        timeSpinner.setBounds(160, 160, 150, 25);
        panel1.add(timeSpinner);

        JLabel DepartureLabel = new JLabel("Departure Date");
        DepartureLabel.setBounds(30, 195, 150, 25);
        panel1.add(DepartureLabel);
        calendar.setBounds(160, 195, 200, 200);
        panel1.add(calendar);


        JPanel panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel ticketSummaryLabel = new JLabel("Ticket Summary");
        ticketSummaryLabel.setBounds(300, 50, 150, 50);
        panel2.add(ticketSummaryLabel);
        JScrollPane scrollPane = new JScrollPane(ticketSummaryArea);
        scrollPane.setBounds(200, 95, 300, 230);
        panel2.add(scrollPane);

        JLabel payableAmountLabel = new JLabel("Payable Amount");
        payableAmountLabel.setBounds(200, 350, 150, 25);
        panel2.add(payableAmountLabel);
        payableAmountField.setBounds(300, 350, 150, 25);
        panel2.add(payableAmountField);

        submitButton.setBounds(300, 400, 100, 30);
        submitButton.setBackground(new Color(59,182,89));
        submitButton.setForeground(Color.WHITE);
        panel1.add(submitButton);
        resetButton.setBounds(360, 400, 100, 30);
        panel2.add(resetButton);
        printButton.setBounds(240, 400, 100, 30);
        panel2.add(printButton);
        exitButton.setBounds(300, 450, 100, 30);
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        panel2.add(exitButton);

        tabbedPane.addTab("Information", panel1);
        tabbedPane.addTab("Ticket Information", panel2);
        getContentPane().add(tabbedPane);
        setVisible(true);



        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAction();
                tabbedPane.setSelectedIndex(1);
            }
        });



    }

    private void addEventHandlers(){

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAction();
            }
        });
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printAction();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });
    }

    private void submitAction() {


        String name = nameField.getText();
        String from = (String) fromComboBox.getSelectedItem();
        String destination = (String) destinationComboBox.getSelectedItem();
        String passengers = numberOfPassengersField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(calendar.getDate());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(timeSpinner.getValue());

        // Calculate the payable amount (dummy calculation)
        int fare = DESTINATION_FARES.get(destination);
        int amount = Integer.parseInt(passengers) * fare;

        ticketSummaryArea.setText("Name: " + name + "\nFrom: " + from + "\nDestination: " + destination +
                "\nPassengers: " + passengers + "\nDate: " + dateStr.toString() + "\nTime: " + time +
                "\nAmount: PHP" + amount);

        payableAmountField.setText(""+ amount);

        try{
            pst = databaseLink.prepareStatement("INSERT INTO ticketinfo(Pname, Npassengers, departureFrom, destination, departureTime, departureDate, fareAmount)values(?,?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, passengers);
            pst.setString(3, from);
            pst.setString(4, destination);
            pst.setString(5, time);
            pst.setString(6, dateStr);
            pst.setString(7, String.valueOf(amount));
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record Added!!!");

            nameField.setText("");
            numberOfPassengersField.setText("");
            fromComboBox.setToolTipText("");
            destinationComboBox.setToolTipText("");
            timeSpinner.setToolTipText("");
            calendar.setToolTipText("");
            payableAmountField.setText("");



        }
        catch (Exception e){
            e.printStackTrace();
        }








        // Add ticket to admin panel's data table

    }

    private void resetAction() {
        nameField.setText("");
        fromComboBox.setSelectedIndex(0);
        destinationComboBox.setSelectedIndex(0);
        numberOfPassengersField.setText("");
        calendar.setDate(new java.util.Date());
        timeSpinner.setValue(new java.util.Date());
        ticketSummaryArea.setText("");
        payableAmountField.setText("");
    }

    private void printAction() {
        try {
            ticketSummaryArea.print();
            resetAction();  // Call resetAction() after printing
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to print: " + e.getMessage());
        }
    }

    private void exitAction() {
        LandingPage landingPage = new LandingPage();
        landingPage.setVisible(true);
        dispose(); // Close the current window
    }



    private static final HashMap<String, Integer> DESTINATION_FARES = new HashMap<>();

    static {
        DESTINATION_FARES.put("Libertad", 25);
        DESTINATION_FARES.put("Pandan", 20);
        DESTINATION_FARES.put("Sebaste", 30);
        DESTINATION_FARES.put("Culasi", 35);
        DESTINATION_FARES.put("Tibiao", 40);
        DESTINATION_FARES.put("Barbaza", 45);
        DESTINATION_FARES.put("Laua-an", 50);
        DESTINATION_FARES.put("Bugasong", 55);
        DESTINATION_FARES.put("Valderama", 60);
        DESTINATION_FARES.put("Patnongon", 65);
        DESTINATION_FARES.put("San Remigio", 70);
        DESTINATION_FARES.put("Belison", 75);
        DESTINATION_FARES.put("Sibalom", 80);
        DESTINATION_FARES.put("Hamtic", 85);
        DESTINATION_FARES.put("Tobias Fornier", 50);
        DESTINATION_FARES.put("Anini-y", 95);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SmartBusTicketingSystem frame = new SmartBusTicketingSystem();
                frame.setVisible(true);
            }
        });

    }

}
