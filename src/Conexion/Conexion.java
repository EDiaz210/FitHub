package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static String URL = "jdbc:mysql://uetysrfgsp7pzfna:K8U7u88YU0iLPn2xDHUx@b3qj80cggc278lxxb5pg-mysql.services.clever-cloud.com:3306/b3qj80cggc278lxxb5pg";
    public static String USER = "uetysrfgsp7pzfna";
    public static String PASSWORD = "K8U7u88YU0iLPn2xDHUx";

    public static Connection connect() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
