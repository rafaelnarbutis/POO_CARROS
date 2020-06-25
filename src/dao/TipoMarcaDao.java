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
import model.TipoMarca;
import model.TipoTanque;
import repositoryLocal.MapTipoMarca;
import repositoryLocal.MapTipoTanque;

/**
 *
 * @author Rafan
 */
public class TipoMarcaDao {
  public List<TipoMarca> getTiposDeMarcas(){
        try {
            List<TipoMarca> listaDeCarros = new ArrayList<>();
            Map<String, TipoMarca> mapTipoMarca = new HashMap<>();
            String sql = "SELECT * FROM marca";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                TipoMarca tipoMarca = new TipoMarca(rs.getInt("ID"), rs.getString("NOME"));
                listaDeCarros.add(tipoMarca);
                mapTipoMarca.put(tipoMarca.getDescricao(), tipoMarca);
            }
            MapTipoMarca.setMapTipoMarca(mapTipoMarca);
            con.close();
            return listaDeCarros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserirMarca(String nome){
        try {
            String sql = "insert into marca(nome) values(?)";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, nome);
            prep.execute();
            return true;
        } catch (Exception e){
            if(e instanceof MySQLIntegrityConstraintViolationException)
                getTiposDeMarcas();
            e.printStackTrace();
            return false;
        }
    }
}
