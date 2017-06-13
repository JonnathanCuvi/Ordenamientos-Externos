package Ordenamiento_Polifase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormatoArchivo implements Comparable<FormatoArchivo>{

    private int campo1;
    private String campo2;
    private Boolean campo3;
    private String campo4;
    public static int CampoDeOrdenacion = 1;
    
    public FormatoArchivo(){}
    
    public FormatoArchivo(String[] campos) {
        this.campo1 = Integer.parseInt(campos[0]);
        this.campo2 = campos[1];
        this.campo3 = Boolean.parseBoolean(campos[2]);
        this.campo4 = campos[3];
    }
    
    public String[] getCsv(){
        String[] formatoCsv = {String.valueOf(this.campo1),campo2,String.valueOf(this.campo3),campo4};
        return formatoCsv;
    }
    
    @Override
    public int compareTo(FormatoArchivo otroArchivo) {

        switch(FormatoArchivo.CampoDeOrdenacion){
            case 1:
                if(this.campo1<otroArchivo.campo1) return -1;
                if(this.campo1>otroArchivo.campo1) return 1;    break;
            case 2:
                return this.campo2.compareTo(otroArchivo.campo2);
            case 3:
                if(!this.campo3 && otroArchivo.campo3) return -1;
                if(this.campo3 && otroArchivo.campo3) return 1;     break;
            case 4:
                // ESTA PARTE TRABAJA CON EXCEPCIONES
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date este = formateador.parse(this.campo4);
                    Date elOtro = formateador.parse(otroArchivo.campo4);
                    if(este.before(elOtro)) return -1;
                    if(este.after(elOtro)) return 1;
                } catch (ParseException ex) {
                    Logger.getLogger(FormatoArchivo.class.getName()).log(Level.SEVERE, null, ex);
                }   break;   
        }   
        return 0;
    }

    void setCampos(String[] campos) {
        this.campo1 = Integer.parseInt(campos[0]);
        this.campo2 = campos[1];
        this.campo3 = Boolean.parseBoolean(campos[2]);
        this.campo4 = campos[3];
    }
    
}
