/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CompraDao;

/**
 *
 * @author Rafan
 */
public class CompraController {
    
    public boolean inserirCompra(Integer idCarro){
        return new CompraDao().inserirCompra(idCarro,null);
    }
    
    public boolean excluirCompra(Integer idCarro){
        return new CompraDao().excluirCompra(idCarro, null);
    }
}
