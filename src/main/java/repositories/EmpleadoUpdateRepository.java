package repositories;

import dataBase.ConnectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpleadoUpdateRepository {
    ConnectionBD conBD = new ConnectionBD();

    public boolean updateDepartamento(int idEmpleado, int idDepartamento) {
        boolean updateOK = false;

        return updateOK;
    }

    public boolean updateOficio(String nuevoOficio, int idEmpleado) throws SQLException {
        boolean updateOK = false;
        try(Connection miCon = conBD.conectarDB()){
            PreparedStatement stmtUpdateOficio = miCon.prepareStatement("UPDATE empleados SET oficio = ? WHERE emp_no = ?");
            stmtUpdateOficio.setString(1, nuevoOficio);
            stmtUpdateOficio.setInt(2, idEmpleado);
            stmtUpdateOficio.executeUpdate();
            updateOK = true;
        }
        return updateOK;
    }
}
