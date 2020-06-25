/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoModeloDao;
import dao.TipoTanqueDAO;
import java.util.List;
import model.TipoModelo;
import model.TipoTanque;
import repositoryLocal.MapTipoModelo;
import repositoryLocal.MapTipoTanque;

/**
 *
 * @author Rafan
 */
public class TipoModeloController {
      public Integer buscarIdTipoModelo(String nome) {
        if (MapTipoModelo.getMapTipoModelo().size() == 0) 
            new TipoModeloDao().getTiposDeModelos();
        int id = 0;
        if(MapTipoModelo.getMapTipoModelo().get(nome) != null)
            id = MapTipoModelo.getMapTipoModelo().get(nome).getId();
        return id;
    }

    public List<TipoModelo> listarTipoModelo() {
        return new TipoModeloDao().getTiposDeModelos();
    }
    
    public boolean inserirModelo(String nome){
        return new TipoModeloDao().inserirModelo(nome);
    }
}
