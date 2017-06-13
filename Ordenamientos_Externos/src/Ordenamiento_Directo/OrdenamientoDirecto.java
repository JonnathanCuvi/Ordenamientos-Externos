package Ordenamiento_Directo;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class OrdenamientoDirecto {
    
    public static void Procesar(int campo, File archivo) throws IOException {
        
        Ordenar ordenar = new Ordenar(campo, archivo);
    }
}