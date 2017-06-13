package Ordenamiento_Balanceado;

import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class MezclaBalanceada {
    
    public static int campo;
    
    public static void particionInicial(String rutaOriginal,String rutaF1,String rutaF2) throws FileNotFoundException, IOException{
        CsvReader F = new CsvReader(rutaOriginal);
        PrintWriter F1 = new PrintWriter(rutaF1);
        PrintWriter F2 = new PrintWriter(rutaF2);
        String R,Aux;
        boolean band=true;
        if (F.readRecord())
            band = false;
        F1.println(F.getRawRecord());
        R = F.get(campo);
        Aux=R;
        band=true;
        while(F.readRecord()){
            R=F.get(campo);
            
            if(compararCampos(Aux,R)){
            //if(Integer.parseInt(R)>=Integer.parseInt(Aux)){
                Aux=R;
                if(band==true)
                    F1.println(F.getRawRecord());
                else
                    F2.println(F.getRawRecord());
            }
            else{
                Aux=R;
                if(band==true){
                    F2.println(F.getRawRecord());
                    band=false;
                }
                else{
                    F1.println(F.getRawRecord());
                    band=true;
                }
            }
        }
        F.close();
        F1.close();
        F2.close();   
    }
  
    public static void transferirArchivos(String srcDestino,String srcOrigen) throws IOException{
        CsvReader FOrigen = new CsvReader(srcOrigen);
        PrintWriter FDestino = new PrintWriter(srcDestino);
        String R2=null;
        while(FOrigen.readRecord()){
            FDestino.println(FOrigen.getRawRecord());
        }
        FDestino.close();
        FOrigen.close();
    }
    
    
    
    public static void particionFusion(String rutaF1,String rutaF3,String rutaOriginal,String rutaF2) throws FileNotFoundException, IOException{
        String R1="",R2="",R="";
        String Aux= "";
        CsvReader FA = new CsvReader(rutaF1);
        CsvReader FB = new CsvReader(rutaF3);
        PrintWriter FC = new PrintWriter(rutaOriginal);
        PrintWriter FD = new PrintWriter(rutaF2);
        boolean band,dele1,dele2;
        band=true;
        FA.readRecord();
        FB.readRecord();
        R1=FA.get(campo);
        R2=FB.get(campo);
        if(R1.equals("")||R2.equals(""))
            return;
        
        //if(Integer.parseInt(R1)<=Integer.parseInt(R2))//Condicional campos--------------------------------------
        if(compararCampos(R1, R2))
            Aux=R1;
        else
            Aux=R2;
        
        dele1=dele2=false;
        while((0!=OrdenamientoBalanceado.contarRegistros(rutaF1)||dele1!=true)&&(0!=OrdenamientoBalanceado.contarRegistros(rutaF3)||dele2!=true)){
            if(dele1){
                FA.readRecord();
                R1=FA.get(campo);
                dele1=false;
                if(R1.equals("")){
                    dele1=true;
                    break;
                }
            }
            if(dele2){
                FB.readRecord();
                R2=FB.get(campo);
                dele2=false;
                if(R2.equals("")){
                    dele2=true;
                    break;
                }
            }
            //if(Integer.parseInt(R1)<Integer.parseInt(R2)){
            if(compararCampos(R1, R2)){
                //if(Integer.parseInt(R1)>=Integer.parseInt(Aux)){//Condicional campos--------------------------------
                if(compararCampos(Aux, R1)){
                    Aux=R1;
                    if(band)
                        FC.println(FA.getRawRecord());
                    else
                        FD.println(FA.getRawRecord());
                    dele1=true;
                }
                //else if(Integer.parseInt(R2)>=Integer.parseInt(Aux)){//Condicional campos-----------------------------------------
                else if(compararCampos(Aux, R2)){
                    Aux=R2;
                    if(band)
                        FC.println(FB.getRawRecord());
                    else
                        FD.println(FB.getRawRecord());
                    dele2=true;
                }
                else{
                    Aux=R1;
                    if(band){
                        FD.println(FA.getRawRecord());
                        band=false;
                    }
                    else{
                        FC.println(FA.getRawRecord());
                        band=true;
                    }
                    dele1=true;
                }
            }
            else{
                //if(Integer.parseInt(R2)>=Integer.parseInt(Aux)){//Condicional campos-----------------------------------------
                if (compararCampos(Aux, R2)){
                    Aux=R2;
                    if(band)
                        FC.println(FB.getRawRecord());
                    else
                        FD.println(FB.getRawRecord());               
                    dele2=true;
                }
                //else if(Integer.parseInt(R1)>=Integer.parseInt(Aux)){//Condicional campos-----------------------------------------
                else if(compararCampos(Aux, R1)){
                    Aux=R1;                  
                    if(band)
                        FC.println(FA.getRawRecord());
                    else
                        FD.println(FA.getRawRecord());
                    dele1=true;
                }
                else{ 
                    Aux=R2;
                    if(band){
                        FD.println(FB.getRawRecord());
                        band=false;
                    }
                    else{
                        FC.println(FB.getRawRecord());
                        band=true;
                    }
                    dele2=true;
                }
            }
        }
        if(dele1){
            //if(Integer.parseInt(R2)>=Integer.parseInt(Aux)){//Condicional campos-----------------------------------------
            if(compararCampos(Aux, R2)){
                Aux=R2;
                if(band)
                    FC.println(FB.getRawRecord());
                else
                    FD.println(FB.getRawRecord());
            }
            else{
                
                Aux=R2;
                if(band){
                    FD.println(FB.getRawRecord());
                    band=false;
                }
                else{
                    FC.println(FB.getRawRecord());
                    band=true;
                }
            }
            while(FB.readRecord()){
                R2=FB.get(campo);
                //if(Integer.parseInt(R2)>=Integer.parseInt(R2)){ AUX//Condicional campos-----------------------------------------
                if (compararCampos(Aux, R2)){//---------------------------------------------------------XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                    Aux=R2;
                    if(band)
                        FC.println(FB.getRawRecord());
                    else
                        FD.println(FB.getRawRecord());
                }
                else{
                    Aux=R2;                  
                    if(band){
                        FD.println(FB.getRawRecord());
                        band=false;
                    }
                    else{
                        FC.println(FB.getRawRecord());
                        band=true;
                    } 
                }
            }
        }
        if(dele2){
            //if(Integer.parseInt(R1)>=Integer.parseInt(Aux)){//Condicional campos-----------------------------------------
            if(compararCampos(Aux,R1)){
                Aux=R1;
                if(band){
                    FC.println(FA.getRawRecord());
                }
                else{
                    FD.println(FA.getRawRecord());
                }
            }
        
            else{
                Aux=R1;
                if(band){
                    FD.println(FA.getRawRecord());
                    band=false;
                }
                else{
                    FC.println(FA.getRawRecord());
                    band=true;
                }
            }
            while(FA.readRecord()){
                R1=FA.get(campo);
                //if(Integer.parseInt(R1)>=Integer.parseInt(Aux)){//Condicional campos-----------------------------------------
                if(compararCampos(Aux, R1)){
                    Aux = R1;
                    if(band)
                        FC.println(FA.getRawRecord());
                    else
                        FD.println(FA.getRawRecord());
                }
                else{
                    Aux=R1;
                    if(band){
                        FD.println(FA.getRawRecord());
                        band=false;
                    }
                    else{
                        FC.println(FA.getRawRecord());
                        band=true;
                    }
                }
            }
        }
        FA.close();
        FB.close();
        FC.close(); 
        FD.close();
    }
    
    static boolean compararCampos(String clave1, String clave2){
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
        return condicion1 <= condicion2;       
    }
}