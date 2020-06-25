/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoCarroDAO;
import dao.TipoTanqueDAO;
import java.util.List;
import model.TipoCarro;
import model.TipoTanque;
import repositoryLocal.MapTipoCarro;
import repositoryLocal.MapTipoTanque;

/**
 *
 * @author Rafan
 */
public class TipoTanqueController {
    public Integer buscarIdTipoTanque(String nome) {
        if (MapTipoTanque.getMapTipoTanque().size() == 0) 
            new TipoTanqueDAO().getTiposDeTanques();
        
        return MapTipoTanque.getMapTipoTanque().get(nome).getId();
    }

    public List<TipoTanque> listarTipoTaques() {
        return new TipoTanqueDAO().getTiposDeTanques();
    }
}
