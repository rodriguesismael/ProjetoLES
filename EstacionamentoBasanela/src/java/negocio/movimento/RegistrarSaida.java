/*
 * Classe RegistrarSaida
 */

package negocio.movimento;

import controller.UpdateInterface;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro
 */
public class RegistrarSaida implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
