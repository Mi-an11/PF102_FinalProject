import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection databaseLink;
    public Connection getConnection() {

        String sqlusername = "root";
        String sqlpassword = "Thegreatestshowman01";
        String dburl = "jdbc:mysql://localhost:3305/smartbus";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(dburl, sqlusername, sqlpassword);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return databaseLink;

    }
}
