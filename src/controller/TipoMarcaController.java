/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoMarcaDao;
import dao.TipoTanqueDAO;
import java.util.List;
import model.TipoMarca;
import model.TipoTanque;
import repositoryLocal.MapTipoMarca;
import repositoryLocal.MapTipoTanque;

/**
 *
 * @author Rafan
 */
public class TipoMarcaController {
     public Integer buscarIdTipoMarca(String nome) {
        if (MapTipoMarca.getMapTipoMarca().size() == 0) 
            new TipoMarcaDao().getTiposDeMarcas();
        int id = 0;
        if(MapTipoMarca.getMapTipoMarca().get(nome) != null)
            id = MapTipoMarca.getMapTipoMarca().get(nome).getId();
        return id;
    }

    public List<TipoMarca> listarTipoMarcas() {
        return new TipoMarcaDao().getTiposDeMarcas();
    }
    
    public boolean inserirMarcar(String nome){
        return new TipoMarcaDao().inserirMarca(nome);
    }
}
