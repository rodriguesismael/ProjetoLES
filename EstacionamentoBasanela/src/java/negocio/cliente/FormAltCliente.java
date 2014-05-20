/*
 * Classe FormAltCliente
 */
package negocio.cliente;

import controller.ControllerInterface;
import dao.cidadeDAO.CidadeDAO;
import dao.clienteDAO.ClienteDAO;
import dao.estadoDAO.EstadoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cidade.Cidade;
import modelo.cliente.Cliente;
import modelo.estado.Estado;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormAltCliente implements ControllerInterface {

    private List<Estado> listaEstado;
    private List<Cidade> listaCidade;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente.setCpf(request.getParameter("id"));
        try {
            cliente = clienteDAO.selectById(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(FormAltCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        EstadoDAO estadoDAO = new EstadoDAO();
        try {
            listaEstado = estadoDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(FormAltCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        CidadeDAO cidadeDAO = new CidadeDAO();
        try {
            listaCidade = cidadeDAO.selectByEstado(cliente.getCidade());
        } catch (SQLException ex) {
            Logger.getLogger(FormAltCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaEstado", listaEstado);
        request.setAttribute("listaCidade", listaCidade);
        request.setAttribute("cliente", cliente);
        return "cliente/formAltCliente.jsp";
    }

}
