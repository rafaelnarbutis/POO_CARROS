/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dao.ClienteDao;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Rafan
 */
public class ClienteController{
    
    public boolean inserirCliente(String nome, String cpf){
        return new ClienteDao().inserirCliente(new Cliente(nome, cpf));
    }
    
    public boolean excluirCliente(String cpf){
        return new ClienteDao().excluirCliete(cpf);
    }
    
    public boolean atualizarCliente(int id, String nome, String cpf){
        return new ClienteDao().atualizarCliente(new Cliente(id,nome, cpf));
    }
    
    public List<Cliente> listarClientes(String nome, String cpf){
        return new ClienteDao().listarClientes(new Cliente(nome, cpf));
    }
    
}
