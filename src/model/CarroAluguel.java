/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Rafan
 */
public class CarroAluguel {
    private Integer id;
    private Integer id_Status;
    private Double diaria;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDiaria() {
        return diaria;
    }

    public void setDiaria(Double diaria) {
        this.diaria = diaria;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getId_Status() {
        return id_Status;
    }

    public void setId_Status(Integer id_Status) {
        this.id_Status = id_Status;
    }
    
    
    
    
}
