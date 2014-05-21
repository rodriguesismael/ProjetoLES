/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.cliente;

import controller.ControllerInterface;
import modelo.estado.Estado;
import dao.estadoDAO.EstadoDAO;
import dao.veiculoDAO.VeiculoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.veiculo.Veiculo;


/**
 *
 * @author ismael
 */
public class FormCadCliente implements ControllerInterface{
    private List<Estado> listaEstado;
    private List<Veiculo> listaVeiculo;
    public String call(HttpServletRequest request, HttpServletResponse response){
        EstadoDAO estadoDao = new EstadoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        try{
            listaEstado = estadoDao.selectAll();
            listaVeiculo  = veiculoDAO.selectAllAvulso();
        }catch(SQLException ex){
            Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        request.setAttribute("listaEstado", listaEstado);
        request.setAttribute("listaVeiculo", listaVeiculo);
        request.setAttribute("cpf",request.getParameter("id"));
        return "cliente/formCadCliente.jsp";
    }
}
