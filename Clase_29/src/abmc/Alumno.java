package abmc;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Alumno {
    private String nombre;
    private int edad;

    public Alumno() {}

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " - " + edad;
    }
    
    //metodos de acceso JDBC
    public static void consultarTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //obtengo la conexion
        Connection unaConexion = Conexion.obtenerConexion();
        
        //armo la consulta
        String unaConsulta = "select * from alumnos";
        
        //armo la sentencia
        Statement unaSentencia = unaConexion.createStatement();
        
        //ejecuto la query y guardo el resultado
        ResultSet unResultado = unaSentencia.executeQuery(unaConsulta);
        
        //itero la sentencia y muestro 
        while (unResultado.next()) {
            System.out.println("Nombre: "+unResultado.getString("nombre"));
            System.out.println("Edad: "+unResultado.getInt("edad"));
            System.out.println("---------------------------------");
        }
        
        //cierro
        unResultado.close();
        unaConexion.close();
        unaSentencia.close();
    }
    
    public static void consultarUno(String nombre) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //obtengo la conexion
        Connection unaConexion = Conexion.obtenerConexion();
        
        //armo la consulta
        String unaConsulta = "select * from alumnos where nombre = '" + nombre +"'";
        
        //armo la sentencia
        Statement unaSentencia = unaConexion.createStatement();
        
        //ejecuto la query y guardo el resultado
        ResultSet unResultado = unaSentencia.executeQuery(unaConsulta);
        
        //itero la sentencia y muestro 
        if (unResultado.next()) {
            System.out.println("Nombre: "+unResultado.getString("nombre"));
            System.out.println("Edad: "+unResultado.getInt("edad"));
        }
        
        //cierro
        unResultado.close();
        unaConexion.close();
        unaSentencia.close();
    }
    
    
    public static void insercion(Alumno a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //obtengo la conexion
        Connection unaConexion = Conexion.obtenerConexion();
        
        //armo la consulta
        String unaInsercion = "insert into alumnos(nombre, edad) values(?, ?)";
        
        //armo la sentencia
        PreparedStatement unaSentencia = unaConexion.prepareStatement(unaInsercion);
        
        //seteo los parametros del ?
        unaSentencia.setString(1, a.getNombre());
        unaSentencia.setInt(2, a.getEdad());
        
        //ejecuto la sentencia
        unaSentencia.execute();
        
        System.out.println("Insercion correcta");
        
        //cierro
        unaConexion.close();
        unaSentencia.close();
    }
    
    public static void actualizacion(String nombreNuevo, String nombreAnterior) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //obtengo la conexion
        Connection unaConexion = Conexion.obtenerConexion();
        
        //armo la consulta
        String unaActualizacion = "update alumnos set nombre = ? where nombre = ?";
        
        //armo la sentencia
        PreparedStatement unaSentencia = unaConexion.prepareStatement(unaActualizacion);
        
        //seteo los parametros del ?
        unaSentencia.setString(1, nombreNuevo);
        unaSentencia.setString(2, nombreAnterior);
        
        //ejecuto la sentencia
        unaSentencia.execute();
        
        System.out.println("Actualizacion correcta");
        
        //cierro
        unaConexion.close();
        unaSentencia.close();
    }
    
    
    public static void eliminacion(String nombre) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //obtengo la conexion
        Connection unaConexion = Conexion.obtenerConexion();
        
        //armo la consulta
        String unaEliminacion = "delete from alumnos where nombre = ?";
        
        //armo la sentencia
        PreparedStatement unaSentencia = unaConexion.prepareStatement(unaEliminacion);
        
        //seteo los parametros del ?
        unaSentencia.setString(1, nombre);
        
        //ejecuto la sentencia
        unaSentencia.execute();
        
        System.out.println("Eliminacion correcta");
        
        //cierro
        unaConexion.close();
        unaSentencia.close();
    }
        
    
}
