package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static String URL = "jdbc:mysql://localhost:3306/fithub";
    public static String USER = "root";
    public static String PASSWORD = "123456";

    public static Connection connect() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
