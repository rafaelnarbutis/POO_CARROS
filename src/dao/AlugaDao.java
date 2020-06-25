/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.TipoStatusCarro;
import db.DB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Carro;
import model.CarroAluguel;
import model.Cliente;

/**
 *
 * @author Rafan
 */
public class AlugaDao {
    public boolean inserirAluguel(CarroAluguel carro, Cliente cliente){
        try {
            String sql = "insert into aluguel (id_carro, id_cli, data_inicial, data_final, diaria)"
                    + " values (?,?,?,?,?)";
            Connection con = DB.connect();
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, carro.getId());
            prep.setInt(2, cliente.getId());
            prep.setDate(3, Date.valueOf(carro.getDataInicial()));
            prep.setDate(4, Date.valueOf(carro.getDataFinal()));
            prep.setDouble(5, carro.getDiaria());
            prep.execute();
            if(!new CarroDao().desativarCarro(carro.getId(),carro.getId_Status() ,con)){
                con.rollback();
                con.close();
                return false;
            }
            if(!new RetirarDao().inserirRetirada(carro.getId(), cliente.getId(), new TipoStatusCarro().buscarTipoStatusCarro("ALUGADO"),con)){
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public boolean finalizarAluguel(Carro carro, Connection con){
        try {
            String sql = "update aluguel set status_aluguel = 0, data_final = ? where id_carro = ? and status_aluguel = 1";
            if(con == null)
                con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setDate(1, Date.valueOf(carro.getDataAluguelFinal()));
            prep.setInt(2, carro.getId());
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Double saldoAlugueis(){
        try {
            String sql = "select sum((datediff(data_final,data_inicial) * diaria))"
                + "  as diaria from aluguel where ativo = 1";
          Connection con = DB.connect();
          ResultSet rs = con.prepareStatement(sql.toUpperCase()).executeQuery();
          Double valor = 0.0;
          while(rs.next()){
              valor += rs.getDouble("DIARIA");
          }
          con.close();
          return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long buscarQtdCarrosAlugados(){
        try {
            String sql = "select count(id) as qtd from aluguel where ativo = 1;";
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
