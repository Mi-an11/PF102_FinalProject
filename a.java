import com.toedter.calendar.JCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

class SmartBusTicketingSystem extends JFrame {
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
    private AdminFrame adminFrame;

    public SmartBusTicketingSystem() {
        initComponents();
        layoutComponents();
        addEventHandlers();
        setTitle("SmartBus Ticketing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the AdminFrame
        adminFrame = new AdminFrame();
    }

    private void initComponents() {
        nameField = new JTextField(20);
        fromComboBox = new JComboBox<>(new String[]{"San Jose"});
        destinationComboBox = new JComboBox<>(new String[]{"Libertad", "Pandan", "Sebaste", "Culasi", "Tibiao", "Barbaza", "Laua-an", "Bugasong", "Bugasong", "Valderama", "Patnongon", "San Remigio", "Belison", "Sibalom", "Hamtic", "Tobias Fornier", "Anini-y"});
        numberOfPassengersField = new JTextField(5);

        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "hh:mm a");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new java.util.Date());

        calendar = new JCalendar();
        ticketSummaryArea = new JTextArea(15, 30);
        payableAmountField = new JTextField(10);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        printButton = new JButton("Print");
        exitButton = new JButton("Exit"); // Initialize the exit button
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JLabel titleLabel = new JLabel("Welcome to SmartBus Ticketing System", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name of Passenger"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("From"), gbc);
        gbc.gridx = 1;
        add(fromComboBox, gbc);

        gbc.gridx = 2;
        add(new JLabel("Destination"), gbc);
        gbc.gridx = 3;
        add(destinationComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("No. of Passengers"), gbc);
        gbc.gridx = 1;
        add(numberOfPassengersField, gbc);

        gbc.gridx = 2;
        add(new JLabel("Time"), gbc);
        gbc.gridx = 3;
        add(timeSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Departure Date"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        add(calendar, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        add(new JLabel("Ticket Summary"), gbc);
        gbc.gridy = 6;
        ticketSummaryArea.setPreferredSize(new Dimension(400, 300));
        add(new JScrollPane(ticketSummaryArea), gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Payable Amount"), gbc);
        gbc.gridx = 1;
        add(payableAmountField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(printButton);
        buttonPanel.add(exitButton); // Add exit button

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 4;
        add(buttonPanel, gbc);
    }

    private void addEventHandlers() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAction();
            }
        });

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
        java.util.Date date = calendar.getDate();
        String time = timeSpinner.getValue().toString();
        // Calculate the payable amount (dummy calculation)
        int fare = DESTINATION_FARES.get(destination);
        int amount = Integer.parseInt(passengers) * fare;

        ticketSummaryArea.setText("Name: " + name + "\nFrom: " + from + "\nDestination: " + destination +
                "\nPassengers: " + passengers + "\nDate: " + date.toString() + "\nTime: " + time +
                "\nAmount: $" + amount);

        payableAmountField.setText(""+ amount);

        // Add ticket to admin panel's data table
        adminFrame.addTicketToTable(name, passengers, destination, date, amount);
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
}

class AdminFrame extends JFrame {
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JButton exitButton; // New exit button

    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

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

    public void addTicketToTable(String name, String passengers, String destination, Date date, int amount) {
        // Format date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);

        // Add ticket to table
        Object[] rowData = {name, passengers, destination, formattedDate, "$" + amount};
        tableModel.addRow(rowData);
    }

    private void exitAction() {
        dispose(); // Close the admin panel
    }
}

class LandingPage extends JFrame {
    private JButton passengerButton;
    private JButton adminButton;

    public LandingPage() {
        setTitle("SmartBus System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Welcome to SmartBus System", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        passengerButton = new JButton("Passenger");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(passengerButton, gbc);

        adminButton = new JButton("Admin");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(adminButton, gbc);

        passengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SmartBusTicketingSystem().setVisible(true);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminLogin();
            }
        });
    }
    private void showAdminLogin() {
        AdminLoginDialog adminLoginDialog = new AdminLoginDialog(this);
        adminLoginDialog.setVisible(true);
        dispose(); // Close the landing page after showing the admin login dialog
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LandingPage().setVisible(true);
            }
        });
    }
}

class AdminLoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public AdminLoginDialog(Frame parent) {
        super(parent, "Admin Login", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check credentials (dummy check for demonstration)
        if ("admin".equals(username) && "password".equals(password)) {
            new AdminFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
