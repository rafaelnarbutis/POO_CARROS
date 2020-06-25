/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TipoStatusCarro;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Carro;
import model.Cliente;

/**
 *
 * @author Rafan
 */
public class VendaDao {
          public boolean excluirVenda(Integer id, Connection con){
        try {
            String sql = "update venda set ativo = 0 where id_carro = ? and ativo = 1";
            if(con == null)
                con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, id);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
          
        public boolean inserirVenda(Carro carro, Cliente cliente) throws SQLException{
            Connection con = null;
        try {
            String sql = "insert into venda (id_carro, id_cli, valor_venda) values (?,?,?)";
            con = DB.connect();
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, carro.getId());
            prep.setInt(2, cliente.getId());
            prep.setDouble(3, carro.getValor());
            prep.execute();
            if(!new CarroDao().desativarCarro(carro.getId(),carro.getId_StatusCarro() ,con)){
                con.rollback();
                con.close();
                return false;
            }
            if(!new RetirarDao().inserirRetirada(carro.getId(), cliente.getId(), new TipoStatusCarro().buscarTipoStatusCarro("VENDIDO"), con)){
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            con.close();
            return true;
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            return false;
        }
    }
        
    public Double buscarValorCarrosVendidos(){
        try {
            String sql = "select sum(valor_venda) as valor from venda where ativo = 1";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            ResultSet rs = prep.executeQuery();
            Double valor = 0.0;
            while(rs.next()){
                valor += rs.getDouble("VALOR");
            }
            con.close();
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long buscarQtdCarrosVendidos(){
        try {
            String sql = "select count(id) as qtd from venda where ativo = 1";
            Connection con = DB.connect();
            ResultSet rs = con.prepareStatement(sql.toUpperCase()).executeQuery();
            Long qtd = 0L;
            while(rs.next()){
                qtd += rs.getLong("QTD");
            }
            con.close();
            return qtd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
