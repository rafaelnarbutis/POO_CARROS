/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import model.Carro;
import model.Cliente;
import model.Retirada;

/**
 *
 * @author Rafan
 */
public class RetirarDao {

    public boolean inserirRetirada(Integer id_carro, Integer id_cliente, Integer id_StatusCompra, Connection con) {
        try {
            String sql = "insert into retirada (id_carro, id_cliente, id_tipo_compra) values (?,?,?)";
            if (con == null) {
                con = DB.connect();
            }
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, id_carro);
            prep.setInt(2, id_cliente);
            prep.setInt(3, id_StatusCompra);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SortedMap<String, Retirada> listarRetiradasDisponiveis(Carro carro, Cliente cliente) {
        try {
            String sql = "SELECT carros.placa, carros.id as id_carro, sc.descricao as status,cli.cpf, r.id, r.disponivel, r.id"
                    + ",a.data_inicial as dataInicial, a.data_final as dataFinal, a.diaria"
                    + " FROM retirada r inner join clientes cli on cli.id = r.id_cliente"
                    + " inner join carros on carros.id = r.id_carro"
                    + " inner join status_carro sc on sc.id = carros.id_disponivel"
                    + " left join aluguel a on (a.id_carro = r.id_carro and a.status_aluguel = 1)"
                    + " where carros.placa like ? and cli.cpf like ? and r.ativo = 1";

            SortedMap<String, Retirada> listaDeRetiradas = new TreeMap<>();

            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, "%" + carro.getPlaca() + "%");
            prep.setString(2, "%" + cliente.getCpf() + "%");

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                Retirada retirada = new Retirada();
                retirada.setId(rs.getInt("ID"));
                retirada.setStatus(rs.getBoolean("DISPONIVEL"));
                Carro carrroN = new Carro();
                carrroN.setPlaca(rs.getString("PLACA"));
                carrroN.setId(rs.getInt("ID_CARRO"));
                carrroN.setStatus(rs.getString("STATUS"));
                Date dataInicial = rs.getDate("DATAINICIAL");
                LocalDate localDateI = null;
                if(dataInicial != null)
                 localDateI = dataInicial.toLocalDate();
                carrroN.setDataAluguelInicial(localDateI);
                Date dataFinal = rs.getDate("DATAFINAL");
                LocalDate localDateF = null;
                if(dataFinal != null)
                    localDateF = dataFinal.toLocalDate();
                carrroN.setDataAluguelFinal(localDateF);
                carrroN.setValorDiaria(rs.getDouble("DIARIA"));
                retirada.setCarro(carrroN);
                retirada.setCliente(new Cliente("", rs.getString("CPF")));
                listaDeRetiradas.put(rs.getString("PLACA"), retirada);
            }

            return listaDeRetiradas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean retirarCarro(Integer idRetirada) {
        try {
            Connection con = DB.connect();
            con.setAutoCommit(false);

            String sql = "SELECT * FROM retirada where id = ? and disponivel = 1";
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, idRetirada);
            ResultSet rs = prep.executeQuery();
            Carro carro = new Carro();
            while (rs.next()) {
                carro.setId(rs.getInt("ID_CARRO"));
                carro.setId_StatusCarro(rs.getInt("ID_TIPO_COMPRA"));
            }

            String sql2 = "update retirada set disponivel = 0, data_retirada = ? where id = ? and disponivel = 1";
            PreparedStatement prep2 = con.prepareStatement(sql2.toUpperCase());
            prep2.setDate(1, Date.valueOf(LocalDate.now()));
            prep2.setInt(2, idRetirada);
            prep2.execute();

            if (!new CarroDao().atualizarStausCarro(carro.getId(), carro.getId_StatusCarro(), con)) {
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desabilitarRetirada(Integer id_carro, Connection con) {
        try {
            String sql = "update retirada set ativo = 0 where id_carro = ? and ativo = 1";
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, id_carro);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
