package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/placement_db", "root", "new_password"
            );
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return conn;
    }
}
