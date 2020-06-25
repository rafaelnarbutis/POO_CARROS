/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoCarroDAO;
import java.util.List;
import model.TipoCarro;
import repositoryLocal.MapTipoCarro;

/**
 *
 * @author Rafan
 */
public class TipocarroController {
    
    public Integer buscarIdTipoCarro(String nome){
         if(MapTipoCarro.getMapTipocarros().size() ==  0)
             new TipoCarroDAO().getTiposDeCarros();
       return MapTipoCarro.getMapTipocarros().get(nome).getId();
    }
    
    public List<TipoCarro> listarTipoCarros(){
        return new TipoCarroDAO().getTiposDeCarros();
    }
}
