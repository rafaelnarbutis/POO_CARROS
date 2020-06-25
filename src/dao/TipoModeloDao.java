/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.TipoMarca;
import model.TipoModelo;
import repositoryLocal.MapTipoMarca;
import repositoryLocal.MapTipoModelo;

/**
 *
 * @author Rafan
 */
public class TipoModeloDao {
      public List<TipoModelo> getTiposDeModelos(){
        try {
            List<TipoModelo> listaDeCarros = new ArrayList<>();
            Map<String, TipoModelo> mapTipoModelo = new TreeMap<>();
            String sql = "SELECT * FROM modelo";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                TipoModelo tipoModelo = new TipoModelo(rs.getInt("ID"), rs.getString("NOME"));
                listaDeCarros.add(tipoModelo);
                mapTipoModelo.put(tipoModelo.getDescricao(), tipoModelo);
            }
            MapTipoModelo.setMapTipoModelo(mapTipoModelo);
            con.close();
            return listaDeCarros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserirModelo(String nome){
        try {
            String sql = "insert into modelo(nome) values(?)";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, nome);
            prep.execute();
            return true;
        } catch (Exception e){
            if(e instanceof MySQLIntegrityConstraintViolationException)
                getTiposDeModelos();
            e.printStackTrace();
            return false;
        }
    }
}
