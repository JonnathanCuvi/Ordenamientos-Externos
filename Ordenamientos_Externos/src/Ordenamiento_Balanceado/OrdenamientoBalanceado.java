/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenamiento_Balanceado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Daniel
 */
public class OrdenamientoBalanceado {
    
    public static void Procesar(int campo, File archivo) throws FileNotFoundException, IOException
    {
        
        MezclaBalanceada.campo = campo;
        String directorio = archivo.getParent();
        String nombreArchivo = archivo.getName();
        
        long contador1,contador2,numRegistros;
        
        String rutaOriginal=directorio + "\\" + nombreArchivo;
        String rutaF1= directorio + "\\" + "Archi_Auxiliar1.csv";
        String rutaF2 = directorio + "\\" + "Archi_Auxiliar2.csv";
        String rutaF3= directorio + "\\" + "Archi_Auxiliar3.csv";
        
        numRegistros = contarRegistros(rutaOriginal);
        
        MezclaBalanceada.particionInicial(rutaOriginal,rutaF2,rutaF3);
        MezclaBalanceada.particionFusion(rutaF2,rutaF3,rutaOriginal,rutaF1);
        
        boolean band= false;
        contador1=contarRegistros(rutaOriginal);
        contador2=contarRegistros(rutaF1);
        
        while(contador1!=0 || contador2!=0){
            if(band){
                MezclaBalanceada.particionFusion(rutaF2,rutaF3,rutaOriginal,rutaF1);
                contador1=contarRegistros(rutaOriginal);
                contador2=contarRegistros(rutaF1);
                if(contador1==0||contador2==0){
                    break;
                }
                band=false;
            }
            else{
                MezclaBalanceada.particionFusion(rutaOriginal,rutaF1,rutaF2,rutaF3);
                band= true;
                contador1=contarRegistros(rutaF2);
                contador2=contarRegistros(rutaF3); 
                if(contador1==0||contador2==0)
                    break;
            }
            
            
        }
        //System.out.println("Contador: " + contador1 + " - contadoGen: " + numRegistros);
        
        if(contador1==numRegistros){
            System.out.println("ARCHIVO ORDENADO 11 ");
            
            long f2 = contarRegistros(rutaF2);
            long org = contarRegistros(rutaOriginal);
            if(f2>org)
            {
                MezclaBalanceada.transferirArchivos(rutaOriginal, rutaF2);
                System.out.println("Caso especial... 11 11");
            }
                
            
            System.out.println("F2: " + f2 + "  -  Original: " + org);
        }
        else{
            
            long f2 = contarRegistros(rutaF2);
            long org = contarRegistros(rutaOriginal);
            if(org==0 && f2>0)
            {
                MezclaBalanceada.transferirArchivos(rutaOriginal, rutaF2);
                System.out.println("Caso especial...");
            }
                
            
            System.out.println("F2: " + f2 + "  -  Original: " + org);
            System.out.println("ARCHIVO ORDENADO 22");
        }
        
        File Fil1 = new File(rutaF1);File Fil2 = new File(rutaF2);File Fil3 = new File(rutaF3);
        if(Fil1.exists()) Fil1.delete();
        if(Fil2.exists()) Fil2.delete();
        if(Fil3.exists()) Fil3.delete();
    }
    
    public static long contarRegistros(String ruta) throws IOException{
            long count=0;
            FileReader fr = new FileReader(ruta);
            BufferedReader bf = new BufferedReader(fr);
            boolean sCad;
            while (sCad = bf.readLine()!=null)
                count++;
            return count;

    }
}
