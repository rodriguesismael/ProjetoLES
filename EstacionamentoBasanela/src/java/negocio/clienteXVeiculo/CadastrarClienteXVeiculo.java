/*
 * Classe ClienteXVeiculo
 */
package negocio.clienteXVeiculo;

import controller.UpdateInterface;
import dao.clienteDAO.ClienteDAO;
import dao.clienteXVeiculoDAO.ClienteXVeiculoDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;
import modelo.clienteXVeiculo.ClienteXVeiculo;
import modelo.veiculo.Veiculo;
import negocio.veiculo.CadastrarVeiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class CadastrarClienteXVeiculo implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, exc);
        }
        ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
        ClienteXVeiculoDAO clienteXVeiculoDAO = new ClienteXVeiculoDAO();
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente.setCpf(request.getParameter("cpf"));
        try {
            cliente = clienteDAO.selectById(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarClienteXVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        clienteXVeiculo.setCliente(cliente);
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarClienteXVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        clienteXVeiculo.setVeiculo(veiculo);
        try {
            clienteXVeiculoDAO.insert(clienteXVeiculo);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarClienteXVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        out.print("{\"placa\": \"" + clienteXVeiculo.getVeiculo().getPlaca() + "\"}");
    }
}
