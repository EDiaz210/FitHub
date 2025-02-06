package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String URL = "jdbc:mysql://localhost:3306/fithub";
    String USER = "root";
    String PASSWORD = "123456";

    public Connection connect() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
