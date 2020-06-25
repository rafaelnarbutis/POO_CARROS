/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.TextField;
import model.abs.Valida;

/**
 *
 * @author Rafan
 */
public class ValidaText<E> extends Valida<E>{
    
     public ValidaText(E componente, int tamanho,String nome){
        setComponente(componente);
        setNome(nome);
        setTamanhoMinimo(tamanho);
    }
    

    @Override
    public boolean valida() {
        TextField tx = (TextField)getComponente();
        String valor = tx.getText();
        if(valor.trim().length() == 0)
            return false;
        if(valor.length() < getTamanhoMinimo())
            return false;
        return true;
    }

    @Override
    public void limpa() {
        TextField tx = (TextField)getComponente();
        tx.clear();
    }

    @Override
    public String msgEro() {
        return getNome()+" - tamanho minimo "+getTamanhoMinimo()+"\n";
    }

}
