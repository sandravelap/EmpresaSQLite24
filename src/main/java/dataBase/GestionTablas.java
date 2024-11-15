package dataBase;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class GestionTablas {
    ConnectionBD conBD = new ConnectionBD();
    public String crearTablas(){
        String mensaje = "";
        //creamos una conexión a la BD con un try with resources para asegurar que se cierra
        try (Connection con = conBD.conectarDB()){
            //creamos un objeto Statement para mandar sentencias al SGBD
            Statement stmCreate = con.createStatement();
            //ejecutamos en el SGBD la sentencia
            String createTDepartamentos ="CREATE TABLE departamentos (\n" +
                    " dept_no  TINYINT(2) NOT NULL PRIMARY KEY,\n" +
                    " dnombre  VARCHAR(15), \n" +
                    " loc      VARCHAR(15)\n" +
                    ")";
            String createTEmpleados ="CREATE TABLE empleados (\n" +
                    " emp_no    SMALLINT(4)  NOT NULL PRIMARY KEY,\n" +
                    " apellido  VARCHAR(10),\n" +
                    " oficio    VARCHAR(10),\n" +
                    " dir       SMALLINT,\n" +
                    " fecha_alt VARCHAR(10),\n" +
                    " salario   FLOAT(6,2),\n" +
                    " comision  FLOAT(6,2),\n" +
                    " dept_no   TINYINT(2) NOT NULL, \n" +
                    " FOREIGN KEY(dept_no) REFERENCES departamentos(dept_no)\n" +
                    ")";
            //para poder crear primero hay que borrarlas en el orden correcto
            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
            stmCreate.executeUpdate("DROP TABLE IF EXISTS empleados");
            stmCreate.executeUpdate("DROP TABLE IF EXISTS departamentos");
            stmCreate.executeUpdate(createTDepartamentos);
            mensaje = "Tabla departamentos creada. ";
            //sentencias SQL para añadir los valores de varios departamentos a la tabla
            List<String> addDeps = Arrays.asList("INSERT INTO departamentos VALUES (10,'CONTABILIDAD','SEVILLA')",
                    "INSERT INTO departamentos VALUES (20,'INVESTIGACIÓN','MADRID')",
                    "INSERT INTO departamentos VALUES (30,'VENTAS','BARCELONA')",
                    "INSERT INTO departamentos VALUES (40,'PRODUCCIÓN','BILBAO')");
            //variable Statement para ejecutar las sentencias SQL en la conexión

            //ejecutamos las sentencias SQL que añaden los departamentos
            for (String stmtInsertDep : addDeps) {
                stmCreate.executeUpdate(stmtInsertDep);
            }
            mensaje += "Departamentos añadidos. \n";
            //creamos la tabla empleados

            stmCreate.executeUpdate(createTEmpleados);
            mensaje += "Tabla empleados creada. ";

            //y le añadimos los datos
            List<String> addEmps = Arrays.asList("INSERT INTO empleados VALUES (7369,'SÁNCHEZ','EMPLEADO',7902,'1990/12/17',1040,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7499,'ARROYO','VENDEDOR',7698,'1990/02/20',1500,390,30)" ,
                    "INSERT INTO empleados VALUES (7521,'SALA','VENDEDOR',7698,'1991/02/22',1625,650,30)" ,
                    "INSERT INTO empleados VALUES (7566,'JIMÉNEZ','DIRECTOR',7839,'1991/04/02',2900,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7654,'MARTÍN','VENDEDOR',7698,'1991/09/29',1600,1020,30)" ,
                    "INSERT INTO empleados VALUES (7698,'NEGRO','DIRECTOR',7839,'1991/05/01',3005,NULL,30)" ,
                    "INSERT INTO empleados VALUES (7782,'CEREZO','DIRECTOR',7839,'1991/06/09',2885,NULL,10)" ,
                    "INSERT INTO empleados VALUES (7788,'GIL','ANALISTA',7566,'1991/11/09',3000,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7839,'REY','PRESIDENTE',NULL,'1991/11/17',4100,NULL,10)" ,
                    "INSERT INTO empleados VALUES (7844,'TOVAR','VENDEDOR',7698,'1991/09/08',1350,0,30)" ,
                    "INSERT INTO empleados VALUES (7876,'ALONSO','EMPLEADO',7788,'1991/09/23',1430,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7900,'JIMENO','EMPLEADO',7698,'1991/12/03',1335,NULL,30)" ,
                    "INSERT INTO empleados VALUES (7902,'FERNÁNDEZ','ANALISTA',7566,'1991/12/03',3000,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7934,'MUÑOZ','EMPLEADO',7782,'1992/01/23',1690,NULL,10)");

            for (String stmtInsertEmp : addEmps){
                stmCreate.executeUpdate(stmtInsertEmp);
            }
            mensaje += "Empleados insertados. ";

        }catch (SQLSyntaxErrorException e) {
            mensaje = "Error en la sintaxis de la sentencia SQL" + e.getMessage();
        }catch (SQLIntegrityConstraintViolationException e) {
            mensaje ="La sentencia SQL no cumple con los requisitos de integridad de la base de datos" + e.getMessage();
        }catch (SQLException e) {
            mensaje = "No se puede conectar a la base de datos";
        }
        return mensaje;
    }
    public String borrarTablas(){
        String mensaje ="";
        //creamos la conexión
        try(Connection miCon = conBD.conectarDB()){
        //creamos un objeto Statement para mandar sentencias al SGBD
            Statement stmDrop = miCon.createStatement();
            //ejecutamos en el SGBD la sentencia
            stmDrop.executeUpdate("DROP TABLE IF EXISTS empleados;");
            mensaje ="Tabla empleados borrada. \n";
            stmDrop.executeUpdate("DROP TABLE IF EXISTS departamentos;");
            mensaje +="Tabla departamentos borrada.";
        }catch (SQLSyntaxErrorException e) {
            mensaje = "Error en la sintaxis de la sentencia SQL" + e.getMessage();
        }catch (SQLIntegrityConstraintViolationException e) {
            mensaje ="La sentencia SQL no cumple con los requisitos de integridad de la base de datos" + e.getMessage();
        }catch (SQLException e) {
            mensaje = "No se puede conectar a la base de datos";
        }
        return mensaje;
    }
}
