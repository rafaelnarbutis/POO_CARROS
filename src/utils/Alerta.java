/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.jfoenix.controls.JFXComboBox;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.ValidaCombo;
import model.ValidaText;
import model.abs.Valida;

/**
 *
 * @author Rafan
 */
public class Alerta {

    public static Alert criarAlerta(String titulo, String contexto, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contexto);
        return alerta;
    }

    public static Alert criarAlertaErroCampo(String titulo, List<Valida> lista, AlertType tipo) {
        String msg = "Verifique os campos:\n";
        
        if(lista != null)
            for(Valida valida : lista)
                msg = msg.concat(valida.msgEro());
        
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(msg);
        return alerta;
    }

}
