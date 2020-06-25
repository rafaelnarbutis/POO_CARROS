/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlugaDao;
import dao.CarroDao;
import dao.VendaDao;

/**
 *
 * @author Rafan
 */
public class CaixaController {

    public Double buscarSaldoAlugueis() {
        return new AlugaDao().saldoAlugueis();
    }
    
    public Double buscarSaldoCarrosVendidos(){
        return new VendaDao().buscarValorCarrosVendidos();
    }
    
    public Double buscarSaldoCarrosComprados(){
        return new CarroDao().buscarValorCarrosComprados();
    }
    
    public Long buscarQtdCarrosAlugados(){
        return new AlugaDao().buscarQtdCarrosAlugados();
    }
    
    public Long buscarQtdCarrosVendidos(){
        return new VendaDao().buscarQtdCarrosVendidos();
    }
    
}
