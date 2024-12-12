package repositories;

import dao.EmpleadoDAO;
import dataBase.ConnectionBD;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EmpleadoRepository {
    ConnectionBD conBD = new ConnectionBD();
    DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    public ArrayList<EmpleadoDAO> listarEmpleados(){
        //instanciamos los objetos DAO donde almacenar la información que se va a recuperar
        ArrayList<EmpleadoDAO> listadoEmpleados = new ArrayList<EmpleadoDAO>();
        EmpleadoDAO empAux;
        //SQLite almacena la fecha como string con el formato "YYYY-MM-DD HH:MM:SS.SSS"
        //creamos el formato que necesitamos que tenga, prescindimos de la hora porque no la tenemos
        SimpleDateFormat formateo= new SimpleDateFormat("yyyy-MM-dd");
        //conectamos con la base de datos asegurando la desconexión con el try with resources
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un Statement porque la consulta no tiene parámetros
            Statement listDepsStatement = miCon.createStatement();
            //recuperamos en un ResultSet ejecutando la sentencia con un executeQuery
            //es recomendable comprobar que la sentencia SQL es correcta en la consola de la base de datos
            ResultSet rs = listDepsStatement.executeQuery("select * from empleados");
            //recorremos los resultados de la consulta almacenando la información recuperada en los DAO
            while(rs.next()){
                empAux = new EmpleadoDAO();
                empAux.setIdEmpleado(rs.getInt("emp_no"));
                empAux.setApellido(rs.getString("apellido"));
                empAux.setOficio(rs.getString("oficio"));
                //SQLite no almacena las fecha como tal, sino como real (https://sqlite.org/datatype3.html)
                //aunque en la columna se vea Date, en realidad internamente es un número o un String
                String utilFechaAlta = rs.getString("fecha_alt");
                java.util.Date utilFecha = formateo.parse(utilFechaAlta);
                Date sqlFecha = new java.sql.Date(utilFecha.getTime());
                empAux.setFechaAlta(sqlFecha);
                empAux.setDepartamento(departamentoRepository.depById(rs.getInt("dept_no")));
                //añadimos el objeto creado a la lista de empleados
                listadoEmpleados.add(empAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return listadoEmpleados;
    }

    //en este caso la comprobación devuelve cero si el usuario no existe, eso lo gestionará el servicio
    public int empleadoByName(String apellido){
        int idEmpleado = 0;
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un preparedStatement para pasarle el apellido
            PreparedStatement stmtEmpByName = miCon.prepareStatement("select emp_no from empleados where apellido = ?");
            //recuperamos en un ResultSet ejecutando la sentencia con un executeQuery
            stmtEmpByName.setString(1, apellido);
            ResultSet rs = stmtEmpByName.executeQuery();
            //recuperamos el id
            while(rs.next()){
                idEmpleado = rs.getInt("emp_no");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idEmpleado;
    }

    //si tenemos en cuenta que el apellido se puede repetir, empleadoByName debería devolver un array

    public ArrayList<Integer> empleadosByName(String apellido) throws SQLException {
        ArrayList<Integer> idsEmpleados = new ArrayList<>();
        try(Connection miCon = conBD.conectarDB()){
            PreparedStatement stmtIdsByName = miCon.prepareStatement("select emp_no from empleados where apellido = ?");
            stmtIdsByName.setString(1, apellido);
            ResultSet rs = stmtIdsByName.executeQuery();
            int i = 0;
            while(rs.next()) {
                idsEmpleados.add(rs.getInt("emp_no"));
            }
        }
        return idsEmpleados;
    }

    public String insertEmpleado(EmpleadoDAO nuevoEmpleado) {
        //en este caso devolvemos un mensaje diciendo si el empleado se ha podido insertar o no
        String mensaje = "";
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un preparedStatement donde insertaremos los datos del DAO
            PreparedStatement stmtInsertEmp = miCon.prepareStatement("insert into empleados values (?,?,?, ?, ?, ?, ?, ?)");
            //insertamos en la sentencia sql los valores del empleado
            stmtInsertEmp.setInt(1, nuevoEmpleado.getIdEmpleado());
            stmtInsertEmp.setString(2, nuevoEmpleado.getApellido());
            stmtInsertEmp.setString(3, nuevoEmpleado.getOficio());
            stmtInsertEmp.setInt(4, nuevoEmpleado.getIdDirector());
            stmtInsertEmp.setString(5, String.valueOf(nuevoEmpleado.getFechaAlta()));
            stmtInsertEmp.setFloat(6, nuevoEmpleado.getSalario());
            stmtInsertEmp.setFloat(7, nuevoEmpleado.getComision());
            stmtInsertEmp.setInt(8, nuevoEmpleado.getDepartamento().getIdDepartamento());
            //y lo ejecutamos actualizando la base de datos
            stmtInsertEmp.executeUpdate();
            mensaje="Empleado insertado con exito";
        } catch (SQLException e) {
            mensaje ="Ocurrió un error al insertar el empleado";
        }
        return mensaje;
    }

    //en este caso propagamos el error para gestionar la excepción en el servicio y poder enviar el mensaje desde allí
    public boolean borrarEmpleado(int idEmpleado) throws SQLException {
        boolean borrado = false;
        try(Connection miCon = conBD.conectarDB()) {
            PreparedStatement stmtDelEmpleado = miCon.prepareStatement("delete from empleados where emp_no = ?");
            stmtDelEmpleado.setInt(1, idEmpleado);
            stmtDelEmpleado.executeUpdate();
            borrado = true;
        }
        return borrado;
    }
}
