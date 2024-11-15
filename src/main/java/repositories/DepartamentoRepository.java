package repositories;

import dao.Departamento;
import dataBase.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;

//en la arquitectura de capas con patrón DAO (Data Access Object)
//en los repositorios accedemos al información persistida y la almacenamos en memoria
//haciendo uso de las clases de Java que la modelan en este caso de las clases del paquete DAO
public class DepartamentoRepository {

    ConnectionBD conBD = new ConnectionBD();

    public ArrayList<Departamento> listarDepartamentos(){
        //instanciamos los objetos DAO donde almacenar la información que se va a recuperar
        ArrayList<Departamento> listadoDepartamentos = new ArrayList<Departamento>();
        Departamento depAux;
        //conectamos con la base de datos asegurando la desconexión con el try with resources
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un Statement porque la consulta no tiene parámetros
            Statement listDepsStatement = miCon.createStatement();
            //recuperamos en un ResultSet ejecutando la sentencia con un executeQuery
            //es recomendable comprobar que la sentencia SQL es correcta en la consola de la base de datos
            ResultSet rs = listDepsStatement.executeQuery("select * from departamentos");
            //recorremos los resultados de la consulta almacenando la información recuperada en los DAO
            while(rs.next()){
                depAux = new Departamento();
                depAux.setIdDepartamento(rs.getInt("dept_no"));
                depAux.setNombre(rs.getString("dnombre"));
                depAux.setLocalidad(rs.getString("loc"));
                listadoDepartamentos.add(depAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listadoDepartamentos;
    }

    public Departamento depById(int id){
        //DAO a devolver
        Departamento departamentoById = new Departamento();
        //conectamos con la base de datos asegurando la desconexión con el try with resources
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un PreparedStatement porque la consulta tiene parámetros
            //pondremos ? donde queramos insertar el valor de una variable
            PreparedStatement statementDepById = miCon.prepareStatement("select * from departamentos where dept_no = ?");
            //fijamos el valor del interrogante con el método adecuado a su tipo de dato
            //y la posición que ocupa en el statement empezando por 1
            statementDepById.setInt(1, id);
            ResultSet rs = statementDepById.executeQuery();
            //almacenamos el resultado de la consulta en el DAO
            while(rs.next()){
                departamentoById = new Departamento();
                departamentoById.setIdDepartamento(rs.getInt("dept_no"));
                departamentoById.setNombre(rs.getString("dnombre"));
                departamentoById.setLocalidad(rs.getString("loc"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departamentoById;
    }

    public boolean nombreDepExiste(String nombre){
        boolean existe = false;
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un PreparedStatement porque la consulta tiene parámetros
            //pondremos ? donde queramos insertar el valor de una variable
            PreparedStatement statementDepById = miCon.prepareStatement("select * from departamentos where dnombre = ?");
            //fijamos el valor del interrogante con el método adecuado a su tipo de dato
            //y la posición que ocupa en el statement empezando por 1
            statementDepById.setString(1, nombre);
            ResultSet rs = statementDepById.executeQuery();
            //si la consulta devuelve algo el departamento existe.
            if(rs.next()){
                existe = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }
    public Departamento depByName(String nombre){
        Departamento dep = new Departamento();
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un PreparedStatement porque la consulta tiene parámetros
            //pondremos ? donde queramos insertar el valor de una variable
            PreparedStatement statementDepById = miCon.prepareStatement("select * from departamentos where dnombre = ?");
            //fijamos el valor del interrogante con el método adecuado a su tipo de dato
            //y la posición que ocupa en el statement empezando por 1
            statementDepById.setString(1, nombre);
            ResultSet rs = statementDepById.executeQuery();
            //si la consulta devuelve algo el departamento existe.
            if(rs.next()){
                dep.setIdDepartamento(rs.getInt("dept_no"));
                dep.setNombre(rs.getString("dnombre"));
                dep.setLocalidad(rs.getString("loc"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dep;
    }
}
