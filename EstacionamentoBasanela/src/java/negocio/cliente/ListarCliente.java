/*
 * Classe ListarCliente
 */
package negocio.cliente;

import controller.ControllerInterface;
import dao.clienteDAO.ClienteDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class ListarCliente implements ControllerInterface {

    private List<Cliente> listaCliente;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            listaCliente = clienteDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(ListarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaCliente", listaCliente);
        return "cliente/listaCliente.jsp";
    }

}
