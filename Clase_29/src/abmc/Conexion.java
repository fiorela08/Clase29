package abmc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection obtenerConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        
        //creo objeto de la clase Driver por API Reflection
        Class.forName(driver).newInstance();
        
        String usuario = "root";
        String clave = "";
        String cadena = "jdbc:mysql://127.0.0.1:3306/colegio";
        
        //retorno el objeto de Conexion con la info del servidor
        return DriverManager.getConnection(cadena,usuario,clave);
    }
}
