/*
 * Classe FormCadVeiculoMensal
 */
package negocio.veiculo;

import controller.ControllerInterface;
import dao.clienteDAO.ClienteDAO;
import dao.marcaDAO.MarcaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;
import modelo.marca.Marca;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormCadVeiculoMensal implements ControllerInterface {

    private List<Marca> listaMarca;
    private List<Cliente> listaCliente;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        MarcaDAO marcaDAO = new MarcaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            listaMarca = marcaDAO.selectAll();
            listaCliente = clienteDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(FormCadVeiculoMensal.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("placa", request.getParameter("id"));
        request.setAttribute("listaMarca", listaMarca);
        request.setAttribute("listaCliente", listaCliente);
        return "veiculo/formCadVeiculoMensal.jsp";
    }
}
