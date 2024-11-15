package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static final String URL = "jdbc:sqlite:src/main/resources/empresa.db";

    public Connection conectarDB() {
        Connection miCon = null;
        try {
            miCon = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return miCon;
    }

}
