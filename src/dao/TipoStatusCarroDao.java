/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import model.TipoStatusCarro;
import repositoryLocal.MapTipoStatusCarro;
import sun.dc.pr.PRError;

/**
 *
 * @author Rafan
 */
public class TipoStatusCarroDao {
    public List<TipoStatusCarro> buscarStatusCarros(){
        try {
            List<TipoStatusCarro> listaStatusCarro = new ArrayList<>();
            String sql = "select * from status_carro where ativo = 1";
            SortedMap<String, TipoStatusCarro> treeStatusCarro = new TreeMap<>();
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                TipoStatusCarro tps = 
                        new TipoStatusCarro(rs.getInt("ID"), rs.getString("DESCRICAO").toUpperCase());
            
                listaStatusCarro.add(tps);
                treeStatusCarro.put(tps.getDescricao(), tps);
            }
            MapTipoStatusCarro.setMapTipoModelo(treeStatusCarro);
            return listaStatusCarro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
