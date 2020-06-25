/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarroDao;
import dao.VendaDao;
import model.Carro;
import model.Cliente;
import objView.CarroView;

/**
 *
 * @author Rafan
 */
public class VendaController {

    public boolean venderCarro(Carro carro, Cliente cli) {
        try {
            carro.setId_StatusCarro(new TipoStatusCarro().buscarTipoStatusCarro("RETIRAR"));
            return new VendaDao().inserirVenda(carro, cli);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirVenda(Integer id) {
        return new VendaDao().excluirVenda(id, null);
    }

}
