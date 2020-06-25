/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Rafan
 */
public class CompraDao {

    public boolean inserirCompra(Integer idCarro, Connection con) {
        try {
            String sql = "insert into compra (id_carro) values(?)";
            if(con == null)
                con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, idCarro);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public boolean excluirCompra(Integer idCaro, Connection con) {
        try {
            String sql = "update compra set ativo = 0 where id_carro = ? and ativo = 1";
            if(con == null)    
                con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, idCaro);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
