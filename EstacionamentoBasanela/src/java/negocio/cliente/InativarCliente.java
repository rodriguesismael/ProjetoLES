/*
 * Classe InativarCliente
 */
package negocio.cliente;

import controller.ControllerInterface;
import dao.clienteDAO.ClienteDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class InativarCliente implements ControllerInterface {

    @Override
    public String call(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente.setCpf(request.getParameter("cpf"));
        try {
            cliente = clienteDAO.selectById(cliente);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cliente.setStatus(false);
        try {
            clienteDAO.update(cliente);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Controller?name=ListarCliente";
    }
}
