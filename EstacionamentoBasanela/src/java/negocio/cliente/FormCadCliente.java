/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.cliente;

import controller.ControllerInterface;
import modelo.estado.Estado;
import dao.estadoDAO.EstadoDAO;
import dao.marcaDAO.MarcaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.marca.Marca;


/**
 *
 * @author ismael
 */
public class FormCadCliente implements ControllerInterface{
    private List<Estado> listaEstado;
    private List<Marca> listaMarca;
    public String call(HttpServletRequest request, HttpServletResponse response){
        Estado estado = new Estado();
        EstadoDAO estadoDao = new EstadoDAO();
        Marca marca = new Marca();
        MarcaDAO marcaDAO = new MarcaDAO();
        try{
            listaEstado = estadoDao.selectAll();
            listaMarca  = marcaDAO.selectAll();
        }catch(SQLException ex){
            Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        request.setAttribute("listaEstado", listaEstado);
        request.setAttribute("listaMarca", listaMarca);
        request.setAttribute("cpf",request.getParameter("id"));
        return "cliente/formCadCliente.jsp";
    }
}
