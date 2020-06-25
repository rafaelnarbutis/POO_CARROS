/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import model.Cliente;
import db.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author Rafan
 */
public class ClienteDao {

    public boolean inserirCliente(Cliente cliente) {
        try {
            String sql = "INSERT INTO clientes (nome, cpf) values (?,?)";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, cliente.getNome());
            prep.setString(2, cliente.getCpf());
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listarClientes(Cliente cliente) {
        try {
            List<Cliente> listaDeClientes = new ArrayList<>();
            listaDeClientes.addAll(listarAlugueisClientes(cliente));

            String sql = "select cli.id, cli.nome, cli.cpf, carros.placa, modelo.nome as modelo "
                    + "from clientes cli left join venda v on (v.id_cli = cli.id and v.ativo =1)"
                    + " left join carros on (carros.id = v.id_carro and carros.ativo = 1)"
                    + " left join modelo on (modelo.id = carros.id_modelo and modelo.ativo = 1)"
                    + " where cli.nome like ? and cli.cpf like ? and cli.ativo = 1;";

            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());

            prep.setString(1, "%" + cliente.getNome() + "%");
            prep.setString(2, "%" + cliente.getCpf() + "%");

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente(rs.getInt("ID"), rs.getString("NOME"),
                        rs.getString("CPF"), rs.getString("MODELO"),
                        rs.getString("PLACA"));
                listaDeClientes.add(cli);
            }
            return listaDeClientes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> listarAlugueisClientes(Cliente cliente) {
        try {
            List<Cliente> listaDeClientes = new ArrayList<>();

            String sql = "select cli.id, cli.nome, cli.cpf, carros.placa, modelo.nome as modelo "
                    + "from clientes cli "
                    + "inner join aluguel a on (a.id_cli = cli.id and a.status_aluguel = 1) "
                    + "inner join carros on (carros.id = a.id_carro and carros.ativo = 1) "
                    + "inner join modelo on (modelo.id = carros.id_modelo and modelo.ativo = 1) "
                    + "inner join retirada on (retirada.id_carro = carros.id and (retirada.disponivel = 0 and retirada.ativo = 1)) "
                    + "where cli.nome like ? and cli.cpf like ? and cli.ativo = 1;";

            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());

            prep.setString(1, "%" + cliente.getNome() + "%");
            prep.setString(2, "%" + cliente.getCpf() + "%");

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente(rs.getInt("ID"), rs.getString("NOME"),
                        rs.getString("CPF"), rs.getString("MODELO"),
                        rs.getString("PLACA"));
                listaDeClientes.add(cli);
            }

            return listaDeClientes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean excluirCliete(String cpf) {
        try {
            String sql = "update clientes set ativo = 0 where cpf = ?";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, cpf);
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarCliente(Cliente cliente) {
        try {
            String sql = "update clientes set nome = ?, cpf = ? where id = ? and ativo = 1";
            Connection con = DB.connect();
            PreparedStatement prep = con.prepareStatement(sql.toUpperCase());
            prep.setString(1, cliente.getNome());
            prep.setString(2, cliente.getCpf());
            prep.setInt(3, cliente.getId());
            prep.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
