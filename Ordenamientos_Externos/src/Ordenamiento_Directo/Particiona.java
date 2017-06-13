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
public class Particiona {
    
    public String directorio;
    public String nombreArchivo;
    public int campo;
    public int part;

    Particiona(int part,String directorio,String nombreArchivo, int campo) throws IOException {
        this.directorio=directorio;
        this.nombreArchivo=nombreArchivo;
        this.campo=campo;
        this.part=part;
        
        particiona();
        
    }
    
    public void particiona() throws FileNotFoundException, IOException {
        
        //Archivo principal
        CsvReader f = new CsvReader(directorio+"\\"+nombreArchivo);
        
        //Archivos auxiliares
        PrintWriter f1 = new PrintWriter(directorio+"\\"+"Archi_Auxiliar1.csv");
        PrintWriter f2 = new PrintWriter(directorio+"\\"+"Archi_Auxiliar2.csv");
        
        int k = 0, l = 0;
        
        f.readRecord();
        
        boolean noTermineLeer=true;
        while(noTermineLeer){ 
        //Se transiferen registros a cada uno de los archivos dependiendo el numero de subconjuntos
            k = 0;
            while (k < part  &&  noTermineLeer){
                f1.println(f.getRawRecord());
                k++;           
                noTermineLeer=false;
                if(f.readRecord()) //Si existe registros sigue leyendo
                    noTermineLeer=true;
            } 
            l = 0;
            while (l < part  &&  noTermineLeer){
                f2.println(f.getRawRecord());
                l++;
                noTermineLeer=false;
                if(f.readRecord()) 
                    noTermineLeer=true;
            }
        }
        f1.flush();
        f2.flush();
        f1.close();
        f2.close();
        f.close();
        
    }
    
    
    
}
