/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenamiento_Natural;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author vladii
 */
public class OrdenarNatural {
    
    public String directorio;
    public String nombreArchivo;
    public int campo;
    
    int cont = 0;

    OrdenarNatural(File archivo, int campo) throws IOException {
        
        directorio = archivo.getParent();
        nombreArchivo = archivo.getName();
        this.campo = campo; 
        
        ordenar();
    }
    
    
    public void ordenar() throws IOException {
        
        ParticionNatural particionNatural =new ParticionNatural(directorio, nombreArchivo, campo, cont);
        FuncionarNatural funcionarNatural = new FuncionarNatural(directorio, nombreArchivo, campo, cont);
        
        while (particionNatural.particion()) {
            funcionarNatural.fusion();
        }
    }
    
    
    
    
}
