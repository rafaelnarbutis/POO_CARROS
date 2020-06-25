/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Rafan
 */
public class Valor {

    public String formataValor(Double valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    }
    
    public String formatarParaKm(Double valor){
        String km = this.formataValor(valor);
          km = km.replace("R$ ", "");
                 if(km.contains(",")){
                    int index = km.indexOf(",");
                    km = km.substring(0,index);
                 }
                 return km;
    }

    public Double limpaValor(String valor) {
        valor = valor.replace("R$ ", "");
        valor = valor.replace(".", "");
        if(valor.contains(","))
            valor = valor.replace(",", ".");
        else
            valor = valor.concat(".00");
        return Double.parseDouble(valor);
    }
}
