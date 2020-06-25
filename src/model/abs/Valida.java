/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.abs;

import model.interfaces.AcaoComponente;

/**
 *
 * @author Rafan
 */
public abstract class Valida<E> implements AcaoComponente{
    private E componente;
    private String nome;
    private int tamanhoMinimo;

    public E getComponente() {
        return componente;
    }

    public void setComponente(E componente) {
        this.componente = componente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanhoMinimo() {
        return tamanhoMinimo;
    }

    public void setTamanhoMinimo(int tamanhoMinimo) {
        this.tamanhoMinimo = tamanhoMinimo;
    }

    @Override
    public String toString() {
        if(tamanhoMinimo == 0)
            return nome+"\n";
        else
             return nome+" tamanho minimo "+tamanhoMinimo+"\n";
    }
    
    
    
}
