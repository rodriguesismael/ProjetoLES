/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.cliente;

import controller.ControllerInterface;
import modelo.estado.Estado;
import dao.estadoDAO.EstadoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ismael
 */
public class FormCadCliente implements ControllerInterface{
    private List<Estado> listaEstado;
    public String call(HttpServletRequest request, HttpServletResponse response){
        Estado estado = new Estado();
        EstadoDAO estadoDao = new EstadoDAO();
        try{
            listaEstado= estadoDao.selectAll();
        }catch(SQLException ex){
            Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        request.setAttribute("listaEstado", listaEstado);
        request.setAttribute("cpf",request.getParameter("id"));
        return "cliente/formCadCliente.jsp";
    }
}
