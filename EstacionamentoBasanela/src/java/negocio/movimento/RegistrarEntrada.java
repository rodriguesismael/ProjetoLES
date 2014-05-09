/*
 * Classe RegistrarEntrada
 */
package negocio.movimento;

import controller.ControllerInterface;
import dao.clienteDAO.ClienteDAO;
import dao.movimentoDAO.MovimentoDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;
import modelo.movimento.Movimento;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class RegistrarEntrada implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        Movimento movimento = new Movimento();
        MovimentoDAO movimentoDAO = new MovimentoDAO();
        Cliente cliente = new Cliente();
        if (request.getParameter("cpf") != null) {
            ClienteDAO clienteDAO = new ClienteDAO();
            try {
                cliente = clienteDAO.selectById(cliente);
            } catch (SQLException ex) {
                Logger.getLogger(RegistrarEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca("placa");
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        movimento.setVeiculo(veiculo);
        return "Controller?name=FormHome";
    }

}
