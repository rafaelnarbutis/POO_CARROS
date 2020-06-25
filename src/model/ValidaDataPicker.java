/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.jfoenix.controls.JFXDatePicker;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.abs.Valida;

/**
 *
 * @author Rafan
 */
public class ValidaDataPicker<E> extends Valida<E>{

    public ValidaDataPicker(E componente, String nome){
        setComponente(componente);
        setNome(nome);
    }
    
    @Override
    public boolean valida() {
        JFXDatePicker dataDatePicker = (JFXDatePicker)getComponente();
        if(dataDatePicker.getValue() == null)
            return false;
        else if(dataDatePicker.getValue().toString().isEmpty())
            return false;
        return !dataDatePicker.getValue().isBefore(LocalDateTime.now().toLocalDate());
    }

    @Override
    public void limpa() {
        ((JFXDatePicker)getComponente()).setValue(null);
    }

    @Override
    public String msgEro() {
        return getNome()+" - data inferior a atual\n";
    }
    
}
