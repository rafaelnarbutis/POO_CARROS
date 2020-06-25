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
import repositoryLocal.MapTipoCarro;

/**
 *
 * @author Rafan
 */
public class TipoCarroDAO {
    public List<TipoCarro> getTiposDeCarros(){
        try {
            List<TipoCarro> listaDeCarros = new ArrayList<>();
            Map<String, TipoCarro> mapTipoCarro = new HashMap<>();
            String sql = "SELECT * FROM tipo_carro";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                TipoCarro tipoCarro = new TipoCarro(rs.getInt("ID"), rs.getString("DESCRICAO"));
                listaDeCarros.add(tipoCarro);
                mapTipoCarro.put(tipoCarro.getDescricao(), tipoCarro);
            }
            MapTipoCarro.setMapTipocarros(mapTipoCarro);
            con.close();
            return listaDeCarros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
