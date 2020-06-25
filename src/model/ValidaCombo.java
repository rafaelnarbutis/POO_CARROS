/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.ComboBox;
import model.abs.Valida;

/**
 *
 * @author Rafan
 */
public class ValidaCombo<E> extends Valida<E>{
    
    public ValidaCombo(E componente, String nome){
        setComponente(componente);
        setNome(nome);
    }
    
    @Override
    public boolean valida() {
       return !((ComboBox)getComponente()).getSelectionModel().isEmpty();
    }

    @Override
    public void limpa() {
       ((ComboBox)getComponente()).getSelectionModel().clearSelection();;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String msgEro() {
        return getNome()+"\n";
    }
}
