/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TipoMarcaController;
import controller.TipoModeloController;
import dao.TipoMarcaDao;
import dao.TipoModeloDao;
import java.time.LocalDate;

/**
 *
 * @author Rafan
 */
public class Carro {

    private Integer id;
    private Integer id_marca;
    private Integer id_tipo;
    private Integer id_tanque;
    private Integer id_modelo;
    private Integer id_StatusCarro;
    private Integer ano;
    private String marca;
    private String modelo;
    private String tipo;
    private String placa;
    private String tanque;
    private String status;
    private Double km;
    private Double valor;
    private LocalDate dataAluguelInicial;
    private LocalDate dataAluguelFinal;
    private Double valorDiaria;
    
    public Carro(){
    
    }
    
    public Carro(String marca, String modelo, String placa){
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }
    
    public Carro(Integer id, Integer ano, String marca, String modelo, String tipo,
             String placa, String tanque, Double km, Double valor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tanque = tanque;
        this.tipo = tipo;
        this.km = km;
        this.valor = valor;
        this.ano = ano;
    }
    
    public Carro(Integer ano, String marca, String modelo, String tipo,
             String placa, String tanque, Double km, Double valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tanque = tanque;
        this.tipo = tipo;
        this.km = km;
        this.valor = valor;
        this.ano = ano;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
         if(id_marca == 0){
            new TipoMarcaController().inserirMarcar(this.getMarca());
            new TipoMarcaController().listarTipoMarcas();
            id_marca = new TipoMarcaController().buscarIdTipoMarca(this.getMarca());
        }
        this.id_marca = id_marca;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Integer getId_tanque() {
        return id_tanque;
    }

    public void setId_tanque(Integer id_tanque) {
        this.id_tanque = id_tanque;
    }

    public Integer getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(Integer id_modelo) {
         if(id_modelo == 0){
            new TipoModeloController().inserirModelo(this.getModelo());
            new TipoModeloController().listarTipoModelo();
            id_modelo = new TipoModeloController().buscarIdTipoModelo(this.getModelo());
        }
        this.id_modelo = id_modelo;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTanque() {
        return tanque;
    }

    public void setTanque(String tanque) {
        this.tanque = tanque;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getId_StatusCarro() {
        return id_StatusCarro;
    }

    public void setId_StatusCarro(Integer id_StatusCarro) {
        this.id_StatusCarro = id_StatusCarro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataAluguelInicial() {
        return dataAluguelInicial;
    }

    public void setDataAluguelInicial(LocalDate dataAluguelInicial) {
        this.dataAluguelInicial = dataAluguelInicial;
    }

    public LocalDate getDataAluguelFinal() {
        return dataAluguelFinal;
    }

    public void setDataAluguelFinal(LocalDate dataAluguelFinal) {
        this.dataAluguelFinal = dataAluguelFinal;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        if(valorDiaria == 0.0)
            valorDiaria = null;
        this.valorDiaria = valorDiaria;
    }
    
}
