package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/pos_system";
    private static final String user = "root";
    private static final String password = "irfan#@123";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);

    }
}
