package libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class UserData {
    static void mostrarEnPantalla(String mensaje) {
        System.out.println(mensaje);
    }

    public static Path pedirRuta(final String mensaje) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String rutaString = "";
        Path ruta = Path.of(rutaString);
        boolean pathOK = false;
        while (!pathOK) {
            try {
                mostrarEnPantalla(mensaje);
                rutaString = dataIn.readLine();
                ruta = Path.of(rutaString);
                pathOK = true;
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor");
            } catch (InvalidPathException e) {
                mostrarEnPantalla("La ruta contiene caracteres ilegales");
            }
        }
        return ruta;
    }

    public static Path pedirRutaXML(final String mensaje) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String rutaString = "";
        Path ruta = Path.of(rutaString);
        boolean pathOK = false;
        while (!pathOK) {
            try {
                mostrarEnPantalla(mensaje);
                rutaString = dataIn.readLine();
                ruta = Path.of(rutaString);
                if (rutaString.endsWith(".xml")) {
                    pathOK = true;
                }else{
                    mostrarEnPantalla("La ruta no se corresponde con un archivo xml. ");
                }
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor");
            } catch (InvalidPathException e) {
                mostrarEnPantalla("La ruta contiene caracteres ilegales");
            }
        }
        return ruta;
    }

    public static Path pedirRutaJson(final String mensaje) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String rutaString = "";
        Path ruta = Path.of(rutaString);
        boolean pathOK = false;
        while (!pathOK) {
            try {
                mostrarEnPantalla(mensaje);
                rutaString = dataIn.readLine();
                ruta = Path.of(rutaString);
                if (rutaString.endsWith(".json")) {
                    pathOK = true;
                }else{
                    mostrarEnPantalla("La ruta no se corresponde con un archivo json. ");
                }
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor");
            } catch (InvalidPathException e) {
                mostrarEnPantalla("La ruta contiene caracteres ilegales");
            }
        }
        return ruta;
    }

    public static boolean dirEscribible(Path p) {
        //método que chequea si se puede escribir en un directorio y si no lo crea
        boolean dirOK = false;
        if (Files.exists(p) && Files.isDirectory(p)) {
            if (Files.isWritable(p)) {
                dirOK = true;
            }

        } else {
            try {
                Files.createDirectory(p);
                dirOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return dirOK;
    }

    //
    public static boolean ficheroLegible(Path p) {
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isReadable(p)) {
                ficheroOK = true;
            }
        } else {
            try {
                Files.createFile(p);
                ficheroOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return ficheroOK;
    }

    public static boolean ficheroEscribible(Path p) {
        //método que comprueba si se puede escribir en un fichero y si no lo crea
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isWritable(p)){
                ficheroOK = true;
            }

        }
        else {
            try {
                Files.createFile(p);
                ficheroOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return ficheroOK;
    }

}
