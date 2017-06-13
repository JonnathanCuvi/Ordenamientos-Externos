/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenamiento_Directo;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vladii
 */
public class Ordenar {
    
    public String directorio;
    public String nombreArchivo;
    public int campo;

    Ordenar(int campo, File archivo) throws IOException{
        
        int numRegistros=0;
        try 
        {
            CsvReader leer = new CsvReader(archivo.getAbsolutePath());
            while (leer.readRecord()) 
                numRegistros++;
            leer.close();
        } 
        catch (FileNotFoundException ex) {} catch (IOException ex) {}
        
        File file1 = new File(archivo.getParent()+"\\"+"Archi_Auxiliar1.csv");
        File file2 = new File(archivo.getParent()+"\\"+"Archi_Auxiliar2.csv"); 
        //System.out.println("numRegistros: " + numRegistros+"  campo: "+campo);
        //MezclaDirecta mezcla = new MezclaDirecta(archivo,file1,file2,numRegistros,campo); 
        mesclaDirecta(archivo,file1,file2,numRegistros,campo);
    }
    
    public void mesclaDirecta(File archivo, File file1, File file2, int rango, int cmp) throws FileNotFoundException, IOException {
        //Seleccion de archivo con Path ;nombbre;campo
        directorio = archivo.getParent();
        nombreArchivo = archivo.getName();
        campo = cmp;
        //Numero particiones
        int nParticiones = 1, cont = 0;
        //Subdivisiones del archivo. 
        //cont: numero de pasadas
        while (nParticiones <= ((int)((rango+1)/2))){
            nParticiones *=  2;
            cont++;
        }
        cont++;
        //System.out.println("numero de pasadas: "+cont);
        //System.out.println("nPart: "+nParticiones);
        
        int i = 1;
        nParticiones = 1;
        //Mezcla hasta el numero de pasadas 
        while (i <= cont){
            Particiona par = new Particiona(nParticiones, directorio, nombreArchivo, campo);
            Funciona fun = new Funciona(nParticiones, directorio, nombreArchivo, campo);
            
            //Particiona( nParticiones);
            //Fusiona( nParticiones);
            nParticiones *=  2;
            i++;
        }
        
        //System.out.println("\nSe ha ordenado el archivo - directo");
    
    }
    
    
    
}
