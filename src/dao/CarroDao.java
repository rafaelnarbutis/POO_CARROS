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
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import model.Carro;
import model.Cliente;
import objView.CarroView;
import repositoryLocal.MapTipoStatusCarro;
import utils.Valor;

/**
 *
 * @author Rafan
 */
public class CarroDao {

    public boolean inserirCarro(Carro carro) throws SQLException {
        Connection con = null;
        try {
            String sql = "insert into carros (id_marca, id_modelo, id_tipo, id_tanque, km, ano, placa, preco)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            con = DB.connect();
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, carro.getId_marca());
            prep.setInt(2, carro.getId_modelo());
            prep.setInt(3, carro.getId_tipo());
            prep.setInt(4, carro.getId_tanque());
            prep.setDouble(5, carro.getKm());
            prep.setInt(6, carro.getAno());
            prep.setString(7, carro.getPlaca());
            prep.setDouble(8, carro.getValor());
            prep.execute();
            ResultSet rs = prep.getGeneratedKeys();
            int idCarro = 0;
            if (rs.next()) {
                idCarro = rs.getInt(1);
            }
            idCarro = rs.getInt(1);
            if (new CompraDao().inserirCompra(idCarro, con)) {
                con.commit();
            } else {
                con.rollback();
                con.close();
                return false;
            }
            con.close();
            return true;
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<CarroView> buscarCarros(Carro carro) {
        try {
            List<CarroView> listaDeCarros = new ArrayList<>();
            String sql = "select carros.id, marca.nome as marca, modelo.nome as modelo,"
                    + " tipo_carro.descricao as tipo, tanque.descricao as tanque,"
                    + " carros.km, carros.ano, carros.placa,  carros.preco ,status_carro.descricao as status,"
                    + " aluguel.data_inicial as dataInicial, aluguel.data_final as dataFinal"
                    + " from carros inner join marca on marca.id = carros.id_marca"
                    + " inner join modelo on modelo.id = carros.id_modelo"
                    + " inner join tipo_carro on tipo_carro.id = carros.id_tipo"
                    + " inner join tanque on tanque.id = carros.id_tanque"
                    + " inner join status_carro on status_carro.id = carros.id_disponivel"
                    + " left join aluguel on (aluguel.id_carro = carros.id and aluguel.status_aluguel = 1)"
                    + " where marca.nome like ? and modelo.nome like ? and (carros.preco = ? or ? is null)"
                    + " and carros.placa like ? and (carros.km = ? or ? is null)"
                    + " and (carros.ano = ? or ? is null) and carros.ativo = 1 and marca.ativo = 1"
                    + " and modelo.ativo = 1 and tanque.ativo = 1 and tipo_carro.ativo = 1";

            Connection con = DB.connect();

            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, "%" + carro.getMarca() + "%");
            prep.setString(2, "%" + carro.getModelo() + "%");
            prep.setObject(3, carro.getValor(), Types.DOUBLE);
            prep.setObject(4, carro.getValor(), Types.DOUBLE);
            prep.setString(5, "%" + carro.getPlaca() + "%");
            prep.setObject(6, carro.getKm(), Types.DOUBLE);
            prep.setObject(7, carro.getKm(), Types.DOUBLE);
            prep.setObject(8, carro.getAno(), Types.INTEGER);
            prep.setObject(9, carro.getAno(), Types.INTEGER);

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                CarroView carroView = new CarroView();
                carroView.setId(rs.getInt("ID"));
                carroView.setMarca(rs.getString("MARCA"));
                carroView.setModelo(rs.getString("MODELO"));
                carroView.setTipo(rs.getString("TIPO"));
                carroView.setTanque(rs.getString("TANQUE"));
                carroView.setAno(rs.getInt("ANO"));
                carroView.setPlaca(rs.getString("PLACA"));
                carroView.setKm(new Valor().formatarParaKm(rs.getDouble("KM")));
                carroView.setValor(new Valor().formataValor(rs.getDouble("PRECO")));
                carroView.setDisponivel(rs.getString("STATUS"));
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date dataIncial = rs.getDate("DATAINICIAL");
                String dataIniFormat = null;
                if(dataIncial != null)
                    dataIniFormat = df.format(dataIncial);
                carroView.setDataAluguelI(dataIniFormat);

                Date dataFinal = rs.getDate("DATAFINAL");
                String dataFinaFormat = null;
                if(dataFinal != null)
                    dataFinaFormat = df.format(dataFinal);
                carroView.setDataAluguelF(dataFinaFormat);
                
                listaDeCarros.add(carroView);
            }
            con.close();
            return listaDeCarros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean atualizarCarro(Carro carro) {
        try {
            String sql = "update carros set id_marca = ?, id_modelo = ?, id_tipo = ?,"
                    + " id_tanque = ?, km = ?, ano = ?, placa = ?, preco = ?";

            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());

            prep.setInt(1, carro.getId_marca());
            prep.setInt(2, carro.getId_modelo());
            prep.setInt(3, carro.getId_tipo());
            prep.setInt(4, carro.getId_tanque());
            prep.setDouble(5, carro.getKm());
            prep.setInt(6, carro.getAno());
            prep.setString(7, carro.getPlaca());
            prep.setDouble(8, carro.getValor());
            prep.execute();

            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean devolverCarro(Carro carro, String tipoDevolucao) {
        try {
            String sql = "update carros set id_tanque = ?, km = ?, id_disponivel = ? where placa = ?";

            Connection con = DB.connect();
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());

            prep.setInt(1, carro.getId_tanque());
            prep.setDouble(2, carro.getKm());
            prep.setInt(3, carro.getId_StatusCarro());
            prep.setString(4, carro.getPlaca());
            prep.execute();
            if (tipoDevolucao.equalsIgnoreCase("VENDIDO")) {
                if (!new VendaDao().excluirVenda(carro.getId(), con)) {
                    con.rollback();
                    con.close();
                    return false;
                }
            }
            if (!new AlugaDao().finalizarAluguel(carro, con)) {
                con.rollback();
                con.close();
                return false;
            }
            if(!new RetirarDao().desabilitarRetirada(carro.getId(), con)){
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
    
    public boolean atualizarStausCarro(Integer id_carro, Integer id_Status, Connection con){
        try {
            String sql = "update carros set id_disponivel = ? where id = ?";
            if(con == null)
                con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, id_Status);
            prep.setInt(2, id_carro);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirCarro(Integer id) {
        try {
            String sql = "update carros set ativo = 0 where id = ?";
            Connection con = DB.connect();
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, id);
            prep.execute();
            if (new CompraDao().excluirCompra(id, con)) {
                con.commit();
            } else {
                con.rollback();
                con.close();
                return false;
            }
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean desativarCarro(Integer id, Integer id_Status, Connection con) {
        try {
            String sql = "update carros set id_disponivel = ? where id = ? and id_disponivel = 1";
            if (con == null) {
                con = DB.connect();
            }

            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setInt(1, id_Status);
            prep.setInt(2, id);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Double buscarValorCarrosComprados(){
        try {
            String sql = "select sum(PRECO) as preco from carros where ativo = 1";
            Connection con = DB.connect();
            ResultSet rs = con.prepareStatement(sql.toUpperCase()).executeQuery();
            Double valor = 0.0;
            while(rs.next()){
                valor += rs.getDouble("preco");
            }
            con.close();
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
