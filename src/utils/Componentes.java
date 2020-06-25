/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import model.abs.Valida;

/**
 *
 * @author Rafan
 */
public class Componentes {

    public boolean verificaErros(List<Valida> lista) {
        return lista.stream().filter(c -> c.valida() != true).count() == 0;
    }
    
    public void erroTextFiled(TextField text, String msg){
         text.setTooltip(new Tooltip(msg));
         text.setText("");
         text.setStyle("-fx-prompt-text-fill: red;");
    }

}
