/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.cliente;

import com.sun.security.ntlm.Client;
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
import negocio.outro.EfetuarLogin;

/**
 *
 * @author ismael
 */
public class BuscarCliente implements UpdateInterface{
    
    public void executa(HttpServletRequest request, HttpServletResponse response){
       PrintWriter out = null;
       try{
           out = response.getWriter();
       }catch(IOException exc){
           Logger.getLogger(EfetuarLogin.class.getName()).log(Level.SEVERE,null,exc);
       }
       
       Cliente cliente = new Cliente();
       ClienteDAO clienteDao = new ClienteDAO();
       
       cliente.setCodCliente(Integer.parseInt(request.getParameter("cpf")));
       try{
           clienteDao.selectById(cliente);
       }catch(SQLException ex){
           Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
       String json = "{\"existeCliente\":";
       if(cliente.getCodCliente() != 0){
           json+= true+",";
           json+= "\"cpf\":\""+cliente.getCodCliente()+"\"";
           json+= "\"nome\":\""+cliente.getNome()+"\"";
           json+= "\"endereco\":\""+cliente.getEndereco()+"\"";
           json+= "\"estado\":\""+cliente.getEstado().getUf()+"\"";
           json+= "\"cidade\":\""+cliente.getCidade().getDescricao()+"\"";
           json+= "\"telefone\":\""+cliente.getTelefone()+"\"";
           json+= "\"celular\":\""+cliente.getCelular()+"\"";
           json+= "\"periodo\":\""+cliente.getPeriodo()+"\"";
           json+= "\"cpf\":\""+cliente.getCodCliente()+"\"";
       }
    }
}
