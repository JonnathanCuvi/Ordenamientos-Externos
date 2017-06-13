package Ordenamiento_Natural;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class OrdenamientoNatural {
    public static void Procesar(int campo, File archivo) throws IOException
    {
        System.out.println("campo: "+campo);
        //MezclaNatural mezcla = new MezclaNatural(archivo,campo);
        //mezcla.ordenar();
        
        OrdenarNatural ordenarNatural = new OrdenarNatural(archivo, campo);
        
        
        System.out.println("Mezcla Natural Terminada..");   
    }
}