/*
 * Classe VincularVeiculo
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

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class VincularVeiculo implements UpdateInterface {

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(VincularVeiculo.class.getName()).log(Level.SEVERE, null, exc);
        }
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
        ClienteXVeiculoDAO clienteXVeiculoDAO = new ClienteXVeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        clienteXVeiculo.setVeiculo(veiculo);
        cliente.setCpf(request.getParameter("cpf"));
        try {
            cliente = clienteDAO.selectById(cliente);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        clienteXVeiculo.setCliente(cliente);
        try {
            clienteXVeiculoDAO.insert(clienteXVeiculo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.setContentType("application/json");
        out.print("{\"placa\": \"" + veiculo.getPlaca() + "\", "
                + "\"tipo\": " + veiculo.getTipo() + ", "
                + "\"marca\": \"" + veiculo.getMarca().getDescricao() + "\", "
                + "\"modelo\": \"" + veiculo.getModelo().getDescricao() + "\"}");
    }
}
