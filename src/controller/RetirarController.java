/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RetirarDao;
import java.util.Map;
import java.util.SortedMap;
import model.Carro;
import model.Cliente;
import model.Retirada;

/**
 *
 * @author Rafan
 */
public class RetirarController {

    public boolean inserirRetirada(Integer id_carro, Integer id_cliente, Integer id_status_compra) {
        return new RetirarDao().inserirRetirada(id_carro, id_cliente, id_status_compra, null);
    }
    
    public SortedMap<String, Retirada>listarRetirada(Carro carro, Cliente cliente){
      return new RetirarDao().listarRetiradasDisponiveis(carro, cliente);
    }
    
    public boolean retirarCarro(Integer id){
        return new RetirarDao().retirarCarro(id);
    }
}
