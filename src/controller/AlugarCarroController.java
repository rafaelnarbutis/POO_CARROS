/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlugaDao;
import model.CarroAluguel;
import model.Cliente;

/**
 *
 * @author Rafan
 */
public class AlugarCarroController {
       public boolean AlugarCarro(CarroAluguel carro, Cliente cli) {
        try {
            carro.setId_Status(new TipoStatusCarro().buscarTipoStatusCarro("RETIRAR"));
            return new AlugaDao().inserirAluguel(carro, cli);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
