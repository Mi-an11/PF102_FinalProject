import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {

    private JButton passengerButton;
    private JButton adminButton;

    public LandingPage() {
        setTitle("SmartBus System");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        JLabel titleLabel = new JLabel("Welcome to SmartBus System", JLabel.CENTER);
        titleLabel.setFont(new Font("Default", Font.BOLD, 16));
        titleLabel.setBounds(50, 30, 300, 30); // x, y, width, height
        add(titleLabel);

        passengerButton = new JButton("Passenger");
        passengerButton.setBounds(100, 100, 100, 30); // x, y, width, height
        add(passengerButton);

        adminButton = new JButton("Admin");
        adminButton.setBounds(210, 100, 100, 30); // x, y, width, height
        add(adminButton);

        passengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SmartBusTicketingSystem().setVisible(true);
                dispose();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminLogin();
                dispose();
            }

            private void showAdminLogin() {
                AdminLoginDialog adminLoginDialog = new AdminLoginDialog( );
                adminLoginDialog.setVisible(true);

            }
        });
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
