package services;

import dao.Departamento;
import repositories.DepartamentoRepository;

import java.util.ArrayList;

//en el patrón DAO los servicios transforman la información recuperada en los DAOs a la información que
//necesita el usuario aplicando la lógica de negocio necesaria
public class DepartamentoService {
    private DepartamentoRepository departamentoRepository = new DepartamentoRepository();

    public ArrayList<String> listarDepartamentos(){
        //recuperamos la información solicitada en un DAO llamando al repositorio, que gestiona la conexión
        ArrayList<Departamento> listaDepartamentosDAO = departamentoRepository.listarDepartamentos();
        //instanciamos los objetos donde almacenar la información a transmitir al usuario
        ArrayList<String> listaDepsString = new ArrayList<String>();
        String depString;
        //transformamos la información para ser transmitida al usuario
        for (Departamento departamento : listaDepartamentosDAO) {
            depString = departamento.getNombre() + ". Localidad: " + departamento.getLocalidad();
            listaDepsString.add(depString);
        }
        return listaDepsString;
    }
}
