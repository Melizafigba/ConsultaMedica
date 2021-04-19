
/**
 *
 * @author Meliza
 */

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion 
{
    private final static String SERVER_NAME = "localhost:3306";
    
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "";
    private final static String DB_NAME = "CONSULTAMEDICA";
    
    public Connection getConnection() throws SQLException, 
            ClassNotFoundException 
    {
        Properties connectionProps = new Properties();
        connectionProps.put("user", USER_NAME);
        connectionProps.put("password", PASSWORD);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + SERVER_NAME + "/"  + DB_NAME, connectionProps);
        System.out.println("¡Conexión Exitosa!");
        return conn;
    }
}
