/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.cliente;

import controller.UpdateInterface;

import modelo.cliente.Cliente;
import dao.clienteDAO.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ismael
 */
public class BuscarCliente implements UpdateInterface{
    
    public void executa(HttpServletRequest request, HttpServletResponse response){
        
    }
}
