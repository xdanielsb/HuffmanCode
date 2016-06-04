
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class Archivo {

    public String readFile(String archivo) {
        String cadena, texto = "";
        try {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                texto += cadena;
            }
            b.close();
        } catch (IOException ex) {
        }
        return texto;
    }
}
