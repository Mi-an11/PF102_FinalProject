import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;

public class AdminLoginDialog extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;



    private JLabel loginMessageLabel;





    public AdminLoginDialog() {
        setTitle("Admin Login");

        setLayout(null);
        setSize(400, 250); // Adjusted size to fit all components


        // Username label and field
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 30, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField(15);
        usernameField.setBounds(150, 30, 200, 25);
        add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 70, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(150, 70, 200, 25);
        add(passwordField);

        // Buttons
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 120, 200, 35);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(e);

            }
        });


    }

    private void login(ActionEvent e){

        if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false){

            validateLogin();

        }else{
            JOptionPane.showMessageDialog(null,  "Please Enter username and password","Warning", JOptionPane.WARNING_MESSAGE);

        }


    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM adminlogin WHERE username = '"+usernameField.getText()+"' AND password = '"+passwordField.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1) {
                    new adminFrame().setVisible(true);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password please try again", "Warning", JOptionPane.WARNING_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        }
        catch (Exception e) {

        }
    }



    public static void main(String[] args) {

    }
}