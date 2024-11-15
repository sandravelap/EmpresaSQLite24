package dataBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionDataBase {
    ConnectionBD conexionBD = new ConnectionBD();
    public String crearDB(){
        String mensaje ="Base de datos creada. ";
        //creamos una conexión al archivo con un try with resources para asegurar que se cierra
        try (Connection con = conexionBD.conectarDB()){
            //al conectarnos, si el archivo no existe ya crea la base de datos
            DatabaseMetaData databaseMetaData = con.getMetaData();
            //ejecutamos en el SGBD la sentencia
            mensaje += "Product name: " + databaseMetaData.getDatabaseProductName();
        } catch (SQLException e) {
            mensaje ="ups, falló";
        }
        return mensaje;
    }
    public String borrarDB(){
        String mensaje ="Base de datos borrada.";
        //al ser una base de datos portable, simplemente se borra el archivo
        Path p = Path.of("src/main/resources/empresa.db");
        if (Files.exists(p)){
            try {
                Files.delete(p);
            } catch (IOException e) {
                mensaje="Ocurrió un problema al borrar la base de datos";
            }
        }else{
            mensaje="La base de datos no existe.";
        }
        return mensaje;
    }

}
