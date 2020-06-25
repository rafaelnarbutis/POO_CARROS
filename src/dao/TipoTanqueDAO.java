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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.TipoCarro;
import model.TipoTanque;
import repositoryLocal.MapTipoTanque;

/**
 *
 * @author Rafan
 */
public class TipoTanqueDAO {
       public List<TipoTanque> getTiposDeTanques(){
        try {
            List<TipoTanque> listaDeCarros = new ArrayList<>();
            Map<String, TipoTanque> mapTipoTanque = new HashMap<>();
            String sql = "SELECT * FROM tanque";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                TipoTanque tipoTanque = new TipoTanque(rs.getInt("ID"), rs.getString("DESCRICAO"));
                listaDeCarros.add(tipoTanque);
                mapTipoTanque.put(tipoTanque.getDescricao(), tipoTanque);
            }
            MapTipoTanque.setMapTipoTanque(mapTipoTanque);
            con.close();
            return listaDeCarros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
