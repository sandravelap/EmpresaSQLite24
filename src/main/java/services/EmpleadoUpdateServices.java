package services;

import repositories.EmpleadoUpdateRepository;

import java.sql.SQLException;

public class EmpleadoUpdateServices {
    EmpleadoUpdateRepository empUpRepo = new EmpleadoUpdateRepository();
    public String updateOficio(String nuevoOficio, int idEmpleado){
        String mensaje = "El oficio no se pudo actualizar";
        try {
            if (empUpRepo.updateOficio(nuevoOficio, idEmpleado)){
                mensaje = "Oficio actualizado correctamente.";
            }
        } catch (SQLException e) {
            mensaje="Ocurrió un error al actualizar el oficio";
        }
        return mensaje;
    }
}
