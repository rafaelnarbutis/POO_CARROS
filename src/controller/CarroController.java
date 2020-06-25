/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarroDao;
import dao.TipoMarcaDao;
import dao.TipoModeloDao;
import java.sql.SQLException;
import java.util.List;
import model.Carro;
import model.Cliente;
import objView.CarroView;
import repositoryLocal.MapTipoCarro;

/**
 *
 * @author Rafan
 */
public class CarroController {
    public boolean inserircarro(Carro carro) throws SQLException{
        return new CarroDao().inserirCarro(popularChavesEstrangeiras(carro));
    }
    
    public boolean atualizarCarro(Carro carro){
        return new CarroDao().atualizarCarro(popularChavesEstrangeiras(carro));
    }
    
    public boolean excluirCarro(Integer id){
        return new CarroDao().excluirCarro(id);
    }
    
    public boolean desativarCarro(Integer id, Integer id_StatusCarro){
        return new CarroDao().desativarCarro(id, id_StatusCarro, null);
    }
    
    public List<CarroView> buscarCarros(Carro carro){
        return  new CarroDao().buscarCarros(carro);
    }
    
    public boolean devolverCarro(Carro carro, String tipoDevolucao){
        carro.setId_StatusCarro(new TipoStatusCarro().buscarTipoStatusCarro("DISPONIVEL"));
        carro.setId_tanque(new TipoTanqueController().buscarIdTipoTanque(carro.getTanque()));
        return new CarroDao().devolverCarro(carro, tipoDevolucao);
    }
    
    private Carro popularChavesEstrangeiras(Carro carro){
        carro.setId_tipo(new TipocarroController().buscarIdTipoCarro(carro.getTipo()));
        carro.setId_tanque(new TipoTanqueController().buscarIdTipoTanque(carro.getTanque()));
        carro.setId_marca(new TipoMarcaController().buscarIdTipoMarca(carro.getMarca()));
        carro.setId_modelo(new TipoModeloController().buscarIdTipoModelo(carro.getModelo()));
        return carro;
    }
}
