/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rafan
 */
public class Cliente {
    private Integer id;
    private String nome;
    private String cpf;
    private String modeloCarro;
    private String placaCarro;

     public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public Cliente(Integer id, String nome, String cpf){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public Cliente(Integer id, String nome, String cpf, String modeloCarro, String placaCarro){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        if(modeloCarro != null)
            this.modeloCarro = modeloCarro;
        else
            this.modeloCarro = "vazio";
        if(placaCarro != null)
            this.placaCarro = placaCarro;
        else
            this.placaCarro = "vazio";
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }
    
    
}
