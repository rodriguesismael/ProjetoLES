/*
 * Classe RegistrarEntrada
 */
package negocio.movimento;

import controller.ControllerInterface;
import dao.clienteXVeiculoDAO.ClienteXVeiculoDAO;
import dao.movimentoDAO.MovimentoDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;
import modelo.clienteXVeiculo.ClienteXVeiculo;
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
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        movimento.setVeiculo(veiculo);
        ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
        ClienteXVeiculoDAO clienteXVeiculoDAO = new ClienteXVeiculoDAO();
        clienteXVeiculo.setVeiculo(veiculo);
        try {
            clienteXVeiculo = clienteXVeiculoDAO.selectByVeiculo(clienteXVeiculo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clienteXVeiculo.getCliente() != null) {
            movimento.setCliente(clienteXVeiculo.getCliente());
        } else {
            movimento.setCliente(new Cliente());
        }
        SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        movimento.setDataInicio(sdfout.format(date));
        movimento.setDataTermino(null);
        try {
            movimentoDAO.insert(movimento);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Controller?name=FormHome";
    }
}
