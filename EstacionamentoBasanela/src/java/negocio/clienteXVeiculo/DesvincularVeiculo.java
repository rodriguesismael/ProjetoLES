/*
 * Classe DesvincularVeiculo
 */
package negocio.clienteXVeiculo;

import controller.UpdateInterface;
import dao.clienteXVeiculoDAO.ClienteXVeiculoDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.clienteXVeiculo.ClienteXVeiculo;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class DesvincularVeiculo implements UpdateInterface {

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) {
        Veiculo veiculo = new Veiculo();
        ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
        ClienteXVeiculoDAO clienteXVeiculoDAO = new ClienteXVeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        clienteXVeiculo.setVeiculo(veiculo);
        try {
            clienteXVeiculoDAO.deleteByVeiculo(clienteXVeiculo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
