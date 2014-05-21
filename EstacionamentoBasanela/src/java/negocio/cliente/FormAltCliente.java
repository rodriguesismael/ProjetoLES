/*
 * Classe FormAltCliente
 */
package negocio.cliente;

import controller.ControllerInterface;
import dao.cidadeDAO.CidadeDAO;
import dao.clienteDAO.ClienteDAO;
import dao.clienteXVeiculoDAO.ClienteXVeiculoDAO;
import dao.estadoDAO.EstadoDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cidade.Cidade;
import modelo.cliente.Cliente;
import modelo.clienteXVeiculo.ClienteXVeiculo;
import modelo.estado.Estado;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormAltCliente implements ControllerInterface {

    private List<Estado> listaEstado;
    private List<Cidade> listaCidade;
    private List<ClienteXVeiculo> listaClienteXVeiculo;
    private List<Veiculo> listaVeiculo;

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
        ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
        ClienteXVeiculoDAO clienteXVeiculoDAO = new ClienteXVeiculoDAO();
        clienteXVeiculo.setCliente(cliente);
        try {
            listaClienteXVeiculo = clienteXVeiculoDAO.selectByCliente(clienteXVeiculo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        try {
            listaVeiculo = veiculoDAO.selectAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.setAttribute("listaEstado", listaEstado);
        request.setAttribute("listaCidade", listaCidade);
        request.setAttribute("listaClienteXVeiculo", listaClienteXVeiculo);
        request.setAttribute("listaVeiculo", listaVeiculo);
        request.setAttribute("cliente", cliente);
        return "cliente/formAltCliente.jsp";
    }
}
