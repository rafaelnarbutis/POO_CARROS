/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoStatusCarroDao;
import java.util.List;
import repositoryLocal.MapTipoStatusCarro;

/**
 *
 * @author Rafan
 */
public class TipoStatusCarro {
   public Integer buscarTipoStatusCarro(String nome){
       if(MapTipoStatusCarro.getMapTipoModelo().size() == 0)
           new TipoStatusCarroDao().buscarStatusCarros();
       return MapTipoStatusCarro.getMapTipoModelo().get(nome.toUpperCase()).getId();
   } 
}
