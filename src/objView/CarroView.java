/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objView;

import model.Carro;

/**
 *
 * @author Rafan
 */
public class CarroView{
    private Integer id;
    private Integer ano;
    private String marca;
    private String modelo;
    private String tipo;
    private String tanque;
    private String km;
    private String valor;
    private String placa;
    private String disponivel; 
    private String dataAluguelI;
    private String dataAluguelF;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTanque() {
        return tanque;
    }

    public void setTanque(String tanque) {
        this.tanque = tanque;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getDataAluguelI() {
        return dataAluguelI;
    }

    public void setDataAluguelI(String dataAluguelI) {
        if(dataAluguelI == null)
            dataAluguelI = "vazio";
        this.dataAluguelI = dataAluguelI;
    }

    public String getDataAluguelF() {
        return dataAluguelF;
    }

    public void setDataAluguelF(String dataAluguelF) {
        if(dataAluguelF == null)
            dataAluguelF = "vazio";
        this.dataAluguelF = dataAluguelF;
    }
 
    
}
