/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenamiento_Natural;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vladii
 */
public class ParticionNatural {
    
    public String directorio;
    public String nombreArchivo;
    public int campo;
    public int cont;
    
    

    ParticionNatural(String directorio,String nombreArchivo, int campo,int cont) {
        
        this.directorio=directorio;
        this.nombreArchivo=nombreArchivo;
        this.campo=campo;
        this.cont=cont;
        
        
    }
    
    public boolean particion() {

        //Se utilizara una logica similar a la del metodo de verificar orden
        //por lo que los indices son declarados de la misma manera
        String actual = null;
        String anterior = null;

        //Variable para controlar el indice del archivo al cual se va a escribir.
        int indice = 0;
        //Variable que determina si existe un cambio de secuencia en el ordenamiento
        boolean hayCambioDeSecuencia = false;
        //Declaracion de los objetos asociados a los archivos y del arreglo de archivos
        PrintWriter archivosAux[] = new PrintWriter[2];
        CsvReader Archivo = null;
        
        try {
            
            archivosAux[0] = new PrintWriter(directorio+"\\"+"F1.csv");
            archivosAux[1] = new PrintWriter(directorio+"\\"+"F2.csv");
            Archivo = new CsvReader(directorio+"//"+nombreArchivo);
            
            //Primero, verifica si existen datos en el archivo que se va a leer
            while (Archivo.readRecord()) {
                anterior = actual;
                actual = Archivo.get(campo);
                String linea = Archivo.getRawRecord();
                if (anterior == null) {
                    anterior = actual;
                }
               
                //Cambio de secuencia. Manipulacion del indice del arreglo de archivos
                if ( compararClaves(anterior,actual)==false) {       //si anterior es mayor a actual
                    indice = (indice == 0) ? 1 : 0;
                    //Actualizacion de la variable booleana, esto indica la existencia
                    //de un cambio de secuencia
                    hayCambioDeSecuencia = true;
                }
                archivosAux[indice].println(linea);
            }
            Archivo.close();
            archivosAux[0].close();
            archivosAux[1].close();
        } catch (FileNotFoundException e) {
            System.out.println("Error lectura/escritura");
        } 
        catch (IOException e) {
            System.out.println("Error en la creacion o apertura de los archivos aux");
        }
        //El valor retornado sirve para determinar cuando existe una particion
        return hayCambioDeSecuencia;
    }
    
    
    //  METODO QUE COMPARA LAS CLAVES DEPENDIENDO EL CAMPO
    //  RETORNA TRUE SI CLAVE_1<=CLAVE_2
    
    boolean compararClaves(String clave1, String clave2){
        //  PREPARO LA CONDICION DEPENDIENDO EL CAMPO QUE SEA...
                
        int condicion1=0, condicion2=0;
        if(campo==0){   //  si el campo es numerico
            condicion1=Integer.parseInt(clave1);
            condicion2=Integer.parseInt(clave2);
        }
        else if(campo==3){  // Si el campo es la fecha
            try{
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha1 = formateador.parse(clave1);
                Date fecha2 = formateador.parse(clave2);

                if ( fecha1.before(fecha2)   ||  (fecha1.equals(fecha2))){       //si la fecha1 es menor  o igual a la fecha 2
                   condicion1=0;
                   condicion2=1;
                }  
                else{
                   condicion1=1;
                   condicion2=0;
               }
            }
           catch (ParseException e) {
                System.out.println("Se Produjo un Error!!!  "+e.getMessage());
           }
        }
        else{
            if(clave1.compareTo(clave2)<=0){
                condicion1=0;
                condicion2=1;
            }
            else{
                condicion1=1;
                condicion2=0;
            }
        }
           
        if (condicion1 <= condicion2)
            return true;
        else
            return false;       
    }
    
}
