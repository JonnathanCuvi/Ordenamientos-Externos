/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenamiento_Directo;

import com.csvreader.CsvReader;
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
public class Funciona {
    
    public String directorio;
    public String nombreArchivo;
    public int campo;
    public int part;

    Funciona(int part,String directorio,String nombreArchivo, int campo) throws IOException{
        
        this.directorio=directorio;
        this.nombreArchivo=nombreArchivo;
        this.campo=campo;
        this.part=part;
        
        funcion();
    }
    
    public void funcion() throws FileNotFoundException, IOException {
        
        //Archivo principal
        PrintWriter f = new PrintWriter(directorio+"\\"+nombreArchivo);
        
        //Archivos auxiliares
        CsvReader f1 = new CsvReader(directorio+"\\"+"Archi_Auxiliar1.csv");
        CsvReader f2 = new CsvReader(directorio+"\\"+"Archi_Auxiliar2.csv");
        
       //variables para almacenar los registros
        int k = 0, l = 0;
        String cadena1 = "", cadena2 = "",clave1="",clave2="";
        
        boolean B1=true;
        boolean B2=true;
        
        if(f1.readRecord()){        //  si se puede leer el primer registro del 1 archivo
            cadena1=f1.getRawRecord();  //  extraigo la linea
            clave1 = f1.get(campo);         //  extraigo el valor del campo
            B1=false;
        }
        else
            cadena1=null;
        //
        if(f2.readRecord()){
            cadena2=f2.getRawRecord();
            clave2 = f2.get(campo);
            B2=false;
        }
        else
            cadena2=null;
       
            
        while(((cadena1 != null) || (B1 == false)) && ((cadena2 != null) || (B2 == false))){
            k = l = 0;
            
            while(((k < part) && (B1 == false)) && ((l < part) && (B2 == false))){
                
                //  Condiciones segun el campo (numerico, texto, bool o fecha)
                int condicion1=0, condicion2=0;
                switch (campo) {
                    case 0://Campo numerico
                        condicion1=Integer.parseInt(clave1);
                        condicion2=Integer.parseInt(clave2);
                        break;
                    case 3://Campo de fecha
                        try{
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            Date fecha1 = formateador.parse(clave1);
                            Date fecha2 = formateador.parse(clave2);
                            
                            if ( fecha1.before(fecha2) ){       //si la fecha1 es menor a la fecha 2
                                condicion1=0;condicion2=1;
                            }
                            else{
                                condicion1=1;condicion2=0;
                            }
                            
                        }
                        catch (ParseException e) {
                            System.out.println("Se Produjo un Error!!!  "+e.getMessage());
                        }    break;
                    default://Campos texto o booleano
                        if(clave1.compareTo(clave2)<0){
                            condicion1=0;
                            condicion2=1;
                        }
                        else{
                            condicion1=1;
                            condicion2=0;
                        }   break;
                }
                //Ordenamiento  y Sigue el proceso para dividir
                if (condicion1 < condicion2){
                    f.println(cadena1);   //Escribe en el archivo principal                 
                    B1=true;
                    k++;
                    if(f1.readRecord()){ 
                        cadena1=f1.getRawRecord();
                        clave1 = f1.get(campo);
                        if (cadena1!=null){
                            B1=false;
                        }
                    }
                    else
                        cadena1=null;
                } 
                else {
                    f.println(cadena2);  //Escribe en el archivo principal  
                    B2= true;
                    l++;
                    if(f2.readRecord()){
                        cadena2=f2.getRawRecord();
                        clave2 = f2.get(campo);
                        if (cadena2 != null){
                            B2 = false;
                        }
                    }
                    else
                        cadena2=null;
                }
            }
            //Particiones 
            while((k < part) && (B1 == false)) {
                f.println(cadena1);
                B1 = true;
                k++;
                if(f1.readRecord()){
                    cadena1=f1.getRawRecord();
                    clave1 = f1.get(campo);
                    if (cadena1!=null){
                        B1=false;
                    }
                }
                else
                    cadena1=null;
                
            }
            while((l < part) && (B2 == false)) {
                f.println(cadena2);
                B2 = true;
                l++;
                if(f2.readRecord()){
                    cadena2=f2.getRawRecord();
                    clave2 = f2.get(campo);
                    if (cadena2 != null){
                        B2 = false;
                    }
                }
                else
                    cadena2=null;
            }
        }
        //Escribe la ultima linea de cada archivo
        if (B1 == false){
            f.println(cadena1);
        }
        
        if (B2 == false) {
                f.println(cadena2);
        }
        
        f.flush();
        f1.close();
        f2.close();
        f.close();
    
    }
    
    
    
}
